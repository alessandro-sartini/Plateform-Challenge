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
-- Table structure for table `categories_resturants`
--

DROP TABLE IF EXISTS `categories_resturants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories_resturants` (
  `category_id` bigint NOT NULL,
  `restaurant_id` bigint NOT NULL,
  KEY `FKisv3ueoooy1udvjs82q8enam0` (`category_id`),
  KEY `FK3ant5jg7d3e1jlt15ylcdq81q` (`restaurant_id`),
  CONSTRAINT `FK3ant5jg7d3e1jlt15ylcdq81q` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`),
  CONSTRAINT `FKisv3ueoooy1udvjs82q8enam0` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories_resturants`
--

LOCK TABLES `categories_resturants` WRITE;
/*!40000 ALTER TABLE `categories_resturants` DISABLE KEYS */;
INSERT INTO `categories_resturants` VALUES (1,53),(2,53),(3,53),(4,53),(1,56),(6,56),(5,56),(2,56),(3,56),(4,56),(1,57),(2,57),(3,57),(4,57),(1,58),(2,58),(3,58),(4,58),(1,59),(2,59),(3,59),(4,59),(1,60),(2,60),(3,60),(4,60),(1,61),(2,61),(3,61),(4,61),(1,62),(2,62),(3,62),(4,62),(1,63),(2,63),(3,63),(4,63),(1,64),(2,64),(3,64),(4,64),(1,65),(3,65),(2,65),(4,65),(7,66),(1,66),(2,66),(3,66),(4,66),(1,67),(2,67),(3,67),(4,67),(1,68),(2,68),(3,68),(4,68),(10,71),(1,71),(2,71),(8,71),(3,71),(4,71),(1,72),(2,72),(3,72),(4,72),(1,73),(2,73),(3,73),(4,73),(1,75),(2,75),(3,75),(4,75),(7,76),(1,76),(2,76),(3,76),(4,76),(1,77),(2,77),(3,77),(4,77),(1,78),(2,78),(3,78),(4,78),(1,79),(2,79),(3,79),(4,79),(1,80),(2,80),(3,80),(4,80),(1,81),(2,81),(3,81),(4,81),(1,82),(3,82),(2,82),(4,82),(1,83),(2,83),(3,83),(4,83),(1,84),(2,84),(3,84),(4,84),(1,86),(2,86),(3,86),(4,86),(1,87),(2,87),(3,87),(4,87),(1,88),(2,88),(3,88),(4,88),(8,89),(1,89),(2,89),(3,89),(4,89),(1,90),(2,90),(3,90),(4,90),(5,91),(1,91),(2,91),(3,91),(4,91),(9,92),(8,92),(1,92),(2,92),(3,92),(4,92),(1,93),(2,93),(3,93),(4,93),(1,94),(2,94),(3,94),(4,94),(1,95),(2,95),(3,95),(4,95),(1,96),(2,96),(3,96),(4,96),(1,97),(2,97),(3,97),(4,97),(1,98),(2,98),(3,98),(4,98),(1,99),(2,99),(3,99),(4,99),(1,100),(2,100),(3,100),(4,100),(1,52),(2,52),(3,52),(4,52),(7,52),(1,55),(2,55),(3,55),(4,55),(8,55),(9,55),(11,55),(1,85),(2,85),(3,85),(4,85),(5,85),(8,85),(1,69),(2,69),(3,69),(4,69),(1,74),(2,74),(3,74),(4,74),(5,101),(1,101),(2,101),(3,101),(4,101),(1,102),(5,102),(2,102),(3,102),(4,102);
/*!40000 ALTER TABLE `categories_resturants` ENABLE KEYS */;
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
