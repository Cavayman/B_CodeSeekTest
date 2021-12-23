import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FootballTeamDetailsComponent } from './football-team-details.component';

describe('FootballTeamDetailsComponent', () => {
  let component: FootballTeamDetailsComponent;
  let fixture: ComponentFixture<FootballTeamDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FootballTeamDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FootballTeamDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
