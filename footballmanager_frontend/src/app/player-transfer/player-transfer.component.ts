import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {PlayerService} from "../player.service";
import {FootballTeam} from "../football-team";
import {FootballTeamService} from "../football-team.service";

@Component({
  selector: 'app-player-transfer',
  templateUrl: './player-transfer.component.html',
  styleUrls: ['./player-transfer.component.css']
})
export class PlayerTransferComponent implements OnInit {
  id!: number;
  oldTeamId!: number;
  newTeamId!: number;
  footballTeams!: FootballTeam[];
  error: Error;

  constructor(private route: ActivatedRoute,
              private playerService: PlayerService,
              private footballTeamService: FootballTeamService,
              private router: Router) { }

  ngOnInit(): void {
    this.oldTeamId = this.route.snapshot.params['oldTeamId'];
    this.id = this.route.snapshot.params['id'];

    this.footballTeamService.getFootballTeams().subscribe({
      next: value => this.footballTeams = value.filter(footballTeam => footballTeam.id != this.oldTeamId),
      error: err => console.log(err)
    });
  }

  onSubmit() {
    this.playerTransfer(this.id, this.newTeamId);
  }

  playerTransfer(playerId: number, newTeamId: number) {
    this.playerService.playerTransfer(playerId, newTeamId).subscribe({
      next: () => this.goToPlayerList(this.oldTeamId),
      error: err => {
        console.log(err.error)
        this.error = err.error
      }
    });
  }

  goToPlayerList(oldTeamId: number) {
    this.router.navigate(['footballTeamDetails', oldTeamId]);
  }
}
