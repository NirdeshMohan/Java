import './TeamPage.scss'
import { React, useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { MatchDetails } from '../components/MatchDetails';
import { MatchShortDetails } from '../components/MatchShortDetails';
import { PieChart } from 'react-minimal-pie-chart';

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
        <div className="team-name-section">
            <h1 className="team-name">{team.teamName}</h1>
        </div>
        <div className="wins-losses-section">
            Wins / Losses
            <PieChart
                data={[
                    { title: 'Wins', value: team.totalWins, color: '#32a8a4'},
                    { title: 'Losses', value: team.totalMatches - team.totalWins, color: '#a86532' }
                ]}
            />
        </div>
        <div className="match-detail-section">
            <h3>Latest Matches</h3>
            <MatchDetails teamName={team.teamName} match={team.matches[0]}/>
        </div>
            {team.matches.slice(1).map(match => <MatchShortDetails teamName={team.teamName} match={match}/>)} 
        <div className="more-link">
            <a href="#"> More =></a>
        </div>
      </div>
    );
}