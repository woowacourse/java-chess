create table turn
(
    color varchar(20) not null,
    primary key (color)
);

create table piece
(
    position varchar(5)  not null,
    type     varchar(10) not null,
    color    varchar(20) not null,
    primary key (position)
);
