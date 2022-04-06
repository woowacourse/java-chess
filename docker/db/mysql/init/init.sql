create table board
(
    position varchar(5)  not null
        primary key,
    symbol   varchar(10) not null,
    color    varchar(10) not null,
    game_id  int         not null,
    constraint board_game_id_fk
        foreign key (game_id) references game (id)
            on update cascade on delete cascade
);

create table game
(
    id    int auto_increment
        primary key,
    state varchar(20) not null
);
