create table board
(
    id         int auto_increment primary key,
    file       varchar(1) not null,
    `rank`     varchar(5) not null,
    piece_type varchar(6) not null,
    team       varchar(5) not null,
    game_id    int        not null,
    constraint board_ibfk_1
        foreign key (game_id) references game (id)
            on delete cascade
);
