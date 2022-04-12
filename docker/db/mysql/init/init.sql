create table chess_game
(
    state varchar(10) not null
);

create table board
(
    location char(2)     not null primary key,
    team     varchar(5)  not null,
    name     varchar(10) not null
);
