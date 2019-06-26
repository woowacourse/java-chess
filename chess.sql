-- MySQL dump 10.13  Distrib 8.0.15, for macos10.14 (x86_64)
--
-- Host: localhost    Database: chess
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
SET NAMES utf8;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `board`
--

DROP TABLE IF EXISTS `board`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8mb4;
CREATE TABLE `board`
(
    `game_id`    int(11)     NOT NULL,
    `round_no`   int(11)     NOT NULL,
    `square_x`   int(11)     NOT NULL,
    `square_y`   int(11)     NOT NULL,
    `piece_type` varchar(45) NOT NULL,
    `team`       varchar(45) NOT NULL,
    PRIMARY KEY (`game_id`, `round_no`, `square_x`, `square_y`),
    CONSTRAINT `game_id` FOREIGN KEY (`game_id`) REFERENCES `game` (`game_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board`
    DISABLE KEYS */;
INSERT INTO `board`
VALUES (11, 0, 0, 0, 'ROOK', 'BLACK'),
       (11, 0, 0, 1, 'PAWN', 'BLACK'),
       (11, 0, 0, 6, 'PAWN', 'WHITE'),
       (11, 0, 0, 7, 'ROOK', 'WHITE'),
       (11, 0, 1, 0, 'KNIGHT', 'BLACK'),
       (11, 0, 1, 1, 'PAWN', 'BLACK'),
       (11, 0, 1, 6, 'PAWN', 'WHITE'),
       (11, 0, 1, 7, 'KNIGHT', 'WHITE'),
       (11, 0, 2, 0, 'BISHOP', 'BLACK'),
       (11, 0, 2, 1, 'PAWN', 'BLACK'),
       (11, 0, 2, 6, 'PAWN', 'WHITE'),
       (11, 0, 2, 7, 'BISHOP', 'WHITE'),
       (11, 0, 3, 0, 'QUEEN', 'BLACK'),
       (11, 0, 3, 1, 'PAWN', 'BLACK'),
       (11, 0, 3, 6, 'PAWN', 'WHITE'),
       (11, 0, 3, 7, 'QUEEN', 'WHITE'),
       (11, 0, 4, 0, 'KING', 'BLACK'),
       (11, 0, 4, 1, 'PAWN', 'BLACK'),
       (11, 0, 4, 6, 'PAWN', 'WHITE'),
       (11, 0, 4, 7, 'KING', 'WHITE'),
       (11, 0, 5, 0, 'BISHOP', 'BLACK'),
       (11, 0, 5, 1, 'PAWN', 'BLACK'),
       (11, 0, 5, 6, 'PAWN', 'WHITE'),
       (11, 0, 5, 7, 'BISHOP', 'WHITE'),
       (11, 0, 6, 0, 'KNIGHT', 'BLACK'),
       (11, 0, 6, 1, 'PAWN', 'BLACK'),
       (11, 0, 6, 6, 'PAWN', 'WHITE'),
       (11, 0, 6, 7, 'KNIGHT', 'WHITE'),
       (11, 0, 7, 0, 'ROOK', 'BLACK'),
       (11, 0, 7, 1, 'PAWN', 'BLACK'),
       (11, 0, 7, 6, 'PAWN', 'WHITE'),
       (11, 0, 7, 7, 'ROOK', 'WHITE'),
       (11, 1, 0, 0, 'ROOK', 'BLACK'),
       (11, 1, 0, 1, 'PAWN', 'BLACK'),
       (11, 1, 0, 4, 'MOVEDPAWN', 'WHITE'),
       (11, 1, 0, 7, 'ROOK', 'WHITE'),
       (11, 1, 1, 0, 'KNIGHT', 'BLACK'),
       (11, 1, 1, 1, 'PAWN', 'BLACK'),
       (11, 1, 1, 6, 'PAWN', 'WHITE'),
       (11, 1, 1, 7, 'KNIGHT', 'WHITE'),
       (11, 1, 2, 0, 'BISHOP', 'BLACK'),
       (11, 1, 2, 1, 'PAWN', 'BLACK'),
       (11, 1, 2, 6, 'PAWN', 'WHITE'),
       (11, 1, 2, 7, 'BISHOP', 'WHITE'),
       (11, 1, 3, 0, 'QUEEN', 'BLACK'),
       (11, 1, 3, 1, 'PAWN', 'BLACK'),
       (11, 1, 3, 6, 'PAWN', 'WHITE'),
       (11, 1, 3, 7, 'QUEEN', 'WHITE'),
       (11, 1, 4, 0, 'KING', 'BLACK'),
       (11, 1, 4, 1, 'PAWN', 'BLACK'),
       (11, 1, 4, 6, 'PAWN', 'WHITE'),
       (11, 1, 4, 7, 'KING', 'WHITE'),
       (11, 1, 5, 0, 'BISHOP', 'BLACK'),
       (11, 1, 5, 1, 'PAWN', 'BLACK'),
       (11, 1, 5, 6, 'PAWN', 'WHITE'),
       (11, 1, 5, 7, 'BISHOP', 'WHITE'),
       (11, 1, 6, 0, 'KNIGHT', 'BLACK'),
       (11, 1, 6, 1, 'PAWN', 'BLACK'),
       (11, 1, 6, 6, 'PAWN', 'WHITE'),
       (11, 1, 6, 7, 'KNIGHT', 'WHITE'),
       (11, 1, 7, 0, 'ROOK', 'BLACK'),
       (11, 1, 7, 1, 'PAWN', 'BLACK'),
       (11, 1, 7, 6, 'PAWN', 'WHITE'),
       (11, 1, 7, 7, 'ROOK', 'WHITE'),
       (11, 2, 0, 0, 'ROOK', 'BLACK'),
       (11, 2, 0, 2, 'MOVEDPAWN', 'BLACK'),
       (11, 2, 0, 4, 'MOVEDPAWN', 'WHITE'),
       (11, 2, 0, 7, 'ROOK', 'WHITE'),
       (11, 2, 1, 0, 'KNIGHT', 'BLACK'),
       (11, 2, 1, 1, 'PAWN', 'BLACK'),
       (11, 2, 1, 6, 'PAWN', 'WHITE'),
       (11, 2, 1, 7, 'KNIGHT', 'WHITE'),
       (11, 2, 2, 0, 'BISHOP', 'BLACK'),
       (11, 2, 2, 1, 'PAWN', 'BLACK'),
       (11, 2, 2, 6, 'PAWN', 'WHITE'),
       (11, 2, 2, 7, 'BISHOP', 'WHITE'),
       (11, 2, 3, 0, 'QUEEN', 'BLACK'),
       (11, 2, 3, 1, 'PAWN', 'BLACK'),
       (11, 2, 3, 6, 'PAWN', 'WHITE'),
       (11, 2, 3, 7, 'QUEEN', 'WHITE'),
       (11, 2, 4, 0, 'KING', 'BLACK'),
       (11, 2, 4, 1, 'PAWN', 'BLACK'),
       (11, 2, 4, 6, 'PAWN', 'WHITE'),
       (11, 2, 4, 7, 'KING', 'WHITE'),
       (11, 2, 5, 0, 'BISHOP', 'BLACK'),
       (11, 2, 5, 1, 'PAWN', 'BLACK'),
       (11, 2, 5, 6, 'PAWN', 'WHITE'),
       (11, 2, 5, 7, 'BISHOP', 'WHITE'),
       (11, 2, 6, 0, 'KNIGHT', 'BLACK'),
       (11, 2, 6, 1, 'PAWN', 'BLACK'),
       (11, 2, 6, 6, 'PAWN', 'WHITE'),
       (11, 2, 6, 7, 'KNIGHT', 'WHITE'),
       (11, 2, 7, 0, 'ROOK', 'BLACK'),
       (11, 2, 7, 1, 'PAWN', 'BLACK'),
       (11, 2, 7, 6, 'PAWN', 'WHITE'),
       (11, 2, 7, 7, 'ROOK', 'WHITE'),
       (11, 3, 0, 0, 'ROOK', 'BLACK'),
       (11, 3, 0, 2, 'MOVEDPAWN', 'BLACK'),
       (11, 3, 0, 3, 'MOVEDPAWN', 'WHITE'),
       (11, 3, 0, 7, 'ROOK', 'WHITE'),
       (11, 3, 1, 0, 'KNIGHT', 'BLACK'),
       (11, 3, 1, 1, 'PAWN', 'BLACK'),
       (11, 3, 1, 6, 'PAWN', 'WHITE'),
       (11, 3, 1, 7, 'KNIGHT', 'WHITE'),
       (11, 3, 2, 0, 'BISHOP', 'BLACK'),
       (11, 3, 2, 1, 'PAWN', 'BLACK'),
       (11, 3, 2, 6, 'PAWN', 'WHITE'),
       (11, 3, 2, 7, 'BISHOP', 'WHITE'),
       (11, 3, 3, 0, 'QUEEN', 'BLACK'),
       (11, 3, 3, 1, 'PAWN', 'BLACK'),
       (11, 3, 3, 6, 'PAWN', 'WHITE'),
       (11, 3, 3, 7, 'QUEEN', 'WHITE'),
       (11, 3, 4, 0, 'KING', 'BLACK'),
       (11, 3, 4, 1, 'PAWN', 'BLACK'),
       (11, 3, 4, 6, 'PAWN', 'WHITE'),
       (11, 3, 4, 7, 'KING', 'WHITE'),
       (11, 3, 5, 0, 'BISHOP', 'BLACK'),
       (11, 3, 5, 1, 'PAWN', 'BLACK'),
       (11, 3, 5, 6, 'PAWN', 'WHITE'),
       (11, 3, 5, 7, 'BISHOP', 'WHITE'),
       (11, 3, 6, 0, 'KNIGHT', 'BLACK'),
       (11, 3, 6, 1, 'PAWN', 'BLACK'),
       (11, 3, 6, 6, 'PAWN', 'WHITE'),
       (11, 3, 6, 7, 'KNIGHT', 'WHITE'),
       (11, 3, 7, 0, 'ROOK', 'BLACK'),
       (11, 3, 7, 1, 'PAWN', 'BLACK'),
       (11, 3, 7, 6, 'PAWN', 'WHITE'),
       (11, 3, 7, 7, 'ROOK', 'WHITE'),
       (11, 4, 0, 0, 'ROOK', 'BLACK'),
       (11, 4, 0, 2, 'MOVEDPAWN', 'BLACK'),
       (11, 4, 0, 3, 'MOVEDPAWN', 'WHITE'),
       (11, 4, 0, 7, 'ROOK', 'WHITE'),
       (11, 4, 1, 0, 'KNIGHT', 'BLACK'),
       (11, 4, 1, 2, 'MOVEDPAWN', 'BLACK'),
       (11, 4, 1, 6, 'PAWN', 'WHITE'),
       (11, 4, 1, 7, 'KNIGHT', 'WHITE'),
       (11, 4, 2, 0, 'BISHOP', 'BLACK'),
       (11, 4, 2, 1, 'PAWN', 'BLACK'),
       (11, 4, 2, 6, 'PAWN', 'WHITE'),
       (11, 4, 2, 7, 'BISHOP', 'WHITE'),
       (11, 4, 3, 0, 'QUEEN', 'BLACK'),
       (11, 4, 3, 1, 'PAWN', 'BLACK'),
       (11, 4, 3, 6, 'PAWN', 'WHITE'),
       (11, 4, 3, 7, 'QUEEN', 'WHITE'),
       (11, 4, 4, 0, 'KING', 'BLACK'),
       (11, 4, 4, 1, 'PAWN', 'BLACK'),
       (11, 4, 4, 6, 'PAWN', 'WHITE'),
       (11, 4, 4, 7, 'KING', 'WHITE'),
       (11, 4, 5, 0, 'BISHOP', 'BLACK'),
       (11, 4, 5, 1, 'PAWN', 'BLACK'),
       (11, 4, 5, 6, 'PAWN', 'WHITE'),
       (11, 4, 5, 7, 'BISHOP', 'WHITE'),
       (11, 4, 6, 0, 'KNIGHT', 'BLACK'),
       (11, 4, 6, 1, 'PAWN', 'BLACK'),
       (11, 4, 6, 6, 'PAWN', 'WHITE'),
       (11, 4, 6, 7, 'KNIGHT', 'WHITE'),
       (11, 4, 7, 0, 'ROOK', 'BLACK'),
       (11, 4, 7, 1, 'PAWN', 'BLACK'),
       (11, 4, 7, 6, 'PAWN', 'WHITE'),
       (11, 4, 7, 7, 'ROOK', 'WHITE'),
       (11, 5, 0, 0, 'ROOK', 'BLACK'),
       (11, 5, 0, 2, 'MOVEDPAWN', 'BLACK'),
       (11, 5, 0, 3, 'MOVEDPAWN', 'WHITE'),
       (11, 5, 0, 7, 'ROOK', 'WHITE'),
       (11, 5, 1, 0, 'KNIGHT', 'BLACK'),
       (11, 5, 1, 2, 'MOVEDPAWN', 'BLACK'),
       (11, 5, 1, 5, 'MOVEDPAWN', 'WHITE'),
       (11, 5, 1, 7, 'KNIGHT', 'WHITE'),
       (11, 5, 2, 0, 'BISHOP', 'BLACK'),
       (11, 5, 2, 1, 'PAWN', 'BLACK'),
       (11, 5, 2, 6, 'PAWN', 'WHITE'),
       (11, 5, 2, 7, 'BISHOP', 'WHITE'),
       (11, 5, 3, 0, 'QUEEN', 'BLACK'),
       (11, 5, 3, 1, 'PAWN', 'BLACK'),
       (11, 5, 3, 6, 'PAWN', 'WHITE'),
       (11, 5, 3, 7, 'QUEEN', 'WHITE'),
       (11, 5, 4, 0, 'KING', 'BLACK'),
       (11, 5, 4, 1, 'PAWN', 'BLACK'),
       (11, 5, 4, 6, 'PAWN', 'WHITE'),
       (11, 5, 4, 7, 'KING', 'WHITE'),
       (11, 5, 5, 0, 'BISHOP', 'BLACK'),
       (11, 5, 5, 1, 'PAWN', 'BLACK'),
       (11, 5, 5, 6, 'PAWN', 'WHITE'),
       (11, 5, 5, 7, 'BISHOP', 'WHITE'),
       (11, 5, 6, 0, 'KNIGHT', 'BLACK'),
       (11, 5, 6, 1, 'PAWN', 'BLACK'),
       (11, 5, 6, 6, 'PAWN', 'WHITE'),
       (11, 5, 6, 7, 'KNIGHT', 'WHITE'),
       (11, 5, 7, 0, 'ROOK', 'BLACK'),
       (11, 5, 7, 1, 'PAWN', 'BLACK'),
       (11, 5, 7, 6, 'PAWN', 'WHITE'),
       (11, 5, 7, 7, 'ROOK', 'WHITE');
/*!40000 ALTER TABLE `board`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `game`
--

DROP TABLE IF EXISTS `game`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8mb4;
CREATE TABLE `game`
(
    `game_id`     int(11)     NOT NULL AUTO_INCREMENT,
    `game_status` tinyint(4)  NOT NULL,
    `last_user`   varchar(45) NOT NULL,
    PRIMARY KEY (`game_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 12
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game`
--

LOCK TABLES `game` WRITE;
/*!40000 ALTER TABLE `game`
    DISABLE KEYS */;
INSERT INTO `game`
VALUES (11, 0, 'BLACK');
/*!40000 ALTER TABLE `game`
    ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2019-06-26 14:05:11
