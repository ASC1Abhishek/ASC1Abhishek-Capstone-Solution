-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ats
-- ------------------------------------------------------
-- Server version	8.4.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
DROP DATABASE IF EXISTS ATS;

CREATE DATABASE ATS;

USE ATS;
--
-- Table structure for table `airports`
--

DROP TABLE IF EXISTS `airports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `airports` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `airport_code` varchar(255) NOT NULL,
  `airport_name` varchar(255) NOT NULL,
  `country_code` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_4ekl2417ug6m1bm05a33a6r9i` (`airport_code`),
  UNIQUE KEY `UK_4ft4jts6m5glpq86lt8iavf8v` (`airport_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airports`
--

/*!40000 ALTER TABLE `airports` DISABLE KEYS */;
INSERT INTO `airports` VALUES (1,'A0001','John F. Kennedy International Airport','US'),(2,'A0002','John F. Kennedy Domestic Airport','US');
/*!40000 ALTER TABLE `airports` ENABLE KEYS */;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `id` varchar(255) NOT NULL,
  `arrival_date` varchar(255) NOT NULL,
  `arrival_time` varchar(255) NOT NULL,
  `booking_date` date NOT NULL,
  `departure_date` varchar(255) NOT NULL,
  `departure_time` varchar(255) NOT NULL,
  `passenger_name` varchar(255) NOT NULL,
  `seat_number` int NOT NULL,
  `ticket_cost` int NOT NULL,
  `total_amount` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES ('B0001','2024-08-24','10:00','2024-08-23','2024-08-24','12:00','Abhishek',10,100,200),('B0002','2024-08-24','10:00','2024-08-23','2024-08-24','12:00','Abhishek',11,100,200);
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;

--
-- Table structure for table `flight`
--

DROP TABLE IF EXISTS `flight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flight` (
  `id` varchar(255) NOT NULL,
  `arrival_airport` varchar(255) NOT NULL,
  `arrival_time` varchar(255) NOT NULL,
  `departure_airport` varchar(255) NOT NULL,
  `departure_time` varchar(255) NOT NULL,
  `flight_number` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight`
--

/*!40000 ALTER TABLE `flight` DISABLE KEYS */;
INSERT INTO `flight` VALUES ('F0001','LAX','2024-08-30T17:00:00','JFK','2024-08-30T14:30:00','FL123');
/*!40000 ALTER TABLE `flight` ENABLE KEYS */;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login` (
  `email` varchar(255) NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `password` varchar(255) NOT NULL,
  `account_locked` tinyint(1) NOT NULL,
  `failed_login_attempts` int NOT NULL,
  `is_admin` tinyint(1) NOT NULL,
  `is_logged_in` tinyint(1) DEFAULT '0',
  `account_locked_until` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`email`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES ('abhishek.1010@gmail.com',3,'abhishek1010',0,0,0,0,NULL),('ad@example.com',4,'Ad@123',0,0,0,0,NULL),('ad1@example.com',6,'Ad1@123',0,0,0,0,NULL),('admin@example.com',1,'Admin@123',1,3,1,0,NULL),('bawa@gmail.com',5,'bawaTheMattii',0,0,0,1,NULL),('user@example.com',2,'User@123',0,0,0,1,NULL);
/*!40000 ALTER TABLE `login` ENABLE KEYS */;

--
-- Table structure for table `passenger`
--

DROP TABLE IF EXISTS `passenger`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `passenger` (
  `id` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passenger`
--

/*!40000 ALTER TABLE `passenger` DISABLE KEYS */;
INSERT INTO `passenger` VALUES ('P0001','john.doe@example.com','John','Doe','+1234567890'),('P0002','abhishek.s@example.com','Abhishek','S','9345673660');
/*!40000 ALTER TABLE `passenger` ENABLE KEYS */;

--
-- Table structure for table `planes`
--

DROP TABLE IF EXISTS `planes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `planes` (
  `registration_number` varchar(255) NOT NULL,
  `image_path` varchar(255) NOT NULL,
  `model` varchar(255) NOT NULL,
  `plane_maker` varchar(255) NOT NULL,
  `seating_capacity` int NOT NULL,
  PRIMARY KEY (`registration_number`),
  UNIQUE KEY `UK_1xhy56dnlapmlgxa4ttx92yuc` (`plane_maker`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `planes`
--

/*!40000 ALTER TABLE `planes` DISABLE KEYS */;
INSERT INTO `planes` VALUES ('R0001','/images/boeing737.jpg','737','Boeing',150),('R0002','/images/EmiratesExp.jpg','1010','Emirates',150);
/*!40000 ALTER TABLE `planes` ENABLE KEYS */;

--
-- Dumping routines for database 'ats'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-26 14:11:49
