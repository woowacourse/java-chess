drop table if exists piece cascade;
drop table if exists position cascade;
drop table if exists chess_game cascade;
drop table if exists chess_board cascade;

CREATE TABLE piece
(
    id           bigint      NOT NULL AUTO_INCREMENT,
    type         varchar(10) NOT NULL,
    color        enum('WHITE', 'BLACK') NOT NULL,
    position_col varchar(1)  NOT NULL,
    position_row varchar(1)  NOT NULL,
    primary key (id)
);

CREATE TABLE chess_game
(
    id         bigint NOT NULL AUTO_INCREMENT,
    turn_state enum('WHITE_RUN', 'BLACK_RUN', 'PROMOTION') NOT NULL,
    winner     enum('WHITE', 'BLACK'),
    primary key (id)
);
