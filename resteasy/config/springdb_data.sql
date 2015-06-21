-- MySQL dump 10.13  Distrib 5.6.19, for debian-linux-gnu (i686)
--
-- Host: gibson    Database: springdb
-- ------------------------------------------------------
-- Server version	5.0.45

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Not dumping tablespaces as no INFORMATION_SCHEMA.FILES table on this server
--

--
-- Dumping data for table `tb_grape`
--

LOCK TABLES `tb_grape` WRITE;
/*!40000 ALTER TABLE `tb_grape` DISABLE KEYS */;
INSERT INTO `tb_grape` VALUES (2,'Grillo','WH'),(3,'Pinot Noir','RE'),(4,'Sangiovese','RE'),(5,'Tempranillo','RE');
/*!40000 ALTER TABLE `tb_grape` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tb_profile`
--

LOCK TABLES `tb_profile` WRITE;
/*!40000 ALTER TABLE `tb_profile` DISABLE KEYS */;
INSERT INTO `tb_profile` VALUES (1,'Admin');
/*!40000 ALTER TABLE `tb_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tb_synonym`
--

LOCK TABLES `tb_synonym` WRITE;
/*!40000 ALTER TABLE `tb_synonym` DISABLE KEYS */;
INSERT INTO `tb_synonym` VALUES (3,'Aragonez',5),(4,'Tinta Roriz',5),(5,'Pinot Nero',3);
/*!40000 ALTER TABLE `tb_synonym` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tb_user`
--

LOCK TABLES `tb_user` WRITE;
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` VALUES (2,'Alberto Pires','alberto','teste',1),(3,'Admin User','admin','teste',1);
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tb_wine`
--

LOCK TABLES `tb_wine` WRITE;
/*!40000 ALTER TABLE `tb_wine` DISABLE KEYS */;
INSERT INTO `tb_wine` VALUES (1,'La Planta',2011,'Spain',2),(3,'Pago Florentino',2011,'Spain',3),(8,'Rupestro',2010,'Italy',2),(9,'Marques de Montemor',2010,'Portugal',2),(58,'MyWine',2012,'Zvezda',2),(59,'MyWine 2',2011,'Portugal',2),(60,'teste',1111,'rrrr',3),(61,'Sol e Vento',2011,'',3);
/*!40000 ALTER TABLE `tb_wine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tb_wine_grape`
--

LOCK TABLES `tb_wine_grape` WRITE;
/*!40000 ALTER TABLE `tb_wine_grape` DISABLE KEYS */;
INSERT INTO `tb_wine_grape` VALUES (1,2),(61,2),(58,3),(59,3),(60,3),(60,4),(1,5),(58,5);
/*!40000 ALTER TABLE `tb_wine_grape` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tb_wine_synonym`
--

LOCK TABLES `tb_wine_synonym` WRITE;
/*!40000 ALTER TABLE `tb_wine_synonym` DISABLE KEYS */;
INSERT INTO `tb_wine_synonym` VALUES (9,3);
/*!40000 ALTER TABLE `tb_wine_synonym` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-09-08 21:07:44
