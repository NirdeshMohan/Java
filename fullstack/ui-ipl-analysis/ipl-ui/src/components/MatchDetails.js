import { React } from 'react';
export const MatchDetails = ({match}) => {
  if(!match) return null;
    return (     
      <div className="MatchDetails">
        <h3>Latest Match Details</h3>
        <h4>Match Details</h4>
        <h4>{match.team1} VS {match.team2}</h4>
      </div>
    );
}