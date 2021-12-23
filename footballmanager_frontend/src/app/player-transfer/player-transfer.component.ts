import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {PlayerService} from "../player.service";
import {Player} from "../player";
import {FootballTeam} from "../football-team";
import {FootballTeamService} from "../football-team.service";

@Component({
  selector: 'app-player-transfer',
  templateUrl: './player-transfer.component.html',
  styleUrls: ['./player-transfer.component.css']
})
export class PlayerTransferComponent implements OnInit {
  id!: number;
  player!: Player;
  oldTeamId!: number;
  newTeamId!: number;
  footballTeams!: FootballTeam[];
  error!: [];

  constructor(private route: ActivatedRoute,
              private playerService: PlayerService,
              private footballTeamService: FootballTeamService,
              private router: Router) { }

  ngOnInit(): void {
    this.oldTeamId = this.route.snapshot.params['oldTeamId'];
    this.id = this.route.snapshot.params['id'];

    this.playerService.getPlayerById(this.id).subscribe({
      next: value => this.player = value,
      error: err => console.log(err)
    });

    this.footballTeamService.getFootballTeams().subscribe({
      next: value => this.footballTeams = value.filter(footballTeam => footballTeam.id != this.oldTeamId),
      error: err => console.log(err)
    });
  }

  onSubmit() {
    this.playerTransfer(this.newTeamId);
  }

  playerTransfer(newTeamId: number) {
    this.playerService.playerTransfer(newTeamId, this.player).subscribe({
      next: value => this.goToPlayerList(this.oldTeamId),
      error: err => this.error = err.error
    });
  }

  goToPlayerList(oldTeamId: number) {
    this.router.navigate(['footballTeamDetails', oldTeamId]);
  }
}
