create table game
(
    id              int auto_increment
        primary key,
    white_user_name varchar(30) not null,
    black_user_name varchar(30) not null,
    state           varchar(30) not null
);

create table board
(
    id       int auto_increment
        primary key,
    symbol   varchar(30) not null,
    team     varchar(30) not null,
    position varchar(30) not null,
    game_id  int         not null,
    constraint game___fk
        foreign key (game_id) references game (id)
            on update cascade on delete cascade
);
