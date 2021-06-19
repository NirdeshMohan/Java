package com.ishnit.iplanalysis.repository;

import java.util.List;

import com.ishnit.iplanalysis.model.Matchdata;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchDataRepository extends JpaRepository<Matchdata, Long>{

    List<Matchdata> findByTeam1OrTeam2OrderByDateDesc(String teamName1, String teamName2, Pageable pageables);

    default List<Matchdata> findLatestMatchesByTeam(String teamName, int count){
        return findByTeam1OrTeam2OrderByDateDesc(teamName, teamName, PageRequest.of(0, count));
    }
    
}
