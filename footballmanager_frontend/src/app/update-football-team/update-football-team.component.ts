import { Component, OnInit } from '@angular/core';
import {FootballTeamService} from "../football-team.service";
import {FootballTeam} from "../football-team";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-update-football-team',
  templateUrl: './update-football-team.component.html',
  styleUrls: ['./update-football-team.component.css']
})
export class UpdateFootballTeamComponent implements OnInit {
  footballTeam: FootballTeam = new FootballTeam();
  teamId!: number;

  constructor(private footballTeamService: FootballTeamService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.teamId = this.route.snapshot.params['teamId'];

    this.footballTeamService.getFootballTeamById(this.teamId).subscribe({
      next: value => this.footballTeam = value,
      error: err => console.log(err)
    });
  }

  onSubmit() {
    this.updateFootballTeam();
  }

  updateFootballTeam() {
    this.footballTeamService.updateFootballTeam(this.teamId, this.footballTeam).subscribe({
      next: value => this.goToFootballTeamList(),
      error: err => console.log(err)
    });
  }

  goToFootballTeamList() {
    this.router.navigate(['/footballTeams'])
  }
}
