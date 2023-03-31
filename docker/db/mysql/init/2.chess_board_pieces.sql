CREATE DATABASE  IF NOT EXISTS `chess`;
USE `chess`;

DROP TABLE IF EXISTS `board_pieces`;
CREATE TABLE `board_pieces` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  `position` varchar(45) NOT NULL,
  `board_id` int NOT NULL,
  `team` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `board_fk_idx` (`board_id`),
  CONSTRAINT `board_fk` FOREIGN KEY (`board_id`) REFERENCES `board` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=132 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
