
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for attribute
-- ----------------------------
DROP TABLE IF EXISTS `attribute`;
CREATE TABLE `attribute` (
  `hero_id` int(6) DEFAULT NULL,
  `hero_name` varchar(20) NOT NULL COMMENT '英雄名',
  `attack_speed` varchar(30) DEFAULT NULL COMMENT '攻速',
  `attack` varchar(30) DEFAULT NULL COMMENT '攻击力',
  `attack_distance` varchar(30) DEFAULT NULL COMMENT '攻击距离',
  `armor` varchar(30) DEFAULT NULL COMMENT '护甲',
  `hp` varchar(30) DEFAULT NULL COMMENT '血量',
  PRIMARY KEY (`hero_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for buff_content
-- ----------------------------
DROP TABLE IF EXISTS `buff_content`;
CREATE TABLE `buff_content` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `json` mediumtext NOT NULL COMMENT '内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=672 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='从外部获取的阵容数据';

-- ----------------------------
-- Table structure for hero
-- ----------------------------
DROP TABLE IF EXISTS `hero`;
CREATE TABLE `hero` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '名称',
  `level` tinyint(2) NOT NULL COMMENT '等级',
  `color` varchar(20) DEFAULT NULL,
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `match_count` bigint(10) DEFAULT NULL COMMENT '选择次数',
  `pick_rate` float(3,1) DEFAULT NULL COMMENT '选择率',
  `icon_img` varchar(255) DEFAULT NULL COMMENT '头像',
  `win_rate` float(3,1) DEFAULT NULL COMMENT '胜率',
  `avg_rank` float(2,1) DEFAULT NULL COMMENT '平均排名',
  `chess_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for relation_hero_tag
-- ----------------------------
DROP TABLE IF EXISTS `relation_hero_tag`;
CREATE TABLE `relation_hero_tag` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `h_name` varchar(20) DEFAULT NULL,
  `t_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `h_name` (`h_name`,`t_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=281 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `type` tinyint(2) DEFAULT NULL COMMENT '1-种族，2-职业',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for tag_effect
-- ----------------------------
DROP TABLE IF EXISTS `tag_effect`;
CREATE TABLE `tag_effect` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `tag_id` int(6) NOT NULL,
  `threshold` tinyint(2) NOT NULL COMMENT '人数门槛',
  `name` varchar(20) DEFAULT NULL,
  `detail` varchar(255) DEFAULT NULL COMMENT '特效描述',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `tag_id` (`tag_id`,`threshold`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for team
-- ----------------------------
DROP TABLE IF EXISTS `team`;
CREATE TABLE `team` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `b_id` int(8) NOT NULL COMMENT '对应buff_content表的主键',
  `hero_names` varchar(255) DEFAULT NULL,
  `buffs` varchar(255) DEFAULT NULL,
  `buff_key` varchar(255) DEFAULT NULL,
  `avg_rank` float(3,1) DEFAULT NULL COMMENT '平均排名',
  `match_count` bigint(10) DEFAULT NULL COMMENT '选择场次',
  `pick_rate` float(3,1) DEFAULT NULL COMMENT '选择率',
  `win_rate` float(3,1) DEFAULT NULL COMMENT '胜率',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13261 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
