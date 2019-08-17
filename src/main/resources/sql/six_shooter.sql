/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 80015
Source Host           : localhost:3306
Source Database       : six_shooter

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2019-08-17 20:22:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cookie_table
-- ----------------------------
DROP TABLE IF EXISTS `cookie_table`;
CREATE TABLE `cookie_table` (
  `user_id` int(11) NOT NULL,
  `cookie` varchar(255) COLLATE utf8_bin NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of cookie_table
-- ----------------------------
INSERT INTO `cookie_table` VALUES ('7', '1f346dbc-f20c-4b39-92d8-209ffdf1c22d', '2019-08-17 20:21:33', '2019-08-17 20:21:33');

-- ----------------------------
-- Table structure for user_table
-- ----------------------------
DROP TABLE IF EXISTS `user_table`;
CREATE TABLE `user_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) COLLATE utf8_bin NOT NULL,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `password` varchar(255) COLLATE utf8_bin NOT NULL,
  `avatar_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `bio` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user_table
-- ----------------------------
INSERT INTO `user_table` VALUES ('1', '1040230345@qq.com', '章杰松', 'root', null, '我是码农', null, null);
INSERT INTO `user_table` VALUES ('2', '123@qq.com', '章小松', 'root', null, null, '2019-08-17 02:38:45', '2019-08-17 02:38:45');
INSERT INTO `user_table` VALUES ('3', '123@qq.com', '章杰', 'root', null, null, '2019-08-17 02:41:20', '2019-08-17 02:41:20');
INSERT INTO `user_table` VALUES ('4', '1040230345@qq.ocm', '章小', 'root', null, null, '2019-08-17 02:53:55', '2019-08-17 02:53:55');
INSERT INTO `user_table` VALUES ('5', '111@qq.com', '章', '123', null, null, '2019-08-17 20:13:50', '2019-08-17 20:13:50');
INSERT INTO `user_table` VALUES ('6', '123@qq.co', '章a', 'root', null, null, '2019-08-17 20:17:35', '2019-08-17 20:17:35');
INSERT INTO `user_table` VALUES ('7', '123@qq.cop', '章da', 'root', null, null, '2019-08-17 20:21:33', '2019-08-17 20:21:33');
