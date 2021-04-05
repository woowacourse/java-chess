-- MySQL dump 10.13  Distrib 8.0.22, for macos10.15 (x86_64)
--
-- Host: localhost    Database: db_name
-- ------------------------------------------------------
-- Server version	5.7.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `piece`
--

DROP TABLE IF EXISTS `piece`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `piece` (
  `pieceid` int(11) NOT NULL AUTO_INCREMENT,
  `gameid` int(11) NOT NULL,
  `name` text,
  `position` text,
  PRIMARY KEY (`pieceid`),
  KEY `gameid` (`gameid`),
  CONSTRAINT `piece_ibfk_1` FOREIGN KEY (`gameid`) REFERENCES `game` (`gameid`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=227 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `piece`
--

LOCK TABLES `piece` WRITE;
/*!40000 ALTER TABLE `piece` DISABLE KEYS */;
INSERT INTO `piece` VALUES (163,7,'N','g8'),(164,7,'p','g2'),(165,7,'n','b1'),(166,7,'N','b8'),(167,7,'p','b2'),(168,7,'P','h7'),(169,7,'k','e1'),(170,7,'P','c7'),(171,7,'p','e2'),(172,7,'K','e8'),(173,7,'P','a7'),(174,7,'n','g1'),(175,7,'P','f7'),(176,7,'P','e7'),(177,7,'r','h1'),(178,7,'B','c8'),(179,7,'B','f8'),(180,7,'q','d1'),(181,7,'p','c2'),(182,7,'p','f2'),(183,7,'r','a1'),(184,7,'b','f1'),(185,7,'b','c1'),(186,7,'P','g7'),(187,7,'P','d7'),(188,7,'p','a2'),(189,7,'R','a8'),(190,7,'p','h2'),(191,7,'R','h8'),(192,7,'P','b7'),(193,7,'Q','d8'),(194,7,'p','d2');
/*!40000 ALTER TABLE `piece` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-05 23:48:53
