DROP DATABASE chess;
CREATE DATABASE chess DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE chess;
DROP TABLE IF EXISTS player;
set sql_safe_updates = 0;
CREATE TABLE player(
	player_id INT NOT NULL AUTO_INCREMENT,
    player_name VARCHAR(64) NOT NULL,
    PRIMARY KEY (player_id)
);

DROP TABLE IF EXISTS turn;
set sql_safe_updates=0;

CREATE TABLE turn (
	board_id  INT NOT NULL,
    turn_color VARCHAR(64) NOT NULL,
    PRIMARY KEY (board_id),
    FOREIGN KEY (board_id) REFERENCES player(player_id)
);

DROP TABLE IF EXISTS piece;
set sql_safe_updates=0;
CREATE TABLE piece (
    piece_id INT NOT NULL AUTO_INCREMENT,
	board_id  INT NOT NULL,
    piece_kind VARCHAR(64) NOT NULL,
    piece_location VARCHAR(64) NOT NULL,
    PRIMARY KEY (piece_id),
    FOREIGN KEY (board_id) REFERENCES player(player_id)
);


