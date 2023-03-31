create table chess_game
(
    id   int auto_increment
        primary key,
    turn varchar(5) null
);

create table piece
(
    chess_game_id int         not null,
    id            int auto_increment
        primary key,
    name          varchar(10) not null,
    file          varchar(1)  not null,
    color         varchar(5)  null,
    `rank`        varchar(1)  not null,
    constraint piece_chess_game_id_fk
        foreign key (chess_game_id) references chess_game (id)
);
