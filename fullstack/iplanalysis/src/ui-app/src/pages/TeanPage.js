import {React} from 'react'
import {MatchDetails} from '../components/MatchDetails'
import {MatchShortDetails} from '../components/MatchShortDetails'

// function TeamPage() {
//   return (
//     <div className="App">
//       <h1>IPL Analysis Dashboard</h1>
//     </div>
//   );
// }

// export default TeamPage;
export const TeamPage = () => {
    return (
      <div className="TeamPage">
        <h1>Team Name</h1>
        <MatchDetails />
        <MatchShortDetails />
        <MatchShortDetails />
        <MatchShortDetails />
      </div>
    );
}