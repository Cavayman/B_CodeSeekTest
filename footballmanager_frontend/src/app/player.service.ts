import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Player} from "./player";

@Injectable({
  providedIn: 'root'
})
export class PlayerService {
  private baseUrl = "http://localhost:8080/players";
  constructor(private httpClient: HttpClient) { }

  public getPlayers(teamId: number): Observable<Player[]> {
    return this.httpClient.get<Player[]>(`${this.baseUrl}/all/${teamId}`);
  }

  public getPlayerById(id: number): Observable<Player> {
    return this.httpClient.get<Player>(`${this.baseUrl}/${id}`);
  }

  public createPlayer(teamId: number, player: Player): Observable<Player> {
    return this.httpClient.post<Player>(`${this.baseUrl}/${teamId}`, player);
  }

  public updatePlayer(id: number, player: Player): Observable<Object> {
    return this.httpClient.put(`${this.baseUrl}/${id}`, player);
  }

  public deletePlayer(id: number): Observable<Object> {
    return this.httpClient.delete(`${this.baseUrl}/${id}`);
  }

  public playerTransfer(newTeamId: number, player: Player): Observable<Object> {
    return this.httpClient.post(`${this.baseUrl}/transfer/${newTeamId}`, player);
  }
}
