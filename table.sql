create table chess_game
(
    id         bigint auto_increment
        primary key,
    name       varchar(20)                         null,
    created_at timestamp default CURRENT_TIMESTAMP not null,
    turn       varchar(8)                          not null
);

create table piece
(
    id            bigint auto_increment
        primary key,
    chess_game_id bigint     not null,
    piece_file    varchar(8) not null,
    piece_rank    varchar(8) not null,
    color         varchar(8) not null,
    type          varchar(8) not null,
    constraint piece_ibfk_1
        foreign key (chess_game_id) references chess_game (id)
            on update cascade
);

create index chess_game_id
    on piece (chess_game_id);
