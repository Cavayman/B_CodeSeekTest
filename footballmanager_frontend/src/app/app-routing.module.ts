import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {FootballTeamListComponent} from "./football-team-list/football-team-list.component";
import {CreateFootballTeamComponent} from "./create-football-team/create-football-team.component";
import {UpdateFootballTeamComponent} from "./update-football-team/update-football-team.component";
import {FootballTeamDetailsComponent} from "./football-team-details/football-team-details.component";
import {UpdatePlayerComponent} from "./update-player/update-player.component";
import {CreatePlayerComponent} from "./create-player/create-player.component";
import {PlayerTransferComponent} from "./player-transfer/player-transfer.component";

const routes: Routes = [
  {path: '', redirectTo: 'footballTeams', pathMatch: 'full'},
  {path: 'footballTeams', component: FootballTeamListComponent},
  {path: 'createFootballTeam', component: CreateFootballTeamComponent},
  {path: 'updateFootballTeam/:teamId', component: UpdateFootballTeamComponent},
  {path: 'footballTeamDetails/:teamId', component: FootballTeamDetailsComponent},
  {path: 'updatePlayer/:teamId/:id', component: UpdatePlayerComponent},
  {path: 'createPlayer/:teamId', component: CreatePlayerComponent},
  {path: 'playerTransfer/:oldTeamId/:id', component: PlayerTransferComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
