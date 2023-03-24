CREATE TABLE game(
  `game_id` INT NOT NULL AUTO_INCREMENT,
  `winner` VARCHAR(12) NOT NULL,
  `state` VARCHAR(12) NOT NULL,
  PRIMARY KEY(game_id)
);

CREATE TABLE moveHistory(
  `move_history_id` INT NOT NULL AUTO_INCREMENT,
  `game_id` INT NOT NULL,
  `source` VARCHAR(12) NOT NULL,
  `destination` VARCHAR(12) NOT NULL,
  FOREIGN KEY(game_id) REFERENCES `game`(game_id),
  PRIMARY KEY(move_history_id)
);