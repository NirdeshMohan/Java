package com.ishnit.iplanalysis.data;

import java.time.LocalDate;

import com.ishnit.iplanalysis.model.Matchdata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Matchdata>  {
    private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

  @Override
  public Matchdata process(final MatchInput inputData) throws Exception {
    Matchdata matchData = new Matchdata();
    matchData.setId(Long.parseUnsignedLong(inputData.getId()));
    matchData.setCity(inputData.getCity());
    matchData.setDate(LocalDate.parse(inputData.getDate()));
    matchData.setEliminator(inputData.getEliminator());
    matchData.setMethod(inputData.getMethod());
    matchData.setPlayerOfMatch(inputData.getPlayer_of_match());
    matchData.setResult(inputData.getResult());
    matchData.setResultMargin(inputData.getResult_margin());
    matchData.setTossDecision(inputData.getToss_decision());

    String firstInningTeam, secondInningTeam;
    if("BAT".equalsIgnoreCase(inputData.getToss_decision())){
      firstInningTeam = inputData.getToss_winner();
      secondInningTeam = inputData.getToss_winner().equals(inputData.getTeam1()) ? inputData.getTeam2() : inputData.getTeam1();
    }else{
      secondInningTeam = inputData.getToss_winner();
      firstInningTeam = inputData.getToss_winner().equals(inputData.getTeam1()) ? inputData.getTeam2() : inputData.getTeam1();
    }
  
    matchData.setTeam1(firstInningTeam);
    matchData.setTeam2(secondInningTeam);
    matchData.setTossWinner(inputData.getToss_winner());
    matchData.setUmpire1(inputData.getUmpire1());
    matchData.setUmpire2(inputData.getUmpire2());
    matchData.setVenue(inputData.getVenue());
    matchData.setWinner(inputData.getWinner());
    
    log.info("Done Processing");
    return matchData;

  }
}
