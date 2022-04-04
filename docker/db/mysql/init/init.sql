create table piece
(
    piece_type varchar(10) not null,
    position   varchar(2)  not null,
    game_name  varchar(10) not null,

    primary key (piece_type, position)
);


create table chess_game
(
    name varchar(10) not null PRIMARY KEY,
    turn varchar(10) not null
);