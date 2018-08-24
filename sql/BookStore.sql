/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.1.62-community : Database - bookstore
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bookstore` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `bookstore`;

/*Table structure for table `bs_book` */

DROP TABLE IF EXISTS `bs_book`;

CREATE TABLE `bs_book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `author` varchar(100) DEFAULT NULL,
  `price` double(11,2) DEFAULT NULL,
  `sales` int(11) unsigned DEFAULT NULL,
  `stock` int(11) unsigned DEFAULT NULL,
  `img_path` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8;

/*Data for the table `bs_book` */

insert  into `bs_book`(`id`,`title`,`author`,`price`,`sales`,`stock`,`img_path`) values (75,'解忧杂货店','东野圭吾',27.20,302,1,'/static/img/default.jpg'),(76,'边城','沈从文',23.00,501,499,'/static/img/default.jpg'),(77,'中国哲学史','冯友兰',44.50,200,100,'/static/img/default.jpg'),(78,'忽然七日',' 劳伦',19.33,101,99,'/static/img/default.jpg'),(79,'苏东坡传','林语堂',19.30,100,100,'/static/img/default.jpg'),(80,'百年孤独','马尔克斯',29.50,100,100,'/static/img/default.jpg'),(81,'扶桑','严歌苓',19.80,100,100,'/static/img/default.jpg'),(82,'教父','马里奥·普佐',29.00,100,100,'/static/img/default.jpg'),(83,'给孩子的诗','北岛',22.20,100,100,'/static/img/default.jpg'),(84,'为奴十二年','所罗门',16.50,100,100,'/static/img/default.jpg'),(85,'平凡的世界','路遥',55.00,100,100,'/static/img/default.jpg'),(86,'悟空传','今何在',14.00,100,100,'/static/img/default.jpg'),(87,'硬派健身','斌卡',31.20,100,100,'/static/img/default.jpg'),(88,'从晚清到民国','唐德刚',39.90,100,100,'/static/img/default.jpg'),(89,'三体','刘慈欣',56.50,100,100,'/static/img/default.jpg'),(90,'看见','柴静',19.50,100,100,'/static/img/default.jpg'),(91,'活着','余华',11.00,101,99,'/static/img/default.jpg'),(92,'小王子','安托万',19.20,110,90,'/static/img/default.jpg'),(93,'我们仨','杨绛',11.30,102,98,'/static/img/default.jpg'),(94,'生命不息,折腾不止','罗永浩',25.20,101,99,'/static/img/default.jpg'),(95,'皮囊','蔡崇达',23.90,100,100,'/static/img/default.jpg'),(96,'恰到好处的幸福','毕淑敏',16.40,101,99,'/static/img/default.jpg'),(97,'艾伦·图灵传','安德鲁',47.20,101,99,'/static/img/default.jpg'),(98,'大数据预测','埃里克',37.20,100,100,'/static/img/default.jpg'),(99,'人月神话','布鲁克斯',55.90,100,100,'/static/img/default.jpg'),(100,'C语言入门经典','霍尔顿',45.00,102,98,'/static/img/default.jpg'),(101,'数学之美','吴军',29.90,100,100,'/static/img/default.jpg'),(102,'Java编程思想','埃史尔',70.50,100,100,'/static/img/default.jpg'),(103,'设计模式之禅','秦小波',20.20,101,99,'/static/img/default.jpg'),(104,'图解机器学习','杉山将',33.80,101,99,'/static/img/default.jpg');

/*Table structure for table `bs_order` */

DROP TABLE IF EXISTS `bs_order`;

CREATE TABLE `bs_order` (
  `id` varchar(100) NOT NULL,
  `order_time` datetime DEFAULT NULL,
  `total_count` int(11) DEFAULT NULL,
  `total_amount` double(11,2) DEFAULT NULL,
  `state` int(2) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `bs_order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `bs_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bs_order` */

insert  into `bs_order`(`id`,`order_time`,`total_count`,`total_amount`,`state`,`user_id`) values ('15223190058599','2018-03-29 18:23:25',10,230.00,2,9),('15223191239609','2018-03-29 18:25:23',10,192.00,2,9),('15223908899859','2018-03-30 14:21:29',6,122.40,0,9),('15223930331499','2018-03-30 14:57:13',2,54.00,0,9),('15228055053789','2018-04-04 09:31:45',2,67.50,0,9),('15228056494149','2018-04-04 09:34:09',1,27.20,0,9),('15228088771639','2018-04-04 10:27:57',1,27.20,0,9),('15228117898329','2018-04-04 11:16:29',3,134.50,0,9),('15230106292459','2018-04-06 18:30:29',98,4361.00,0,9);

/*Table structure for table `bs_order_item` */

DROP TABLE IF EXISTS `bs_order_item`;

CREATE TABLE `bs_order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `COUNT` int(11) DEFAULT NULL,
  `amount` double(11,2) DEFAULT NULL,
  `title` varchar(120) DEFAULT NULL,
  `author` varchar(120) DEFAULT NULL,
  `price` double(11,2) DEFAULT NULL,
  `img_path` varchar(200) DEFAULT NULL,
  `order_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`),
  CONSTRAINT `bs_order_item_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `bs_order` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

/*Data for the table `bs_order_item` */

insert  into `bs_order_item`(`id`,`COUNT`,`amount`,`title`,`author`,`price`,`img_path`,`order_id`) values (7,10,230.00,'边城','沈从文',23.00,'/static/img/default.jpg','15223190058599'),(8,10,192.00,'小王子','安托万',19.20,'/static/img/default.jpg','15223191239609'),(9,2,22.60,'我们仨','杨绛',11.30,'/static/img/default.jpg','15223908899859'),(10,1,25.20,'生命不息,折腾不止','罗永浩',25.20,'/static/img/default.jpg','15223908899859'),(11,1,11.00,'活着','余华',11.00,'/static/img/default.jpg','15223908899859'),(12,1,16.40,'恰到好处的幸福','毕淑敏',16.40,'/static/img/default.jpg','15223908899859'),(13,1,47.20,'艾伦·图灵传','安德鲁',47.20,'/static/img/default.jpg','15223908899859'),(14,1,33.80,'图解机器学习','杉山将',33.80,'/static/img/default.jpg','15223930331499'),(15,1,20.20,'设计模式之禅','秦小波',20.20,'/static/img/default.jpg','15223930331499'),(16,1,44.50,'中国哲学史','冯友兰',44.50,'/static/img/default.jpg','15228055053789'),(17,1,23.00,'边城','沈从文',23.00,'/static/img/default.jpg','15228055053789'),(18,1,27.20,'解忧杂货店','东野圭吾',27.20,'/static/img/default.jpg','15228056494149'),(19,1,27.20,'解忧杂货店','东野圭吾',27.20,'/static/img/default.jpg','15228088771639'),(21,2,90.00,'C语言入门经典','霍尔顿',45.00,'/static/img/default.jpg','15228117898329'),(22,1,44.50,'中国哲学史','冯友兰',44.50,'/static/img/default.jpg','15228117898329'),(23,98,4361.00,'中国哲学史','冯友兰',44.50,'/static/img/default.jpg','15230106292459');

/*Table structure for table `bs_user` */

DROP TABLE IF EXISTS `bs_user`;

CREATE TABLE `bs_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

/*Data for the table `bs_user` */

insert  into `bs_user`(`id`,`username`,`password`,`email`) values (9,'abcd','111111','111@qq.com'),(10,'111111','222222','222@qq.com'),(11,'sfsffdsf','123qqq','123@qq.com'),(12,'aaaa','111111','123456@qq.com'),(21,'abc','111111','1111c@cc.cds');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(100) NOT NULL,
  `login_pwd` varchar(100) NOT NULL,
  `nickname` varchar(50) DEFAULT NULL,
  `head_icon_path` varchar(200) DEFAULT NULL,
  `user_role` int(11) DEFAULT '0',
  `account_balance` double DEFAULT '0',
  `age` int(11) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT '1',
  `is_deleted` tinyint(1) DEFAULT '0',
  `email` varchar(100) DEFAULT NULL,
  `birth` date DEFAULT NULL,
  `regist_date` date DEFAULT NULL,
  `id_number` varchar(30) DEFAULT NULL,
  `is_alipay_identified` tinyint(1) DEFAULT '0',
  `is_ant_credit_identified` tinyint(1) DEFAULT '0',
  `ant_credit_score` int(11) DEFAULT '0',
  `real_name` varchar(50) DEFAULT NULL,
  `phone_num` varchar(20) DEFAULT NULL,
  `gender` tinyint(4) DEFAULT '1',
  `credit_level` int(11) DEFAULT '5',
  `stu_num` varchar(20) DEFAULT NULL,
  `school` varchar(50) DEFAULT NULL,
  `institute` varchar(50) DEFAULT NULL,
  `major` varchar(50) DEFAULT NULL,
  `graduate_date` date DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `login_name` (`login_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `users` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
