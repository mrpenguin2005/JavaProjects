-- MySQL dump 10.13  Distrib 5.5.43, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: springdb
-- ------------------------------------------------------
-- Server version	5.5.43-0ubuntu0.14.04.1

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
-- Table structure for table `tb_grape`
--

DROP TABLE IF EXISTS `tb_grape`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_grape` (
  `id_grape` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `color` varchar(2) NOT NULL,
  PRIMARY KEY (`id_grape`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_profile`
--

DROP TABLE IF EXISTS `tb_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_profile` (
  `id_profile` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_profile`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_synonym`
--

DROP TABLE IF EXISTS `tb_synonym`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_synonym` (
  `id_synonym` bigint(20) NOT NULL AUTO_INCREMENT,
  `synonym` varchar(45) NOT NULL,
  `id_grape` bigint(20) NOT NULL,
  PRIMARY KEY (`id_synonym`),
  UNIQUE KEY `synonym_UNIQUE` (`synonym`),
  KEY `fk_tb_synonym_tb_grape1` (`id_grape`),
  CONSTRAINT `fk_tb_synonym_tb_grape1` FOREIGN KEY (`id_grape`) REFERENCES `tb_grape` (`id_grape`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_teste`
--

DROP TABLE IF EXISTS `tb_teste`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_teste` (
  `id_teste` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id_teste`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_user`
--

DROP TABLE IF EXISTS `tb_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_user` (
  `id_user` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `id_profile` bigint(20) NOT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  KEY `fk_tb_user_tb_profile1` (`id_profile`),
  CONSTRAINT `fk_tb_user_tb_profile1` FOREIGN KEY (`id_profile`) REFERENCES `tb_profile` (`id_profile`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_wine`
--

DROP TABLE IF EXISTS `tb_wine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_wine` (
  `id_wine` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `vintage` int(11) NOT NULL,
  `country` varchar(45) NOT NULL,
  `id_user` bigint(20) NOT NULL,
  PRIMARY KEY (`id_wine`),
  UNIQUE KEY `id_UNIQUE` (`id_wine`),
  KEY `fk_tb_wine_tb_user1` (`id_user`),
  CONSTRAINT `fk_tb_wine_tb_user1` FOREIGN KEY (`id_user`) REFERENCES `tb_user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_wine_grape`
--

DROP TABLE IF EXISTS `tb_wine_grape`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_wine_grape` (
  `id_wine` bigint(20) NOT NULL,
  `id_grape` bigint(20) NOT NULL,
  PRIMARY KEY (`id_wine`,`id_grape`),
  KEY `fk_tb_wine_has_tb_grape_tb_grape1` (`id_grape`),
  KEY `fk_tb_wine_has_tb_grape_tb_wine1` (`id_wine`),
  CONSTRAINT `fk_tb_wine_has_tb_grape_tb_grape1` FOREIGN KEY (`id_grape`) REFERENCES `tb_grape` (`id_grape`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_wine_has_tb_grape_tb_wine1` FOREIGN KEY (`id_wine`) REFERENCES `tb_wine` (`id_wine`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_wine_synonym`
--

DROP TABLE IF EXISTS `tb_wine_synonym`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_wine_synonym` (
  `id_wine` bigint(20) NOT NULL,
  `id_synonym` bigint(20) NOT NULL,
  PRIMARY KEY (`id_wine`,`id_synonym`),
  KEY `fk_tb_synonym_has_tb_wine_tb_wine1` (`id_wine`),
  KEY `fk_tb_synonym_has_tb_wine_tb_synonym1` (`id_synonym`),
  CONSTRAINT `fk_tb_synonym_has_tb_wine_tb_synonym1` FOREIGN KEY (`id_synonym`) REFERENCES `tb_synonym` (`id_synonym`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_synonym_has_tb_wine_tb_wine1` FOREIGN KEY (`id_wine`) REFERENCES `tb_wine` (`id_wine`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-06-26 13:36:42
