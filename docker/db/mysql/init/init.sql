create table board (
    game_number int not null,
    position varchar(2) not null,
    color varchar(10) not null,
    piece varchar(10) not null,
    primary key (game_number, position)
);

create table turn (
    game_number int not null,
    color varchar(10) not null,
    primary key (game_number)
);
