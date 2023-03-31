create table ChessGameDB
(
    gameIdx int(100) AUTO_INCREMENT PRIMARY KEY,
    turn    varchar(10) NOT NULL DEFAULT 'white'
);

create table ChessBoardDB
(
    gameIdx   int(100),
    bFile     int(10),
    bRank     int(10),
    pieceType varchar(20),
    side      varchar(10)
);
