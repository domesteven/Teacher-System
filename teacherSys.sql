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

/*Table structure for table `attach` */

DROP TABLE IF EXISTS `attach`;

CREATE TABLE `attach` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `instance_id` varchar(100) NOT NULL COMMENT '关联的实例ID',
  `file_name` varchar(200) NOT NULL COMMENT '附件名字',
  `size` int(100) DEFAULT NULL COMMENT '附件大小',
  `type` varchar(100) NOT NULL COMMENT '附件类型',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

/*Data for the table `attach` */

insert  into `attach`(`id`,`instance_id`,`file_name`,`size`,`type`,`modify_time`) values (1,'1a1694dd716a40d184fffe6adf443f18','file',NULL,'image/gif','2018-06-02 21:47:40'),(2,'1a1694dd716a40d184fffe6adf443f18','file',NULL,'image/jpeg','2018-06-02 21:48:19'),(3,'a39cb30c7d24414694d0cfca2fb648f6','1.gif',NULL,'image/gif','2018-06-02 21:50:24'),(4,'a39cb30c7d24414694d0cfca2fb648f6','big.jpg',NULL,'image/jpeg','2018-06-02 21:50:26'),(11,'2fc0a234e82e423d904b4a2c5a168797','big.jpg',NULL,'image/jpeg','2018-06-03 00:00:50'),(12,'2fc0a234e82e423d904b4a2c5a168797','c2a71730gy1fcvvdqriuij20qp0rntcd.jpg',NULL,'image/jpeg','2018-06-03 00:00:50'),(13,'2fc0a234e82e423d904b4a2c5a168797','20161014013219615.jpg.1080.1920.jpg',NULL,'image/jpeg','2018-06-03 00:09:27'),(14,'2fc0a234e82e423d904b4a2c5a168797','LGW.jpg',NULL,'image/jpeg','2018-06-03 00:09:27'),(15,'2fc0a234e82e423d904b4a2c5a168797','small.jpg',NULL,'image/jpeg','2018-06-03 00:09:27'),(16,'2fc0a234e82e423d904b4a2c5a168797','sspl.jpg',NULL,'image/jpeg','2018-06-03 00:09:27'),(17,'a39cb30c7d24414694d0cfca2fb648f6','c2a71730gy1fcvvdqriuij20qp0rntcd.jpg',NULL,'image/jpeg','2018-06-03 00:10:06'),(18,'a39cb30c7d24414694d0cfca2fb648f6','sspl.jpg',NULL,'image/jpeg','2018-06-03 15:54:50'),(19,'a39cb30c7d24414694d0cfca2fb648f6','电磁猫.jpg',NULL,'image/jpeg','2018-06-03 15:54:51'),(20,'a39cb30c7d24414694d0cfca2fb648f6','small.jpg',NULL,'image/jpeg','2018-06-03 15:58:41'),(21,'a39cb30c7d24414694d0cfca2fb648f6','20160724105431258.jpg.1080.1920.jpg',NULL,'image/jpeg','2018-06-03 16:06:19'),(22,'1a1694dd716a40d184fffe6adf443f18','big.jpg',NULL,'image/jpeg','2018-06-03 16:14:56'),(23,'a39cb30c7d24414694d0cfca2fb648f6','c2a71730gy1fcvvdqriuij20qp0rntcd.jpg',NULL,'image/jpeg','2018-06-03 16:41:43'),(24,'a39cb30c7d24414694d0cfca2fb648f6','Corleone.jpg',NULL,'image/jpeg','2018-06-03 16:42:14'),(25,'a39cb30c7d24414694d0cfca2fb648f6','1.gif',NULL,'image/gif','2018-06-03 16:45:23'),(26,'a39cb30c7d24414694d0cfca2fb648f6','c2a71730gy1fcvvdqriuij20qp0rntcd.jpg',NULL,'image/jpeg','2018-06-03 16:48:34'),(27,'a39cb30c7d24414694d0cfca2fb648f6','Corleone.jpg',NULL,'image/jpeg','2018-06-03 16:48:34'),(28,'a39cb30c7d24414694d0cfca2fb648f6','big.jpg',NULL,'image/jpeg','2018-06-03 16:49:04'),(29,'a39cb30c7d24414694d0cfca2fb648f6','big.jpg',NULL,'image/jpeg','2018-06-03 16:51:46'),(30,'a39cb30c7d24414694d0cfca2fb648f6','20160724105431258.jpg.1080.1920.jpg',NULL,'image/jpeg','2018-06-03 16:56:43'),(31,'2bdbe2fc432a45af8e037266df52c3f2','1.gif',NULL,'image/gif','2018-06-03 17:21:12'),(32,'2bdbe2fc432a45af8e037266df52c3f2','1.gif',NULL,'image/gif','2018-06-03 17:22:37'),(33,'2bdbe2fc432a45af8e037266df52c3f2','50060ac7e2fd4.jpg',NULL,'image/jpeg','2018-06-03 17:22:37'),(34,'2b85ef604038452695c9320c10c9ff3b','20160724105431258.jpg.1080.1920.jpg',NULL,'image/jpeg','2018-06-03 17:23:13'),(35,'2b85ef604038452695c9320c10c9ff3b','20161014013219615.jpg.1080.1920.jpg',NULL,'image/jpeg','2018-06-03 17:23:13'),(36,'2b85ef604038452695c9320c10c9ff3b','big.jpg',NULL,'image/jpeg','2018-06-03 17:23:13'),(37,'f6d91cef447f4f64858056b30043fd23','50060ac7e2fd4.jpg',NULL,'image/jpeg','2018-06-03 17:35:33'),(38,'f6d91cef447f4f64858056b30043fd23','c2a71730gy1fcvvdqriuij20qp0rntcd.jpg',NULL,'image/jpeg','2018-06-03 17:35:42'),(39,'f6d91cef447f4f64858056b30043fd23','Corleone.jpg',NULL,'image/jpeg','2018-06-03 17:35:42'),(40,'76df71e9e92b4e8e8b2b2ef4f3fcad91','20160724105431258.jpg.1080.1920.jpg',NULL,'image/jpeg','2018-06-03 17:41:57'),(41,'76df71e9e92b4e8e8b2b2ef4f3fcad91','big.jpg',NULL,'image/jpeg','2018-06-03 17:41:57'),(42,'408f9406da4b4a8fbaf7a183d6b2736b','big.jpg',NULL,'image/jpeg','2018-06-03 17:42:18'),(43,'408f9406da4b4a8fbaf7a183d6b2736b','Corleone.jpg',NULL,'image/jpeg','2018-06-03 17:42:18'),(44,'61df41a964a64e738b2614b7ed0ca505','1.gif',NULL,'image/gif','2018-06-03 17:48:28'),(45,'61df41a964a64e738b2614b7ed0ca505','Corleone.jpg',NULL,'image/jpeg','2018-06-03 17:48:28'),(46,'a77625fb2ab04dc69e26c4bdcc6b660b','big.jpg',NULL,'image/jpeg','2018-06-03 17:49:02'),(48,'fbebff9aefbc407dbec1d07ce04f25ab','20160724105431258.jpg.1080.1920.jpg',NULL,'image/jpeg','2018-06-03 18:04:00'),(49,'a7a6d8d6ba75413ab4d27b92d8e02ec0','big.jpg',NULL,'image/jpeg','2018-06-03 18:05:40'),(50,'f03e2031fca64d7dafd5fb83c4324d17','20150209021428157.jpg.640.1136.jpg',NULL,'image/jpeg','2018-06-03 18:06:02'),(51,'245b07815cd045cc8903bf65c478ffaa','开题.txt',NULL,'text/plain','2018-06-03 18:08:47'),(52,'245b07815cd045cc8903bf65c478ffaa','sql.sql',NULL,'application/octet-stream','2018-06-03 18:09:29'),(53,'245b07815cd045cc8903bf65c478ffaa','新建文本文档 (3).txt',NULL,'text/plain','2018-06-03 18:21:55'),(54,'2b85ef604038452695c9320c10c9ff3b','毕业设计--开题报告--秦叶0.wps',NULL,'application/kswps','2018-06-03 18:37:07');

/*Table structure for table `author` */

DROP TABLE IF EXISTS `author`;

CREATE TABLE `author` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `author_name` varchar(100) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `author` */

insert  into `author`(`id`,`author_name`,`description`,`modify_time`) values (1,'管理员','管理员权限','2018-03-07 14:39:40'),(2,'用户','用户权限','2018-03-07 14:39:55');

/*Table structure for table `project_lecture` */

DROP TABLE IF EXISTS `project_lecture`;

CREATE TABLE `project_lecture` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `t_id` int(20) NOT NULL COMMENT '教师ID',
  `t_name` varchar(100) DEFAULT NULL COMMENT '教师名字',
  `attach` varchar(500) DEFAULT NULL,
  `name` varchar(200) NOT NULL COMMENT '学术讲座名称',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '学术讲座时间',
  `academics` varchar(200) DEFAULT NULL COMMENT '学术讲座人员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `project_lecture` */

insert  into `project_lecture`(`id`,`t_id`,`t_name`,`attach`,`name`,`modify_time`,`time`,`academics`) values (2,2,'qinye',NULL,'1123213','2018-04-17 19:52:43','2018-04-03 00:00:00','111'),(4,1,'admin','245b07815cd045cc8903bf65c478ffaa','rrr','2018-06-03 18:08:30','2018-05-31 00:00:00','rrrr');

/*Table structure for table `project_person` */

DROP TABLE IF EXISTS `project_person`;

CREATE TABLE `project_person` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `t_id` int(20) NOT NULL COMMENT '教师ID',
  `t_name` varchar(100) DEFAULT NULL COMMENT '教师姓名',
  `attach` varchar(500) DEFAULT NULL COMMENT '附件',
  `name` varchar(200) NOT NULL COMMENT '人才工程名称',
  `student_name` varchar(100) NOT NULL COMMENT '姓名',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '人才工程开始时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `project_person` */

insert  into `project_person`(`id`,`t_id`,`t_name`,`attach`,`name`,`student_name`,`modify_time`,`time`) values (3,2,'qinye',NULL,'2222','2222','2018-04-17 10:34:50','2018-04-12 00:00:00'),(4,1,'admin','a7a6d8d6ba75413ab4d27b92d8e02ec0','123213','213321','2018-06-03 18:05:34','2018-06-07 00:00:00'),(5,1,'admin','f03e2031fca64d7dafd5fb83c4324d17','fff','ffffff','2018-06-03 18:05:55','2018-06-05 00:00:00');

/*Table structure for table `project_publish` */

DROP TABLE IF EXISTS `project_publish`;

CREATE TABLE `project_publish` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `t_id` int(20) NOT NULL COMMENT '教师ID',
  `t_name` varchar(100) DEFAULT NULL COMMENT '教师姓名',
  `name` varchar(200) NOT NULL COMMENT '刊物名称',
  `attach` varchar(500) DEFAULT NULL COMMENT '附件',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `press_company` varchar(200) DEFAULT NULL COMMENT '发表商',
  `publish_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '发表时间',
  `issn` varchar(100) NOT NULL COMMENT '出版物编号',
  `type` varchar(10) NOT NULL COMMENT '(1:是论文；2：著作教材)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `project_publish` */

insert  into `project_publish`(`id`,`t_id`,`t_name`,`name`,`attach`,`modify_time`,`press_company`,`publish_time`,`issn`,`type`) values (2,2,'qinye','中国地理',NULL,'2018-04-19 09:55:59','知网','2018-04-02 00:00:00','111111','2'),(4,1,'admin','21321','fbebff9aefbc407dbec1d07ce04f25ab','2018-06-03 18:03:54','123','2018-06-15 00:00:00','123213','2');

/*Table structure for table `project_socialservice` */

DROP TABLE IF EXISTS `project_socialservice`;

CREATE TABLE `project_socialservice` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `t_id` int(20) NOT NULL COMMENT '教师ID',
  `t_name` varchar(100) DEFAULT NULL COMMENT '教师姓名',
  `company_name` varchar(200) NOT NULL COMMENT '服务企业名称或部门',
  `attach` varchar(500) DEFAULT NULL,
  `member` varchar(500) DEFAULT NULL COMMENT '成员',
  `name` varchar(200) NOT NULL COMMENT '项目名称',
  `service_time` int(10) DEFAULT NULL COMMENT '服务时限',
  `money` int(20) DEFAULT NULL COMMENT '经费',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `start_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '立项时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `project_socialservice` */

/*Table structure for table `task_company` */

DROP TABLE IF EXISTS `task_company`;

CREATE TABLE `task_company` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `t_id` int(20) NOT NULL COMMENT '教师ID',
  `t_name` varchar(100) DEFAULT NULL,
  `name` varchar(100) NOT NULL COMMENT '企业名称',
  `attach` varchar(500) DEFAULT NULL COMMENT '附件',
  `phone` varchar(100) NOT NULL COMMENT '电话',
  `place` varchar(500) DEFAULT NULL COMMENT '地址',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `task_company` */

insert  into `task_company`(`id`,`t_id`,`t_name`,`name`,`attach`,`phone`,`place`,`modify_time`) values (3,1,'admin','111','f6d91cef447f4f64858056b30043fd23','111','1111','2018-06-03 17:35:22');

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `task_directortournament` */

insert  into `task_directortournament`(`id`,`t_id`,`t_name`,`student_name`,`name`,`attach`,`time`,`modify_time`) values (9,1,'admin','RRR','柔柔弱弱','1a1694dd716a40d184fffe6adf443f18','2017-06-12 00:00:00','2018-06-02 21:48:19'),(10,1,'admin','WWE','WE','a39cb30c7d24414694d0cfca2fb648f6','2018-06-05 00:00:00','2018-06-03 00:10:06'),(11,1,'admin','凤飞飞','反反复复','2fc0a234e82e423d904b4a2c5a168797','2018-06-01 00:00:00','2018-06-03 00:09:27');

/*Table structure for table `task_graduation` */

DROP TABLE IF EXISTS `task_graduation`;

CREATE TABLE `task_graduation` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `t_id` int(20) NOT NULL COMMENT '教师ID',
  `t_name` varchar(100) DEFAULT NULL,
  `name` varchar(100) NOT NULL COMMENT '设计或者论文名称',
  `attach` varchar(500) DEFAULT NULL COMMENT '附件',
  `student_name` varchar(100) NOT NULL COMMENT '学生姓名',
  `is_public` varchar(10) NOT NULL DEFAULT '2' COMMENT '是否发布（1发布，2未发布）',
  `publication_name` varchar(100) DEFAULT NULL COMMENT '发表的刊物名称',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `task_graduation` */

insert  into `task_graduation`(`id`,`t_id`,`t_name`,`name`,`attach`,`student_name`,`is_public`,`publication_name`,`modify_time`) values (4,2,NULL,'223',NULL,'23232','2','1111','2018-04-13 10:21:48'),(5,1,'admin','13','76df71e9e92b4e8e8b2b2ef4f3fcad91','123213','2','123','2018-06-03 17:41:49'),(6,1,'admin','rrr','408f9406da4b4a8fbaf7a183d6b2736b','RRr','2','rrrr','2018-06-03 17:42:10');

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
  `attach` varchar(500) DEFAULT NULL COMMENT '附件',
  `hour` varchar(100) DEFAULT NULL COMMENT '课时',
  `type` varchar(20) NOT NULL DEFAULT '1' COMMENT '类型（1理论；2实践）',
  `time` timestamp NULL DEFAULT NULL COMMENT '时间',
  `count_man` int(20) DEFAULT NULL COMMENT '人数',
  `assessment_method` varchar(100) DEFAULT NULL COMMENT '考核方式',
  `teaching_subject` varchar(100) DEFAULT NULL COMMENT '所属教学改革课题',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

/*Data for the table `task_teaching` */

insert  into `task_teaching`(`id`,`t_id`,`t_name`,`name`,`code`,`major`,`property`,`attach`,`hour`,`type`,`time`,`count_man`,`assessment_method`,`teaching_subject`,`modify_time`) values (45,1,'admin','111','87599fc8b2554c1c83f5e627bc077928','111','11','2bdbe2fc432a45af8e037266df52c3f2','11','2','2018-06-07 00:00:00',1,'111','111','2018-06-03 17:18:52'),(46,1,'admin','222','b7ced174654f4a4caf1a55ce45941c0d','222','222','2b85ef604038452695c9320c10c9ff3b','22','1','2018-06-07 00:00:00',222,'222','22','2018-06-03 17:23:01');

/*Table structure for table `task_tutor` */

DROP TABLE IF EXISTS `task_tutor`;

CREATE TABLE `task_tutor` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `t_id` int(20) NOT NULL COMMENT '教师ID',
  `t_name` varchar(100) NOT NULL COMMENT '教师姓名',
  `attach` varchar(500) DEFAULT NULL COMMENT '附件',
  `student_name` varchar(100) NOT NULL COMMENT '学生姓名',
  `student_class` varchar(100) DEFAULT NULL COMMENT '班级',
  `major` varchar(100) DEFAULT NULL COMMENT '学生专业',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `task_tutor` */

insert  into `task_tutor`(`id`,`t_id`,`t_name`,`attach`,`student_name`,`student_class`,`major`,`modify_time`) values (4,1,'admin','61df41a964a64e738b2614b7ed0ca505','12321','312312','3123123','2018-06-03 17:48:18'),(5,1,'admin','a77625fb2ab04dc69e26c4bdcc6b660b','eeee','eee','eeee','2018-06-03 17:48:54');

/*Table structure for table `teacher` */

DROP TABLE IF EXISTS `teacher`;

CREATE TABLE `teacher` (
  `t_id` int(20) NOT NULL AUTO_INCREMENT,
  `t_name` varchar(100) NOT NULL COMMENT '名字',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `authorlever` int(20) DEFAULT '2' COMMENT '权限级别',
  `school_year` timestamp NULL DEFAULT NULL COMMENT '入校年份',
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

insert  into `teacher`(`t_id`,`t_name`,`password`,`authorlever`,`school_year`,`education`,`title`,`sex`,`native_place`,`certificate_number`,`image`,`major`,`graduate_school`,`teaching_research`,`job`,`modify_time`) values (1,'admin','123',1,'2018-05-23 00:00:00','硕士','教师',2,NULL,'430424','1-150FQ94345.jpg','电子商务','湖南人文科技学院',NULL,NULL,'2018-05-23 14:46:13'),(2,'qinye','123',2,'2017-05-23 00:00:00',NULL,NULL,1,NULL,'123',NULL,'电子商务',NULL,NULL,NULL,'2018-04-10 11:55:30');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
