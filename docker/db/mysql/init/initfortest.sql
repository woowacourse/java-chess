USE chess;

DROP TABLE IF EXISTS chessboard_for_test;

CREATE TABLE IF NOT EXISTS chessboard_for_test
(
    id     int         NOT NULL AUTO_INCREMENT,
    file   VARCHAR(64) NOT NULL,
    `rank` int         NOT NULL,
    type   VARCHAR(10) NOT NULL,
    color  VARCHAR(5)  NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO chessboard_for_test (file, `rank`, type, color)
VALUES ('a', 1, 'ROOK', 'WHITE'),
       ('b', 1, 'KNIGHT', 'WHITE'),
       ('c', 1, 'BISHOP', 'WHITE'),
       ('d', 1, 'QUEEN', 'WHITE'),
       ('e', 1, 'KING', 'WHITE'),
       ('f', 1, 'BISHOP', 'WHITE'),
       ('g', 1, 'KNIGHT', 'WHITE'),
       ('h', 1, 'ROOK', 'WHITE'),
       ('a', 2, 'PAWN', 'WHITE'),
       ('b', 2, 'PAWN', 'WHITE'),
       ('c', 2, 'PAWN', 'WHITE'),
       ('d', 2, 'PAWN', 'WHITE'),
       ('e', 2, 'PAWN', 'WHITE'),
       ('f', 2, 'PAWN', 'WHITE'),
       ('g', 2, 'PAWN', 'WHITE'),
       ('h', 2, 'PAWN', 'WHITE');
