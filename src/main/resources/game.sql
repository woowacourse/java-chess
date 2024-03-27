create table game
(
    id              int auto_increment primary key,
    current_team    varchar(5) not null,
    status          varchar(7) not null,
    black_player_id int        not null,
    white_player_id int        not null,
    constraint game_ibfk_1
        foreign key (black_player_id) references player (id)
            on update cascade on delete cascade,
    constraint game_ibfk_2
        foreign key (white_player_id) references player (id)
            on update cascade on delete cascade
);
