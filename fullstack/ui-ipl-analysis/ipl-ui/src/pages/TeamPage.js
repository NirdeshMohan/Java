import { React, useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { MatchDetails } from '../components/MatchDetails';
import { MatchShortDetails } from '../components/MatchShortDetails';

export const TeamPage = () => {

    const [team, setTeam] = useState({matches : []});
    const { teamName } = useParams();

    useEffect(
        () => {
            const fetchMatches = async () => {
                const response = await fetch(`http://localhost:8087/teams/${teamName}`);
                const data = await response.json();
                setTeam(data);
                console.log(data);
            };
            fetchMatches();
        }, [teamName]
        
    );
    
    if (!team || !team.teamName){
        return <h2>No Team found!!</h2>;
    }
    return (
      <div className="TeamPage">
        <h1>{team.teamName}</h1>
        <MatchDetails teamName={team.teamName} match={team.matches[0]}/>
        {team.matches.slice(1).map(match => <MatchShortDetails teamName={team.teamName} match={match}/>)} 
      </div>
    );
}