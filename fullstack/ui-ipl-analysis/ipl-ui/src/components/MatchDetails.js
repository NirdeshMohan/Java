import { React } from 'react';
import { Link } from 'react-router-dom';

export const MatchDetails = ({teamName, match}) => {
  if(!match) return null;

  const otherTeam = match.team1 === teamName ? match.team2 : match.team1;
  const otherTeamRoute = `/teams/${otherTeam}`;
    return (     
      <div className="MatchDetails">
        <h3>Latest Match Details</h3>
        <h4>Match Details</h4>
        <p>Match played on, {match.date} - VS <Link to={otherTeamRoute}>{otherTeam}</Link></p>
      </div>
    );
}