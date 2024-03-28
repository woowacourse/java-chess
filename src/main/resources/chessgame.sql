USE chess;

create table chessgame
(
    current_turn varchar(5) not null,
    primary key (current_turn)
);
