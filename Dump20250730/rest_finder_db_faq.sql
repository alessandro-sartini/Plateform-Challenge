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
-- Table structure for table `faq`
--

DROP TABLE IF EXISTS `faq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faq` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `response` varchar(1000) NOT NULL,
  `it_question` varchar(255) NOT NULL,
  `it_response` varchar(255) NOT NULL,
  `question` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK8kgfltf7jimd1umwbgjwcouq7` (`it_question`),
  UNIQUE KEY `UK39r0oi5luc65ni65tvsq6c5co` (`it_response`),
  UNIQUE KEY `UKef0paet8cnaifkaeepso1siu0` (`question`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faq`
--

LOCK TABLES `faq` WRITE;
/*!40000 ALTER TABLE `faq` DISABLE KEYS */;
INSERT INTO `faq` VALUES (1,'To modify a booking log in to your profile and select the desired reservation','Come posso modificare una prenotazione','Accedi al tuo account e vai nella sezione Le mie prenotazioni per apportare modifiche','How can I modify a booking'),(2,'Yes cancellation is allowed within 24 hours from the booking date','E possibile cancellare una prenotazione','Puoi annullare la tua prenotazione entro 24 ore direttamente dal tuo profilo utente','Is it possible to cancel a booking'),(3,'After completing the booking you will receive a confirmation email','Come ricevo la conferma della prenotazione','La conferma della prenotazione viene inviata via email all indirizzo registrato','How do I receive booking confirmation'),(4,'Yes it is possible to book for others by entering their details','Posso prenotare per un altra persona','Durante il processo di prenotazione puoi specificare i dati della persona per cui prenoti','Can I book for another person'),(5,'We accept credit debit cards and other digital payment methods','Quali metodi di pagamento sono accettati','Puoi pagare con carta di credito debito oppure con metodi digitali come PayPal','What payment methods are accepted');
/*!40000 ALTER TABLE `faq` ENABLE KEYS */;
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
