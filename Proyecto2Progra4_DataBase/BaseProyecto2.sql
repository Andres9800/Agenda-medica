CREATE DATABASE  IF NOT EXISTS `proyecto2progra4` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `proyecto2progra4`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: proyecto2progra4
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `administrador`
--

DROP TABLE IF EXISTS `administrador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrador` (
  `id_administrador` int NOT NULL AUTO_INCREMENT,
  `usuario` int NOT NULL,
  PRIMARY KEY (`id_administrador`),
  KEY `fk_usuario_administrador` (`usuario`),
  CONSTRAINT `fk_usuario_administrador` FOREIGN KEY (`usuario`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrador`
--

LOCK TABLES `administrador` WRITE;
/*!40000 ALTER TABLE `administrador` DISABLE KEYS */;
INSERT INTO `administrador` VALUES (1,6);
/*!40000 ALTER TABLE `administrador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `citas`
--

DROP TABLE IF EXISTS `citas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `citas` (
  `id_cita` int NOT NULL AUTO_INCREMENT,
  `estado` varchar(16) COLLATE utf8mb4_spanish_ci DEFAULT 'pendiente',
  `fecha` timestamp NULL DEFAULT NULL,
  `medico` int DEFAULT NULL,
  `paciente` int DEFAULT NULL,
  `signos` varchar(45) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `diagnostico` varchar(45) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `prescripciones` varchar(45) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `motivo` varchar(45) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id_cita`),
  KEY `fk_id_medico` (`medico`),
  KEY `fk_id_paciente` (`paciente`),
  CONSTRAINT `fk_id_medico` FOREIGN KEY (`medico`) REFERENCES `medicos` (`id_medico`),
  CONSTRAINT `fk_id_paciente` FOREIGN KEY (`paciente`) REFERENCES `pacientes` (`id_paciente`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `citas`
--

LOCK TABLES `citas` WRITE;
/*!40000 ALTER TABLE `citas` DISABLE KEYS */;
INSERT INTO `citas` VALUES (1,'terminada','2022-06-20 14:00:00',1,1,'200/200','Falta de sueno','dormir','Dolor de Cabeza'),(2,'terminada','2022-06-05 11:29:00',2,1,'multi','multi','multi','Dolor'),(4,'pendiente','2022-04-05 11:29:00',2,1,'40/80','Nada','Nada','Dolor de pecho'),(6,'pendiente','2022-06-21 16:00:00',1,4,'60/80','Diarrea','acetaminofen','Vomito');
/*!40000 ALTER TABLE `citas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `especialidades`
--

DROP TABLE IF EXISTS `especialidades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `especialidades` (
  `id_especialidad` int NOT NULL AUTO_INCREMENT,
  `especialidad` varchar(16) COLLATE utf8mb4_spanish_ci NOT NULL,
  PRIMARY KEY (`id_especialidad`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `especialidades`
--

LOCK TABLES `especialidades` WRITE;
/*!40000 ALTER TABLE `especialidades` DISABLE KEYS */;
INSERT INTO `especialidades` VALUES (1,'Cardiologia11'),(2,'Pediatria'),(3,'Neurocirugia'),(4,'NINGUNA');
/*!40000 ALTER TABLE `especialidades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lugares`
--

DROP TABLE IF EXISTS `lugares`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lugares` (
  `id_lugares` int NOT NULL AUTO_INCREMENT,
  `lugar` varchar(16) COLLATE utf8mb4_spanish_ci NOT NULL,
  PRIMARY KEY (`id_lugares`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lugares`
--

LOCK TABLES `lugares` WRITE;
/*!40000 ALTER TABLE `lugares` DISABLE KEYS */;
INSERT INTO `lugares` VALUES (1,'Heredia'),(2,'Alajuela'),(3,'San Jose');
/*!40000 ALTER TABLE `lugares` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicos`
--

DROP TABLE IF EXISTS `medicos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medicos` (
  `id_medico` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `especialidad` varchar(45) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `costo` double DEFAULT NULL,
  `localidad` varchar(45) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `duracion` int DEFAULT NULL,
  `usuario` int DEFAULT NULL,
  PRIMARY KEY (`id_medico`),
  KEY `fk_usuario_medicos` (`usuario`),
  CONSTRAINT `fk_usuario_medicos` FOREIGN KEY (`usuario`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicos`
--

LOCK TABLES `medicos` WRITE;
/*!40000 ALTER TABLE `medicos` DISABLE KEYS */;
INSERT INTO `medicos` VALUES (1,'Pablo Mendez','Cardiologi',50000,'Heredia',20,7),(2,'María Brenes','Pediatria ',80000,'Alajuela ',30,8),(3,'Claudia Torres ','Neurocirugía  ',105000,'San Jose ',60,9);
/*!40000 ALTER TABLE `medicos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pacientes`
--

DROP TABLE IF EXISTS `pacientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pacientes` (
  `id_paciente` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) COLLATE utf8mb4_spanish_ci NOT NULL,
  `sexo` char(1) COLLATE utf8mb4_spanish_ci NOT NULL,
  `contactoEmer` varchar(45) COLLATE utf8mb4_spanish_ci DEFAULT 'NA',
  `enfermedades` varchar(45) COLLATE utf8mb4_spanish_ci DEFAULT 'NA',
  `alergias` varchar(45) COLLATE utf8mb4_spanish_ci DEFAULT 'NA',
  `cirugias` varchar(45) COLLATE utf8mb4_spanish_ci DEFAULT 'NA',
  `medico` int DEFAULT NULL,
  PRIMARY KEY (`id_paciente`),
  KEY `fk_medico_paciente` (`medico`),
  CONSTRAINT `fk_medico_paciente` FOREIGN KEY (`medico`) REFERENCES `medicos` (`id_medico`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pacientes`
--

LOCK TABLES `pacientes` WRITE;
/*!40000 ALTER TABLE `pacientes` DISABLE KEYS */;
INSERT INTO `pacientes` VALUES (1,'Jose Rojas','M','85878487','Leucemia','NA','Extracion de vesicula',1),(2,'Maria Perez','F','74757879','Varicela','polen','NA',2),(3,'Ricardo Murillo','M','NA','Gripe','mani','Extracion de muelas',3),(4,'Andres Picado','M','812856839','Vomito','anestesia','Extracion de pancreas',1),(5,'Paulina Sanchez','F','84818283','Diabetes','NA','NA',1),(6,'Gasparin','M','2222','Muerto','Polen','Frente',1);
/*!40000 ALTER TABLE `pacientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `slots`
--

DROP TABLE IF EXISTS `slots`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `slots` (
  `id_slots` int NOT NULL AUTO_INCREMENT,
  `hora_inicio` int DEFAULT NULL,
  `hora_fin` int DEFAULT NULL,
  `dia` int DEFAULT NULL,
  `medico` int DEFAULT NULL,
  PRIMARY KEY (`id_slots`),
  KEY `fk_medico` (`medico`),
  CONSTRAINT `fk_medico` FOREIGN KEY (`medico`) REFERENCES `medicos` (`id_medico`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `slots`
--

LOCK TABLES `slots` WRITE;
/*!40000 ALTER TABLE `slots` DISABLE KEYS */;
INSERT INTO `slots` VALUES (1,8,18,5,1),(2,7,16,1,2),(3,9,17,2,3),(15,8,14,1,1);
/*!40000 ALTER TABLE `slots` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id_usuario` int NOT NULL,
  `contrasena` varchar(45) COLLATE utf8mb4_spanish_ci NOT NULL,
  `rol` int DEFAULT NULL,
  `estado` varchar(16) COLLATE utf8mb4_spanish_ci DEFAULT 'pendiente',
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (6,'111',3,'pendiente'),(7,'111',1,'activo'),(8,'222',1,'pendiente'),(9,'333',1,'activo');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'proyecto2progra4'
--

--
-- Dumping routines for database 'proyecto2progra4'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-24 11:49:36
