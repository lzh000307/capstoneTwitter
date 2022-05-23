-- MySQL dump 10.13  Distrib 8.0.27, for macos11 (arm64)
--
-- Host: 127.0.0.1    Database: blog_mb
-- ------------------------------------------------------
-- Server version	8.0.27

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

--
-- Table structure for table `censorship`
--

DROP TABLE IF EXISTS `censorship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `censorship` (
  `id` int NOT NULL AUTO_INCREMENT,
  `word` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `censorship`
--

LOCK TABLES `censorship` WRITE;
/*!40000 ALTER TABLE `censorship` DISABLE KEYS */;
/*!40000 ALTER TABLE `censorship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `tweet_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `parent_comment_id` bigint DEFAULT NULL,
  `reply_user_id` bigint DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (9,'test','2022-05-18 18:10:21',23,1,-1,NULL,1000),(10,'test','2022-05-18 18:10:52',23,1,-1,NULL,1000),(11,'test','2022-05-18 18:12:48',23,1,-1,NULL,1000),(12,'test','2022-05-18 18:23:08',23,1,-1,NULL,1000),(13,'test32','2022-05-18 18:23:13',23,1,-1,NULL,1000),(14,'test','2022-05-18 18:23:16',23,1,-1,NULL,1000),(15,'test','2022-05-18 18:23:18',23,1,-1,NULL,1000),(16,'reply','2022-05-18 18:32:00',23,1,-1,NULL,1),(17,'test','2022-05-18 18:32:15',23,1,-1,NULL,1000),(18,'test','2022-05-18 18:33:28',23,1,-1,NULL,1000),(19,'test','2022-05-18 18:33:31',23,1,18,1,1000),(20,'test','2022-05-18 18:36:08',23,1,18,1,1000),(21,'testreply','2022-05-18 18:36:47',23,1,18,1,1000),(22,'test','2022-05-18 18:38:03',23,1,18,1,1000),(23,'reply','2022-05-18 18:39:17',23,1,18,1,1),(26,'reply to you ','2022-05-18 19:00:48',23,6,23,1,1),(27,'test 4','2022-05-18 19:01:27',23,6,-1,NULL,1),(29,'我tm直接回复','2022-05-18 19:04:18',23,6,18,1,1),(30,'tewstsdgs 富商大贾这是个','2022-05-19 18:40:56',23,1,14,1,1000),(31,'Good!','2022-05-20 14:44:03',33,8,-1,NULL,1),(32,'Nice','2022-05-20 14:44:06',33,8,31,8,1),(33,'1','2022-05-20 14:58:48',34,1,-1,NULL,1000),(34,'2','2022-05-20 14:58:50',34,1,33,1,1000),(35,'3','2022-05-20 14:58:52',34,1,33,1,1000),(36,'test comment','2022-05-21 18:49:23',47,1,-1,NULL,1000),(37,'test comment ','2022-05-21 18:49:27',47,1,36,1,1000),(38,'comment','2022-05-21 18:49:31',47,1,36,1,1000),(39,'test','2022-05-21 18:49:33',47,1,36,1,1000),(40,'comment','2022-05-21 18:49:36',47,1,-1,NULL,1000),(41,'test ','2022-05-21 18:49:38',47,1,40,1,1000),(42,'test comment','2022-05-21 18:49:42',47,1,40,1,1000),(43,'comment','2022-05-21 18:49:45',47,1,40,1,1000),(44,'test','2022-05-21 19:23:37',48,1,-1,NULL,1000),(45,'test','2022-05-21 19:31:01',51,1,-1,NULL,1000),(46,'test','2022-05-21 19:31:02',51,1,-1,NULL,1000),(47,'test','2022-05-21 19:31:27',46,1,-1,NULL,1000),(48,'test2','2022-05-21 19:31:29',46,1,-1,NULL,1000),(49,'comment','2022-05-21 19:31:32',46,1,-1,NULL,1000),(50,'testtest','2022-05-22 13:01:58',51,1,46,1,1000),(51,'tsetset','2022-05-22 13:02:00',51,1,46,1,1000),(52,'testset','2022-05-22 13:02:03',51,1,-1,NULL,1000),(53,'这个是回复','2022-05-23 00:03:12',46,1,49,1,1000),(54,'这个是回复的回复','2022-05-23 00:03:20',46,1,49,1,1000),(55,'这是第二个评论','2022-05-23 00:03:31',46,1,-1,NULL,1000),(56,'啦啦啦','2022-05-23 00:04:12',46,1,49,1,1000),(57,'回复你','2022-05-23 00:04:40',46,7,49,1,1),(58,'回复你','2022-05-23 00:04:47',46,1,49,7,1000),(59,'第三个评论','2022-05-23 00:04:53',46,7,-1,NULL,1);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `like`
--

DROP TABLE IF EXISTS `like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `like` (
  `user_id` bigint DEFAULT NULL,
  `tweet_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `like`
--

LOCK TABLES `like` WRITE;
/*!40000 ALTER TABLE `like` DISABLE KEYS */;
INSERT INTO `like` VALUES (1,19),(6,24),(1,32);
/*!40000 ALTER TABLE `like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_tag`
--

DROP TABLE IF EXISTS `t_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_tag` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_tag`
--

LOCK TABLES `t_tag` WRITE;
/*!40000 ALTER TABLE `t_tag` DISABLE KEYS */;
INSERT INTO `t_tag` VALUES (3,'前端'),(4,'后端'),(5,'springboot'),(6,'java'),(7,'中国'),(8,'美国'),(11,'偷着乐');
/*!40000 ALTER TABLE `t_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trend`
--

DROP TABLE IF EXISTS `trend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trend` (
  `tweet_id` bigint DEFAULT NULL,
  `tag_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trend`
--

LOCK TABLES `trend` WRITE;
/*!40000 ALTER TABLE `trend` DISABLE KEYS */;
INSERT INTO `trend` VALUES (23,3),(23,6),(23,7),(34,5),(34,6),(40,3),(20,7),(20,11),(20,8);
/*!40000 ALTER TABLE `trend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tweet`
--

DROP TABLE IF EXISTS `tweet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tweet` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `first_picture` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` int DEFAULT NULL,
  `views` int DEFAULT NULL,
  `likes` int NOT NULL DEFAULT '0',
  `commentable` int NOT NULL DEFAULT '0',
  `published` int NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `tag_ids` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tweet`
--

LOCK TABLES `tweet` WRITE;
/*!40000 ALTER TABLE `tweet` DISABLE KEYS */;
INSERT INTO `tweet` VALUES (1,'testitle','testcontent','https://wkphoto.cdn.bcebos.com/0df3d7ca7bcb0a46b664515b7b63f6246b60af0a.jpg',1000,4,0,1,0,'2022-05-18 12:16:01','2022-05-17 12:16:03',1,''),(15,'testsetset','美方的行为，无异于搬起石头砸自己的脚！','https://wkphoto.cdn.bcebos.com/0df3d7ca7bcb0a46b664515b7b63f6246b60af0a.jpg',1000,5,2,1,1,'2022-05-17 17:02:15','2022-05-17 17:02:15',1,''),(16,'test title','test content','https://wkphoto.cdn.bcebos.com/0df3d7ca7bcb0a46b664515b7b63f6246b60af0a.jpg',1000,4,2,1,1,'2022-05-17 17:08:26','2022-05-17 17:08:26',1,''),(17,'title wrarawrawr','content','https://wkphoto.cdn.bcebos.com/a8773912b31bb051c3b2394d267adab44bede0c5.jpg',1,2,2,1,1,'2022-05-17 17:36:09','2022-05-17 17:36:09',1,''),(19,'test','test 5 user(id=6)','https://wkphoto.cdn.bcebos.com/a8773912b31bb051c3b2394d267adab44bede0c5.jpg',1,15,3,0,1,'2022-05-17 18:56:09','2022-05-17 18:56:09',7,''),(20,'港府考慮禁用Telegram應用程式　影響十多萬香港用戶','消息人士表示，過去7個月，公署共發現7百多宗有關「起底」違規訊息，反映「起底」問題正死灰復燃，因為去年全年的有關「起底」個案才是8百多宗。更大問題的是，公署轉介Telegram公司，獲得的正面回應，愈來愈少，由以往百分百的回應率，降至兩三成，甚至是近期的零回應。','https://cdn.hk01.com/di/media/images/5306769/org/c4f58d428a3aca6a8e922b56feb685ee.JPG/wxJcmL2bCzOIzbbCDGyb7Qlbav5CnmBQwyhuKdkobik?v=w800',1000,1,0,1,0,'2022-05-17 21:36:05','2022-05-17 21:36:05',1,'7,11,8'),(22,'test tag','test tag','test',1000,45,2,1,1,'2022-05-17 23:44:14','2022-05-17 23:44:14',1,''),(23,'test tag','test tag','test tag',1000,227,2,1,1,'2022-05-18 11:54:48','2022-05-18 11:54:48',1,'3,6,7'),(25,'test img','test img','test img',1000,3,0,0,1,'2022-05-19 22:13:11','2022-05-19 22:13:11',1,''),(31,'test','test',NULL,1000,1,0,1,1,'2022-05-20 00:33:47','2022-05-20 00:33:47',1,''),(32,'test ','test',NULL,1000,8,1,1,1,'2022-05-20 14:18:06','2022-05-20 14:18:06',1,''),(33,'I was sign up a new account','It\'s great! ',NULL,1,2,0,1,1,'2022-05-20 14:43:55','2022-05-20 14:43:55',8,''),(34,'test','test',NULL,1000,35,0,1,1,'2022-05-20 14:58:40','2022-05-20 14:58:40',1,'5,6'),(35,'test','test',NULL,1000,1,0,1,1,'2022-05-20 15:45:19','2022-05-20 15:45:19',1,''),(36,'imgtest','imgtest','http://127.0.0.1:9000/twitter/Screen+Shot+2022-05-17+at+6_1653033736532.41',1000,5,0,1,1,'2022-05-20 16:02:17','2022-05-20 16:02:17',1,''),(37,'imgtest2','imgtest2','http://127.0.0.1:9000/twitter/Screen%20Shot%202022-05-17%20at%206_1653034243699.41',1000,186,0,1,1,'2022-05-20 16:10:44','2022-05-20 16:10:44',1,''),(38,'6imgs','6imgs','http://127.0.0.1:9000/twitter/Screen%20Shot%202022-05-20%20at%203_1653044623999.53',1000,0,0,1,1,'2022-05-20 19:03:44','2022-05-20 19:03:44',1,''),(39,'4 imgs','4 imgs','http://127.0.0.1:9000/twitter/Screen%20Shot%202022-05-20%20at%203_1653044667509.53',1000,3,0,1,1,'2022-05-20 19:04:28','2022-05-20 19:04:28',1,''),(40,'test','testtest','http://127.0.0.1:9000/twitter/1653118391838',1000,0,0,1,1,'2022-05-20 19:12:07','2022-05-20 19:12:07',1,'3'),(43,'间谍过家家 ED','锦织敦史',NULL,1000,5,0,1,1,'2022-05-21 12:39:44','2022-05-21 12:39:44',1,''),(44,'test','test','http://127.0.0.1:9000/twitter/1653118441989',1000,4,0,1,1,'2022-05-21 12:40:58','2022-05-21 12:40:58',1,''),(45,'间谍过家家ED','监督：锦织敦史',NULL,1000,3,0,1,1,'2022-05-21 12:44:37','2022-05-21 12:44:37',1,''),(46,'间谍过家家ED','锦织敦史','http://127.0.0.1:9000/twitter/ed_1653108355281.mp4',1000,22,0,1,1,'2022-05-21 12:45:55','2022-05-21 12:45:55',1,''),(47,'间谍过家家ed','test','http://127.0.0.1:9000/twitter/%e3%80%8eSPY%c3%97FAMILY%e3%80%8f%e3%82%a8%e3%83%b3%e3%83%86%e3%82%99%e3%82%a3%e3%83%b3%e3%82%af%e3%82%99%e4%b8%bb%e9%a1%8c%e6%ad%8c%e3%82%a2%e3%83%8b%e3%83%a1%e6%98%a0%e5%83%8f%ef%bc%88%e3%83%8e%e3%83%b3%e3%82%af%e3%83%ac%e3%82%b7%e3%82%99%e3%83%83%e3%83%88%ef%bc%89_1653108638652.mp4',1000,1,0,1,1,'2022-05-21 12:50:39','2022-05-21 12:50:39',1,'');
/*!40000 ALTER TABLE `tweet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tweet_img`
--

DROP TABLE IF EXISTS `tweet_img`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tweet_img` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tweet_id` bigint DEFAULT NULL,
  `img_url` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tweet_img`
--

LOCK TABLES `tweet_img` WRITE;
/*!40000 ALTER TABLE `tweet_img` DISABLE KEYS */;
INSERT INTO `tweet_img` VALUES (3,36,'http://127.0.0.1:9000/twitter/Screen+Shot+2022-05-18+at+12_1653033736627.59'),(4,37,'http://127.0.0.1:9000/twitter/Screen%20Shot%202022-05-17%20at%206_1653034243699.41'),(5,37,'http://127.0.0.1:9000/twitter/Screen%20Shot%202022-05-19%20at%2011_1653034243779.12'),(6,37,'http://127.0.0.1:9000/twitter/Screen%20Shot%202022-05-18%20at%2012_1653034243788.59'),(7,38,'http://127.0.0.1:9000/twitter/Screen%20Shot%202022-05-20%20at%203_1653044623999.53'),(8,38,'http://127.0.0.1:9000/twitter/Screen%20Shot%202022-05-19%20at%2011_1653044624077.12'),(9,38,'http://127.0.0.1:9000/twitter/Screen%20Shot%202022-05-18%20at%2012_1653044624087.59'),(10,38,'http://127.0.0.1:9000/twitter/Screen%20Shot%202022-05-17%20at%206_1653044624096.41'),(11,38,'http://127.0.0.1:9000/twitter/Screen%20Shot%202022-05-14%20at%209_1653044624101.54'),(12,38,'http://127.0.0.1:9000/twitter/Screen%20Shot%202022-05-12%20at%2012_1653044624108.44'),(13,39,'http://127.0.0.1:9000/twitter/Screen%20Shot%202022-05-20%20at%203_1653044667509.53'),(14,39,'http://127.0.0.1:9000/twitter/Screen%20Shot%202022-05-19%20at%2011_1653044667526.12'),(15,39,'http://127.0.0.1:9000/twitter/Screen%20Shot%202022-05-18%20at%2012_1653044667541.59'),(16,39,'http://127.0.0.1:9000/twitter/Screen%20Shot%202022-05-17%20at%206_1653044667552.41'),(18,41,'http://127.0.0.1:9000/twitter/image_2022-05-19_23-54-15_1653107776933.png'),(19,41,'http://127.0.0.1:9000/twitter/image_2022-05-20_15-59-26_1653107777021.png'),(20,41,'http://127.0.0.1:9000/twitter/image_2022-05-19_23-55-07_1653107777045.png'),(21,42,'http://127.0.0.1:9000/twitter/image_2022-05-20_16-58-08_1653107916370.png'),(22,43,'http://127.0.0.1:9000/twitter/image_2022-05-20_15-59-26_1653107983570.png'),(24,45,'http://127.0.0.1:9000/twitter/Screen%20Shot%202022-05-21%20at%2012_1653108280777.44'),(25,46,'http://127.0.0.1:9000/twitter/Screen%20Shot%202022-05-21%20at%2012_1653108280777.44'),(26,47,'http://127.0.0.1:9000/twitter/Screen%20Shot%202022-05-21%20at%2012_1653108638565.44'),(27,36,'http://127.0.0.1:9000/twitter/Screen+Shot+2022-05-17+at+6_1653033736532.41'),(28,37,'http://127.0.0.1:9000/twitter/Screen%20Shot%202022-05-17%20at%206_1653034243699.41'),(29,38,'http://127.0.0.1:9000/twitter/Screen%20Shot%202022-05-20%20at%203_1653044623999.53'),(30,39,'http://127.0.0.1:9000/twitter/Screen%20Shot%202022-05-20%20at%203_1653044667509.53'),(32,1,'https://wkphoto.cdn.bcebos.com/0df3d7ca7bcb0a46b664515b7b63f6246b60af0a.jpg'),(33,15,'https://wkphoto.cdn.bcebos.com/0df3d7ca7bcb0a46b664515b7b63f6246b60af0a.jpg'),(34,16,'https://wkphoto.cdn.bcebos.com/0df3d7ca7bcb0a46b664515b7b63f6246b60af0a.jpg'),(35,17,'https://wkphoto.cdn.bcebos.com/a8773912b31bb051c3b2394d267adab44bede0c5.jpg'),(36,19,'https://wkphoto.cdn.bcebos.com/a8773912b31bb051c3b2394d267adab44bede0c5.jpg'),(37,20,'https://cdn.hk01.com/di/media/images/5306769/org/c4f58d428a3aca6a8e922b56feb685ee.JPG/wxJcmL2bCzOIzbbCDGyb7Qlbav5CnmBQwyhuKdkobik?v=w800'),(38,40,'http://127.0.0.1:9000/twitter/Screen%20Shot%202022-05-21%20at%2012_1653118391692.44'),(39,40,'http://127.0.0.1:9000/twitter/Screen%20Shot%202022-05-20%20at%203_1653118391796.53'),(40,40,'http://127.0.0.1:9000/twitter/Screen%20Shot%202022-05-19%20at%2011_1653118391807.12'),(41,40,'http://127.0.0.1:9000/twitter/Screen%20Shot%202022-05-18%20at%2012_1653118391817.59'),(42,40,'http://127.0.0.1:9000/twitter/Screen%20Shot%202022-05-17%20at%206_1653118391824.41'),(44,44,'http://127.0.0.1:9000/twitter/Screen%20Shot%202022-05-21%20at%2012_1653118441899.44'),(45,44,'http://127.0.0.1:9000/twitter/Screen%20Shot%202022-05-20%20at%203_1653118441934.53'),(46,44,'http://127.0.0.1:9000/twitter/Screen%20Shot%202022-05-19%20at%2011_1653118441948.12'),(47,44,'http://127.0.0.1:9000/twitter/Screen%20Shot%202022-05-18%20at%2012_1653118441961.59'),(48,44,'http://127.0.0.1:9000/twitter/Screen%20Shot%202022-05-17%20at%206_1653118441970.41'),(49,44,'http://127.0.0.1:9000/twitter/Screen%20Shot%202022-05-14%20at%209_1653118441975.54'),(50,48,'http://127.0.0.1:9000/twitter/1653132205002'),(51,49,'http://127.0.0.1:9000/twitter/1653132436737'),(52,50,'http://127.0.0.1:9000/twitter/1653132540791'),(56,52,'http://127.0.0.1:9000/twitter/image_2022-05-19_23-54-15_1653150286741.png');
/*!40000 ALTER TABLE `tweet_img` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phone_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'test1_nn','test','098f6bcd4621d373cade4e832627b4f6','test@test.com','10086','https://wkphoto.cdn.bcebos.com/0df3d7ca7bcb0a46b664515b7b63f6246b60af0a.jpg',1000,'2022-05-16 16:52:23','2022-05-22 19:20:22'),(2,'test2','test2','test2','test2@test2.com',NULL,'https://wkphoto.cdn.bcebos.com/a8773912b31bb051c3b2394d267adab44bede0c5.jpg',1,'2022-05-17 18:53:43','2022-05-17 18:53:43'),(3,'test3','test3','20086','test2@test2.com',NULL,'https://wkphoto.cdn.bcebos.com/a8773912b31bb051c3b2394d267adab44bede0c5.jpg',1,'2022-05-17 18:53:43','2022-05-17 18:53:43'),(4,'test2','test2-set','test2','test2@test2.com','20087','https://wkphoto.cdn.bcebos.com/a8773912b31bb051c3b2394d267adab44bede0c5.jpg',1,'2022-05-17 18:53:43','2022-05-17 18:53:43'),(6,'test4','test4','098f6bcd4621d373cade4e832627b4f6','testmail','testphone','https://wkphoto.cdn.bcebos.com/a8773912b31bb051c3b2394d267adab44bede0c5.jpg',1,'2022-05-17 18:53:43','2022-05-17 18:53:43'),(7,'test500','test5','098f6bcd4621d373cade4e832627b4f6','linzh@live.us','110','http://127.0.0.1:9000/twitter/Screen%20Shot%202022-05-18%20at%2012_1653210337375.59',1,'2022-05-17 18:55:55','2022-05-22 17:05:37'),(8,'linzh_test0520','linzh_test0520','098f6bcd4621d373cade4e832627b4f6','test0520@linzh.live','10010','https://wkphoto.cdn.bcebos.com/a8773912b31bb051c3b2394d267adab44bede0c5.jpg',1,'2022-05-20 14:43:20','2022-05-20 14:43:20'),(9,'test233','test233','098f6bcd4621d373cade4e832627b4f6','testttt','testtt','https://wkphoto.cdn.bcebos.com/a8773912b31bb051c3b2394d267adab44bede0c5.jpg',1,'2022-05-22 19:21:11','2022-05-22 19:21:11'),(10,'setset','setset','112a711f766db446c2f517b794a1c04a','setset','','https://wkphoto.cdn.bcebos.com/a8773912b31bb051c3b2394d267adab44bede0c5.jpg',1,'2022-05-22 19:22:51','2022-05-22 19:22:51'),(11,'testcode','testcode','84680f162529fe9f37993a48f71400ae','testcode','testcode','https://wkphoto.cdn.bcebos.com/a8773912b31bb051c3b2394d267adab44bede0c5.jpg',1,'2022-05-22 19:48:13','2022-05-22 19:48:13');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_collection`
--

DROP TABLE IF EXISTS `user_collection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_collection` (
  `user_id` bigint DEFAULT NULL,
  `tweet_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_collection`
--

LOCK TABLES `user_collection` WRITE;
/*!40000 ALTER TABLE `user_collection` DISABLE KEYS */;
INSERT INTO `user_collection` VALUES (8,33),(1,47);
/*!40000 ALTER TABLE `user_collection` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-23 20:22:53
