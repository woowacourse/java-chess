create user 'root'@'localhost' identified by 'root';

grant all privileges on *.* to 'root'@'localhost';

flush privileges;

CREATE DATABASE chessdb DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE chessdb;

CREATE TABLE board
(
    name       VARCHAR(30) NOT NULL,
    board_size INT         NOT NULL,
    turn       VARCHAR(10) NOT NULL,
    checked    BOOLEAN,
    king_dead  BOOLEAN,
    PRIMARY KEY (name)
);

CREATE TABLE piece
(
    id         INT AUTO_INCREMENT NOT NULL,
    name       VARCHAR(10)        NOT NULL,
    team       VARCHAR(10)        NOT NULL,
    score      INT                NOT NULL,
    position   VARCHAR(10)        NOT NULL,
    board_name VARCHAR(30)        NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (board_name) references board (name)
);


