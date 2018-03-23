/*
SQLyog Trial v10.2 
MySQL - 5.7.19-log : Database - mysql
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mysql` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `mysql`;

/*Table structure for table `t_author` */

DROP TABLE IF EXISTS `t_author`;

CREATE TABLE `t_author` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `author_name` varchar(100) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `t_author` */

insert  into `t_author`(`id`,`author_name`,`description`,`modify_time`) values (1,'管理员','管理员权限','2018-03-07 14:39:40'),(2,'用户','用户权限','2018-03-07 14:39:55');

/*Table structure for table `teacher` */

DROP TABLE IF EXISTS `teacher`;

CREATE TABLE `teacher` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `authorlever` int(50) NOT NULL DEFAULT '2',
  `email` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `teacher` */

insert  into `teacher`(`id`,`username`,`password`,`authorlever`,`email`) values (1,'admin','123456',1,'1173259939@qq.com'),(2,'yeqin','123456',2,'1173259939@qq.com');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
