create table game
(
    id         bigint auto_increment
        primary key,
    turn       varchar(5)                           not null,
    name       text                                 not null,
    is_end     tinyint(1) default 0                 not null,
    created_at datetime   default CURRENT_TIMESTAMP not null
);

create table pieces
(
    id         bigint auto_increment,
    position   varchar(2) not null,
    piece_name varchar(6) not null,
    game_id    bigint     not null,
    constraint pieces_pk
        unique (id, position),
    constraint pieces_game_id_fk
        foreign key (game_id) references game (id)
);
