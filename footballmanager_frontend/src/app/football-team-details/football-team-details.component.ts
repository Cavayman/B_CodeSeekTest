import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {PlayerService} from "../player.service";
import {Player} from "../player";
import {FootballTeamService} from "../football-team.service";

@Component({
  selector: 'app-football-team-details',
  templateUrl: './football-team-details.component.html',
  styleUrls: ['./football-team-details.component.css']
})
export class FootballTeamDetailsComponent implements OnInit {
  teamId!: number;
  players: Player[] = [];

  constructor(private route: ActivatedRoute,
              private playerService: PlayerService,
              private teamService: FootballTeamService,
              private router: Router) { }

  ngOnInit(): void {
    this.teamId = this.route.snapshot.params['teamId'];
    this.getFootballTeamDetails(this.teamId);
  }

  getFootballTeamDetails(teamId: number) {
    this.teamService.getPlayers(teamId).subscribe({
      next: value => this.players = value,
      error: err => console.log(err)
    });
  }

  updatePlayer(teamId: number, id: number) {
    this.router.navigate(['updatePlayer',teamId, id]);
  }

  deletePlayer(id: number) {
    this.playerService.deletePlayer(id).subscribe({
      next: () => this.getFootballTeamDetails(this.teamId),
      error: err => console.log(err)
    });
  }

  playerTransfer(teamId: number, id: number) {
    this.router.navigate(['playerTransfer', teamId, id]);
  }
}
