GRANT ALL PRIVILEGES ON *.* TO 'root'@'localhost';
FLUSH PRIVILEGES;

CREATE DATABASE IF NOT EXISTS `chess` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
CREATE DATABASE IF NOT EXISTS `chess-test` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE chess;

CREATE TABLE IF NOT EXISTS game_information
(
    game_id            int        NOT NULL AUTO_INCREMENT,
    current_turn_color VARCHAR(5) NOT NULL,
    PRIMARY KEY (game_id)
);

CREATE TABLE IF NOT EXISTS chessboard
(
    id      int         NOT NULL AUTO_INCREMENT,
    file    VARCHAR(64) NOT NULL,
    `rank`  int         NOT NULL,
    type    VARCHAR(10) NOT NULL,
    color   VARCHAR(5)  NOT NULL,
    game_id int         NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (game_id) REFERENCES game_information (game_id) ON DELETE CASCADE
);

USE `chess-test`;

DROP TABLE IF EXISTS chessboard;
DROP TABLE IF EXISTS game_information;

CREATE TABLE IF NOT EXISTS game_information
(
    game_id            int        NOT NULL AUTO_INCREMENT,
    current_turn_color VARCHAR(5) NOT NULL,
    PRIMARY KEY (game_id)
);

CREATE TABLE IF NOT EXISTS chessboard
(
    id      int         NOT NULL AUTO_INCREMENT,
    file    VARCHAR(64) NOT NULL,
    `rank`  int         NOT NULL,
    type    VARCHAR(10) NOT NULL,
    color   VARCHAR(5)  NOT NULL,
    game_id int         NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (game_id) REFERENCES game_information (game_id) ON DELETE CASCADE
);

INSERT INTO game_information (current_turn_color) VALUE ('WHITE');

INSERT INTO chessboard (file, `rank`, type, color, game_id)
VALUES ('a', 1, 'ROOK', 'WHITE', 1),
       ('b', 1, 'KNIGHT', 'WHITE', 1),
       ('c', 1, 'BISHOP', 'WHITE', 1),
       ('d', 1, 'QUEEN', 'WHITE', 1),
       ('e', 1, 'KING', 'WHITE', 1),
       ('g', 1, 'KNIGHT', 'WHITE', 1),
       ('f', 1, 'BISHOP', 'WHITE', 1),
       ('h', 1, 'ROOK', 'WHITE', 1),
       ('a', 2, 'PAWN', 'WHITE', 1),
       ('b', 2, 'PAWN', 'WHITE', 1),
       ('c', 2, 'PAWN', 'WHITE', 1),
       ('d', 2, 'PAWN', 'WHITE', 1),
       ('e', 2, 'PAWN', 'WHITE', 1),
       ('f', 2, 'PAWN', 'WHITE', 1),
       ('g', 2, 'PAWN', 'WHITE', 1),
       ('h', 2, 'PAWN', 'WHITE', 1),
       ('a', 8, 'ROOK', 'BLACK', 1),
       ('b', 8, 'KNIGHT', 'BLACK', 1),
       ('c', 8, 'BISHOP', 'BLACK', 1),
       ('d', 8, 'QUEEN', 'BLACK', 1),
       ('e', 8, 'KING', 'BLACK', 1),
       ('f', 8, 'BISHOP', 'BLACK', 1),
       ('g', 8, 'KNIGHT', 'BLACK', 1),
       ('h', 8, 'ROOK', 'BLACK', 1),
       ('a', 7, 'PAWN', 'BLACK', 1),
       ('b', 7, 'PAWN', 'BLACK', 1),
       ('c', 7, 'PAWN', 'BLACK', 1),
       ('d', 7, 'PAWN', 'BLACK', 1),
       ('e', 7, 'PAWN', 'BLACK', 1),
       ('f', 7, 'PAWN', 'BLACK', 1),
       ('g', 7, 'PAWN', 'BLACK', 1),
       ('h', 7, 'PAWN', 'BLACK', 1);

