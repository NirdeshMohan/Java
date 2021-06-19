import {React, useEffect} from 'react';
import { MatchDetails } from '../components/MatchDetails';
import { MatchShortDetails } from '../components/MatchShortDetails';

export const TeamPage = () => {

    useEffect(
        () => {
            const fetchMatches = async () => {
                const response = await fetch('http://localhost:8087/teams/Delhi%20Capitals');
                const data = await response.json();
                console.log(data);
            };
            fetchMatches();
        }
        
    );

    return (
      <div className="TeamPage">
        <MatchDetails />
        <MatchShortDetails />
        <MatchShortDetails />
        <MatchShortDetails />
      </div>
    );
}