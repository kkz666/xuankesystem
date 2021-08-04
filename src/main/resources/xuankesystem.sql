/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.24 : Database - xuankesystem
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`xuankesystem` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `xuankesystem`;

/*Table structure for table `curriculum` */

DROP TABLE IF EXISTS `curriculum`;

CREATE TABLE `curriculum` (
  `id` int NOT NULL AUTO_INCREMENT,
  `credit` int DEFAULT NULL,
  `t_name` varchar(32) DEFAULT NULL,
  `c_name` varchar(32) DEFAULT NULL,
  `limitnumber` int NOT NULL,
  `detailtime` varchar(32) NOT NULL,
  `currentnumber` int NOT NULL DEFAULT '0',
  `starttime` date NOT NULL,
  `endtime` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `curriculum` */

insert  into `curriculum`(`id`,`credit`,`t_name`,`c_name`,`limitnumber`,`detailtime`,`currentnumber`,`starttime`,`endtime`) values (1,5,'老师一号','高等数学',150,'第八九节',74,'2021-08-01','2021-08-15'),(4,1,'测试数据一','测试数据一',12,'第一二节',0,'2021-08-15','2021-08-22'),(5,21,'测试数据二','测试数据二',4,'第十一节课',1,'2021-08-05','2021-08-20'),(6,2,'今晚打卢克','测试数据三',421,'第六七节课',21,'2021-08-19','2021-08-28'),(7,19,'就离开健康了','历史',19,'第一二节',1,'2021-05-13','2021-11-26'),(9,9,'如今我看了','解开了',94,'第十一节课',29,'2021-08-06','2021-08-07'),(10,10,'留时间','测试样例',18,'第一二节',2,'2021-08-19','2021-09-04'),(11,3,'端午节埃里克','就索拉卡',213,'第五六节课',0,'2021-08-06','2021-08-29'),(12,21,'读完了','特价了',214,'第八九节课',21,'2021-07-26','2021-07-26'),(13,4,'端午快乐','打击我',421,'第五六节课',421,'2021-08-27','2021-08-28');

/*Table structure for table `stu_cur` */

DROP TABLE IF EXISTS `stu_cur`;

CREATE TABLE `stu_cur` (
  `s_id` int DEFAULT NULL,
  `c_id` int DEFAULT NULL,
  KEY `s_id` (`s_id`),
  KEY `c_id` (`c_id`),
  CONSTRAINT `stu_cur_ibfk_1` FOREIGN KEY (`s_id`) REFERENCES `user` (`id`),
  CONSTRAINT `stu_cur_ibfk_2` FOREIGN KEY (`c_id`) REFERENCES `curriculum` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `stu_cur` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `name` varchar(32) DEFAULT NULL,
  `xuehao` varchar(32) NOT NULL,
  `zhuanye` varchar(32) NOT NULL,
  `banji` varchar(32) NOT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `admin` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`name`,`xuehao`,`zhuanye`,`banji`,`sex`,`admin`) values (1,'kkkkzzzz','123456789','凯凯张','312999','计算机','三班','男',1),(4,'zzzzkkkk','123456789','张凯凯','165165165','计算机类','八班','男',0),(49,'dwadawdaw','132456789','刘晓庆','402194','小傲娇','的境外哦','女',0),(52,'jdkslajdk','djwalkjdk','测试数据一号','490128410','测试数据一号','测试数据一班','男',0),(54,'xxxxxxxx','123456789','测试数据','32154656000','测试数据','测试数据','女',0),(55,'qqqqqqqqqqqqqqqqq','123456789','删除测试数据','12313132','极大克里斯','端午节埃里克','男',0),(56,'asdasdas','123456789','删除测试数据','12313132','极大克里斯','端午节埃里克','男',0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
