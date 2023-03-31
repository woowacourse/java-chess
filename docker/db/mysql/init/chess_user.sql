CREATE DATABASE  IF NOT EXISTS `chess`;
USE `chess`;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` varchar(12) NOT NULL,
  `name` varchar(64) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
