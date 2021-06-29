import './MatchDetails.scss'
import { React } from 'react';
import { Link } from 'react-router-dom';

export const MatchDetails = ({teamName, match}) => {
  if(!match) return null;

  const otherTeam = match.team1 === teamName ? match.team2 : match.team1;
  const otherTeamRoute = `/teams/${otherTeam}`;
  const isMatchWon = teamName === match.winner;

    return (     
      <div className={isMatchWon ? 'MatchDetails won-game' : 'MatchDetails lost-game'}>
        <div>
          <span className="Vs">vs</span>
          <h1><Link to={otherTeamRoute}>{otherTeam}</Link></h1>
          <h2 className="match-date">{match.date}</h2>
          <h3 className="match-venu"> at {match.venu}</h3> 
          <h3 className="match-result"> {match.winner} won by {match.resultMargin}  {match.result} </h3>
        </div>
        <div className="additional-detail">
          <h3>First Innings</h3>
          <p>{match.team1}</p>
          <h3>Second Innings</h3>
          <p>{match.team2}</p>
          <h3>Man of the Match</h3>
          <p>{match.playerOfMatch}</p>
          <h3>Umpires</h3>
          <p>{match.umpire1}, {match.umpire2}</p>
        </div>
      </div>
    );
}