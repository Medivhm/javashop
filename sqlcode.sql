create database javashop default character set utf8 collate utf8_general_ci;


SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
--  Table structure for `gift`
-- ----------------------------
DROP TABLE IF EXISTS `gift`;
CREATE TABLE `gift` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `giftName` varchar(255) DEFAULT NULL,
  `price` double(255,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `goods`
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goodsName` varchar(255) DEFAULT NULL,
  `prodArea` varchar(255) DEFAULT NULL,
  `spec` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `orderdetail`
-- ----------------------------
DROP TABLE IF EXISTS `orderdetail`;
CREATE TABLE `orderdetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderId` int(11) DEFAULT NULL,
  `goodsId` int(11) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `orders`
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `addDate` datetime DEFAULT NULL,
  `isPay` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `usergift`
-- ----------------------------
DROP TABLE IF EXISTS `usergift`;
CREATE TABLE `usergift` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `giftId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginId` varchar(255) DEFAULT NULL,
  `loginPwd` varchar(255) DEFAULT NULL,
  `realName` varchar(255) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records 
-- ----------------------------
INSERT INTO `gift` VALUES ('1','8GU盘','32'), ('2','飞利浦P520剃须刀','198'), ('3','京东商城20元无限制购物券','20'), ('4','IPad Mini 16G','1980'), ('5','小米3移动定制版','899'), ('6','联想平板电脑16G','1200'), ('7','IPad Mini 32G','2980'), ('8','红米3手机','1299'), ('9','QQ会员两年','180');
INSERT INTO `goods` VALUES ('1','红双喜乒乓球拍','上海','S12','120',''), ('2','特步跑步机','湖南','双人','3980',''), ('3','红尘一笑羽毛球拍','江苏','中号','199',''), ('4','篮球','上海','学生专用','78',''), ('5','耐克运动鞋','广州','均码','299',''), ('6','小米手机','北京','4','1800','');
INSERT INTO `orderdetail` VALUES ('3','6','2','1'), ('4','6','3','2'), ('5','7','2','1'), ('6','7','5','1'), ('7','8','2','2'), ('8','8','3','1');
INSERT INTO `orders` VALUES ('6','2','100','2014-05-14 10:24:34','0'), ('7','1','100','2014-06-23 16:13:34','0'), ('8','1','100','2014-09-15 15:55:21','0');
INSERT INTO `usergift` VALUES ('8','2','3'), ('9','1','3');
INSERT INTO `users` VALUES ('1','admin','123456','默认管理员','0'), ('2','test','123','李地二','0');
