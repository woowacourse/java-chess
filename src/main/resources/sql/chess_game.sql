create user 'inbi'@'localhost' identified by '1234';

grant all privileges on *.* to 'inbi'@'localhost';

flush privileges;

CREATE DATABASE chess_game DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

use chess_game;

CREATE TABLE position
(
    id bigint unsigned AUTO_INCREMENT PRIMARY KEY,
    file_value varchar(255) NOT NULL,
    rank_value varchar(255) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE piece
(
    id bigint unsigned AUTO_INCREMENT PRIMARY KEY,
    name varchar(255) NOT NULL,
    position_id bigint unsigned NOT NULL,
    FOREIGN KEY (position_id) REFERENCES position (id)
) ENGINE=InnoDB;

CREATE TABLE pieces
(
    id bigint unsigned AUTO_INCREMENT PRIMARY KEY,
    piece_id bigint unsigned,
    FOREIGN KEY (piece_id) REFERENCES piece (id)
) ENGINE=InnoDB;

CREATE TABLE player
(
    id bigint unsigned AUTO_INCREMENT PRIMARY KEY,
    score decimal(5,5) NOT NULL DEFAULT 0.00,
    pieces_id bigint unsigned NOT NULL,
    FOREIGN KEY (pieces_id) REFERENCES pieces (id)
) ENGINE=InnoDB;

CREATE TABLE chess_room
(
    id bigint unsigned AUTO_INCREMENT PRIMARY KEY,
    title varchar(255) NOT NULL,
    current_turn_team_color varchar(255) NOT NULL DEFAULT 'white',
    white_team_player_id bigint unsigned NOT NULL,
    black_team_player_id bigint unsigned NOT NULL,
    FOREIGN KEY (white_team_player_id) REFERENCES player(id),
    FOREIGN KEY (black_team_player_id) REFERENCES player(id)
) ENGINE=InnoDB;