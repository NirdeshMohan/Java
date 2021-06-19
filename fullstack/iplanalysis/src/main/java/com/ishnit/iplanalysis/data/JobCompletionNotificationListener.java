package com.ishnit.iplanalysis.data;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import com.ishnit.iplanalysis.model.Team;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);
  
    //private final JdbcTemplate jdbcTemplate;

    private final EntityManager em;
  
    @Autowired
    public JobCompletionNotificationListener(EntityManager em) {
      this.em = em;
    }
  
    @Override
    @Transactional
    public void afterJob(JobExecution jobExecution) {
      if(jobExecution.getStatus() == BatchStatus.COMPLETED) {

        log.info("!!! JOB FINISHED! Time to verify the results");

        Map<String, Team> teamData = new HashMap<>();

        em.createQuery("select m.team1, count(*) from Matchdata m group by m.team1", Object[].class)
          .getResultList()
          .stream()
          .map(e -> new Team((String) e[0], (long) e[1]))
          .forEach(team -> {
            teamData.put(team.getTeamName(), team);
            log.info("-->"+team.toString());
          });

          em.createQuery("select m.team2, count(*) from Matchdata m group by m.team2", Object[].class)
          .getResultList()
          .stream()
          .forEach(e-> {
                       Team team = teamData.get((String) e[0]);
                       if (team != null ){
                         team.setTotalMatches(team.getTotalMatches() + (long) e[1]);
                         log.info("1-->"+team.getTotalMatches());
                       }

          });

          em.createQuery("select m.winner, count(*) from Matchdata m group by m.winner", Object[].class)
          .getResultList()
          .stream()
          .forEach(e-> {
                       Team team = teamData.get((String) e[0]);
                       if (team != null ){
                         team.setTotalWins((long) e[1]);
                         log.info("2-->"+team.getTotalWins());
                       }

          });

          log.info("teamData-"+teamData.size());
          teamData.values().forEach(team -> {
            log.info("3-->"+team.getTotalMatches());
            em.persist(team);
          });
          
        // jdbcTemplate.query("SELECT team1, team2, date FROM `match`",
        //   (rs, row) -> "Team 1-"+ rs.getString(1) + "-Team 2-" + rs.getString(2) + "-Date-" + rs.getDate(3)
        // ).forEach(str -> log.info("Record:-" + str));
      }
    }
  }