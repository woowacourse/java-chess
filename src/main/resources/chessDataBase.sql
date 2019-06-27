CREATE TABLE `chessgame` (
  `room_number` bigint(20) NOT NULL AUTO_INCREMENT,
  `turn` varchar(45) NOT NULL,
  `gameover` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`room_number`)
);

CREATE TABLE `piece` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `player` varchar(45) NOT NULL,
  `piece_type` varchar(45) NOT NULL,
  `x_position` int(11) NOT NULL,
  `y_position` int(11) NOT NULL,
  `room_number` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_piece_chessgame` (`room_number`),
  CONSTRAINT `fk_piece_chessgame` FOREIGN KEY (`room_number`) REFERENCES `chessgame` (`room_number`) ON DELETE CASCADE
);