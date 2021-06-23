package com.ishnit.iplanalysis.controller;

import java.time.LocalDate;
import java.util.List;

import com.ishnit.iplanalysis.model.Matchdata;
import com.ishnit.iplanalysis.model.Team;
import com.ishnit.iplanalysis.service.MatchService;
import com.ishnit.iplanalysis.service.TeamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class TeamController {
    @Autowired
    private TeamService teamService;

    @Autowired
    private MatchService matchService;

    @GetMapping("/teams")
    public List<Team> getAllTeams(){
        return teamService.getAllTeams();
    }

    @GetMapping("/teams/{teamName}")
    public Team getTeamByName(@PathVariable String teamName){
        return teamService.getTeam(teamName);
    }

    @GetMapping("/teams/{teamName}/matches")
    public List<Matchdata> getMatchesDataForATeam(@PathVariable String teamName, @RequestParam int year){
        LocalDate startDate = LocalDate.of(year, 1,1);
        LocalDate endDate = LocalDate.of(year+1, 1,1);
        //return matchService.getByTeam1OrTeam2AndDateBetweenOrderByDateDesc(teamName, startDate, endDate);
        return matchService.getByTeam1OrTeam2AndDateBetweenOrderByDateDesc(teamName, startDate, endDate);
    }
}
