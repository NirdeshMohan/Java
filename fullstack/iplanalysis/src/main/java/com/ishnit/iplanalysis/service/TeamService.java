package com.ishnit.iplanalysis.service;

import java.util.List;

import com.ishnit.iplanalysis.model.Team;
import com.ishnit.iplanalysis.repository.TeamRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MatchService matchService;

    public List<Team> getAllTeams(){
        return teamRepository.findAll();
    }

    public Team getTeam(String teamName){
        Team team = teamRepository.findByTeamName(teamName);
        System.out.println("TEAM NAME:"+teamName);
        team.setMatches(matchService.findLatestMatchesByTeam(teamName));
        return team;
    }    
        
}
