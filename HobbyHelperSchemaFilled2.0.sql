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
-- Table structure for table `activities`
--

DROP TABLE IF EXISTS `activities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activities` (
  `activityID` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `category` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`activityID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activities`
--

LOCK TABLES `activities` WRITE;
/*!40000 ALTER TABLE `activities` DISABLE KEYS */;
INSERT INTO `activities` VALUES (1,'Baseball','5'),(2,'Basketball','5'),(3,'Bass fishing','3'),(4,'Clay Shooting','4'),(5,'Climbing','2'),(6,'Field Hockey','4'),(7,'Golf','5'),(8,'Ice Hockey','5'),(9,'Lacrosse','4'),(10,'Powerlifting','2'),(11,'Rowing','2'),(12,'Rugby','5'),(13,'Sailing','3'),(14,'Soccer','5'),(15,'Softball','5'),(16,'Swimming','3'),(17,'Team Handball','5'),(18,'Climbing','2'),(19,'Tennis','5'),(20,'Ultimate Frisbee','5'),(21,'Volleyball','5'),(22,'Waterpolo','5'),(23,'Waterski','3'),(24,'Wrestling','2');
/*!40000 ALTER TABLE `activities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groups`
--

DROP TABLE IF EXISTS `groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `groups` (
  `group_id` varchar(10) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `category` int DEFAULT NULL,
  `activity_id` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groups`
--

LOCK TABLES `groups` WRITE;
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
INSERT INTO `groups` VALUES ('G001','Baseball Club',0,'A001'),('G002','Basketball Club',0,'A002'),('G003','Bass Fishing Club',0,'A003'),('G004','Clay Shooting Club',0,'A004'),('G005','Climbing Club',0,'A005'),('G006','Cycling Club',0,'A006'),('G007','Field Hockey Club',0,'A007'),('G008','Golf Club',0,'A008'),('G009','Ice Hockey Club',0,'A009'),('G010','Lacrosse Club',0,'A010'),('G011','Powerlifting Club',0,'A011'),('G012','Rowing Club',0,'A012'),('G013','Rugby Club',0,'A013'),('G014','Sailing Club',0,'A014'),('G015','Soccer Club',0,'A015'),('G016','Softball Club',0,'A016'),('G017','Swimming Club',0,'A017'),('G018','Team Handball Club',0,'A018'),('G019','Tennis Club',0,'A019'),('G020','Ultimate Frisbee Club',0,'A020'),('G021','Volleyball Club',0,'A021'),('G022','Waterpolo Club',0,'A022'),('G023','Waterski Club',0,'A023'),('G024','Wrestling Club',0,'A024');
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_activity_data`
--

DROP TABLE IF EXISTS `user_activity_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_activity_data` (
  `user_id` int NOT NULL,
  `activity_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_activity_data`
--

LOCK TABLES `user_activity_data` WRITE;
/*!40000 ALTER TABLE `user_activity_data` DISABLE KEYS */;
INSERT INTO `user_activity_data` VALUES (2,1),(2,11),(2,19),(2,24);
/*!40000 ALTER TABLE `user_activity_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_group_data`
--

DROP TABLE IF EXISTS `user_group_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_group_data` (
  `user_id` int NOT NULL,
  `group_id` int DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_group_data`
--

LOCK TABLES `user_group_data` WRITE;
/*!40000 ALTER TABLE `user_group_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_group_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_info` (
  `user_id` int NOT NULL,
  `username` varchar(45) DEFAULT NULL,
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
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES (1,'','','',NULL,'',NULL,NULL),(2,'wyatt','Wyatt','Lem',NULL,'1234',NULL,NULL);
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-03 10:20:31
