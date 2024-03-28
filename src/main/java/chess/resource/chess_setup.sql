CREATE DATABASE chess DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE chess;

CREATE TABLE pieces
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    board_file INT         NOT NULL,
    board_rank INT         NOT NULL,
    type       VARCHAR(64) NOT NULL
);

CREATE TABLE turns
(
    id    INT AUTO_INCREMENT PRIMARY KEY,
    color varchar(10) NOT NULL
);
