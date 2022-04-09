create table chessgame(
    turn varchar(10) not null
);

create table chessboard(
    position varchar(20) not null,
    piece varchar(20) not null,
    moveCount int not null
);