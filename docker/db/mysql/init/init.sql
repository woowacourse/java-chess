create table piece
(
    position varchar(2) not null,
    team     varchar(5) not null,
    name     varchar(6) not null,
    primary key (position)
);

create table game
(
    state varchar(7),
    turn  varchar(5)
);