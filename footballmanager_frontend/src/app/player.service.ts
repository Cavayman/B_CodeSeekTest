import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Player} from "./player";

@Injectable({
  providedIn: 'root'
})
export class PlayerService {
  private baseUrl = "http://localhost:8080/players";
  constructor(private httpClient: HttpClient) { }

  public getPlayerById(id: number): Observable<Player> {
    return this.httpClient.get<Player>(`${this.baseUrl}/${id}`);
  }

  public createPlayer(player: Player): Observable<Player> {
    return this.httpClient.post<Player>(`${this.baseUrl}`, player);
  }

  public updatePlayer(id: number, player: Player): Observable<Object> {
    return this.httpClient.put(`${this.baseUrl}/${id}`, player);
  }

  public deletePlayer(id: number): Observable<Object> {
    return this.httpClient.delete(`${this.baseUrl}/${id}`);
  }

  public playerTransfer(playerId: number, newTeamId: number): Observable<Object> {
    return this.httpClient.post(`${this.baseUrl}/${playerId}/transfer/${newTeamId}`, null);
  }
}
