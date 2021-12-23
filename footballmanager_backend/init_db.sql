-- I used PostgreSQL to create a database using the following queries

-- CREATE DATABASE football_manager
--     WITH
--     OWNER = postgres
--     ENCODING = 'UTF8'
--     CONNECTION LIMIT = -1;

DROP TABLE IF EXISTS players;
DROP TABLE IF EXISTS football_teams;

CREATE TABLE football_teams
(
    team_id serial PRIMARY KEY,
    name text,
    country varchar(25),
    account_balance int,
    transfer_tax real
);

INSERT INTO football_teams VALUES
(0, 'Players without team', 'All countries', 0, 0.0),
(DEFAULT, 'FC Barcelona', 'Spain', 17000000, 7.5),
(DEFAULT, 'Manchester United', 'England', 12000000, 6.8);


CREATE TABLE players
(
    player_id serial PRIMARY KEY,
    full_name text,
    age int,
    months_of_experience smallint,
    nationality varchar(25),
    fk_team_id int REFERENCES football_teams(team_id)
);

INSERT INTO players
VALUES
(DEFAULT, 'Marc-André ter Stegen', 29, 134, 'Germany', 1),
(DEFAULT, 'Neto', 32, 161, 'Brazil', 1),
(DEFAULT, 'Iñaki Peña', 22, 106, 'Spain', 1),
(DEFAULT, 'Ronald Araújo', 22, 187, 'Uruguay', 1),
(DEFAULT, 'Eric García', 20, 75, 'Spain', 1),
(DEFAULT, 'Clément Lenglet', 26, 117, 'France', 1),
(DEFAULT, 'Óscar Mingueza', 22, 168, 'Spain', 1),
(DEFAULT, 'Gerard Piqué', 34, 75, 'Spain', 1),
(DEFAULT, 'Samuel Umtiti', 28, 144, 'France', 1),
(DEFAULT, 'Jordi Alba', 32, 70, 'Spain', 1),

(DEFAULT, 'Dean Henderson', 24, 94, 'England', 2),
(DEFAULT, 'David de Gea', 31, 136, 'Spain', 2),
(DEFAULT, 'Tom Heaton', 35, 16, 'England', 2),
(DEFAULT, 'Lee Grant', 38, 35, 'England', 2),
(DEFAULT, 'Raphaël Varane', 28, 63, 'France', 2),
(DEFAULT, 'Harry Maguire', 28, 45, 'England', 2),
(DEFAULT, 'Victor Lindelöf', 27, 159, 'Sweden', 2),
(DEFAULT, 'Eric Bailly', 27, 179, 'Cote d''Ivoire', 2),
(DEFAULT, 'Phil Jones', 29, 193, 'England', 2),
(DEFAULT, 'Luke Shaw', 26, 161, 'England', 2);
