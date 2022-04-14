create table chessGame(
    gameID varchar(10) not null,
    turn varchar(10) not null,
    primary key(gameID)
);

create table piece(
    position varchar(10) not null,
    type varchar(10) not null,
    color varchar(10) not null,
    gameID varchar(10) not null,
    primary key(position),
    foreign key(gameID) references chessGame(gameID)
);
