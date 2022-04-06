create table turn
(
    turn varchar(5) not null
);

create table pieces
(
    position varchar(2) not null
        primary key,
    piece_name     varchar(2) not null
);

