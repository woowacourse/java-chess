create table chess.game
(
    id       int auto_increment primary key,
    finished tinyint(1) not null
);

create table chess.move_history
(
    id        int auto_increment primary key,
    source    varchar(2) null,
    target    varchar(2) null,
    move_time timestamp  not null,
    game_id   int        null,
    constraint move_history_fk_1
        foreign key (game_id) references chess.game (id)
);
