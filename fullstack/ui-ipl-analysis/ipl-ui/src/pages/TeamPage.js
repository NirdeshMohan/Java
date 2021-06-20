import { React, useEffect, useState } from 'react';
import { MatchDetails } from '../components/MatchDetails';
import { MatchShortDetails } from '../components/MatchShortDetails';

export const TeamPage = () => {

    const [team, setTeam] = useState({matches : []});

    useEffect(
        () => {
            const fetchMatches = async () => {
                const response = await fetch('http://localhost:8087/teams/Delhi Capitals');
                const data = await response.json();
                setTeam(data);
                console.log(data);
            };
            fetchMatches();
        }, []
        
    );

    return (
      <div className="TeamPage">
        <h1>{team.teamName}</h1>
        <MatchDetails match={team.matches[0]}/>
        {team.matches.slice(1).map(match => <MatchShortDetails match={match}/>)} 
      </div>
    );
}