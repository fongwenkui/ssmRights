/*
Navicat MySQL Data Transfer

Source Server         : localhost_mysql
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : qx

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-09-28 12:36:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` varchar(100) NOT NULL,
  `menuname` varchar(100) NOT NULL,
  `pid` varchar(100) NOT NULL,
  `code` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('028495d9297a40b699dc08e8df1eafd6', '用户列表', '1', 'user/list');
INSERT INTO `menu` VALUES ('0d1f1839f450460d8b3953e20ecb1315', '删除用户', '1', 'user/delete');
INSERT INTO `menu` VALUES ('1', '用户管理', '0', 'user');
INSERT INTO `menu` VALUES ('2f383f896a084fcea139be2a74af3d02', '修改用户', '1', 'user/update');
INSERT INTO `menu` VALUES ('427f3dbcb06347cdb1ec6c26b25edd47', '添加用户', '1', 'user/add');
INSERT INTO `menu` VALUES ('564128256fe64c70860507cfa0c3b855', '修改角色', 'b1dd4a57891f4fc2b4936a94b961bb81', 'role/update');
INSERT INTO `menu` VALUES ('59cd4b2b12ec465aade099d05bc7f9b2', '用户角色分配', 'f0cc106045914915a68a289e88d2ab2c', 'userRole/update');
INSERT INTO `menu` VALUES ('5a9086f484234226a5014e1a7bfe5e3f', '添加权限', 'a25898e9cce94a36922254ce71bbb423', 'menu/add');
INSERT INTO `menu` VALUES ('6c059653dc7e4bffa2f13b9ce2824f60', '角色权限分配', 'b9c7562ef9654361b6b5a83d4fa0fb78', 'roleMenu/update');
INSERT INTO `menu` VALUES ('89b105fd83a34d23a18fb0184fb827b7', '删除角色', 'b1dd4a57891f4fc2b4936a94b961bb81', 'role/delete');
INSERT INTO `menu` VALUES ('89e84292f0c84a689d2a1e642ab6aee3', '角色列表', 'b1dd4a57891f4fc2b4936a94b961bb81', 'role/list');
INSERT INTO `menu` VALUES ('9538549cd2a84a04ac45de250f011eff', '用户角色列表', 'f0cc106045914915a68a289e88d2ab2c', 'userRole/list');
INSERT INTO `menu` VALUES ('993793c63b4e435ab1801f9d93e49e02', '修改权限', 'a25898e9cce94a36922254ce71bbb423', 'menu/update');
INSERT INTO `menu` VALUES ('9a6c50d8e4c9416fa6e28b43fd8bab33', '权限列表', 'a25898e9cce94a36922254ce71bbb423', 'menu/list');
INSERT INTO `menu` VALUES ('a25898e9cce94a36922254ce71bbb423', '权限管理', '0', 'menu');
INSERT INTO `menu` VALUES ('b1dd4a57891f4fc2b4936a94b961bb81', '角色管理', '0', 'role');
INSERT INTO `menu` VALUES ('b6f4f6f842bf49749aae864083890444', '删除权限', 'a25898e9cce94a36922254ce71bbb423', 'menu/delete');
INSERT INTO `menu` VALUES ('b9c7562ef9654361b6b5a83d4fa0fb78', '权限分配', '0', 'roleMenu');
INSERT INTO `menu` VALUES ('f0cc106045914915a68a289e88d2ab2c', '角色分配', '0', 'userRole');
INSERT INTO `menu` VALUES ('f335a62912e34c7f8283a2bde51a764e', '角色权限列表', 'b9c7562ef9654361b6b5a83d4fa0fb78', 'roleMenu/list');
INSERT INTO `menu` VALUES ('f37ad7d173a049dfbf53d4c3825bed4d', '添加角色', 'b1dd4a57891f4fc2b4936a94b961bb81', 'role/add');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` varchar(100) NOT NULL,
  `rolename` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('582c48fc3e9747b3ac5fee065e21837b', '超级管理员');
INSERT INTO `role` VALUES ('97f93afdcd7e40e6ba2a6ba7c1dc7c6a', 'test');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `id` varchar(100) NOT NULL,
  `menid` varchar(100) NOT NULL,
  `roleid` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `menid` (`menid`),
  KEY `roleid` (`roleid`),
  CONSTRAINT `role_menu_ibfk_1` FOREIGN KEY (`menid`) REFERENCES `menu` (`id`),
  CONSTRAINT `role_menu_ibfk_2` FOREIGN KEY (`roleid`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES ('025cfe4f-f382-424c-915e-84545752e828', '028495d9297a40b699dc08e8df1eafd6', '97f93afdcd7e40e6ba2a6ba7c1dc7c6a');
INSERT INTO `role_menu` VALUES ('097a2a01-6092-4ddc-86b5-aca0db98e94b', '89b105fd83a34d23a18fb0184fb827b7', '582c48fc3e9747b3ac5fee065e21837b');
INSERT INTO `role_menu` VALUES ('0d378cd5-7869-446c-a5b6-53131f1d5c3d', '0d1f1839f450460d8b3953e20ecb1315', '582c48fc3e9747b3ac5fee065e21837b');
INSERT INTO `role_menu` VALUES ('30944667-7c0e-4fb7-a2b4-fbe1b0603feb', 'f335a62912e34c7f8283a2bde51a764e', '582c48fc3e9747b3ac5fee065e21837b');
INSERT INTO `role_menu` VALUES ('3ec04390-8f73-4911-ac53-85dceed958d1', 'b9c7562ef9654361b6b5a83d4fa0fb78', '582c48fc3e9747b3ac5fee065e21837b');
INSERT INTO `role_menu` VALUES ('3f2b298d-8f5e-4b14-80e9-434b79e7c4b2', 'b6f4f6f842bf49749aae864083890444', '582c48fc3e9747b3ac5fee065e21837b');
INSERT INTO `role_menu` VALUES ('4d54e9f9-2063-4926-923a-d6b5b09a76c9', '2f383f896a084fcea139be2a74af3d02', '582c48fc3e9747b3ac5fee065e21837b');
INSERT INTO `role_menu` VALUES ('58776bf8-3a2b-44ec-a38c-98b3cad74f74', '89e84292f0c84a689d2a1e642ab6aee3', '582c48fc3e9747b3ac5fee065e21837b');
INSERT INTO `role_menu` VALUES ('63158c89-e1b6-44b7-bacb-fe66b6ee7451', '5a9086f484234226a5014e1a7bfe5e3f', '582c48fc3e9747b3ac5fee065e21837b');
INSERT INTO `role_menu` VALUES ('652defbc-244e-410c-b970-255b6eff8619', '564128256fe64c70860507cfa0c3b855', '582c48fc3e9747b3ac5fee065e21837b');
INSERT INTO `role_menu` VALUES ('6665038e-439c-4c57-a200-428e0520c80f', '59cd4b2b12ec465aade099d05bc7f9b2', '582c48fc3e9747b3ac5fee065e21837b');
INSERT INTO `role_menu` VALUES ('79dc6ff1-484d-4e73-8b13-cfe8cf88744b', '028495d9297a40b699dc08e8df1eafd6', '582c48fc3e9747b3ac5fee065e21837b');
INSERT INTO `role_menu` VALUES ('8a5072a8-2089-429c-909b-56c7258bb756', '993793c63b4e435ab1801f9d93e49e02', '582c48fc3e9747b3ac5fee065e21837b');
INSERT INTO `role_menu` VALUES ('8f5dc88d-c314-4c0e-9d32-3f1a13917053', 'f0cc106045914915a68a289e88d2ab2c', '582c48fc3e9747b3ac5fee065e21837b');
INSERT INTO `role_menu` VALUES ('93396993-25b2-40a1-bbf5-b27cc8ee595f', '1', '582c48fc3e9747b3ac5fee065e21837b');
INSERT INTO `role_menu` VALUES ('9e1cacdc-ac92-4c31-8f8e-caa9f6c335c8', 'b1dd4a57891f4fc2b4936a94b961bb81', '582c48fc3e9747b3ac5fee065e21837b');
INSERT INTO `role_menu` VALUES ('af942161-543f-4096-9aeb-fd0d8a554491', '9538549cd2a84a04ac45de250f011eff', '582c48fc3e9747b3ac5fee065e21837b');
INSERT INTO `role_menu` VALUES ('b6420c52-afa9-41e3-b697-1b1ffead8da1', '1', '97f93afdcd7e40e6ba2a6ba7c1dc7c6a');
INSERT INTO `role_menu` VALUES ('b90cef4d-7d23-4d65-85ac-dc527736ef84', '0d1f1839f450460d8b3953e20ecb1315', '97f93afdcd7e40e6ba2a6ba7c1dc7c6a');
INSERT INTO `role_menu` VALUES ('c88ced9b-045d-4189-b880-fb7ea1b68831', 'a25898e9cce94a36922254ce71bbb423', '582c48fc3e9747b3ac5fee065e21837b');
INSERT INTO `role_menu` VALUES ('cbbfb63b-b967-4640-9472-d11978405f4a', '6c059653dc7e4bffa2f13b9ce2824f60', '582c48fc3e9747b3ac5fee065e21837b');
INSERT INTO `role_menu` VALUES ('d297d221-1f08-42b3-bb21-b1e37d0b412d', '9a6c50d8e4c9416fa6e28b43fd8bab33', '582c48fc3e9747b3ac5fee065e21837b');
INSERT INTO `role_menu` VALUES ('f866ad7d-8a33-4398-9396-f5d7448ed18f', '427f3dbcb06347cdb1ec6c26b25edd47', '582c48fc3e9747b3ac5fee065e21837b');
INSERT INTO `role_menu` VALUES ('fa625c4b-fce0-4e63-9256-8c63b2a48332', 'f37ad7d173a049dfbf53d4c3825bed4d', '582c48fc3e9747b3ac5fee065e21837b');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('40f8b41533374d89bbb1a57f3b2dd3d6', 'admin', 'admin');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` varchar(100) NOT NULL,
  `userid` varchar(100) NOT NULL,
  `roleid` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userid` (`userid`),
  KEY `roleid` (`roleid`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`id`),
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`roleid`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('f4441482-1766-4864-aa5b-c8b0e248b0ca', '40f8b41533374d89bbb1a57f3b2dd3d6', '582c48fc3e9747b3ac5fee065e21837b');
