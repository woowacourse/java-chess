USE chess;

CREATE TABLE Room
(
    Name  VARCHAR(10) NOT NULL UNIQUE,
    White VARCHAR(10) UNIQUE,
    Black VARCHAR(10) UNIQUE,
    PRIMARY KEY (Name)
);

CREATE TABLE ChessPiece
(
    Room_Id    VARCHAR(10) NOT NULL,
    Position   VARCHAR(10) NOT NULL,
    ChessPiece VARCHAR(10) NOT NULL,
    Color      VARCHAR(10) NOT NULL,
    FOREIGN KEY (Room_Id) REFERENCES Room (Name)
);

CREATE TABLE Status
(
    Room_Id     VARCHAR(10) NOT NULL,
    GameStatus  VARCHAR(10) NOT NULL,
    CurrentTurn VARCHAR(10) NOT NULL,
    FOREIGN KEY (Room_Id) REFERENCES Room (Name)
);

