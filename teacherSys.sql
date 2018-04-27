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

/*Table structure for table `project_lecture` */

DROP TABLE IF EXISTS `project_lecture`;

CREATE TABLE `project_lecture` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `t_id` int(20) NOT NULL COMMENT '教师ID',
  `t_name` varchar(100) DEFAULT NULL COMMENT '教师名字',
  `name` varchar(200) NOT NULL COMMENT '学术讲座名称',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '学术讲座时间',
  `academics` varchar(200) DEFAULT NULL COMMENT '学术讲座人员',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `project_lecture` */

insert  into `project_lecture`(`id`,`t_id`,`t_name`,`name`,`time`,`academics`,`modify_time`) values (1,1,'admin','红楼梦','2018-04-10 00:00:00','夜勤','2018-04-17 15:03:20'),(2,2,'qinye','1123213','2018-04-03 00:00:00','111','2018-04-17 19:52:43');

/*Table structure for table `project_person` */

DROP TABLE IF EXISTS `project_person`;

CREATE TABLE `project_person` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `t_id` int(20) NOT NULL COMMENT '教师ID',
  `t_name` varchar(100) DEFAULT NULL COMMENT '教师姓名',
  `name` varchar(200) NOT NULL COMMENT '人才工程名称',
  `student_name` varchar(100) NOT NULL COMMENT '姓名',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '人才工程开始时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `project_person` */

insert  into `project_person`(`id`,`t_id`,`t_name`,`name`,`student_name`,`time`,`modify_time`) values (1,1,'admin','3333','2121','2018-04-10 00:00:00','2018-04-17 10:06:56'),(2,1,'admin','1111','1111','2018-04-18 00:00:00','2018-04-17 10:07:06'),(3,2,'qinye','2222','2222','2018-04-12 00:00:00','2018-04-17 10:34:50');

/*Table structure for table `project_publish` */

DROP TABLE IF EXISTS `project_publish`;

CREATE TABLE `project_publish` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `t_id` int(20) NOT NULL COMMENT '教师ID',
  `t_name` varchar(100) DEFAULT NULL COMMENT '教师姓名',
  `name` varchar(200) NOT NULL COMMENT '刊物名称',
  `press_company` varchar(200) DEFAULT NULL COMMENT '发表商',
  `publish_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '发表时间',
  `issn` varchar(100) NOT NULL COMMENT '出版物编号',
  `type` varchar(10) NOT NULL COMMENT '(1:是论文；2：著作教材)',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `project_publish` */

insert  into `project_publish`(`id`,`t_id`,`t_name`,`name`,`press_company`,`publish_time`,`issn`,`type`,`modify_time`) values (1,1,'admin','123','123213','2018-04-16 00:00:00','32132','1','2018-04-24 11:24:20'),(2,2,'qinye','中国地理','知网','2018-04-02 00:00:00','111111','2','2018-04-19 09:55:59');

/*Table structure for table `project_socialservice` */

DROP TABLE IF EXISTS `project_socialservice`;

CREATE TABLE `project_socialservice` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `t_id` int(20) NOT NULL COMMENT '教师ID',
  `t_name` varchar(100) DEFAULT NULL COMMENT '教师姓名',
  `company_name` varchar(200) NOT NULL COMMENT '服务企业名称或部门',
  `member` varchar(500) DEFAULT NULL COMMENT '成员',
  `name` varchar(200) NOT NULL COMMENT '项目名称',
  `service_time` int(10) DEFAULT NULL COMMENT '服务时限',
  `money` int(20) DEFAULT NULL COMMENT '经费',
  `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '立项时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `project_socialservice` */

insert  into `project_socialservice`(`id`,`t_id`,`t_name`,`company_name`,`member`,`name`,`service_time`,`money`,`start_time`,`modify_time`) values (2,1,'admin','555','12312','31231233123',1231,2312,'2018-04-17 00:00:00','2018-04-17 14:23:33');

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
  `t_name` varchar(100) DEFAULT NULL,
  `name` varchar(100) NOT NULL COMMENT '企业名称',
  `phone` varchar(100) NOT NULL COMMENT '电话',
  `place` varchar(500) DEFAULT NULL COMMENT '地址',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `task_company` */

insert  into `task_company`(`id`,`t_id`,`t_name`,`name`,`phone`,`place`,`modify_time`) values (1,1,'admin','eeee','eeeeee','eeeeeeee','2018-04-13 09:15:26'),(2,2,'qinye','aaaa','aaa','aaa','2018-04-13 11:06:34');

/*Table structure for table `task_directortournament` */

DROP TABLE IF EXISTS `task_directortournament`;

CREATE TABLE `task_directortournament` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `t_id` int(20) NOT NULL COMMENT '教师Id',
  `t_name` varchar(100) NOT NULL COMMENT '指导老师姓名',
  `student_name` varchar(100) NOT NULL COMMENT '学生姓名',
  `name` varchar(100) NOT NULL COMMENT '项目名字',
  `attach` varchar(500) DEFAULT NULL COMMENT '获奖荣誉附件',
  `time` timestamp NULL DEFAULT NULL COMMENT '获奖时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `task_directortournament` */

insert  into `task_directortournament`(`id`,`t_id`,`t_name`,`student_name`,`name`,`attach`,`time`,`modify_time`) values (1,1,'admin','夜勤','yqhp',NULL,'2018-04-14 00:00:00','2018-04-16 15:50:53'),(2,1,'123123','12312','123',NULL,'2015-01-01 00:00:00','2018-04-16 15:40:58'),(3,1,'123213','213123','123',NULL,'2022-01-01 00:00:00','2018-04-16 15:46:34'),(4,1,'3123','123123','12312',NULL,'2014-01-16 00:00:00','2018-04-16 15:46:13'),(5,1,'admin','测试','测试',NULL,'2018-04-04 00:00:00','2018-04-16 15:42:11'),(6,1,'admin','3423','434','123.jpg','2018-04-13 00:00:00','2018-04-16 15:58:42');

/*Table structure for table `task_graduation` */

DROP TABLE IF EXISTS `task_graduation`;

CREATE TABLE `task_graduation` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `t_id` int(20) NOT NULL COMMENT '教师ID',
  `t_name` varchar(100) DEFAULT NULL,
  `name` varchar(100) NOT NULL COMMENT '设计或者论文名称',
  `student_name` varchar(100) NOT NULL COMMENT '学生姓名',
  `is_public` varchar(10) NOT NULL DEFAULT '2' COMMENT '是否发布（1发布，2未发布）',
  `publication_name` varchar(100) DEFAULT NULL COMMENT '发表的刊物名称',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `task_graduation` */

insert  into `task_graduation`(`id`,`t_id`,`t_name`,`name`,`student_name`,`is_public`,`publication_name`,`modify_time`) values (2,1,NULL,'213','12312','1','1231212','2018-04-13 10:03:36'),(3,1,'admin','1111','111','2','2222','2018-04-16 16:04:24'),(4,2,NULL,'223','23232','2','1111','2018-04-13 10:21:48');

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
  `t_name` varchar(100) NOT NULL COMMENT '教师名字',
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
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

/*Data for the table `task_teaching` */

insert  into `task_teaching`(`id`,`t_id`,`t_name`,`name`,`code`,`major`,`property`,`hour`,`count_man`,`assessment_method`,`teaching_subject`,`modify_time`) values (14,1,'admin','课程','111','电子商务','2','12',12,'11111111','1111111','2018-04-11 15:54:34'),(15,1,'admin','课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:34'),(16,1,'admin','课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:34'),(17,1,'admin','课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:35'),(18,1,'admin','课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:35'),(19,1,'admin','课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:35'),(20,1,'admin','课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:35'),(21,1,'admin','课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:35'),(22,1,'admin','课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:35'),(23,1,'admin','课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:35'),(24,1,'admin','课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:40'),(25,1,'admin','课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:40'),(26,1,'admin','课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:40'),(27,1,'admin','课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:40'),(28,1,'admin','课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:41'),(29,1,'admin','课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:41'),(30,1,'admin','课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:41'),(31,1,'admin','课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:41'),(32,1,'admin','课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:41'),(33,1,'admin','课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:41'),(34,1,'admin','课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:41'),(35,1,'admin','课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:41'),(36,1,'admin','课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:42'),(37,1,'admin','课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:42'),(38,1,'admin','课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:42'),(39,1,'admin','课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:42'),(40,1,'admin','课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:42'),(41,1,'admin','课程','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:42'),(42,1,'admin','课程42','111','电子商务','2','12',12,NULL,NULL,'2018-04-11 15:54:42'),(43,1,'admin','1111','6c15a28fbca347ea9ea57a64e82fc926','1111','111','111',111,'111','1111','2018-04-12 09:13:56'),(44,2,'qinye','33123123',NULL,'222','22','2',22,'222','222','2018-04-12 17:09:04');

/*Table structure for table `task_tutor` */

DROP TABLE IF EXISTS `task_tutor`;

CREATE TABLE `task_tutor` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `t_id` int(20) NOT NULL COMMENT '教师ID',
  `t_name` varchar(100) NOT NULL COMMENT '教师姓名',
  `student_name` varchar(100) NOT NULL COMMENT '学生姓名',
  `student_class` varchar(100) DEFAULT NULL COMMENT '班级',
  `major` varchar(100) DEFAULT NULL COMMENT '学生专业',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `task_tutor` */

insert  into `task_tutor`(`id`,`t_id`,`t_name`,`student_name`,`student_class`,`major`,`modify_time`) values (1,1,'admin','123','123','123','2018-04-14 11:42:02'),(3,1,'2143','夜勤','423423','423432423','2018-04-14 13:09:40');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `teacher` */

insert  into `teacher`(`t_id`,`t_name`,`password`,`authorlever`,`school_year`,`education`,`title`,`sex`,`native_place`,`certificate_number`,`image`,`major`,`graduate_school`,`teaching_research`,`job`,`modify_time`) values (1,'admin','123',1,3,'硕士','教师',2,NULL,'430424',NULL,'电子商务','湖南人文科技学院',NULL,NULL,'2018-04-11 17:36:48'),(2,'qinye','123',2,12,NULL,NULL,1,NULL,'123',NULL,'电子商务',NULL,NULL,NULL,'2018-04-10 11:55:30');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
