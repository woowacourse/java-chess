create table turn
(
    team char(5) not null,
    primary key (team)
);

create table board
(
    location char(2)    not null,
    team     char(5)    not null,
    name     varchar(6) not null,
    primary key (location)
);
