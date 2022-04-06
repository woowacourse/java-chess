create table chessgame(
    turn varchar(10) not null,
    white_score varchar(20) not null,
    black_score varchar(20) not null
);

create table chessboard(
    position varchar(20) not null,
    piece varchar(20) not null
);