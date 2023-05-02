CREATE DATABASE  IF NOT EXISTS `hobbyhelper` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `hobbyhelper`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: hobbyhelper
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `activites`
--

DROP TABLE IF EXISTS `activites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activites` (
  `activityID` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `category` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`activityID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
ALTER TABLE `activites` MODIFY `activityID` varchar(10);
INSERT INTO `activites` (`activityID`, `name`, `category`)
VALUES
('A001', 'Baseball', ''),
('A002', 'Basketball', ''),
('A003', 'Bass fishing', ''),
('A004', 'Clay Shooting', ''),
('A005', 'Climbing', ''),
('A006', 'Field Hockey', ''),
('A007', 'Golf', ' '),
('A008', 'Ice Hockey', ''),
('A009', 'Lacrosse', ''),
('A010', 'Powerlifting', ''),
('A011', 'Rowing', ''),
('A012', 'Rugby', ''),
('A013', 'Sailing', ''),
('A014', 'Soccer', ''),
('A015', 'Softball', ''),
('A016', 'Swimming', ''),
('A017', 'Team Handball', ''),
('A018', 'Climbing', ''),
('A019', 'Tennis', ''),
('A020', 'Ultimate Frisbee', ''),
('A021', 'Volleyball', ''),
('A022', 'Waterpolo', ''),
('A023', 'Waterski', ''),
('A024', 'Wrestling', '');


--
-- Dumping data for table `activites`
--

LOCK TABLES `activites` WRITE;
/*!40000 ALTER TABLE `activites` DISABLE KEYS */;
/*!40000 ALTER TABLE `activites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groups`
--

DROP TABLE IF EXISTS `groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `groups` (
  `group_id` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `category` int DEFAULT NULL,
  `activity_id` int DEFAULT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
ALTER TABLE `groups` MODIFY `group_id` varchar(10);
ALTER TABLE `groups` MODIFY `activity_id` varchar(10);
INSERT INTO `groups` (`group_id`, `name`, `category`, `activity_id`) 
VALUES 
('G001', 'Baseball Club','', 'A001'), 
('G002', 'Basketball Club','', 'A002'),
('G003', 'Bass Fishing Club','', 'A003'),
('G004', 'Clay Shooting Club','', 'A004'),
('G005', 'Climbing Club','', 'A005'),
('G006', 'Cycling Club','', 'A006'),
('G007', 'Field Hockey Club','', 'A007'),
('G008', 'Golf Club','', 'A008'),
('G009', 'Ice Hockey Club','', 'A009'),
('G010', 'Lacrosse Club','', 'A010'),
('G011', 'Powerlifting Club','', 'A011'),
('G012', 'Rowing Club','', 'A012'),
('G013', 'Rugby Club','', 'A013'),
('G014', 'Sailing Club','', 'A014'),
('G015', 'Soccer Club','', 'A015'),
('G016', 'Softball Club','', 'A016'),
('G017', 'Swimming Club','', 'A017'),
('G018', 'Team Handball Club','', 'A018'),
('G019', 'Tennis Club','', 'A019'),
('G020', 'Ultimate Frisbee Club','', 'A020'),
('G021', 'Volleyball Club','', 'A021'),
('G022', 'Waterpolo Club','', 'A022'),
('G023', 'Waterski Club','', 'A023'),
('G024', 'Wrestling Club', '', 'A024');
--
-- Dumping data for table `groups`
--

LOCK TABLES `groups` WRITE;
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_data`
--

DROP TABLE IF EXISTS `user_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_data` (
  `user_id` int NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `activites` int DEFAULT NULL,
  `groups` int DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_data`
--

LOCK TABLES `user_data` WRITE;
/*!40000 ALTER TABLE `user_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_data` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-02 13:04:07
