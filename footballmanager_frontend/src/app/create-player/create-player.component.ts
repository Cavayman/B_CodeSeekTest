import { Component, OnInit } from '@angular/core';
import {Player} from "../player";
import {PlayerService} from "../player.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-create-player',
  templateUrl: './create-player.component.html',
  styleUrls: ['./create-player.component.css']
})
export class CreatePlayerComponent implements OnInit {
  player: Player = new Player();
  teamId!: number;

  constructor(private playerService: PlayerService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.teamId = this.route.snapshot.params['teamId'];
  }

  onSubmit() {
    console.log(this.player);
    this.savePlayer();
  }

  savePlayer() {
    this.playerService.createPlayer(this.teamId, this.player).subscribe({
      next: value => this.goToPlayerList(this.teamId),
      error: err => console.log(err)
    });
  }

  goToPlayerList(teamId: number) {
    this.router.navigate(['footballTeamDetails', teamId]);
  }
}
