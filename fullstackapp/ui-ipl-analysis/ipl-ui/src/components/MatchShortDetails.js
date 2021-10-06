import './MatchShortDetails.scss'
import { React } from 'react';
import { Link } from 'react-router-dom';

export const MatchShortDetails = ({teamName, match}) => {

  if(!match) return null;
  console.log('TTTTT'+teamName);
  const otherTeam = match.team1 === teamName ? match.team2 : match.team1;
  const otherTeamRoute = `/teams/${otherTeam}`;
  const isMatchWon = teamName === match.winner;

console.log('TTTTT2'+match.matchWinner);
console.log('TTTTT3'+isMatchWon);
    return (
      <div className={isMatchWon ? 'MatchShortDetails won-game' : 'MatchShortDetails lost-game'}>
        <span className="Vs">vs</span>
          <h1><Link to={otherTeamRoute}>{otherTeam}</Link></h1>
        <p className="match-result">{match.winner} won by {match.resultMargin} {match.result}</p>
      </div>
    );
}
