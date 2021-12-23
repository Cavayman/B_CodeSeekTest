import { Component, OnInit } from '@angular/core';
import {FootballTeam} from "../football-team";
import {FootballTeamService} from "../football-team.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-create-football-team',
  templateUrl: './create-football-team.component.html',
  styleUrls: ['./create-football-team.component.css']
})
export class CreateFootballTeamComponent implements OnInit {

  footballTeam: FootballTeam = new FootballTeam();

  constructor(private footballTeamService: FootballTeamService,
              private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.saveFootballTeam();
  }

  saveFootballTeam() {
    this.footballTeamService.createFootballTeam(this.footballTeam).subscribe({
      next: value => this.goToFootballTeamList(),
      error: err => console.log(err)
    });
  }

  goToFootballTeamList() {
    this.router.navigate(['/footballTeams'])
  }
}
