package com.ishnit.iplanalysis.service;

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
    
}
