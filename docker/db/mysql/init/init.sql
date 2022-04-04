DROP TABLE IF EXISTS piece;
DROP TABLE IF EXISTS score;
DROP TABLE IF EXISTS chess_game;

CREATE TABLE piece
(
    position CHAR(2)     NOT NULL PRIMARY KEY,
    color    CHAR(5)     NOT NULL,
    type     VARCHAR(10) NOT NULL
);

CREATE TABLE score
(
    color CHAR(5)     NOT NULL PRIMARY KEY,
    score VARCHAR(10) NOT NULL
);

CREATE TABLE chess_game
(
    id           INT         NOT NULL PRIMARY KEY,
    status       VARCHAR(10) NOT NULL,
    current_color CHAR(5)     NOT NULL,
    winner       VARCHAR(10)
);