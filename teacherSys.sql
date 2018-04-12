/*
SQLyog Trial v10.2 
MySQL - 5.7.19-log : Database - teachersys
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

/*Table structure for table `author` */

DROP TABLE IF EXISTS `author`;

CREATE TABLE `author` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `author_name` varchar(100) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `author` */

insert  into `author`(`id`,`author_name`,`description`,`modify_time`) values (1,'管理员','管理员权限','2018-03-07 14:39:40'),(2,'用户','用户权限','2018-03-07 14:39:55');

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

/*Table structure for table `task_company` */

DROP TABLE IF EXISTS `task_company`;

CREATE TABLE `task_company` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `t_id` int(20) NOT NULL COMMENT '教师ID',
  `name` varchar(100) NOT NULL COMMENT '企业名称',
  `phone` varchar(100) NOT NULL COMMENT '电话',
  `place` varchar(500) DEFAULT NULL COMMENT '地址',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `task_company` */

/*Table structure for table `task_directortournament` */

DROP TABLE IF EXISTS `task_directortournament`;

CREATE TABLE `task_directortournament` (
  `d_id` int(20) NOT NULL AUTO_INCREMENT,
  `t_id` int(20) NOT NULL COMMENT '教师Id',
  `t_name` varchar(100) NOT NULL COMMENT '指导老师姓名',
  `student_name` varchar(100) NOT NULL COMMENT '学生姓名',
  `project_name` varchar(100) NOT NULL COMMENT '项目名字',
  `attach` varchar(500) DEFAULT NULL COMMENT '获奖荣誉附件',
  `time` timestamp NULL DEFAULT NULL COMMENT '获奖时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`d_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `task_directortournament` */

/*Table structure for table `task_graduation` */

DROP TABLE IF EXISTS `task_graduation`;

CREATE TABLE `task_graduation` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `t_id` int(20) NOT NULL COMMENT '教师ID',
  `name` varchar(100) NOT NULL COMMENT '设计或者论文名称',
  `student_name` varchar(100) NOT NULL COMMENT '学生姓名',
  `is_public` varchar(10) NOT NULL DEFAULT '2' COMMENT '是否发布（1发布，2未发布）',
  `publication_name` varchar(100) DEFAULT NULL COMMENT '发表的刊物名称',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `task_graduation` */

/*Table structure for table `task_resources` */

DROP TABLE IF EXISTS `task_resources`;

CREATE TABLE `task_resources` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `t_id` int(20) NOT NULL COMMENT '教师ID',
  `name` varchar(100) DEFAULT NULL COMMENT '课程名称',
  `type` varchar(100) DEFAULT NULL COMMENT '资源类型',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `task_resources` */

/*Table structure for table `task_teaching` */

DROP TABLE IF EXISTS `task_teaching`;

CREATE TABLE `task_teaching` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `t_id` int(20) NOT NULL COMMENT '教师ID',
  `name` varchar(200) NOT NULL COMMENT '课程名称',
  `code` varchar(100) DEFAULT NULL COMMENT '编码',
  `major` varchar(100) DEFAULT NULL COMMENT '所属专业',
  `property` varchar(100) DEFAULT NULL COMMENT '课程所属性质',
  `hour` varchar(100) DEFAULT NULL COMMENT '课时',
  `count_man` int(20) DEFAULT NULL COMMENT '人数',
  `assessment_method` varchar(100) DEFAULT NULL COMMENT '考核方式',
  `teaching_subject` varchar(100) DEFAULT NULL COMMENT '所属教学改革课题',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

/*Data for the table `task_teaching` */

insert  into `task_teaching`(`id`,`t_id`,`name`,`code`,`major`,`property`,`hour`,`count_man`,`assessment_method`,`teaching_subject`,`modify_time`) values (14,1,'课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:34'),(15,1,'课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:34'),(16,1,'课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:34'),(17,1,'课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:35'),(18,1,'课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:35'),(19,1,'课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:35'),(20,1,'课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:35'),(21,1,'课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:35'),(22,1,'课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:35'),(23,1,'课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:35'),(24,1,'课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:40'),(25,1,'课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:40'),(26,1,'课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:40'),(27,1,'课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:40'),(28,1,'课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:41'),(29,1,'课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:41'),(30,1,'课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:41'),(31,1,'课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:41'),(32,1,'课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:41'),(33,1,'课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:41'),(34,1,'课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:41'),(35,1,'课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:41'),(36,1,'课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:42'),(37,1,'课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:42'),(38,1,'课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:42'),(39,1,'课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:42'),(40,1,'课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:42'),(41,1,'课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:42'),(42,1,'课程42','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:42');

/*Table structure for table `task_tutor` */

DROP TABLE IF EXISTS `task_tutor`;

CREATE TABLE `task_tutor` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `t_id` int(20) NOT NULL COMMENT '教师ID',
  `t_name` varchar(100) NOT NULL COMMENT '教师姓名',
  `student_name` varchar(100) NOT NULL COMMENT '学生姓名',
  `class` varchar(100) DEFAULT NULL COMMENT '班级',
  `major` varchar(100) DEFAULT NULL COMMENT '学生专业',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `task_tutor` */

/*Table structure for table `teacher` */

DROP TABLE IF EXISTS `teacher`;

CREATE TABLE `teacher` (
  `t_id` int(20) NOT NULL AUTO_INCREMENT,
  `t_name` varchar(100) NOT NULL COMMENT '名字',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `authorlever` int(20) DEFAULT '2' COMMENT '权限级别',
  `school_year` int(20) DEFAULT '1' COMMENT '入校年份',
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `teacher` */

insert  into `teacher`(`t_id`,`t_name`,`password`,`authorlever`,`school_year`,`education`,`title`,`sex`,`native_place`,`certificate_number`,`image`,`major`,`graduate_school`,`teaching_research`,`job`,`modify_time`) values (1,'admin','123',1,3,'硕士','教师',1,NULL,'430424',NULL,'电子商务','湖南人文科技学院',NULL,NULL,'2018-04-11 17:36:48'),(2,'qinye','123',2,1123,NULL,NULL,1,NULL,'123',NULL,NULL,NULL,NULL,NULL,'2018-04-10 11:44:58'),(3,'qinye','123',2,12,NULL,NULL,1,NULL,'123',NULL,NULL,NULL,NULL,NULL,'2018-04-10 11:55:30'),(4,'qinye','123',2,12,NULL,NULL,1,NULL,'123',NULL,NULL,NULL,NULL,NULL,'2018-04-10 11:57:28'),(5,'qinye','123',2,12,NULL,NULL,1,NULL,'123',NULL,NULL,NULL,NULL,NULL,'2018-04-10 11:59:02'),(6,'qinye','123',2,333,NULL,NULL,1,NULL,'3333',NULL,NULL,NULL,NULL,NULL,'2018-04-10 11:59:46');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
