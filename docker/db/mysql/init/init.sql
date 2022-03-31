DROP TABLE IF EXISTS Piece;
DROP TABLE IF EXISTS Score;
DROP TABLE IF EXISTS ChessGame;

CREATE TABLE Piece
(
    position CHAR(2)     NOT NULL PRIMARY KEY,
    color    CHAR(5)     NOT NULL,
    type     VARCHAR(10) NOT NULL
);

CREATE TABLE Score
(
    color CHAR(5)     NOT NULL PRIMARY KEY,
    score VARCHAR(10) NOT NULL
);

CREATE TABLE ChessGame
(
    id           INT         NOT NULL PRIMARY KEY,
    status       VARCHAR(10) NOT NULL,
    currentColor CHAR(5) NOT NULL,
    winner       VARCHAR(10)
);