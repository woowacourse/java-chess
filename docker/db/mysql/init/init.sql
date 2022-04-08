create table game
(
    id        int                  not null
        primary key,
    whiteName varchar(100)         null,
    blackName varchar(100)         null,
    winner    varchar(100)         null,
    turn      tinyint(1) default 0 null
);

create table board
(
    gameId   int        null,
    position varchar(2) null,
    piece    varchar(2) null
);

create table user
(
    id   int          not null
        primary key,
    name varchar(100) null,
    win  int          null,
    lost int          null
);

create table initialBoard
(
    initialPosition varchar(2) null,
    initialPiece    varchar(2) null
);

INSERT INTO initialBoard (initialPosition, initialPiece)
VALUES ('A8', 'rb')
     , ('B8', 'nb')
     , ('C8', 'bb')
     , ('D8', 'qb')
     , ('E8', 'kb')
     , ('F8', 'bb')
     , ('G8', 'nb')
     , ('H8', 'rb')
     , ('A7', 'pb')
     , ('B7', 'pb')
     , ('C7', 'pb')
     , ('D7', 'pb')
     , ('E7', 'pb')
     , ('F7', 'pb')
     , ('G7', 'pb')
     , ('H7', 'pb')
     , ('A2', 'pw')
     , ('B2', 'pw')
     , ('C2', 'pw')
     , ('D2', 'pw')
     , ('E2', 'pw')
     , ('F2', 'pw')
     , ('G2', 'pw')
     , ('H2', 'pw')
     , ('A1', 'rw')
     , ('B1', 'nw')
     , ('C1', 'bw')
     , ('D1', 'qw')
     , ('E1', 'kw')
     , ('F1', 'bw')
     , ('G1', 'nw')
     , ('H1', 'rw');
