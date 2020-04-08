CREATE DATABASE chess_game DEFAULT CHARACTER SET utf8mb4 collate utf8mb4_general_ci;

USE chess_game;

CREATE TABLE chess_game (
    id INT NOT NULL AUTO_INCREMENT,
    state VARCHAR(8) NOT NULL,
    board VARCHAR(64),
    turn VARCHAR(5),
    PRIMARY KEY (id)
);