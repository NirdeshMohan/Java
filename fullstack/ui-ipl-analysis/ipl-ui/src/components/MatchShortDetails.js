import { React } from 'react';
import { Link } from 'react-router-dom';

export const MatchShortDetails = ({teamName, match}) => {

  const otherTeam = match.team1 === teamName ? match.team2 : match.team1;
  const otherTeamRoute = `/teams/${otherTeam}`;

    return (
      <div className="MatchShortDetails">
        <p>Match played on, {match.date} - VS <Link to={otherTeamRoute}>{otherTeam}</Link></p>
      </div>
    );
}
