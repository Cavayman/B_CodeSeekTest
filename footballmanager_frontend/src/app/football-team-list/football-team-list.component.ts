import {Component, OnInit} from '@angular/core';
import {FootballTeam} from "../football-team";
import {FootballTeamService} from "../football-team.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-football-team-list',
  templateUrl: './football-team-list.component.html',
  styleUrls: ['./football-team-list.component.css']
})
export class FootballTeamListComponent implements OnInit {
  footballTeams: FootballTeam[] = [];

  constructor(private footballTeamService: FootballTeamService,
              private router: Router) { }

  ngOnInit(): void {
    this.getFootballTeams();
  }

  updateFootballTeam(id: number) {
    this.router.navigate(['updateFootballTeam', id]);
  }

  deleteFootballTeam(id: number) {
    this.footballTeamService.deleteFootballTeam(id).subscribe({
      next: () => this.getFootballTeams(),
      error: err => console.log(err)
    });
  }

  footballTeamDetails(id: number) {
    this.router.navigate(['footballTeamDetails', id]);
  }

  private getFootballTeams() {
    this.footballTeamService.getFootballTeams().subscribe({
      next: value => this.footballTeams = value,
      error: err => console.log(err)
    });
  }
}
