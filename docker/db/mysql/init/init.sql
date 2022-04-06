create table board
(
    id       int auto_increment
        primary key,
    symbol   varchar(10) not null,
    team     varchar(10) not null,
    position varchar(5)  not null,
    game_id  int         not null,
    constraint game___fk
        foreign key (game_id) references game (id)
            on update cascade on delete cascade
);

create table game
(
    id              int auto_increment
        primary key,
    white_user_name varchar(15) not null,
    black_user_name varchar(15) not null,
    state           varchar(10) not null
);
