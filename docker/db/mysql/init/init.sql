create table chess_game
(
    turn varchar(5) not null
);

insert into chess_game (turn) value ("WHITE");

create table piece
(
    position varchar(2) not null,
    name     varchar(6) not null,
    team     varchar(5) not null,
    primary key (position)
);
