/*
SQLyog 企业版 - MySQL GUI v7.14
MySQL - 5.6.12-enterprise-commercial-advanced-log : Database - payment_demo
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`payment_demo` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `payment_demo`;

/*Table structure for table `files` */

CREATE TABLE `files` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文件名称',
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文件类型',
  `size` bigint(20) DEFAULT NULL COMMENT '文件大小(kb)',
  `url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '下载链接',
  `md5` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文件md5',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  `enable` tinyint(1) DEFAULT '1' COMMENT '是否禁用链接',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

/*Data for the table `files` */

insert  into `files`(`id`,`name`,`type`,`size`,`url`,`md5`,`is_delete`,`enable`,`create_time`,`update_time`) values (73,'lgy.jpg','jpg',35,'http://localhost:9090/files/2023/04/22/20230422123301000000166.jpg','eb81db8974a4924ba39ccc049c078516',0,1,'2023-04-22 00:33:01',NULL),(74,'lgy.png','png',197,'http://localhost:9090/files/2023/04/22/20230422123303000000698.png','466ebb0a2ea027b04ab2f60f2dcbf1f6',0,1,'2023-04-22 00:33:04',NULL);

/*Table structure for table `sys_dict` */

CREATE TABLE `sys_dict` (
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '名称',
  `value` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '内容',
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '类型'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

/*Data for the table `sys_dict` */

insert  into `sys_dict`(`name`,`value`,`type`) values ('user','el-icon-user','icon'),('house','el-icon-house','icon'),('menu','el-icon-menu','icon'),('s-custom','el-icon-s-custom','icon'),('s-grid','el-icon-s-grid','icon'),('document','el-icon-document','icon'),('coffee','el-icon-coffee\r\n','icon'),('s-marketing','el-icon-s-marketing','icon'),('files','el-icon-files','icon');

/*Table structure for table `sys_menu` */

CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '名称',
  `path` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '路径',
  `icon` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图标',
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  `permission` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '权限标识',
  `pid` int(11) DEFAULT NULL COMMENT '父级id',
  `page_path` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '页面路径',
  `sort_num` int(11) DEFAULT NULL COMMENT '排序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_delete` tinyint(4) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`name`,`path`,`icon`,`description`,`permission`,`pid`,`page_path`,`sort_num`,`create_time`,`update_time`,`is_delete`) values (4,'系统管理',NULL,'el-icon-s-grid',NULL,NULL,NULL,NULL,300,NULL,NULL,0),(5,'用户管理','/user','el-icon-user',NULL,'user',4,'User',301,NULL,'2023-04-20 10:17:23',0),(6,'角色管理','/role','el-icon-s-custom',NULL,'role',4,'Role',302,NULL,'2023-04-20 10:53:11',0),(7,'菜单管理','/menu','el-icon-menu',NULL,NULL,4,'Menu',303,NULL,NULL,0),(10,'主页','/home','el-icon-house','主页',NULL,NULL,'Home',0,NULL,'2023-04-20 09:45:07',0),(48,'支付案例',NULL,'el-icon-s-grid',NULL,NULL,NULL,NULL,100,'2023-06-19 10:09:00',NULL,0),(49,'购买课程','/index','el-icon-user',NULL,NULL,48,'index',101,'2023-06-19 10:10:57',NULL,0),(50,'我的订单','/orders','el-icon-house',NULL,NULL,48,'Orders',102,'2023-06-19 10:11:43',NULL,0),(51,'下载订单','/download','el-icon-menu',NULL,NULL,48,'Download',103,'2023-06-19 10:13:02','2023-06-19 10:13:42',0),(52,'支付成功','/success','el-icon-s-custom',NULL,NULL,48,'Success',104,'2023-06-19 10:14:58',NULL,0);

/*Table structure for table `sys_role` */

CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_key` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '唯一标识',
  `name` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '名称',
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_delete` tinyint(4) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='角色表';

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`role_key`,`name`,`description`,`create_time`,`update_time`,`is_delete`) values (1,'admin','超级管理员','烦烦烦',NULL,'2023-04-20 09:32:12',0),(2,'user','普通用户',NULL,NULL,NULL,0),(3,'vip','Vip用户',NULL,NULL,'2023-04-22 00:33:12',0);

/*Table structure for table `sys_role_menu` */

CREATE TABLE `sys_role_menu` (
  `role_id` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色id',
  `menu_id` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜单id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`role_id`,`menu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='角色-菜单-关联表';

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`role_id`,`menu_id`,`create_time`,`update_time`) values ('1','10','2023-06-19 10:16:12',NULL),('1','4','2023-06-19 10:16:12',NULL),('1','48','2023-06-19 10:16:12',NULL),('1','49','2023-06-19 10:16:12',NULL),('1','5','2023-06-19 10:16:12',NULL),('1','50','2023-06-19 10:16:12',NULL),('1','51','2023-06-19 10:16:12',NULL),('1','52','2023-06-19 10:16:12',NULL),('1','6','2023-06-19 10:16:12',NULL),('1','7','2023-06-19 10:16:12',NULL),('2','10','2023-06-19 10:24:19',NULL),('2','4','2023-06-19 10:24:19',NULL),('2','48','2023-06-19 10:24:19',NULL),('2','49','2023-06-19 10:24:19',NULL),('2','5','2023-06-19 10:24:19',NULL),('2','50','2023-06-19 10:24:19',NULL),('2','6','2023-06-19 10:24:19',NULL),('3','10','2023-06-19 10:24:00',NULL),('3','4','2023-06-19 10:24:00',NULL),('3','48','2023-06-19 10:24:00',NULL),('3','49','2023-06-19 10:24:00',NULL),('3','5','2023-06-19 10:24:00',NULL),('3','50','2023-06-19 10:24:00',NULL),('3','51','2023-06-19 10:24:00',NULL),('3','6','2023-06-19 10:24:00',NULL),('3','7','2023-06-19 10:24:00',NULL);

/*Table structure for table `sys_user` */

CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `nickname` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '昵称',
  `email` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱',
  `phonenumber` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '电话',
  `address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地址',
  `avatar_url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT 'http://localhost:9090/files/20230409082108000000936.jpg' COMMENT '头像',
  `is_delete` tinyint(4) DEFAULT '0' COMMENT '是否删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`username`,`password`,`nickname`,`email`,`phonenumber`,`address`,`avatar_url`,`is_delete`,`create_time`,`update_time`) values (1,'admin','202cb962ac59075b964b07152d234b70','无所谓^_^','2673152463@qq.com','2673152463','浙江省','http://localhost:9090/files/2023/04/22/20230422123301000000166.jpg',0,'2022-01-22 21:10:27','2023-04-22 00:33:05'),(16,'vip','202cb962ac59075b964b07152d234b70','小黑子','2','2','2','http://localhost:9090/files/2023/04/22/20230422123303000000698.png',0,'2022-02-26 22:10:14','2023-04-22 12:20:29'),(17,'user','202cb962ac59075b964b07152d234b70','我是三三哦豁','3','2673152463','3','https://profile.csdnimg.cn/B/7/0/1_weixin_51603038',0,'2022-02-26 22:10:18','2023-04-22 12:16:05'),(18,'nzz','202cb962ac59075b964b07152d234b70','哪吒','2','2','2','',0,'2022-03-29 16:59:44','2023-04-21 23:16:50'),(25,'sir','202cb962ac59075b964b07152d234b70','安琪拉',NULL,NULL,NULL,NULL,0,'2022-06-08 17:00:47','2023-04-21 23:16:50'),(26,'err','202cb962ac59075b964b07152d234b70','妲己','11','1','1',NULL,0,'2022-07-08 17:20:01','2023-04-21 23:10:29'),(28,'ddd','202cb962ac59075b964b07152d234b70','ddd','','','','http://localhost:9090/file/7de0e50f915547539db12023cf997276.jpg',0,'2022-11-09 10:41:07','2023-04-21 23:10:29'),(29,'ffff','202cb962ac59075b964b07152d234b70','ffff',NULL,NULL,NULL,NULL,0,'2022-12-10 11:53:31','2023-04-21 23:10:29'),(36,'aaa','47bce5c74f589f4867dbd57e9ca9f808',NULL,NULL,NULL,NULL,'http://localhost:9090/files/20230409082108000000936.jpg',0,'2023-04-21 22:45:25','2023-04-21 23:10:16'),(37,'fff','343d9040a671c45832ee5381860e2996',NULL,NULL,NULL,NULL,'http://localhost:9090/files/20230409082108000000936.jpg',0,'2023-04-21 23:02:56','2023-04-21 23:17:24');

/*Table structure for table `sys_user_role` */

CREATE TABLE `sys_user_role` (
  `user_id` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户id',
  `role_id` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='用户-角色关联表';

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`user_id`,`role_id`,`create_time`,`update_time`) values ('1','1','2023-04-20 11:03:24',NULL),('16','3','2023-04-22 12:21:54',NULL),('17','2','2023-04-22 12:16:05',NULL),('18','2','2023-04-22 12:30:26',NULL),('25','2','2023-04-22 12:30:29',NULL),('26','2','2023-04-22 12:30:34',NULL),('28','2','2023-04-22 12:30:36',NULL),('29','2','2023-04-22 12:30:39',NULL),('35','1','2023-04-20 09:07:19',NULL),('36','2','2023-04-22 12:30:41',NULL),('37','2','2023-04-22 12:30:45',NULL);

/*Table structure for table `t_order_info` */

CREATE TABLE `t_order_info` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `title` varchar(256) DEFAULT NULL COMMENT '订单标题',
  `order_no` varchar(50) DEFAULT NULL COMMENT '商户订单编号',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `product_id` bigint(20) DEFAULT NULL COMMENT '支付产品id',
  `total_fee` int(11) DEFAULT NULL COMMENT '订单金额(分)',
  `code_url` varchar(50) DEFAULT NULL COMMENT '订单二维码连接',
  `order_status` varchar(10) DEFAULT NULL COMMENT '订单状态',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_order_info` */

insert  into `t_order_info`(`id`,`title`,`order_no`,`user_id`,`product_id`,`total_fee`,`code_url`,`order_status`,`create_time`,`update_time`) values (1,'Java课程','ORDER_20230619101858988',NULL,1,1,'weixin://wxpay/bizpayurl?pr=eJjA50Ezz','支付成功','2023-06-19 10:18:58','2023-06-19 10:20:00');

/*Table structure for table `t_payment_info` */

CREATE TABLE `t_payment_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '支付记录id',
  `order_no` varchar(50) DEFAULT NULL COMMENT '商户订单编号',
  `transaction_id` varchar(50) DEFAULT NULL COMMENT '支付系统交易编号',
  `payment_type` varchar(20) DEFAULT NULL COMMENT '支付类型',
  `trade_type` varchar(20) DEFAULT NULL COMMENT '交易类型',
  `trade_state` varchar(50) DEFAULT NULL COMMENT '交易状态',
  `payer_total` int(11) DEFAULT NULL COMMENT '支付金额(分)',
  `content` text COMMENT '通知参数',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_payment_info` */

insert  into `t_payment_info`(`id`,`order_no`,`transaction_id`,`payment_type`,`trade_type`,`trade_state`,`payer_total`,`content`,`create_time`,`update_time`) values (1,'ORDER_20230619101858988','4200001865202306196318599916','微信','NATIVE','SUCCESS',1,'{\"amount\":{\"currency\":\"CNY\",\"payer_currency\":\"CNY\",\"payer_total\":1,\"total\":1},\"appid\":\"wx7b36b2ad2d5d1528\",\"attach\":\"\",\"bank_type\":\"OTHERS\",\"mchid\":\"1600700474\",\"out_trade_no\":\"ORDER_20230619101858988\",\"payer\":{\"openid\":\"onYekwGw0NoD8Qdec02wRGODNeaw\"},\"promotion_detail\":[],\"success_time\":\"2023-06-19T10:19:29+08:00\",\"trade_state\":\"SUCCESS\",\"trade_state_desc\":\"支付成功\",\"trade_type\":\"NATIVE\",\"transaction_id\":\"4200001865202306196318599916\"}','2023-06-19 10:20:00','2023-06-19 10:20:00');

/*Table structure for table `t_product` */

CREATE TABLE `t_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `title` varchar(20) DEFAULT NULL COMMENT '商品名称',
  `price` int(11) DEFAULT NULL COMMENT '价格（分）',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_product` */

insert  into `t_product`(`id`,`title`,`price`,`create_time`,`update_time`) values (1,'Java课程',1,'2023-06-19 10:06:02','2023-06-19 10:06:02'),(2,'大数据课程',1,'2023-06-19 10:06:02','2023-06-19 10:06:02'),(3,'前端课程',1,'2023-06-19 10:06:02','2023-06-19 10:06:02'),(4,'UI课程',1,'2023-06-19 10:06:02','2023-06-19 10:06:02');

/*Table structure for table `t_refund_info` */

CREATE TABLE `t_refund_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '退款单id',
  `order_no` varchar(50) DEFAULT NULL COMMENT '商户订单编号',
  `refund_no` varchar(50) DEFAULT NULL COMMENT '商户退款单编号',
  `refund_id` varchar(50) DEFAULT NULL COMMENT '支付系统退款单号',
  `total_fee` int(11) DEFAULT NULL COMMENT '原订单金额(分)',
  `refund` int(11) DEFAULT NULL COMMENT '退款金额(分)',
  `reason` varchar(50) DEFAULT NULL COMMENT '退款原因',
  `refund_status` varchar(10) DEFAULT NULL COMMENT '退款状态',
  `content_return` text COMMENT '申请退款返回参数',
  `content_notify` text COMMENT '退款结果通知参数',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_refund_info` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
