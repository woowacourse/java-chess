CREATE DATABASE  IF NOT EXISTS `chess`;
USE `chess`;

DROP TABLE IF EXISTS `board`;
CREATE TABLE `board` (
  `id` int NOT NULL AUTO_INCREMENT,
  `start_team` varchar(45) NOT NULL,
  `board_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `board_name_UNIQUE` (`board_name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
