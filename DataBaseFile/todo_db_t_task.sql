-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: todo_db
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
-- Table structure for table `t_task`
--

DROP TABLE IF EXISTS `t_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_task` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `content` varchar(100) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `complete_date` date DEFAULT NULL,
  `delete_flg` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_task`
--

LOCK TABLES `t_task` WRITE;
/*!40000 ALTER TABLE `t_task` DISABLE KEYS */;
INSERT INTO `t_task` VALUES (1,NULL,'おおおおおおおお','2022-04-11','2023-02-02',NULL,0),(2,NULL,'sagreb','2022-04-11','2222-02-02',NULL,0),(11,2,'test1を実施するあ','2019-01-30','2019-01-31','2022-04-21',0),(12,2,'test2の結果を報告する','2019-01-30','2019-01-30','2022-05-02',0),(13,3,'test3はどうなっているのか尋ねる','2019-01-30','2019-01-30','2022-04-21',0),(14,2,'100文字チェックあああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ','2019-01-30','2022-02-22','2022-04-01',0),(15,3,'test3はどうなっているのか尋ねる','2019-01-30','2018-01-30','2022-05-02',0),(16,1,'登録内容をここに入力します',NULL,'2222-02-22','2022-03-21',1),(17,1,'登録内容をここに入力します2',NULL,'2000-02-02','2022-03-25',1),(18,3,'登録内容をここに入力します3',NULL,'2000-02-22','2022-03-30',1),(19,2,'登録内容をここに入力します4',NULL,'2222-02-22','2022-04-05',1),(20,1,'登録内容をここに入力します5',NULL,'2022-05-05','2022-04-05',1),(21,2,'登録内容をここに入力します6','2022-03-04','2222-02-22',NULL,0),(22,1,'あｇ','2022-03-04','2000-02-02','2022-03-30',1),(23,1,'あｇdfbdx','2022-03-04','2000-02-22','2022-03-25',1),(24,2,'いいいいいい','2022-03-05','2023-02-23',NULL,0),(25,1,'aes','2022-03-05','2222-02-22','2022-05-06',1),(26,3,'ててて','2022-03-16','2022-05-04','2022-05-06',0),(27,3,'ててて1','2022-03-16','2022-06-05','2022-03-25',0),(28,1,'ｚｄｔんｄｔｎ','2022-03-17','2023-01-01','2022-04-05',1),(29,3,'てててugooooooooooooooooo','2022-03-18','2222-02-02',NULL,0),(30,3,'fxtjsrt','2022-03-21','2023-02-22','2022-03-21',1),(31,3,'csRgah','2022-03-22','2023-02-02',NULL,0),(32,1,'fmf','2022-03-23','2023-02-02','2022-04-21',1),(33,1,'ztdjsfdjts','2022-03-24','2025-02-08','2022-05-06',0),(34,1,'argahgrq','2022-03-24','2222-02-02',NULL,1),(35,1,'wwwwwwwwwwwwww','2022-03-24','2023-02-02','2022-03-25',1),(36,1,'Ｒはえ','2022-03-31','3000-03-03',NULL,1),(38,1,'テストテスト','2022-04-01','2222-02-02','2022-04-01',0),(39,3,'テストテストテスト','2022-04-01','2222-02-02','2022-04-01',0),(40,2,'テストテストテストテスト','2022-04-01','3333-03-03','2022-04-01',0),(41,1,'あああ','2022-04-08','3333-02-02',NULL,1),(42,2,'いいいいいいいいいいいいいいい','2022-04-11','2023-02-02',NULL,0),(43,2,'ｚttttttttttttt','2022-05-06','2222-02-02','2022-05-06',0);
/*!40000 ALTER TABLE `t_task` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-09 10:08:54
