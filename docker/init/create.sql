create table chess.game
(
    id          int auto_increment
        primary key,
    is_finished tinyint(1)   not null,
    turn        varchar(100) not null
);

create table chess.piece
(
    game_id int          not null,
    file    varchar(100) not null,
    `rank`  varchar(100) not null,
    type    varchar(100) not null,
    team    varchar(100) not null,
    constraint piece_game_id_fk
        foreign key (game_id) references chess.game (id)
);