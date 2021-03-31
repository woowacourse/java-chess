CREATE DATABASE chess CHARACTER SET utf8 COLLATE utf8_general_ci;

use chess;

CREATE TABLE chess (
   id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
   board json NOT NULL,
   status json NOT NULL
);