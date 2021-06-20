import { React } from 'react';
export const MatchShortDetails = ({match}) => {
    return (
      <div className="MatchShortDetails">
        <p>Match played on, {match.date}</p>
        <p>{match.team1} VS {match.team2}</p>
      </div>
    );
}
