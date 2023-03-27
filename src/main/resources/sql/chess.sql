CREATE DATABASE IF NOT EXISTS chess;
use chess;

DROP TABLE IF EXISTS MOVEMENT;
DROP TABLE IF EXISTS GAME;

CREATE TABLE IF NOT EXISTS GAME
(
    id         bigint     not null PRIMARY KEY auto_increment,
    is_end     tinyint(1) not null default 0,
    turn_color varchar(5) not null
);

CREATE TABLE IF NOT EXISTS MOVEMENT
(
    id            bigint     not null PRIMARY KEY auto_increment,
    square_source varchar(2) not null,
    square_target varchar(2) not null,
    square_color varchar(10) not null,
    game_id       bigint     not null,
    FOREIGN KEY (game_id) REFERENCES GAME (id)
);

select * from GAME;
select * from MOVEMENT;
