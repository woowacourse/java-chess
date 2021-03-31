CREATE DATABASE chess CHARACTER SET utf8 COLLATE utf8_general_ci;

use chess;

CREATE TABLE chess (
   id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
   serialized_board json NOT NULL,
   serialized_status json NOT NULL
);