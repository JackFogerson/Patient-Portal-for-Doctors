-- MySQL dump 10.13  Distrib 8.0.18, for macos10.14 (x86_64)
--
-- Host: 127.0.0.1    Database: eTRTSchema
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `Patients`
--

DROP TABLE IF EXISTS `Patients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Patients` (
  `THCNumber` int(11) NOT NULL,
  `Date` date NOT NULL,
  `FirstName` varchar(45) NOT NULL,
  `MiddleName` varchar(45) DEFAULT NULL,
  `LastName` varchar(45) NOT NULL,
  `DOB` date NOT NULL,
  `Gender` varchar(6) NOT NULL,
  `Phone` varchar(11) NOT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `StreetAddress` varchar(45) NOT NULL,
  `City` varchar(45) NOT NULL,
  `State` varchar(45) DEFAULT NULL,
  `Zip` int(11) NOT NULL,
  `Country` varchar(45) NOT NULL,
  `Photo` longblob,
  `SSID` varchar(45) DEFAULT NULL,
  `Insurance` varchar(45) DEFAULT NULL,
  `Occupation` varchar(45) DEFAULT NULL,
  `WorkStatus` varchar(45) DEFAULT NULL,
  `EducationalDegree` varchar(45) DEFAULT NULL,
  `TOnset` varchar(45) DEFAULT NULL,
  `TEtiology` varchar(45) DEFAULT NULL,
  `HOnset` varchar(45) DEFAULT NULL,
  `HEtiology` varchar(45) DEFAULT NULL,
  `Comments` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`THCNumber`),
  UNIQUE KEY `THCNumber_UNIQUE` (`THCNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Patients`
--

LOCK TABLES `Patients` WRITE;
/*!40000 ALTER TABLE `Patients` DISABLE KEYS */;
INSERT INTO `Patients` VALUES (0,'2019-11-10','Test',NULL,'Test','2000-01-01','Male','5555555555',NULL,'123 Test','Austin',NULL,95112,'United States of America',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `Patients` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-11 15:48:20
