USE chess;

DROP TABLE IF EXISTS game_information_for_test;

CREATE TABLE IF NOT EXISTS game_information_for_test
(
    game_id            int        NOT NULL AUTO_INCREMENT,
    current_turn_color VARCHAR(5) NOT NULL,
    PRIMARY KEY (game_id)
);

INSERT INTO game_information_for_test (current_turn_color) VALUE ('WHITE');

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
       ('h', 2, 'PAWN', 'WHITE'),
       ('a', 8, 'ROOK', 'BLACK'),
       ('b', 8, 'KNIGHT', 'BLACK'),
       ('c', 8, 'BISHOP', 'BLACK'),
       ('d', 8, 'QUEEN', 'BLACK'),
       ('e', 8, 'KING', 'BLACK'),
       ('f', 8, 'BISHOP', 'BLACK'),
       ('g', 8, 'KNIGHT', 'BLACK'),
       ('h', 8, 'ROOK', 'BLACK'),
       ('a', 7, 'PAWN', 'BLACK'),
       ('b', 7, 'PAWN', 'BLACK'),
       ('c', 7, 'PAWN', 'BLACK'),
       ('d', 7, 'PAWN', 'BLACK'),
       ('e', 7, 'PAWN', 'BLACK'),
       ('f', 7, 'PAWN', 'BLACK'),
       ('g', 7, 'PAWN', 'BLACK'),
       ('h', 7, 'PAWN', 'BLACK');
