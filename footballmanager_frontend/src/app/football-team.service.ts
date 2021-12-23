import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {FootballTeam} from "./football-team";

@Injectable({
  providedIn: 'root'
})
export class FootballTeamService {
  private baseUrl = "http://localhost:8080/footballTeams";
  constructor(private httpClient: HttpClient) { }

  public getFootballTeams(): Observable<FootballTeam[]> {
    return this.httpClient.get<FootballTeam[]>(`${this.baseUrl}`);
  }

  public getFootballTeamById(teamId: number): Observable<FootballTeam> {
    return this.httpClient.get<FootballTeam>(`${this.baseUrl}/${teamId}`);
  }

  public createFootballTeam(footballTeam: FootballTeam): Observable<FootballTeam> {
    return this.httpClient.post<FootballTeam>(`${this.baseUrl}`, footballTeam);
  }

  public updateFootballTeam(teamId: number, footballTeam: FootballTeam): Observable<Object> {
    return this.httpClient.put(`${this.baseUrl}/${teamId}`, footballTeam);
  }

  public deleteFootballTeam(teamId: number): Observable<Object> {
    return this.httpClient.delete(`${this.baseUrl}/${teamId}`);
  }
}
