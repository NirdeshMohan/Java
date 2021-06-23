import { React, useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { MatchDetails } from '../components/MatchDetails';
//import { MatchShortDetails } from '../components/MatchShortDetails';

export const MatchPage = () => {

    const [matches, setMatches] = useState([]);
    const { teamName, year } = useParams();
    useEffect(
        () => {
            const fetchMatches = async () => {
                console.log(teamName,' ' +year)
                const response = await fetch(`http://localhost:8087/teams/${teamName}/matches?year=${year}`);
                // const response = await fetch(`http://localhost:8087/teams/${teamName}/matches?year=2019`);
                const data = await response.json();
                setMatches(data);
                console.log(data);
            };
            fetchMatches();
        }, [teamName,year]
        
    );
    
    // if (!matches || !matches.teamName){
    //     return <h2>No Team Matches found!!</h2>;
    // }

    return (
      <div className="TeamPage">
        <h1>Match Page</h1>
            {matches.map(match => <MatchDetails teamName={teamName} match={match}/>)} 
      </div>
    );
}