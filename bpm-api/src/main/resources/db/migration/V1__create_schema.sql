-- MySQL dump 10.13  Distrib 8.0.29, for Linux (x86_64)
--
-- Host: localhost    Database: BPM
-- ------------------------------------------------------
-- Server version	5.7.13-log

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
-- Table structure for table `PHASES`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS `PHASES` (
  `ID` int(11) NOT NULL,
  `CODE` varchar(20) NOT NULL,
  `DESCRIPTION` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PHASES`
--

LOCK TABLES `PHASES` WRITE;
/*!40000 ALTER TABLE `PHASES` DISABLE KEYS */;
INSERT IGNORE INTO `PHASES` SET `ID` = 1, `CODE` = 'INICIAL', `DESCRIPTION` = 'Fase inicial';
INSERT IGNORE INTO `PHASES` SET `ID` = 2, `CODE` = 'ANALISIS_TECNICO', `DESCRIPTION` = 'Fase de análisis técnico';
INSERT IGNORE INTO `PHASES` SET `ID` = 3, `CODE` = 'VALIDADO', `DESCRIPTION` = 'Fase de validación';
INSERT IGNORE INTO `PHASES` SET `ID` = 4, `CODE` = 'RECHAZADO', `DESCRIPTION` = 'Fase rechazado';
INSERT IGNORE INTO `PHASES` SET `ID` = 5, `CODE` = 'FINALIZADO', `DESCRIPTION` = 'Finalizado';
/*!40000 ALTER TABLE `PHASES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PHASE_INSTANCES`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS `PHASE_INSTANCES` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_PROCES` int(11) NOT NULL,
  `ID_PHASE` int(11) NOT NULL,
  `DATE` timestamp NOT NULL,
  `DATE_FINISHED` timestamp NULL DEFAULT NULL,
  `USER` varchar(20) NOT NULL,
  `USER_FINISHED` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `PHASE_INSTANCES_FK` (`ID_PROCES`),
  KEY `PHASE_INSTANCES_FK_1` (`ID_PHASE`),
  CONSTRAINT `PHASE_INSTANCES_FK` FOREIGN KEY (`ID_PROCES`) REFERENCES `PROCESS` (`ID`),
  CONSTRAINT `PHASE_INSTANCES_FK_1` FOREIGN KEY (`ID_PHASE`) REFERENCES `PHASES` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PHASE_INSTANCES`
--

LOCK TABLES `PHASE_INSTANCES` WRITE;
/*!40000 ALTER TABLE `PHASE_INSTANCES` DISABLE KEYS */;
/*!40000 ALTER TABLE `PHASE_INSTANCES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PROCESS`
--

DROP TABLE IF EXISTS `PROCESS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS `PROCESS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(100) NOT NULL,
  `DATE` timestamp NOT NULL,
  `ACTIVE` bit(1) DEFAULT b'0',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `PROCESS_CODE_IDX` (`CODE`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PROCESS`
--

LOCK TABLES `PROCESS` WRITE;
/*!40000 ALTER TABLE `PROCESS` DISABLE KEYS */;
/*!40000 ALTER TABLE `PROCESS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-10 18:08:44
