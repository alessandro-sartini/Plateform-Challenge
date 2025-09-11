-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: rest_finder_db
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `restaurant_images`
--

DROP TABLE IF EXISTS `restaurant_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `restaurant_images` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `restaurant_id` bigint DEFAULT NULL,
  `url` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK810i11orew47qx1nrcwlh43jb` (`restaurant_id`),
  CONSTRAINT `FK810i11orew47qx1nrcwlh43jb` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=277 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant_images`
--

LOCK TABLES `restaurant_images` WRITE;
/*!40000 ALTER TABLE `restaurant_images` DISABLE KEYS */;
INSERT INTO `restaurant_images` VALUES (124,52,'/uploads/52/photo1.jpg'),(125,52,'/uploads/52/photo2.jpg'),(126,52,'/uploads/52/photo3.jpg'),(127,53,'/uploads/53/photo1.jpg'),(128,53,'/uploads/53/photo2.jpg'),(129,53,'/uploads/53/photo3.jpg'),(133,55,'/uploads/55/photo1.jpg'),(134,55,'/uploads/55/photo2.jpg'),(135,55,'/uploads/55/photo3.jpg'),(136,56,'/uploads/56/photo1.jpg'),(137,56,'/uploads/56/photo2.jpg'),(138,56,'/uploads/56/photo3.jpg'),(139,57,'/uploads/57/photo1.jpg'),(140,57,'/uploads/57/photo2.jpg'),(141,57,'/uploads/57/photo3.jpg'),(142,58,'/uploads/58/photo1.jpg'),(143,58,'/uploads/58/photo2.jpg'),(144,58,'/uploads/58/photo3.jpg'),(145,59,'/uploads/59/photo1.jpg'),(146,59,'/uploads/59/photo2.jpg'),(147,59,'/uploads/59/photo3.jpg'),(148,60,'/uploads/60/photo1.jpg'),(149,60,'/uploads/60/photo2.jpg'),(150,60,'/uploads/60/photo3.jpg'),(151,61,'/uploads/61/photo1.jpg'),(152,61,'/uploads/61/photo2.jpg'),(153,61,'/uploads/61/photo3.jpg'),(154,62,'/uploads/62/photo1.jpg'),(155,62,'/uploads/62/photo2.jpg'),(156,62,'/uploads/62/photo3.jpg'),(157,63,'/uploads/63/photo1.jpg'),(158,63,'/uploads/63/photo2.jpg'),(159,63,'/uploads/63/photo3.jpg'),(160,64,'/uploads/64/photo1.jpg'),(161,64,'/uploads/64/photo2.jpg'),(162,64,'/uploads/64/photo3.jpg'),(163,65,'/uploads/65/photo1.jpg'),(164,65,'/uploads/65/photo2.jpg'),(165,65,'/uploads/65/photo3.jpg'),(166,66,'/uploads/66/photo1.jpg'),(167,66,'/uploads/66/photo2.jpg'),(168,66,'/uploads/66/photo3.jpg'),(169,67,'/uploads/67/photo1.jpg'),(170,67,'/uploads/67/photo2.jpg'),(171,67,'/uploads/67/photo3.jpg'),(172,68,'/uploads/68/photo1.jpg'),(173,68,'/uploads/68/photo2.jpg'),(174,68,'/uploads/68/photo3.jpg'),(175,69,'/uploads/69/photo1.jpg'),(176,69,'/uploads/69/photo2.jpg'),(177,69,'/uploads/69/photo3.jpg'),(181,71,'/uploads/71/photo1.jpg'),(182,71,'/uploads/71/photo2.jpg'),(183,71,'/uploads/71/photo3.jpg'),(184,72,'/uploads/72/photo1.jpg'),(185,72,'/uploads/72/photo2.jpg'),(186,72,'/uploads/72/photo3.jpg'),(187,73,'/uploads/73/photo1.jpg'),(188,73,'/uploads/73/photo2.jpg'),(189,73,'/uploads/73/photo3.jpg'),(190,74,'/uploads/74/photo1.jpg'),(191,74,'/uploads/74/photo2.jpg'),(192,74,'/uploads/74/photo3.jpg'),(193,75,'/uploads/75/photo1.jpg'),(194,75,'/uploads/75/photo2.jpg'),(195,75,'/uploads/75/photo3.jpg'),(196,76,'/uploads/76/photo1.jpg'),(197,76,'/uploads/76/photo2.jpg'),(198,76,'/uploads/76/photo3.jpg'),(199,77,'/uploads/77/photo1.jpg'),(200,77,'/uploads/77/photo2.jpg'),(201,77,'/uploads/77/photo3.jpg'),(202,78,'/uploads/78/photo1.jpg'),(203,78,'/uploads/78/photo2.jpg'),(204,78,'/uploads/78/photo3.jpg'),(205,79,'/uploads/79/photo1.jpg'),(206,79,'/uploads/79/photo2.jpg'),(207,79,'/uploads/79/photo3.jpg'),(208,80,'/uploads/80/photo1.jpg'),(209,80,'/uploads/80/photo2.jpg'),(210,80,'/uploads/80/photo3.jpg'),(211,81,'/uploads/81/photo1.jpg'),(212,81,'/uploads/81/photo2.jpg'),(213,81,'/uploads/81/photo3.jpg'),(214,82,'/uploads/82/photo1.jpg'),(215,82,'/uploads/82/photo2.jpg'),(216,82,'/uploads/82/photo3.jpg'),(217,83,'/uploads/83/photo1.jpg'),(218,83,'/uploads/83/photo2.jpg'),(219,83,'/uploads/83/photo3.jpg'),(220,84,'/uploads/84/photo1.jpg'),(221,84,'/uploads/84/photo2.jpg'),(222,84,'/uploads/84/photo3.jpg'),(223,85,'/uploads/85/photo1.jpg'),(224,85,'/uploads/85/photo2.jpg'),(225,85,'/uploads/85/photo3.jpg'),(226,86,'/uploads/86/photo1.jpg'),(227,86,'/uploads/86/photo2.jpg'),(228,86,'/uploads/86/photo3.jpg'),(229,87,'/uploads/87/photo1.jpg'),(230,87,'/uploads/87/photo2.jpg'),(231,87,'/uploads/87/photo3.jpg'),(232,88,'/uploads/88/photo1.jpg'),(233,88,'/uploads/88/photo2.jpg'),(234,88,'/uploads/88/photo3.jpg'),(235,89,'/uploads/89/photo1.jpg'),(236,89,'/uploads/89/photo2.jpg'),(237,89,'/uploads/89/photo3.jpg'),(238,90,'/uploads/90/photo1.jpg'),(239,90,'/uploads/90/photo2.jpg'),(240,90,'/uploads/90/photo3.jpg'),(241,91,'/uploads/91/photo1.jpg'),(242,91,'/uploads/91/photo2.jpg'),(243,91,'/uploads/91/photo3.jpg'),(244,92,'/uploads/92/photo1.jpg'),(245,92,'/uploads/92/photo2.jpg'),(246,92,'/uploads/92/photo3.jpg'),(247,93,'/uploads/93/photo1.jpg'),(248,93,'/uploads/93/photo2.jpg'),(249,93,'/uploads/93/photo3.jpg'),(250,94,'/uploads/94/photo1.jpg'),(251,94,'/uploads/94/photo2.jpg'),(252,94,'/uploads/94/photo3.jpg'),(253,95,'/uploads/95/photo1.jpg'),(254,95,'/uploads/95/photo2.jpg'),(255,95,'/uploads/95/photo3.jpg'),(256,96,'/uploads/96/photo1.jpg'),(257,96,'/uploads/96/photo2.jpg'),(258,96,'/uploads/96/photo3.jpg'),(259,97,'/uploads/97/photo1.jpg'),(260,97,'/uploads/97/photo2.jpg'),(261,97,'/uploads/97/photo3.jpg'),(262,98,'/uploads/98/photo1.jpg'),(263,98,'/uploads/98/photo2.jpg'),(264,98,'/uploads/98/photo3.jpg'),(265,99,'/uploads/99/photo1.jpg'),(266,99,'/uploads/99/photo2.jpg'),(267,99,'/uploads/99/photo3.jpg'),(268,100,'/uploads/100/photo1.jpg'),(269,100,'/uploads/100/photo2.jpg'),(270,100,'/uploads/100/photo3.jpg'),(271,101,'/uploads/101/photo1.jpg'),(272,101,'/uploads/101/photo2.jpg'),(273,101,'/uploads/101/photo3.jpg'),(274,102,'/uploads/102/photo1.jpg'),(275,102,'/uploads/102/photo2.jpg'),(276,102,'/uploads/102/photo3.jpg');
/*!40000 ALTER TABLE `restaurant_images` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-30 15:04:16
