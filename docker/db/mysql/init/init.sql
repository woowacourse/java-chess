create table board (
    position varchar(2) not null,
    color varchar(10) not null,
    piece varchar(10) not null,
    primary key (position)
);

create table turn (
    color varchar(10) not null,
    primary key (color)
);
