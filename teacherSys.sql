/*
SQLyog Trial v10.2 
MySQL - 5.1.68-community : Database - teachersys
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`teachersys` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `teachersys`;

/*Table structure for table `education` */

DROP TABLE IF EXISTS `education`;

CREATE TABLE `education` (
  `e_id` int(20) NOT NULL AUTO_INCREMENT,
  `e_name` varchar(100) NOT NULL COMMENT '项目名称',
  `e_title` varchar(500) DEFAULT NULL COMMENT '项目标题',
  `e_target` varchar(500) NOT NULL COMMENT '项目目标',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `start_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '开始时间',
  `end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '结束时间',
  PRIMARY KEY (`e_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `education` */

/*Table structure for table `reward` */

DROP TABLE IF EXISTS `reward`;

CREATE TABLE `reward` (
  `r_id` int(20) NOT NULL AUTO_INCREMENT,
  `r_name` varchar(100) NOT NULL COMMENT '获奖名称',
  `ranking` varchar(20) DEFAULT NULL COMMENT '排名',
  `form_achievements` varchar(20) NOT NULL COMMENT '成果形式',
  `win_categories` varchar(20) DEFAULT NULL COMMENT '获奖类别',
  `award_unit` varchar(100) DEFAULT NULL COMMENT '获奖单位',
  `award_time` timestamp NULL DEFAULT NULL COMMENT '获奖时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`r_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `reward` */

/*Table structure for table `teacher` */

DROP TABLE IF EXISTS `teacher`;

CREATE TABLE `teacher` (
  `t_id` int(20) NOT NULL AUTO_INCREMENT,
  `t_name` varchar(100) NOT NULL COMMENT '名字',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `authorlever` int(20) NOT NULL DEFAULT '2' COMMENT '权限级别',
  `school_year` int(20) NOT NULL DEFAULT '1' COMMENT '入校年份',
  `education` varchar(20) DEFAULT NULL COMMENT '学历',
  `title` varchar(20) DEFAULT NULL COMMENT '职称',
  `sex` int(10) NOT NULL DEFAULT '1' COMMENT '(1:男;2：女)',
  `native_place` varchar(100) DEFAULT NULL COMMENT '籍贯',
  `certificate_number` varchar(100) NOT NULL COMMENT '身份证号码',
  `image` varchar(200) DEFAULT NULL COMMENT '照片',
  `major` varchar(200) DEFAULT NULL COMMENT '专业',
  `graduate_school` varchar(200) DEFAULT NULL COMMENT '毕业学校',
  `teaching_research` varchar(200) DEFAULT NULL COMMENT '所属教研室',
  `job` varchar(200) DEFAULT NULL COMMENT '职务',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `teacher` */

insert  into `teacher`(`t_id`,`t_name`,`password`,`authorlever`,`school_year`,`education`,`title`,`sex`,`native_place`,`certificate_number`,`image`,`major`,`graduate_school`,`teaching_research`,`job`,`modify_time`) values (1,'admin','123456',1,1,'本科','教师',1,NULL,'430424',NULL,NULL,NULL,NULL,NULL,'2018-04-08 20:41:48');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
