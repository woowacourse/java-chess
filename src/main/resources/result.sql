create table result
(
    id              int auto_increment primary key,
    game_id         int         not null,
    black_player_id int         not null,
    white_player_id int         not null,
    black_score double null,
    white_score double null,
    win_status      varchar(10) not null,
    constraint result_ibfk_1
        foreign key (game_id) references game (id)
            on update cascade on delete cascade,
    constraint result_ibfk_2
        foreign key (black_player_id) references player (id)
            on update cascade on delete cascade,
    constraint result_ibfk_3
        foreign key (white_player_id) references player (id)
            on update cascade on delete cascade
);
