import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateFootballTeamComponent } from './update-football-team.component';

describe('UpdateFootballTeamComponent', () => {
  let component: UpdateFootballTeamComponent;
  let fixture: ComponentFixture<UpdateFootballTeamComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateFootballTeamComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateFootballTeamComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
