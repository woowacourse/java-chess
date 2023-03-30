create table chess.game
(
    game_id int not null
        primary key
);

create table chess.board
(
    game_id int         not null,
    x       int         not null,
    y       int         not null,
    role    varchar(10) not null,
    team    varchar(10) not null,
    constraint board_game_id_fk
        foreign key (game_id) references chess.game (game_id)
            on delete cascade
);

create table chess.game_result
(
    game_result_id int auto_increment
        primary key,
    nickname       varchar(10) not null,
    score          int         not null,
    result         varchar(10) not null
);

create table chess.state
(
    game_id int         not null,
    state   varchar(15) not null,
    constraint state_game_id_fk
        foreign key (game_id) references chess.game (game_id)
            on delete cascade
);
