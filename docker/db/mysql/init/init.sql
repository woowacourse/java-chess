create table board
(
    position varchar(2) not null,
    piece varchar(10) not null,
    primary key (position)
);

create table player
(
    color varchar(5) not null,
    primary key (color)
);