CREATE DATABASE chess CHARACTER SET utf8 COLLATE utf8_general_ci;

use chess;

CREATE TABLE chess (
	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	serialized_board blob NOT NULL,
    serialized_status blob NOT NULL
);