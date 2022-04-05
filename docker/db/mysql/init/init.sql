create table piece
(
    id         varchar(2)  not null,
    piece_type varchar(20) not null,
    primary key (id)
);

create table turn
(
    turn_type varchar(10) not null,
    primary key (turn_type)
)
