import { Component, OnInit } from '@angular/core';
import {Player} from "../player";
import {PlayerService} from "../player.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-update-player',
  templateUrl: './update-player.component.html',
  styleUrls: ['./update-player.component.css']
})
export class UpdatePlayerComponent implements OnInit {
  player: Player = new Player();
  id!: number;
  teamId!: number;

  constructor(private playerService: PlayerService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.teamId = this.route.snapshot.params['teamId'];
    this.id = this.route.snapshot.params['id'];
    this.playerService.getPlayerById(this.id).subscribe({
      next: value => this.player = value,
      error: err => console.log(err)
    });
  }

  onSubmit() {
    this.updatePlayer();
  }

  updatePlayer() {
    this.playerService.updatePlayer(this.id, this.player).subscribe({
      next: value => this.goToPlayerList(this.teamId),
      error: err => console.log(err)
    });
  }

  goToPlayerList(teamId: number) {
    this.router.navigate(['footballTeamDetails', teamId]);
  }
}
