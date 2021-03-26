create user 'inbi'@'localhost' identified by '1234';

grant all privileges on *.* to 'inbi'@'localhost';

flush privileges;

CREATE DATABASE chess_game DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

use chess_game;

show tables;

drop table piece;

CREATE TABLE position
(
  id bigint unsigned AUTO_INCREMENT PRIMARY KEY,
  file_value varchar(255) NOT NULL,
  rank_value varchar(255) NOT NULL,
  piece_id bigint unsigned,
  FOREIGN KEY (piece_id) REFERENCES piece (id)
) ENGINE=InnoDB;

CREATE TABLE piece
(
  id bigint unsigned AUTO_INCREMENT PRIMARY KEY,
  piece_name varchar(255) NOT NULL,
  color varchar(255) NOT NULL,
  player_id bigint unsigned,
  FOREIGN KEY (player_id) REFERENCES player (id)
) ENGINE=InnoDB;

CREATE TABLE player
(
  id bigint unsigned AUTO_INCREMENT PRIMARY KEY,
  chess_room_id bigint unsigned,
  FOREIGN KEY (chess_room_id) REFERENCES chess_room (id)
) ENGINE=InnoDB;

CREATE TABLE chess_room
(
  id bigint unsigned AUTO_INCREMENT PRIMARY KEY,
  title varchar(255) NOT NULL,
  current_turn_team_color varchar(255) NOT NULL
) ENGINE=InnoDB;

select * from piece;
