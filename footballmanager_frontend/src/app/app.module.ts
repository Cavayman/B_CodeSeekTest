import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule} from "@angular/common/http";
import { FootballTeamListComponent } from './football-team-list/football-team-list.component';
import { CreateFootballTeamComponent } from './create-football-team/create-football-team.component';
import {FormsModule} from "@angular/forms";
import { UpdateFootballTeamComponent } from './update-football-team/update-football-team.component';
import { FootballTeamDetailsComponent } from './football-team-details/football-team-details.component';
import { UpdatePlayerComponent } from './update-player/update-player.component';
import { CreatePlayerComponent } from './create-player/create-player.component';
import { PlayerTransferComponent } from './player-transfer/player-transfer.component';

@NgModule({
  declarations: [
    AppComponent,
    FootballTeamListComponent,
    CreateFootballTeamComponent,
    UpdateFootballTeamComponent,
    FootballTeamDetailsComponent,
    UpdatePlayerComponent,
    CreatePlayerComponent,
    PlayerTransferComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
