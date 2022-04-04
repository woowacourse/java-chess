CREATE DATABASE IF NOT EXISTS chess;

CREATE TABLE game_status(
    id INT NOT NULL AUTO_INCREMENT,
    game_status VARCHAR(15) NOT NULL,
    PRIMARY KEY (id),
    INDEX game_status_index (game_status ASC));

INSERT INTO game_status(game_status) VALUES("READY");
INSERT INTO game_status(game_status) VALUES("WHITE_ROOM");
INSERT INTO game_status(game_status) VALUES("BLACK_ROOM");
INSERT INTO game_status(game_status) VALUES("END");

CREATE TABLE user (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL,
    game_status_id INT NOT NULL DEFAULT 1,
    PRIMARY KEY (id),
    INDEX name_idx (name ASC),
    UNIQUE INDEX name_UNIQUE (name ASC),
    FOREIGN KEY (game_status_id)
    REFERENCES game_status (id));

CREATE TABLE piece (
   id INT NOT NULL AUTO_INCREMENT,
   symbol VARCHAR(1) NOT NULL,
   score INT NOT NULL,
   color VARCHAR(1) NOT NULL,
   image_url VARCHAR(100) NOT NULL,
   PRIMARY KEY (id)
);

CREATE TABLE board(
    id INT NOT NULL AUTO_INCREMENT,
    piece_id INT NOT NULL,
    position VARCHAR(2) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX positino_UNIQUE (position ASC),
    FOREIGN KEY (piece_id)
    REFERENCES piece(id));


