drop table if exists piece cascade;
drop table if exists chess_game cascade;

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
    turn enum('WHITE_TURN', 'BLACK_TURN', 'END') NOT NULL,
    primary key (turn)
);

insert into chess_game (turn) values('END');
