/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80013
Source Host           : localhost:3306
Source Database       : zero_cloud

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2019-03-10 19:22:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for app_user
-- ----------------------------
DROP TABLE IF EXISTS `app_user`;
CREATE TABLE `app_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(60) NOT NULL COMMENT '密码',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `head_img_url` varchar(1024) DEFAULT NULL COMMENT '头像url',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `sex` tinyint(1) DEFAULT NULL COMMENT '性别',
  `enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态（1有效,0无效）',
  `type` varchar(16) NOT NULL COMMENT '类型（暂未用）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

-- ----------------------------
-- Records of app_user
-- ----------------------------
INSERT INTO `app_user` VALUES ('1', 'admin', '$2a$10$3uOoX1ps14CxuotogUoDreW8zXJOZB9XeGdrC/xDV36hhaE8Rn9HO', '测试1', '', '', '1', '1', 'APP', '2018-01-17 16:57:01', '2018-01-17 16:57:01');
INSERT INTO `app_user` VALUES ('11', '韩立', '$2a$10$fV3.42vsCFwE0ti37YzaG.nDqHOgQ7gUPlXmrtmPueq4gcBqBg2je', '韩立', null, '12312312311', '1', '1', 'APP', '2019-02-17 21:02:33', '2019-02-18 19:23:29');
INSERT INTO `app_user` VALUES ('14', 'zx', '$2a$10$xY2tbVhrZGaS3EMEbEkdTu9jA68dQaViH5VuahgzixJA.QouPjNHO', 'zx', null, null, '1', '0', 'APP', '2019-02-20 22:34:57', '2019-02-21 10:47:38');
INSERT INTO `app_user` VALUES ('15', '张三', '$2a$10$EGjRCuNDNiognB6kezK/fOUr03LCD18d/jCW20zPI4zQbagTl4Eiy', '张三', null, '12312312311', '1', '1', 'APP', '2019-03-09 21:31:59', '2019-03-09 22:00:57');
INSERT INTO `app_user` VALUES ('17', '关羽', '$2a$10$48HGAhDlAv.wUsxdl9Cqp.DVX6Zmhr0QaJVlwSijq6iJ0CHnmqtZC', 'gy666', null, '12345678912', '1', '1', 'APP', '2019-03-10 12:09:59', '2019-03-10 12:09:59');

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(128) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL COMMENT '密码',
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL COMMENT '支持的授权方式',
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL COMMENT 'access_token有效期（单位秒）',
  `refresh_token_validity` int(11) DEFAULT NULL COMMENT 'refresh_token有效期（单位秒）',
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='oauth2的client表';

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('system', null, '$2a$10$QN9vg9iX3WFovHnDX7bJO.rWWDkS0VP7HYhV.HDiVEE56xPwZfjKe', 'app', 'authorization_code,password,refresh_token', null, null, '28800', null, null, null);

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `code` varchar(128) NOT NULL COMMENT '临时code',
  `authentication` blob,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='授权码模式code表';

-- ----------------------------
-- Records of oauth_code
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL,
  `code` varchar(30) NOT NULL,
  `name` varchar(60) NOT NULL,
  `desc` varchar(120) DEFAULT NULL,
  `sort` int(6) NOT NULL DEFAULT '0',
  `is_leaf` tinyint(1) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx2_menu` (`code`) USING BTREE,
  KEY `idx1_menu` (`parent_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', 'system', '系统管理', '系统管理', '1', '0', '2019-02-07 10:00:10', '2019-02-07 09:59:39');
INSERT INTO `sys_menu` VALUES ('2', '1', 'upms', '权限管理', '权限管理', '2', '0', '2019-02-07 10:00:05', '2019-02-07 10:00:08');
INSERT INTO `sys_menu` VALUES ('3', '2', 'user', '用户管理', '用户管理', '3', '1', '2019-02-07 10:03:24', '2019-02-07 10:03:27');
INSERT INTO `sys_menu` VALUES ('4', '2', 'role', '角色管理', '角色管理', '4', '1', '2019-02-20 19:14:27', '2019-02-20 19:14:29');
INSERT INTO `sys_menu` VALUES ('5', '1', 'monitor', '系统监控', '系统监控', '5', '0', '2019-02-20 20:45:23', '2019-02-20 20:45:26');
INSERT INTO `sys_menu` VALUES ('6', '5', 'eureka', '服务监控', '服务监控', '6', '1', '2019-02-20 20:46:10', '2019-02-20 20:46:13');
INSERT INTO `sys_menu` VALUES ('7', '5', 'interface', '接口文档', '接口文档', '7', '1', '2019-02-20 20:46:51', '2019-02-20 20:46:55');
INSERT INTO `sys_menu` VALUES ('8', '5', 'zipkin', '服务跟踪', '服务跟踪', '8', '1', '2019-02-20 20:49:32', '2019-02-20 20:49:34');
INSERT INTO `sys_menu` VALUES ('9', '5', 'elk', '系统日志', '系统日志', '9', '1', '2019-02-20 20:49:20', '2019-02-20 20:49:22');
INSERT INTO `sys_menu` VALUES ('10', '5', 'log', '服务日志', '服务日志', '10', '1', '2019-03-09 22:27:07', '2019-03-09 22:27:09');

-- ----------------------------
-- Table structure for sys_menu_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_permission`;
CREATE TABLE `sys_menu_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx1_menu_permission` (`menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_menu_permission
-- ----------------------------
INSERT INTO `sys_menu_permission` VALUES ('1', '3', '1');
INSERT INTO `sys_menu_permission` VALUES ('2', '3', '2');
INSERT INTO `sys_menu_permission` VALUES ('3', '3', '3');
INSERT INTO `sys_menu_permission` VALUES ('4', '4', '7');
INSERT INTO `sys_menu_permission` VALUES ('5', '4', '8');
INSERT INTO `sys_menu_permission` VALUES ('6', '4', '9');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `permission` varchar(32) NOT NULL COMMENT '权限标识',
  `type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '权限类型 0菜单1按钮',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `desc` varchar(100) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `permission` (`permission`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限标识表';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', 'system:user:save', '1', '保存', '保存用户', '2019-02-07 09:56:57', '2019-02-07 09:57:00');
INSERT INTO `sys_permission` VALUES ('2', 'system:user:update', '1', '更新', '更新用户', '2019-02-07 09:57:57', '2019-02-07 09:57:59');
INSERT INTO `sys_permission` VALUES ('3', 'system:user:delete', '1', '删除', '删除用户', '2019-02-07 09:58:23', '2019-02-07 09:58:26');
INSERT INTO `sys_permission` VALUES ('4', 'system:user', '0', '用户管理', '查询用户', '2019-02-07 10:11:47', '2019-02-07 10:13:00');
INSERT INTO `sys_permission` VALUES ('5', 'system', '0', '系统管理', '查询系统管理', '2019-02-07 10:21:16', '2019-02-07 10:21:19');
INSERT INTO `sys_permission` VALUES ('6', 'system:role', '0', '角色管理', '查询角色', '2019-02-08 23:33:06', '2019-02-08 23:33:09');
INSERT INTO `sys_permission` VALUES ('7', 'system:role:save', '1', '保存', '保存角色', '2019-02-08 23:33:54', '2019-02-08 23:33:56');
INSERT INTO `sys_permission` VALUES ('8', 'system:role:update', '1', '更新', '更新角色', '2019-02-08 23:34:25', '2019-02-08 23:34:27');
INSERT INTO `sys_permission` VALUES ('9', 'system:role:delete', '1', '删除', '删除角色', '2019-02-08 23:35:24', '2019-02-08 23:35:27');
INSERT INTO `sys_permission` VALUES ('10', 'system:monitor', '0', '系统监控', '系统监控', '2019-02-20 20:51:12', '2019-02-20 20:51:15');
INSERT INTO `sys_permission` VALUES ('11', 'system:monitor:eureka', '0', '服务监控', '服务监控', '2019-02-20 20:52:59', '2019-02-20 20:52:59');
INSERT INTO `sys_permission` VALUES ('12', 'system:monitor:interface', '0', '接口文档', '接口文档', '2019-02-20 20:53:30', '2019-02-20 20:53:30');
INSERT INTO `sys_permission` VALUES ('13', 'system:monitor:zipkin', '0', '服务跟踪', '服务跟踪', '2019-02-20 20:53:39', '2019-02-20 20:53:39');
INSERT INTO `sys_permission` VALUES ('14', 'system:monitor:elk', '0', '系统日志', '系统日志', '2019-02-20 20:54:14', '2019-02-20 20:54:14');
INSERT INTO `sys_permission` VALUES ('15', 'system:monitor:log', '0', '服务日志', '服务日志', '2019-03-09 22:26:20', '2019-03-09 22:26:20');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `code` varchar(32) NOT NULL COMMENT '角色code',
  `name` varchar(50) NOT NULL COMMENT '角色名',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'SUPER_ADMIN', '超级管理员', '2018-01-19 20:32:16', '2018-01-19 20:32:18');
INSERT INTO `sys_role` VALUES ('5', '222', '111', '2019-02-09 18:24:38', '2019-02-09 18:24:38');
INSERT INTO `sys_role` VALUES ('7', 'common_role', '普通角色', '2019-03-09 21:29:58', '2019-03-09 21:29:58');
INSERT INTO `sys_role` VALUES ('8', 'permission_admin', '权限管理员', '2019-03-09 22:04:29', '2019-03-09 22:04:29');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `permission_id` int(11) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色权限关系表';

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '1');
INSERT INTO `sys_role_permission` VALUES ('1', '2');
INSERT INTO `sys_role_permission` VALUES ('1', '3');
INSERT INTO `sys_role_permission` VALUES ('1', '4');
INSERT INTO `sys_role_permission` VALUES ('1', '5');
INSERT INTO `sys_role_permission` VALUES ('1', '6');
INSERT INTO `sys_role_permission` VALUES ('1', '7');
INSERT INTO `sys_role_permission` VALUES ('1', '8');
INSERT INTO `sys_role_permission` VALUES ('1', '9');
INSERT INTO `sys_role_permission` VALUES ('1', '10');
INSERT INTO `sys_role_permission` VALUES ('1', '11');
INSERT INTO `sys_role_permission` VALUES ('1', '12');
INSERT INTO `sys_role_permission` VALUES ('1', '13');
INSERT INTO `sys_role_permission` VALUES ('1', '14');
INSERT INTO `sys_role_permission` VALUES ('1', '15');
INSERT INTO `sys_role_permission` VALUES ('1', '16');
INSERT INTO `sys_role_permission` VALUES ('1', '17');
INSERT INTO `sys_role_permission` VALUES ('1', '18');
INSERT INTO `sys_role_permission` VALUES ('1', '19');
INSERT INTO `sys_role_permission` VALUES ('1', '20');
INSERT INTO `sys_role_permission` VALUES ('1', '21');
INSERT INTO `sys_role_permission` VALUES ('1', '22');
INSERT INTO `sys_role_permission` VALUES ('1', '23');
INSERT INTO `sys_role_permission` VALUES ('1', '24');
INSERT INTO `sys_role_permission` VALUES ('1', '25');
INSERT INTO `sys_role_permission` VALUES ('1', '26');
INSERT INTO `sys_role_permission` VALUES ('1', '27');
INSERT INTO `sys_role_permission` VALUES ('1', '28');
INSERT INTO `sys_role_permission` VALUES ('1', '29');
INSERT INTO `sys_role_permission` VALUES ('1', '30');
INSERT INTO `sys_role_permission` VALUES ('1', '31');
INSERT INTO `sys_role_permission` VALUES ('1', '32');
INSERT INTO `sys_role_permission` VALUES ('1', '33');
INSERT INTO `sys_role_permission` VALUES ('1', '34');
INSERT INTO `sys_role_permission` VALUES ('1', '35');
INSERT INTO `sys_role_permission` VALUES ('5', '1');
INSERT INTO `sys_role_permission` VALUES ('5', '4');
INSERT INTO `sys_role_permission` VALUES ('5', '5');
INSERT INTO `sys_role_permission` VALUES ('5', '6');
INSERT INTO `sys_role_permission` VALUES ('5', '7');
INSERT INTO `sys_role_permission` VALUES ('7', '1');
INSERT INTO `sys_role_permission` VALUES ('7', '2');
INSERT INTO `sys_role_permission` VALUES ('7', '4');
INSERT INTO `sys_role_permission` VALUES ('7', '5');
INSERT INTO `sys_role_permission` VALUES ('7', '6');
INSERT INTO `sys_role_permission` VALUES ('7', '8');
INSERT INTO `sys_role_permission` VALUES ('7', '10');
INSERT INTO `sys_role_permission` VALUES ('7', '11');
INSERT INTO `sys_role_permission` VALUES ('8', '1');
INSERT INTO `sys_role_permission` VALUES ('8', '2');
INSERT INTO `sys_role_permission` VALUES ('8', '3');
INSERT INTO `sys_role_permission` VALUES ('8', '4');
INSERT INTO `sys_role_permission` VALUES ('8', '5');
INSERT INTO `sys_role_permission` VALUES ('8', '6');
INSERT INTO `sys_role_permission` VALUES ('8', '7');
INSERT INTO `sys_role_permission` VALUES ('8', '8');

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色用户关系表';

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES ('1', '1');
INSERT INTO `sys_role_user` VALUES ('1', '5');
INSERT INTO `sys_role_user` VALUES ('11', '1');
INSERT INTO `sys_role_user` VALUES ('11', '5');
INSERT INTO `sys_role_user` VALUES ('15', '5');
INSERT INTO `sys_role_user` VALUES ('15', '7');
INSERT INTO `sys_role_user` VALUES ('17', '7');
INSERT INTO `sys_role_user` VALUES ('17', '8');

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `module` varchar(100) NOT NULL COMMENT '模块名',
  `params` text COMMENT '方法参数',
  `remark` text COMMENT '备注',
  `flag` tinyint(1) NOT NULL COMMENT '是否成功(1成功，0失败)',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `username` (`username`),
  KEY `createTime` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='日志表';

-- ----------------------------
-- Records of t_log
-- ----------------------------
INSERT INTO `t_log` VALUES ('1', 'admin', '登陆', null, '用户名密码登陆', '1', '2019-02-05 00:37:12');
INSERT INTO `t_log` VALUES ('2', 'admin', '分配权限', '{\"id\":7,\"permissionIds\":[null,1,2,4,5,6,8,10,11]}', null, '1', '2019-03-09 22:00:32');
INSERT INTO `t_log` VALUES ('3', 'admin', '修改用户', '{\"appUser\":{\"createTime\":1552138319000,\"enabled\":true,\"id\":15,\"nickname\":\"张三\",\"password\":\"\",\"phone\":\"12312312311\",\"sex\":1,\"type\":\"APP\",\"updateTime\":1552138319000,\"username\":\"张三\"}}', null, '1', '2019-03-09 22:00:57');
INSERT INTO `t_log` VALUES ('4', 'admin', '添加角色', '{\"sysRole\":{\"code\":\"permission_admin\",\"name\":\"权限管理员\"}}', null, '1', '2019-03-09 22:04:29');
INSERT INTO `t_log` VALUES ('5', 'admin', '分配权限', '{\"id\":8,\"permissionIds\":[null,1,2,3,4,5,6,7,8,9]}', null, '1', '2019-03-09 22:04:38');
INSERT INTO `t_log` VALUES ('6', 'admin', '登陆', null, '用户名密码登陆', '1', '2019-03-09 23:07:01');
INSERT INTO `t_log` VALUES ('7', 'admin', '登陆', null, '用户名密码登陆', '1', '2019-03-09 23:09:40');
INSERT INTO `t_log` VALUES ('8', 'admin', '登陆', null, '用户名密码登陆', '1', '2019-03-10 09:02:50');
INSERT INTO `t_log` VALUES ('9', 'admin', '登陆', null, '用户名密码登陆', '1', '2019-03-10 10:32:34');
INSERT INTO `t_log` VALUES ('10', 'admin', '分配权限', '{\"id\":8,\"permissionIds\":[null,1,2,3,4,5,6,7,8]}', null, '1', '2019-03-10 11:42:18');
INSERT INTO `t_log` VALUES ('11', 'admin', '登陆', null, '用户名密码登陆', '1', '2019-03-10 11:56:28');

-- ----------------------------
-- Table structure for t_sms
-- ----------------------------
DROP TABLE IF EXISTS `t_sms`;
CREATE TABLE `t_sms` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(16) NOT NULL COMMENT '手机号码',
  `sign_name` varchar(128) DEFAULT NULL COMMENT '阿里云短信签名',
  `template_code` varchar(128) DEFAULT NULL COMMENT '阿里云模板code',
  `params` varchar(500) DEFAULT NULL COMMENT '参数',
  `bizId` varchar(128) DEFAULT NULL COMMENT '阿里云返回的',
  `code` varchar(64) DEFAULT NULL COMMENT '阿里云返回的code',
  `message` varchar(128) DEFAULT NULL COMMENT '阿里云返回的',
  `day` date NOT NULL COMMENT '日期（冗余字段,便于统计某天的发送次数）',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `phone` (`phone`),
  KEY `day` (`day`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='发短信记录表';

-- ----------------------------
-- Records of t_sms
-- ----------------------------

-- ----------------------------
-- Table structure for t_wechat
-- ----------------------------
DROP TABLE IF EXISTS `t_wechat`;
CREATE TABLE `t_wechat` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `openid` varchar(128) NOT NULL COMMENT '微信openid',
  `unionid` varchar(128) DEFAULT NULL COMMENT '微信unionid',
  `userId` int(11) DEFAULT NULL COMMENT '绑定用户的id',
  `app` varchar(32) NOT NULL COMMENT '公众号标识',
  `nickname` varchar(64) NOT NULL COMMENT '微信昵称',
  `sex` varchar(16) DEFAULT NULL COMMENT '微信返回的性别',
  `province` varchar(64) DEFAULT NULL COMMENT '微信返回的省',
  `city` varchar(64) DEFAULT NULL COMMENT '微信返回的城市',
  `country` varchar(64) DEFAULT NULL COMMENT '微信返回的国家',
  `headimg_url` varchar(1024) DEFAULT NULL COMMENT '微信头像',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `openid` (`openid`),
  KEY `userId` (`userId`),
  KEY `unionid` (`unionid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='微信信息表';

-- ----------------------------
-- Records of t_wechat
-- ----------------------------

-- ----------------------------
-- Table structure for user_credentials
-- ----------------------------
DROP TABLE IF EXISTS `user_credentials`;
CREATE TABLE `user_credentials` (
  `username` varchar(50) NOT NULL COMMENT '用户名或手机号等',
  `type` varchar(16) NOT NULL COMMENT '账号类型（用户名、手机号）',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`username`),
  KEY `userId` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户凭证表';

-- ----------------------------
-- Records of user_credentials
-- ----------------------------
INSERT INTO `user_credentials` VALUES ('admin', 'USERNAME', '1');
INSERT INTO `user_credentials` VALUES ('zx', 'USERNAME', '14');
INSERT INTO `user_credentials` VALUES ('关羽', 'USERNAME', '17');
INSERT INTO `user_credentials` VALUES ('张三', 'USERNAME', '15');
INSERT INTO `user_credentials` VALUES ('韩立', 'USERNAME', '11');

-- ----------------------------
-- Table structure for zipkin_annotations
-- ----------------------------
DROP TABLE IF EXISTS `zipkin_annotations`;
CREATE TABLE `zipkin_annotations` (
  `trace_id_high` bigint(20) NOT NULL DEFAULT '0' COMMENT 'If non zero, this means the trace uses 128 bit traceIds instead of 64 bit',
  `trace_id` bigint(20) NOT NULL COMMENT 'coincides with zipkin_spans.trace_id',
  `span_id` bigint(20) NOT NULL COMMENT 'coincides with zipkin_spans.id',
  `a_key` varchar(255) NOT NULL COMMENT 'BinaryAnnotation.key or Annotation.value if type == -1',
  `a_value` blob COMMENT 'BinaryAnnotation.value(), which must be smaller than 64KB',
  `a_type` int(11) NOT NULL COMMENT 'BinaryAnnotation.type() or -1 if Annotation',
  `a_timestamp` bigint(20) DEFAULT NULL COMMENT 'Used to implement TTL; Annotation.timestamp or zipkin_spans.timestamp',
  `endpoint_ipv4` int(11) DEFAULT NULL COMMENT 'Null when Binary/Annotation.endpoint is null',
  `endpoint_ipv6` binary(16) DEFAULT NULL COMMENT 'Null when Binary/Annotation.endpoint is null, or no IPv6 address',
  `endpoint_port` smallint(6) DEFAULT NULL COMMENT 'Null when Binary/Annotation.endpoint is null',
  `endpoint_service_name` varchar(255) DEFAULT NULL COMMENT 'Null when Binary/Annotation.endpoint is null',
  UNIQUE KEY `trace_id_high` (`trace_id_high`,`trace_id`,`span_id`,`a_key`,`a_timestamp`) COMMENT 'Ignore insert on duplicate',
  KEY `trace_id_high_2` (`trace_id_high`,`trace_id`,`span_id`) COMMENT 'for joining with zipkin_spans',
  KEY `trace_id_high_3` (`trace_id_high`,`trace_id`) COMMENT 'for getTraces/ByIds',
  KEY `endpoint_service_name` (`endpoint_service_name`) COMMENT 'for getTraces and getServiceNames',
  KEY `a_type` (`a_type`) COMMENT 'for getTraces',
  KEY `a_key` (`a_key`) COMMENT 'for getTraces'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPRESSED;

-- ----------------------------
-- Records of zipkin_annotations
-- ----------------------------

-- ----------------------------
-- Table structure for zipkin_dependencies
-- ----------------------------
DROP TABLE IF EXISTS `zipkin_dependencies`;
CREATE TABLE `zipkin_dependencies` (
  `day` date NOT NULL,
  `parent` varchar(255) NOT NULL,
  `child` varchar(255) NOT NULL,
  `call_count` bigint(20) DEFAULT NULL,
  UNIQUE KEY `day` (`day`,`parent`,`child`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPRESSED;

-- ----------------------------
-- Records of zipkin_dependencies
-- ----------------------------

-- ----------------------------
-- Table structure for zipkin_spans
-- ----------------------------
DROP TABLE IF EXISTS `zipkin_spans`;
CREATE TABLE `zipkin_spans` (
  `trace_id_high` bigint(20) NOT NULL DEFAULT '0' COMMENT 'If non zero, this means the trace uses 128 bit traceIds instead of 64 bit',
  `trace_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `debug` bit(1) DEFAULT NULL,
  `start_ts` bigint(20) DEFAULT NULL COMMENT 'Span.timestamp(): epoch micros used for endTs query and to implement TTL',
  `duration` bigint(20) DEFAULT NULL COMMENT 'Span.duration(): micros used for minDuration and maxDuration query',
  UNIQUE KEY `trace_id_high` (`trace_id_high`,`trace_id`,`id`) COMMENT 'ignore insert on duplicate',
  KEY `trace_id_high_2` (`trace_id_high`,`trace_id`,`id`) COMMENT 'for joining with zipkin_annotations',
  KEY `trace_id_high_3` (`trace_id_high`,`trace_id`) COMMENT 'for getTracesByIds',
  KEY `name` (`name`) COMMENT 'for getTraces and getSpanNames',
  KEY `start_ts` (`start_ts`) COMMENT 'for getTraces ordering and range'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPRESSED;

-- ----------------------------
-- Records of zipkin_spans
-- ----------------------------
