package com.ishnit.iplanalysis.controller;

import java.util.List;

import com.ishnit.iplanalysis.model.Team;
import com.ishnit.iplanalysis.service.TeamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TeamController {
    @Autowired
    private TeamService teamService;

    @GetMapping("/teams")
    public List<Team> getAllTeams(){
        return teamService.getAllTeams();
    }

    @GetMapping("/teams/{teamName}")
    public Team getTeamByName(@PathVariable String teamName){
        return teamService.getTeam(teamName);
    }

    
}
