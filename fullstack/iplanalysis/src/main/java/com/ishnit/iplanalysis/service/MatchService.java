package com.ishnit.iplanalysis.service;

import java.time.LocalDate;
import java.util.List;

import com.ishnit.iplanalysis.model.Matchdata;
import com.ishnit.iplanalysis.repository.MatchDataRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchService {

    @Autowired
    private MatchDataRepository matchDataRepository;

    public List<Matchdata> findLatestMatchesByTeam(String teamName) {
        return matchDataRepository.findLatestMatchesByTeam(teamName, 4);
    }

    public List<Matchdata> getByTeam1OrTeam2AndDateBetweenOrderByDateDesc(String teamName, LocalDate startDate, LocalDate endDate) {
        return matchDataRepository.findTeamDataByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(teamName, startDate, endDate, teamName, startDate, endDate);
    }
}
