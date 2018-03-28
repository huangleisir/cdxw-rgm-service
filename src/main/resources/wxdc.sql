/*
Navicat MySQL Data Transfer

Source Server         : 10.101.130.6(dev)
Source Server Version : 50632
Source Host           : 10.101.130.6:3306
Source Database       : wxdc

Target Server Type    : MYSQL
Target Server Version : 50632
File Encoding         : 65001

Date: 2018-03-28 14:14:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_addr`
-- ----------------------------
DROP TABLE IF EXISTS `t_addr`;
CREATE TABLE `t_addr` (
  `id` varchar(20) NOT NULL DEFAULT '',
  `open_id` varchar(60) DEFAULT NULL,
  `addr` varchar(400) DEFAULT NULL COMMENT '地址',
  `name` varchar(40) DEFAULT NULL COMMENT '收货人称呼',
  `sex` tinyint(1) DEFAULT NULL COMMENT '性别1男2女',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(1) DEFAULT '0' COMMENT '0代表正常，1代表删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_addr
-- ----------------------------
INSERT INTO `t_addr` VALUES ('11', '666666666', '北海紫光阁1号院707房间门口', '苏女士', '2', '13510115899', '1');
INSERT INTO `t_addr` VALUES ('2', '666666666', '北海紫光阁1号院707房间门口', '苏女士', '2', '13510115899', '0');
INSERT INTO `t_addr` VALUES ('3', '666666666', '北海紫光阁1号院707房间门口', '苏女士', '2', '13510115899', '0');
INSERT INTO `t_addr` VALUES ('4', '666666666', '北海紫光阁1号院707房间门口', '苏女士', '2', '13510115899', '0');
INSERT INTO `t_addr` VALUES ('420988130766618624', 'openid3453453533', '深圳市紫光阁', '苏女士', '0', '152', '0');
INSERT INTO `t_addr` VALUES ('421666531189981184', 'openid3453453533', '北海紫光阁1号院707房间门口', 'lijun', '0', '152', '0');
INSERT INTO `t_addr` VALUES ('422715833525993472', 'openid3453453533', '深圳市紫光阁', '苏女士', '0', '152', '0');
INSERT INTO `t_addr` VALUES ('422717346168176640', 'openid3453453533', '北海紫光阁1号院707房间门口', 'lijun', '0', '152', '0');
INSERT INTO `t_addr` VALUES ('422719243243487232', 'openid3453453533', '深圳市紫光阁', '苏女士', '0', '152', '0');
INSERT INTO `t_addr` VALUES ('423164672108134400', '1', '湖南省郴州124123', 'lijun23123', '1', '152123123', '0');
INSERT INTO `t_addr` VALUES ('423167967002361856', '1', '湖南省郴州gb gwerwe', '李', '1', '152123123', '0');
INSERT INTO `t_addr` VALUES ('423171024398319616', 'oTj1BwgtIy-O3TdUXf2Efx4xzofk', '龙尾路10号', 'max', '1', '13425155396', '0');
INSERT INTO `t_addr` VALUES ('423196493072039936', 'oTj1BwmU9usDeONtSvi1bPHWXRgY', '咯经 logo going 哦', '李', '2', '18825265187', '1');
INSERT INTO `t_addr` VALUES ('423198973663117312', 'oTj1BwmU9usDeONtSvi1bPHWXRgY', '急急急急急', '111111111111111', '1', '15455454515', '0');
INSERT INTO `t_addr` VALUES ('423199617899823104', 'oTj1BwmU9usDeONtSvi1bPHWXRgY', '可怜了咯咯', 'mojljjkkkl', '2', '52555666', '0');
INSERT INTO `t_addr` VALUES ('427858943901958144', '666666666', 'uwerv', 'ncrj', '0', '15207301440', '0');
INSERT INTO `t_addr` VALUES ('5', '666666666', '北海紫光阁1号院707房间门口', '苏女士', '2', '13510115899', '0');
INSERT INTO `t_addr` VALUES ('6', '666666666', '北海紫光阁1号院707房间门口', '苏女士', '2', '13510115899', '0');
INSERT INTO `t_addr` VALUES ('7', '666666666', '北海紫光阁1号院707房间门口', '苏女士', '2', '13510115899', '0');
INSERT INTO `t_addr` VALUES ('8', '666666666', '北海紫光阁1号院707房间门口', '苏女士', '2', '13510115899', '0');
INSERT INTO `t_addr` VALUES ('9', '666666666', '北海紫光阁1号院707房间门口', '苏女士', '2', '13510115899', '0');

-- ----------------------------
-- Table structure for `t_food`
-- ----------------------------
DROP TABLE IF EXISTS `t_food`;
CREATE TABLE `t_food` (
  `food_id` varchar(20) NOT NULL,
  `type_id` varchar(20) NOT NULL COMMENT '关联t_food_type的id',
  `food_name` varchar(100) NOT NULL COMMENT '菜品名称',
  `pic_path` varchar(400) DEFAULT NULL COMMENT '图片地址',
  `sale_price` int(10) DEFAULT NULL COMMENT '售价分',
  `old_price` int(10) DEFAULT NULL COMMENT '原价分',
  `status` tinyint(1) DEFAULT NULL COMMENT '0删除1正常2售罄',
  `spice_level` tinyint(1) DEFAULT NULL COMMENT '辣度等级1微辣，2少辣，3多辣',
  `mark` varchar(200) DEFAULT NULL COMMENT '备注',
  `commend_mark` char(1) DEFAULT NULL COMMENT '推荐标识1招牌价2今日特价3特别推荐',
  `latest_update_time` datetime DEFAULT NULL COMMENT '最近更新时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`food_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_food
-- ----------------------------
INSERT INTO `t_food` VALUES ('010003', '01', '123', 'http://10.101.130.21/group1/M00/0E/C2/CmWCFFqmMsWAMLqwAAMmvjZtZhQ039.jpg', '123', '123', '0', '3', '12', '1', '2018-03-15 15:11:56', null);
INSERT INTO `t_food` VALUES ('01001', '01', '茄角之恋', 'http://10.101.130.21/group1/M00/0E/D1/CmWCFFqmTuiAUQYJAAMmvjZtZhQ222.jpg', '0', '0', '0', '3', '吃货必点', '1', '2018-03-15 15:12:02', null);
INSERT INTO `t_food` VALUES ('01002', '01', '清炒土豆丝', 'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3256360533,4264795697&fm=27&gp=0.jpg', '2600', '2900', '0', '1', '好吃不贵', '1', '2018-03-15 15:12:31', null);
INSERT INTO `t_food` VALUES ('01003', '07', '123', 'http://10.101.130.21/group1/M00/0E/C8/CmWCFFqmSQCAK3NqAARLBTT_EwQ225.jpg', '100', '100', '1', '3', '123', '1', '2018-03-15 15:12:19', null);
INSERT INTO `t_food` VALUES ('01004', '01', '123', 'http://10.101.130.21/group1/M00/0E/CA/CmWCFFqmSlOAe7crAAMmvjZtZhQ330.jpg', '123', '123', '1', '3', '123', '1', '2018-03-15 15:12:29', null);
INSERT INTO `t_food` VALUES ('01005', '01', '123', 'http://10.101.130.21/group1/M00/0E/CB/CmWCFFqmSp2AU7cNAAP_kA2eNYQ679.jpg', '56100', '0', '1', '3', '12', '1', '2018-03-16 16:56:57', null);
INSERT INTO `t_food` VALUES ('01006', '01', '354', 'http://10.101.130.21/group1/M00/0E/CC/CmWCFFqmSseASVeQAAMmvjZtZhQ027.jpg', '35', '35', '1', '2', '35', '2', '2018-03-15 15:12:16', null);
INSERT INTO `t_food` VALUES ('01007', '01', '1', 'http://121.34.253.98:20007/group1/M00/0F/08/CmWCFFqp1wKAbhrUAACM0WVJbhQ877.png', '100', '100', '1', null, '', null, '2018-03-15 15:12:21', null);
INSERT INTO `t_food` VALUES ('06001', '02', '夫妻肺片', 'http://10.101.130.21/group1/M00/0E/DE/CmWCFFqnn5WABqLiAACtwd9nelM663.jpg', '20', '18', '1', '2', '无', '1', '2018-03-15 15:12:26', null);
INSERT INTO `t_food` VALUES ('07001', '07', '1', 'http://121.34.253.98:20007/group1/M00/0F/0D/CmWCFFqqHHyAVSIoAACM0WVJbhQ274.png', '100', '100', '1', null, '', null, '2018-03-15 15:15:42', null);
INSERT INTO `t_food` VALUES ('08001', '09', '绿萝', 'http://10.101.130.21/group1/M00/0E/E7/CmWCFFqohlKAEzXiAAM7lvobu6Q945.jpg', '11', '22', '1', null, '', '1', '2018-03-15 15:12:13', null);
INSERT INTO `t_food` VALUES ('08002', '08', '4', 'http://121.34.253.98:20007/group1/M00/0F/07/CmWCFFqp1nqAF3_fAACM0WVJbhQ651.png', '40000', '40000', '1', null, '', null, '2018-03-15 18:12:24', null);
INSERT INTO `t_food` VALUES ('08003', '08', '54', 'http://121.34.253.98:20007/group1/M00/0F/09/CmWCFFqp2d2AT2R6AAN7pFNQKFE718.jpg', '2500', '24500', '1', null, '245', '1', '2018-03-15 15:12:10', null);
INSERT INTO `t_food` VALUES ('09001', '09', 'xxx', 'http://10.101.130.21/group1/M00/0F/8F/CmWCFFqyL_6AW2lwAAvf07g1nfM387.png', '1111', '2000', '0', '2', 'ncudsbv ', '2', '2018-03-21 18:16:38', '2018-03-21 18:16:38');
INSERT INTO `t_food` VALUES ('09002', '09', 'liii', 'http://10.101.130.21/group1/M00/0F/93/CmWCFFqyMeaAIx4yAAvf07g1nfM110.png', '1111', '2000', '0', '2', 'cxervr', '2', '2018-03-21 18:24:46', '2018-03-21 18:24:46');
INSERT INTO `t_food` VALUES ('09003', '09', 'woshihaoren', 'http://10.101.130.21/group1/M00/0F/95/CmWCFFqyMguAd17pAAvf07g1nfM725.png', '1111', '1200', '0', '2', 'cd ', '2', '2018-03-21 18:25:23', '2018-03-21 18:25:23');
INSERT INTO `t_food` VALUES ('09004', '09', 'vhrweu8gvberuvgb', 'http://10.101.130.21/group1/M00/0F/97/CmWCFFqyMl2APWEXAAvf07g1nfM197.png', '2222', '3000', '0', '2', 'vrevb', '2', '2018-03-21 18:26:45', '2018-03-21 18:26:45');
INSERT INTO `t_food` VALUES ('09005', '09', 'vrgt', 'http://10.101.130.21/group1/M00/0F/F4/CmWCFFqzhuOACBdXAALU5JC1Ktk681.png', '2200', '3300', '0', '2', '3', '2', '2018-03-22 18:39:39', '2018-03-22 18:39:39');
INSERT INTO `t_food` VALUES ('12001', '12', '156', 'http://10.101.130.21/group1/M00/10/1B/CmWCFFq4bWGAKwThAAMmvjZtZhQ762.jpg', '15600', '500', '1', '2', '1898', null, '2018-03-26 13:53:44', '2018-03-26 11:52:15');
INSERT INTO `t_food` VALUES ('12002', '12', '499', 'http://10.101.130.21/group1/M00/10/1C/CmWCFFq4bZ2AYHK3AAMmvjZtZhQ274.jpg', '16516500', '61600', '0', null, '', null, '2018-03-26 11:53:15', '2018-03-26 11:53:15');
INSERT INTO `t_food` VALUES ('12003', '12', '345', 'http://10.101.130.21/group1/M00/10/2F/CmWCFFq6AAqADfp5AAcbJZEsOEE694.jpg', '43500', '3500', '1', '3', '', '2', '2018-03-27 16:30:16', '2018-03-26 11:53:37');

-- ----------------------------
-- Table structure for `t_food_type`
-- ----------------------------
DROP TABLE IF EXISTS `t_food_type`;
CREATE TABLE `t_food_type` (
  `type_id` varchar(20) NOT NULL COMMENT '分类ID',
  `type_name` varchar(20) NOT NULL COMMENT '分类名称(冷菜，热菜，饮品)',
  `type_index` int(10) DEFAULT NULL COMMENT '排序',
  `status` tinyint(1) DEFAULT NULL COMMENT '0禁用1启用',
  `mark` varchar(100) DEFAULT NULL COMMENT '备注',
  `latest_update_time` datetime DEFAULT NULL COMMENT '最近更新时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_food_type
-- ----------------------------
INSERT INTO `t_food_type` VALUES ('01', '热菜', '11', '1', '热的烫舌头', null, null);
INSERT INTO `t_food_type` VALUES ('02', '凉菜', '12', '1', '凉的冻住嘴', null, null);
INSERT INTO `t_food_type` VALUES ('03', '饮品', '13', '1', '喝点好喝的', null, null);
INSERT INTO `t_food_type` VALUES ('04', '165', '15156', '2', '1561', null, null);
INSERT INTO `t_food_type` VALUES ('05', '47878', '894', '0', '49898', null, null);
INSERT INTO `t_food_type` VALUES ('06', '主食', null, '1', '好吃', null, null);
INSERT INTO `t_food_type` VALUES ('07', '水果', null, '1', '', '2018-03-21 17:16:11', null);
INSERT INTO `t_food_type` VALUES ('08', '蔬菜', null, '1', '', null, null);
INSERT INTO `t_food_type` VALUES ('09', '树叶', null, '1', '', '2018-03-26 10:34:38', null);
INSERT INTO `t_food_type` VALUES ('10', '肉类', '5', '1', '', '2018-03-27 16:27:30', null);
INSERT INTO `t_food_type` VALUES ('11', '鱼类', '9', '1', '', null, null);
INSERT INTO `t_food_type` VALUES ('12', '鲜花', '4', '1', '', null, null);
INSERT INTO `t_food_type` VALUES ('13', '盆栽', '15', '1', '', null, null);
INSERT INTO `t_food_type` VALUES ('14', '钉钉', '17', '1', '', null, null);
INSERT INTO `t_food_type` VALUES ('15', '干货', '21', '1', '', null, null);
INSERT INTO `t_food_type` VALUES ('16', '快餐', '22', '1', '', '2018-03-21 18:10:29', null);
INSERT INTO `t_food_type` VALUES ('17', '西餐', '23', '1', '', null, null);
INSERT INTO `t_food_type` VALUES ('18', '1', null, '1', '', null, null);
INSERT INTO `t_food_type` VALUES ('19', 'vrevrte', null, '2', 'ce', '2018-03-21 18:32:14', '2018-03-21 18:32:14');
INSERT INTO `t_food_type` VALUES ('20', 'vf3ev', null, '2', 'gv45', '2018-03-21 19:29:05', '2018-03-21 19:29:05');
INSERT INTO `t_food_type` VALUES ('21', 'huierh', null, '2', '', '2018-03-22 18:13:12', '2018-03-22 18:13:12');
INSERT INTO `t_food_type` VALUES ('22', '51', null, '1', '', '2018-03-26 10:25:45', '2018-03-26 10:25:45');
INSERT INTO `t_food_type` VALUES ('23', '563', null, '1', '', '2018-03-26 10:29:56', '2018-03-26 10:29:56');

-- ----------------------------
-- Table structure for `t_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `menu_id` int(10) NOT NULL COMMENT '菜单ID',
  `menu_name` varchar(100) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(255) DEFAULT NULL COMMENT '连接URL',
  `parent_id` varchar(100) DEFAULT NULL COMMENT '父节点',
  `open` varchar(50) DEFAULT 'true' COMMENT '是否展开',
  `position` varchar(50) DEFAULT NULL COMMENT '页面 1 操作 0',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '菜单创建时间',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('0', '所有权限', null, '-1', 'true', '1', '2018-03-22 15:07:45');
INSERT INTO `t_menu` VALUES ('10', '订单管理', null, '0', 'true', '1', '2018-03-23 11:35:00');
INSERT INTO `t_menu` VALUES ('11', '订单列表', 'index.html', '10', '', '1', '2018-03-26 17:25:08');
INSERT INTO `t_menu` VALUES ('100', '菜品设置', null, '0', 'true', '1', '2018-03-23 14:06:46');
INSERT INTO `t_menu` VALUES ('101', '分类管理', 'classify_lists.html', '100', '', '1', '2018-03-26 17:25:09');
INSERT INTO `t_menu` VALUES ('102', '菜品管理', 'dishes_lists.html', '100', '', '1', '2018-03-26 17:25:10');
INSERT INTO `t_menu` VALUES ('200', '系统管理', null, '0', 'true', '1', '2018-03-23 11:38:52');
INSERT INTO `t_menu` VALUES ('201', '用户管理', 'user_lists.html', '200', '', '1', '2018-03-26 17:25:11');
INSERT INTO `t_menu` VALUES ('202', '系统参数', 'system_param.html', '200', '', '1', '2018-03-26 17:25:13');

-- ----------------------------
-- Table structure for `t_menu_ctrl`
-- ----------------------------
DROP TABLE IF EXISTS `t_menu_ctrl`;
CREATE TABLE `t_menu_ctrl` (
  `ctrl_id` int(50) NOT NULL AUTO_INCREMENT COMMENT '关联ID',
  `menu_id` varchar(50) DEFAULT NULL COMMENT '菜单ID',
  `id` varchar(50) DEFAULT NULL COMMENT '用户编号',
  `creater` varchar(100) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(100) DEFAULT NULL COMMENT '更新人',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`ctrl_id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu_ctrl
-- ----------------------------
INSERT INTO `t_menu_ctrl` VALUES ('1', '0', '01', 'admin', '2018-03-27 09:55:22', 'admin', '2018-03-27 09:55:22');
INSERT INTO `t_menu_ctrl` VALUES ('2', '10', '01', 'admin', '2018-03-27 09:55:25', 'admin', '2018-03-27 09:55:25');
INSERT INTO `t_menu_ctrl` VALUES ('3', '11', '01', 'admin', '2018-03-27 09:55:26', 'admin', '2018-03-27 09:55:26');
INSERT INTO `t_menu_ctrl` VALUES ('4', '100', '01', 'admin', '2018-03-27 09:55:27', 'admin', '2018-03-27 09:55:27');
INSERT INTO `t_menu_ctrl` VALUES ('5', '101', '01', 'admin', '2018-03-27 09:55:29', 'admin', '2018-03-27 09:55:29');
INSERT INTO `t_menu_ctrl` VALUES ('6', '102', '01', 'admin', '2018-03-27 09:55:30', 'admin', '2018-03-27 09:55:30');
INSERT INTO `t_menu_ctrl` VALUES ('7', '200', '01', 'admin', '2018-03-27 09:55:31', 'admin', '2018-03-27 09:55:31');
INSERT INTO `t_menu_ctrl` VALUES ('8', '201', '01', 'admin', '2018-03-27 09:55:33', 'admin', '2018-03-27 09:55:33');
INSERT INTO `t_menu_ctrl` VALUES ('9', '202', '01', 'admin', '2018-03-27 09:55:36', 'admin', '2018-03-27 09:55:36');
INSERT INTO `t_menu_ctrl` VALUES ('65', '0', '02', 'admin', '2018-03-27 16:15:28', 'admin', '2018-03-27 16:15:28');
INSERT INTO `t_menu_ctrl` VALUES ('66', '200', '02', 'admin', '2018-03-27 16:15:28', 'admin', '2018-03-27 16:15:28');
INSERT INTO `t_menu_ctrl` VALUES ('67', '201', '02', 'admin', '2018-03-27 16:15:28', 'admin', '2018-03-27 16:15:28');

-- ----------------------------
-- Table structure for `t_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` varchar(20) NOT NULL,
  `open_id` varchar(60) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '下单时间',
  `pay_type` varchar(40) DEFAULT NULL COMMENT '支付类型1全网通2餐到付款',
  `pay_status` tinyint(1) DEFAULT NULL COMMENT '0未支付1已支付2关闭',
  `print_status` tinyint(1) DEFAULT NULL COMMENT '打印状态0未打印1已打印',
  `mark` varchar(200) DEFAULT NULL COMMENT '备注',
  `send_status` tinyint(1) DEFAULT NULL COMMENT '0未确认1已确认2已完成',
  `food_amount` int(10) DEFAULT NULL COMMENT '订单金额',
  `send_amount` int(10) DEFAULT NULL COMMENT '配送金额',
  `addr_id` varchar(20) DEFAULT NULL COMMENT '地址表id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('12', '1', '2018-03-13 09:24:25', '1', '1', '1', '备注点啥呢', '2', '1', '1', '1');
INSERT INTO `t_order` VALUES ('421615515891400704', 'openId123', '2018-03-13 09:16:34', '1', '0', '0', 'mark一下', '2', '10', '0', '334');
INSERT INTO `t_order` VALUES ('421738460299132928', 'openId123', '2018-03-12 14:08:20', '1', '0', '0', 'mark一下', '0', '10800', '0', '1');
INSERT INTO `t_order` VALUES ('422676293100765184', 'openId123', '2018-03-12 14:08:20', '1', '0', '0', 'mark一下', '0', '10800', '0', '1');
INSERT INTO `t_order` VALUES ('422676674195226624', 'openId123', '2018-03-12 14:08:20', '1', '0', '0', 'mark一下', '0', '10800', '0', '1');
INSERT INTO `t_order` VALUES ('422678526462787584', 'openId456', '2018-03-12 14:08:20', '1', '0', '0', 'mark一下', '0', '10800', '0', '1');
INSERT INTO `t_order` VALUES ('422684841788571648', 'openId456', '2018-03-12 14:08:20', '1', '0', '0', 'mark一下', '0', '10800', '0', '1');
INSERT INTO `t_order` VALUES ('422689006480785408', 'openId789', '2018-03-12 14:08:20', '1', '0', '0', 'mark一下', '0', '10800', '0', '1');
INSERT INTO `t_order` VALUES ('422693982699520000', 'openId789', '2018-03-12 14:08:20', '1', '0', '0', 'mark一下', '0', '10800', '0', '1');
INSERT INTO `t_order` VALUES ('422705585704665088', 'openId111', '2018-03-12 14:08:20', '1', '0', '0', 'mark一下', '0', '10800', '0', '1');
INSERT INTO `t_order` VALUES ('422723681228161024', 'openId111', '2018-03-12 14:08:20', '1', '0', '0', 'mark一下', '0', '10800', '0', '1');
INSERT INTO `t_order` VALUES ('422755605984313344', 'openId111', '2018-03-12 14:08:20', '1', '0', '0', 'mark一下', '0', '8000', '0', '1');
INSERT INTO `t_order` VALUES ('422816533345992704', '1', '2018-03-13 09:16:36', '1', '0', '0', '', '0', '20', '0', '422690029685440512');
INSERT INTO `t_order` VALUES ('422817933941538816', '1', '2018-03-13 09:16:39', '1', '0', '0', '', '0', '29', '0', '422690029685440512');
INSERT INTO `t_order` VALUES ('422817998500265984', '1', '2018-03-12 18:07:57', '1', '0', '0', '', '0', '0', '0', '334');
INSERT INTO `t_order` VALUES ('422818369847164928', '1', '2018-03-12 18:09:26', '1', '0', '0', '', '0', '0', '0', '422690029685440512');
INSERT INTO `t_order` VALUES ('422819960717312000', '1', '2018-03-12 18:15:45', '1', '0', '0', '', '0', '0', '0', '422725236341866496');
INSERT INTO `t_order` VALUES ('422821829967609856', 'openId111', '2018-03-12 18:24:22', '全网通', '0', '0', 'mark一下', '0', '5200', '0', '1');
INSERT INTO `t_order` VALUES ('422821977523224576', '1', '2018-03-12 18:24:49', '1', '0', '0', '', '0', '3338', '300', '422725236341866496');
INSERT INTO `t_order` VALUES ('422822043025670144', '1', '2018-03-12 18:25:05', '1', '0', '0', '', '0', '3338', '300', '422725236341866496');
INSERT INTO `t_order` VALUES ('422822696817000448', '1', '2018-03-12 18:27:41', '1', '0', '0', '', '0', '5200', '0', '1');
INSERT INTO `t_order` VALUES ('422826164990509056', 'openId110', '2018-03-13 10:13:57', '全网通', '1', '0', 'mark一下', '0', '2846', '300', '1');
INSERT INTO `t_order` VALUES ('422826904572133376', 'openId110', '2018-03-12 19:02:49', '全网通', '0', '0', 'mark一下', '0', '2846', '300', '1');
INSERT INTO `t_order` VALUES ('423036035455254528', 'openId110', '2018-03-13 08:35:23', '全网通', '0', '0', 'mark一下', '0', '2846', '300', '1');
INSERT INTO `t_order` VALUES ('423051368345894912', '1', '2018-03-13 09:36:19', '1', '0', '0', '', '0', '2175', '300', '1');
INSERT INTO `t_order` VALUES ('423053546980638720', '1', '2018-03-13 09:44:59', '1', '0', '0', '', '0', '2175', '300', '1');
INSERT INTO `t_order` VALUES ('423054143721046016', '1', '2018-03-13 09:47:21', '1', '0', '0', '', '0', '2091', '300', '1');
INSERT INTO `t_order` VALUES ('423059279155560448', '1', '2018-03-13 10:07:45', '1', '0', '0', '', '0', '2091', '300', '1');
INSERT INTO `t_order` VALUES ('423067068775006208', '1', '2018-03-13 10:38:42', '1', '0', '0', '', '0', '2091', '300', '1');
INSERT INTO `t_order` VALUES ('423073821851910144', '1', '2018-03-13 11:05:32', '1', '0', '0', '', '0', '2091', '300', '1');
INSERT INTO `t_order` VALUES ('423075193250906112', '1', '2018-03-13 11:10:59', '1', '0', '0', '', '0', '2091', '300', '1');
INSERT INTO `t_order` VALUES ('423075490878717952', '1', '2018-03-13 11:12:10', '1', '0', '0', '', '0', '2091', '300', '1');
INSERT INTO `t_order` VALUES ('423119433691561984', 'openId152', '2018-03-13 16:22:23', '全网通', '1', '0', 'mark一下', '0', '2846', '600', '1');
INSERT INTO `t_order` VALUES ('423119535936110592', '1', '2018-03-13 14:07:11', '1', '0', '0', '', '0', '2091', '600', '1');
INSERT INTO `t_order` VALUES ('423119763250610176', '1', '2018-03-13 14:08:14', '1', '1', '0', '', '0', '2091', '600', '1');
INSERT INTO `t_order` VALUES ('423120168583954432', '1', '2018-03-13 14:14:23', '1', '1', '0', '', '0', '2460', '600', '1');
INSERT INTO `t_order` VALUES ('423121891687923712', '1', '2018-03-13 14:16:47', '1', '1', '0', '', '0', '2460', '600', '1');
INSERT INTO `t_order` VALUES ('423127727667675136', '1', '2018-03-13 14:39:44', '1', '0', '0', '', '0', '2460', '600', '422690029685440512');
INSERT INTO `t_order` VALUES ('423130081037123584', 'openId152', '2018-03-13 14:49:06', '全网通', '0', '0', 'mark一下', '0', '2846', '600', '1');
INSERT INTO `t_order` VALUES ('423171041183924224', 'oTj1BwgtIy-O3TdUXf2Efx4xzofk', '2018-03-13 17:31:53', '1', '0', '0', '', '0', '2460', '600', '423171024398319616');
INSERT INTO `t_order` VALUES ('423171099568635904', 'oTj1BwgtIy-O3TdUXf2Efx4xzofk', '2018-03-13 17:32:07', '1', '0', '0', '', '0', '2460', '600', '423171024398319616');
INSERT INTO `t_order` VALUES ('423178906124681216', 'oTj1BwgtIy-O3TdUXf2Efx4xzofk', '2018-03-13 18:03:08', '1', '0', '0', '', '0', '2460', '600', '423171024398319616');
INSERT INTO `t_order` VALUES ('423180040348368896', 'oTj1BwgtIy-O3TdUXf2Efx4xzofk', '2018-03-13 18:07:38', '1', '0', '0', '', '0', '2460', '600', '423171024398319616');
INSERT INTO `t_order` VALUES ('423190736490463232', 'oTj1BwgtIy-O3TdUXf2Efx4xzofk', '2018-03-13 18:50:08', '1', '0', '0', '', '0', '2460', '600', '423171024398319616');
INSERT INTO `t_order` VALUES ('423196573304881152', 'oTj1BwmU9usDeONtSvi1bPHWXRgY', '2018-03-13 19:16:53', '1', '1', '0', 'OK你紧紧', '0', '2460', '600', '423196493072039936');
INSERT INTO `t_order` VALUES ('423197004680658944', 'oTj1BwgtIy-O3TdUXf2Efx4xzofk', '2018-03-13 19:15:57', '1', '1', '0', '', '0', '2460', '600', '423171024398319616');
INSERT INTO `t_order` VALUES ('423197391580037120', 'oTj1BwgtIy-O3TdUXf2Efx4xzofk', '2018-03-13 19:16:35', '1', '0', '0', '', '0', '2091', '600', '423171024398319616');
INSERT INTO `t_order` VALUES ('423197498987773952', 'oTj1BwgtIy-O3TdUXf2Efx4xzofk', '2018-03-13 19:17:01', '1', '0', '0', '', '0', '2460', '600', '423171024398319616');
INSERT INTO `t_order` VALUES ('423197649835917312', 'oTj1BwmU9usDeONtSvi1bPHWXRgY', '2018-03-13 19:17:37', '1', '0', '0', '', '0', '3147', '600', '423196493072039936');
INSERT INTO `t_order` VALUES ('423199978383474688', 'oTj1BwgtIy-O3TdUXf2Efx4xzofk', '2018-03-13 19:26:52', '1', '0', '0', '', '0', '2583', '600', '423171024398319616');
INSERT INTO `t_order` VALUES ('423200635538636800', 'oTj1BwmU9usDeONtSvi1bPHWXRgY', '2018-03-15 16:42:30', '1', '1', '0', '', '1', '2013', '600', '423196493072039936');

-- ----------------------------
-- Table structure for `t_order_detail`
-- ----------------------------
DROP TABLE IF EXISTS `t_order_detail`;
CREATE TABLE `t_order_detail` (
  `id` varchar(20) NOT NULL,
  `order_id` varchar(20) NOT NULL COMMENT '订单id',
  `food_id` varchar(20) DEFAULT NULL,
  `price` int(10) DEFAULT NULL COMMENT '价格',
  `num` int(10) DEFAULT NULL COMMENT '点菜数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order_detail
-- ----------------------------
INSERT INTO `t_order_detail` VALUES ('1', '12', '01001', '30', '2');
INSERT INTO `t_order_detail` VALUES ('2', '12', '01002', '20', '3');
INSERT INTO `t_order_detail` VALUES ('421336051991183361', '421336051991183360', '01001', '2350', '2');
INSERT INTO `t_order_detail` VALUES ('421336054008643584', '421336051991183360', '01002', '2350', '2');
INSERT INTO `t_order_detail` VALUES ('421608832628686849', '421608832628686848', '01001', '0', '2');
INSERT INTO `t_order_detail` VALUES ('421608832993591296', '421608832628686848', '01002', '0', '2');
INSERT INTO `t_order_detail` VALUES ('421611906449539073', '421611906449539072', '01001', '0', '2');
INSERT INTO `t_order_detail` VALUES ('421611906864775168', '421611906449539072', '01002', '0', '2');
INSERT INTO `t_order_detail` VALUES ('421612209274093569', '421612209274093568', '01001', '0', '2');
INSERT INTO `t_order_detail` VALUES ('421612209462837248', '421612209274093568', '01002', '0', '2');
INSERT INTO `t_order_detail` VALUES ('421612253670801409', '421612253670801408', '01001', '0', '2');
INSERT INTO `t_order_detail` VALUES ('421612253922459648', '421612253670801408', '01002', '0', '2');
INSERT INTO `t_order_detail` VALUES ('421612257890271233', '421612257890271232', '01001', '0', '2');
INSERT INTO `t_order_detail` VALUES ('421612258070626304', '421612257890271232', '01002', '0', '2');
INSERT INTO `t_order_detail` VALUES ('421612463801237505', '421612463801237504', '01001', '0', '2');
INSERT INTO `t_order_detail` VALUES ('421612466032607232', '421612463801237504', '01002', '0', '2');
INSERT INTO `t_order_detail` VALUES ('421612935530414081', '421612935530414080', '01001', '0', '2');
INSERT INTO `t_order_detail` VALUES ('421612937807921152', '421612935530414080', '01002', '0', '2');
INSERT INTO `t_order_detail` VALUES ('421613208114036737', '421613208114036736', '01001', '0', '2');
INSERT INTO `t_order_detail` VALUES ('421613210404126720', '421613208114036736', '01002', '0', '2');
INSERT INTO `t_order_detail` VALUES ('421613486829731841', '421613486829731840', '01001', '0', '2');
INSERT INTO `t_order_detail` VALUES ('421613489052712960', '421613486829731840', '01002', '0', '2');
INSERT INTO `t_order_detail` VALUES ('421614615525326849', '421614615525326848', '01001', '0', '2');
INSERT INTO `t_order_detail` VALUES ('421614615751819264', '421614615525326848', '01002', '0', '2');
INSERT INTO `t_order_detail` VALUES ('421615515891400705', '421615515891400704', '01001', '0', '2');
INSERT INTO `t_order_detail` VALUES ('421615516105310208', '421615515891400704', '01002', '0', '2');
INSERT INTO `t_order_detail` VALUES ('421617120858603521', '421617120858603520', '01001', '0', '2');
INSERT INTO `t_order_detail` VALUES ('421617123010281472', '421617120858603520', '01002', '0', '2');
INSERT INTO `t_order_detail` VALUES ('421617208473419777', '421617208473419776', '01001', '0', '2');
INSERT INTO `t_order_detail` VALUES ('421617208666357760', '421617208473419776', '01002', '0', '2');
INSERT INTO `t_order_detail` VALUES ('421702563948134401', '421702563948134400', '01001', '0', '2');
INSERT INTO `t_order_detail` VALUES ('421702567874002944', '421702563948134400', '01002', '0', '2');
INSERT INTO `t_order_detail` VALUES ('421737232504717313', '421737232504717312', '01001', '0', '2');
INSERT INTO `t_order_detail` VALUES ('421737232857038848', '421737232504717312', '01002', '0', '2');
INSERT INTO `t_order_detail` VALUES ('421738460299132929', '421738460299132928', '01001', '0', '2');
INSERT INTO `t_order_detail` VALUES ('421738460722757632', '421738460299132928', '01002', '0', '2');
INSERT INTO `t_order_detail` VALUES ('421744627448545281', '421744627448545280', '01001', '0', '2');
INSERT INTO `t_order_detail` VALUES ('421744627704397824', '421744627448545280', '01002', '0', '2');
INSERT INTO `t_order_detail` VALUES ('421750585335742465', '421750585335742464', '01001', '0', '2');
INSERT INTO `t_order_detail` VALUES ('421750589219667968', '421750585335742464', '01002', '0', '2');
INSERT INTO `t_order_detail` VALUES ('422673373634494465', '422673373634494464', '01001', '0', '2');
INSERT INTO `t_order_detail` VALUES ('422673377791049728', '422673373634494464', '01002', '0', '2');
INSERT INTO `t_order_detail` VALUES ('422674511209431041', '422674511209431040', '01001', '0', '2');
INSERT INTO `t_order_detail` VALUES ('422674511507226624', '422674511209431040', '01002', '0', '2');
INSERT INTO `t_order_detail` VALUES ('422676293100765185', '422676293100765184', '01001', '0', '2');
INSERT INTO `t_order_detail` VALUES ('422676293490835456', '422676293100765184', '01002', '0', '2');
INSERT INTO `t_order_detail` VALUES ('422676674195226625', '422676674195226624', '01001', '0', '2');
INSERT INTO `t_order_detail` VALUES ('422676675252191232', '422676674195226624', '01002', '0', '2');
INSERT INTO `t_order_detail` VALUES ('422678526462787585', '422678526462787584', '01001', '0', '2');
INSERT INTO `t_order_detail` VALUES ('422684841788571649', '422684841788571648', '01001', '0', '2');
INSERT INTO `t_order_detail` VALUES ('422684842786816000', '422684841788571648', '01002', '0', '2');
INSERT INTO `t_order_detail` VALUES ('422689006480785409', '422689006480785408', '01001', '2800', '2');
INSERT INTO `t_order_detail` VALUES ('422689006648557568', '422689006480785408', '01002', '2600', '2');
INSERT INTO `t_order_detail` VALUES ('422693991906017280', '422693982699520000', '01001', '2800', '2');
INSERT INTO `t_order_detail` VALUES ('422694106838335488', '422693982699520000', '01002', '2600', '2');
INSERT INTO `t_order_detail` VALUES ('422705585704665089', '422705585704665088', '01001', '2800', '2');
INSERT INTO `t_order_detail` VALUES ('422705589429207040', '422705585704665088', '01002', '2600', '2');
INSERT INTO `t_order_detail` VALUES ('422714665605267457', '422714665605267456', '01001', '0', '2');
INSERT INTO `t_order_detail` VALUES ('422714668218318848', '422714665605267456', '01002', '0', '2');
INSERT INTO `t_order_detail` VALUES ('422715452934848513', '422715452934848512', '0', '0', '0');
INSERT INTO `t_order_detail` VALUES ('422715453190701056', '422715452934848512', '01001', '0', '2');
INSERT INTO `t_order_detail` VALUES ('422715547373797377', '422715547373797376', '01001', '0', '2');
INSERT INTO `t_order_detail` VALUES ('422715547512209408', '422715547373797376', '01002', '0', '2');
INSERT INTO `t_order_detail` VALUES ('422715572019527681', '422715572019527680', null, '0', '0');
INSERT INTO `t_order_detail` VALUES ('422715572216659968', '422715572019527680', '01001', '0', '2');
INSERT INTO `t_order_detail` VALUES ('422716065907212289', '422716065907212288', null, '0', '0');
INSERT INTO `t_order_detail` VALUES ('422716066192424960', '422716065907212288', '01001', '0', '2');
INSERT INTO `t_order_detail` VALUES ('422716102867419137', '422716102867419136', null, '0', '0');
INSERT INTO `t_order_detail` VALUES ('422716103152631808', '422716102867419136', '01001', '0', '2');
INSERT INTO `t_order_detail` VALUES ('422716231338950657', '422716231338950656', '01001', '0', '2');
INSERT INTO `t_order_detail` VALUES ('422721846442459137', '422721846442459136', '01001', '0', '2');
INSERT INTO `t_order_detail` VALUES ('422721848698994688', '422721846442459136', '01002', '0', '2');
INSERT INTO `t_order_detail` VALUES ('422723686177439744', '422723681228161024', '01001', '2800', '2');
INSERT INTO `t_order_detail` VALUES ('422723827542261760', '422723681228161024', '01002', '2600', '2');
INSERT INTO `t_order_detail` VALUES ('422726164075773953', '422726164075773952', '01001', '0', '2');
INSERT INTO `t_order_detail` VALUES ('422755608748359680', '422755605984313344', '01001', '2800', '1');
INSERT INTO `t_order_detail` VALUES ('422755667737051136', '422755605984313344', '01002', '2600', '2');
INSERT INTO `t_order_detail` VALUES ('422786658300067841', '422786658300067840', '01001', '0', '2');
INSERT INTO `t_order_detail` VALUES ('422816533345992705', '422816533345992704', '01001', '0', '2');
INSERT INTO `t_order_detail` VALUES ('422817933941538817', '422817933941538816', '01001', '0', '2');
INSERT INTO `t_order_detail` VALUES ('422817998500265985', '422817998500265984', '01001', '0', '2');
INSERT INTO `t_order_detail` VALUES ('422818369847164929', '422818369847164928', '01002', '0', '1');
INSERT INTO `t_order_detail` VALUES ('422818370052685824', '422818369847164928', '01003', '0', '3');
INSERT INTO `t_order_detail` VALUES ('422818370224652288', '422818369847164928', '01004', '0', '3');
INSERT INTO `t_order_detail` VALUES ('422819960717312001', '422819960717312000', '01002', '0', '1');
INSERT INTO `t_order_detail` VALUES ('422819961098993664', '422819960717312000', '01003', '0', '3');
INSERT INTO `t_order_detail` VALUES ('422819961262571520', '422819960717312000', '01004', '0', '3');
INSERT INTO `t_order_detail` VALUES ('422821861575884800', '422821829967609856', '01001', '0', '1');
INSERT INTO `t_order_detail` VALUES ('422821865283649536', '422821829967609856', '01002', '2600', '2');
INSERT INTO `t_order_detail` VALUES ('422821977523224577', '422821977523224576', '01002', '2600', '1');
INSERT INTO `t_order_detail` VALUES ('422821977737134080', '422821977523224576', '01003', '123', '3');
INSERT INTO `t_order_detail` VALUES ('422821977883934720', '422821977523224576', '01004', '123', '3');
INSERT INTO `t_order_detail` VALUES ('422822043025670145', '422822043025670144', '01002', '2600', '1');
INSERT INTO `t_order_detail` VALUES ('422822043189248000', '422822043025670144', '01003', '123', '3');
INSERT INTO `t_order_detail` VALUES ('422822043336048640', '422822043025670144', '01004', '123', '3');
INSERT INTO `t_order_detail` VALUES ('422822696817000449', '422822696817000448', '01002', '2600', '2');
INSERT INTO `t_order_detail` VALUES ('422826164990509057', '422826164990509056', '01002', '2600', '1');
INSERT INTO `t_order_detail` VALUES ('422826169126092800', '422826164990509056', '01003', '123', '2');
INSERT INTO `t_order_detail` VALUES ('422826904572133377', '422826904572133376', '01002', '2600', '1');
INSERT INTO `t_order_detail` VALUES ('422826904815403008', '422826904572133376', '01003', '123', '2');
INSERT INTO `t_order_detail` VALUES ('423035829976301569', '423035829976301568', '01003', '123', '1');
INSERT INTO `t_order_detail` VALUES ('423035833642123264', '423035829976301568', '01004', '123', '2');
INSERT INTO `t_order_detail` VALUES ('423036035455254529', '423036035455254528', '01002', '2600', '1');
INSERT INTO `t_order_detail` VALUES ('423036035681746944', '423036035455254528', '01003', '123', '2');
INSERT INTO `t_order_detail` VALUES ('423051368350089216', '423051368345894912', '01003', '123', '2');
INSERT INTO `t_order_detail` VALUES ('423051368614330368', '423051368345894912', '01004', '123', '11');
INSERT INTO `t_order_detail` VALUES ('423051368735965184', '423051368345894912', '01005', '12', '13');
INSERT INTO `t_order_detail` VALUES ('423051368916320256', '423051368345894912', '01006', '35', '12');
INSERT INTO `t_order_detail` VALUES ('423053546980638721', '423053546980638720', '01003', '123', '2');
INSERT INTO `t_order_detail` VALUES ('423053547265851392', '423053546980638720', '01004', '123', '11');
INSERT INTO `t_order_detail` VALUES ('423053547404263424', '423053546980638720', '01005', '12', '13');
INSERT INTO `t_order_detail` VALUES ('423053547538481152', '423053546980638720', '01006', '35', '12');
INSERT INTO `t_order_detail` VALUES ('423054143721046017', '423054143721046016', '01003', '123', '7');
INSERT INTO `t_order_detail` VALUES ('423054143901401088', '423054143721046016', '01004', '123', '10');
INSERT INTO `t_order_detail` VALUES ('423059279155560449', '423059279155560448', '01003', '123', '7');
INSERT INTO `t_order_detail` VALUES ('423059279579185152', '423059279155560448', '01004', '123', '10');
INSERT INTO `t_order_detail` VALUES ('423067068775006209', '423067068775006208', '01003', '123', '7');
INSERT INTO `t_order_detail` VALUES ('423067069093773312', '423067068775006208', '01004', '123', '10');
INSERT INTO `t_order_detail` VALUES ('423073821851910145', '423073821851910144', '01003', '123', '7');
INSERT INTO `t_order_detail` VALUES ('423073822036459520', '423073821851910144', '01004', '123', '10');
INSERT INTO `t_order_detail` VALUES ('423075193250906113', '423075193250906112', '01003', '123', '7');
INSERT INTO `t_order_detail` VALUES ('423075193422872576', '423075193250906112', '01004', '123', '10');
INSERT INTO `t_order_detail` VALUES ('423075490878717953', '423075490878717952', '01003', '123', '7');
INSERT INTO `t_order_detail` VALUES ('423075491025518592', '423075490878717952', '01004', '123', '10');
INSERT INTO `t_order_detail` VALUES ('423085308943269889', '423085308943269888', '01003', '123', '7');
INSERT INTO `t_order_detail` VALUES ('423085309182345216', '423085308943269888', '01004', '123', '10');
INSERT INTO `t_order_detail` VALUES ('423085365528625153', '423085365528625152', '01003', '123', '7');
INSERT INTO `t_order_detail` VALUES ('423085365792866304', '423085365528625152', '01004', '123', '10');
INSERT INTO `t_order_detail` VALUES ('423115700907802625', '423115700907802624', '01003', '123', '7');
INSERT INTO `t_order_detail` VALUES ('423115701234958336', '423115700907802624', '01004', '123', '10');
INSERT INTO `t_order_detail` VALUES ('423115787302076417', '423115787302076416', '01003', '123', '7');
INSERT INTO `t_order_detail` VALUES ('423115787553734656', '423115787302076416', '01004', '123', '10');
INSERT INTO `t_order_detail` VALUES ('423116067557081089', '423116067557081088', '01003', '123', '7');
INSERT INTO `t_order_detail` VALUES ('423116067733241856', '423116067557081088', '01004', '123', '10');
INSERT INTO `t_order_detail` VALUES ('423117531369177089', '423117531369177088', '01002', '2600', '1');
INSERT INTO `t_order_detail` VALUES ('423117531629223936', '423117531369177088', '01003', '123', '2');
INSERT INTO `t_order_detail` VALUES ('423118102734045185', '423118102734045184', '01002', '2600', '1');
INSERT INTO `t_order_detail` VALUES ('423118112590659584', '423118102734045184', '01003', '123', '2');
INSERT INTO `t_order_detail` VALUES ('423118695158513664', '423118687935922176', '01002', '2600', '1');
INSERT INTO `t_order_detail` VALUES ('423118836791771136', '423118687935922176', '01003', '123', '2');
INSERT INTO `t_order_detail` VALUES ('423119433695756288', '423119433691561984', '01002', '2600', '1');
INSERT INTO `t_order_detail` VALUES ('423119437348995072', '423119433691561984', '01003', '123', '2');
INSERT INTO `t_order_detail` VALUES ('423119535936110593', '423119535936110592', '01003', '123', '7');
INSERT INTO `t_order_detail` VALUES ('423119536191963136', '423119535936110592', '01004', '123', '10');
INSERT INTO `t_order_detail` VALUES ('423119763250610177', '423119763250610176', '01003', '123', '7');
INSERT INTO `t_order_detail` VALUES ('423119763443548160', '423119763250610176', '01004', '123', '10');
INSERT INTO `t_order_detail` VALUES ('423120168583954433', '423120168583954432', '01003', '123', '10');
INSERT INTO `t_order_detail` VALUES ('423120168734949376', '423120168583954432', '01004', '123', '10');
INSERT INTO `t_order_detail` VALUES ('423121891687923713', '423121891687923712', '01003', '123', '10');
INSERT INTO `t_order_detail` VALUES ('423121892040245248', '423121891687923712', '01004', '123', '10');
INSERT INTO `t_order_detail` VALUES ('423127727667675137', '423127727667675136', '01003', '123', '10');
INSERT INTO `t_order_detail` VALUES ('423127727818670080', '423127727667675136', '01004', '123', '10');
INSERT INTO `t_order_detail` VALUES ('423130081037123585', '423130081037123584', '01002', '2600', '1');
INSERT INTO `t_order_detail` VALUES ('423130084610670592', '423130081037123584', '01003', '123', '2');
INSERT INTO `t_order_detail` VALUES ('423171041188118528', '423171041183924224', '01003', '123', '10');
INSERT INTO `t_order_detail` VALUES ('423171041389445120', '423171041183924224', '01004', '123', '10');
INSERT INTO `t_order_detail` VALUES ('423171099568635905', '423171099568635904', '01003', '123', '10');
INSERT INTO `t_order_detail` VALUES ('423171099774156800', '423171099568635904', '01004', '123', '10');
INSERT INTO `t_order_detail` VALUES ('423178906124681217', '423178906124681216', '01003', '123', '10');
INSERT INTO `t_order_detail` VALUES ('423178906439254016', '423178906124681216', '01004', '123', '10');
INSERT INTO `t_order_detail` VALUES ('423180040348368897', '423180040348368896', '01003', '123', '10');
INSERT INTO `t_order_detail` VALUES ('423180040482586624', '423180040348368896', '01004', '123', '10');
INSERT INTO `t_order_detail` VALUES ('423190736490463233', '423190736490463232', '01003', '123', '10');
INSERT INTO `t_order_detail` VALUES ('423190736645652480', '423190736490463232', '01004', '123', '10');
INSERT INTO `t_order_detail` VALUES ('423196573304881153', '423196573304881152', '01003', '123', '20');
INSERT INTO `t_order_detail` VALUES ('423197004680658945', '423197004680658944', '01003', '123', '10');
INSERT INTO `t_order_detail` VALUES ('423197004823265280', '423197004680658944', '01004', '123', '10');
INSERT INTO `t_order_detail` VALUES ('423197391580037121', '423197391580037120', '01003', '123', '10');
INSERT INTO `t_order_detail` VALUES ('423197391768780800', '423197391580037120', '01004', '123', '7');
INSERT INTO `t_order_detail` VALUES ('423197498987773953', '423197498987773952', '01003', '123', '10');
INSERT INTO `t_order_detail` VALUES ('423197499163934720', '423197498987773952', '01004', '123', '10');
INSERT INTO `t_order_detail` VALUES ('423197649835917313', '423197649835917312', '01003', '123', '17');
INSERT INTO `t_order_detail` VALUES ('423197650016272384', '423197649835917312', '01004', '123', '8');
INSERT INTO `t_order_detail` VALUES ('423197650087575552', '423197649835917312', '01005', '12', '6');
INSERT INTO `t_order_detail` VALUES ('423199978383474689', '423199978383474688', '01003', '123', '10');
INSERT INTO `t_order_detail` VALUES ('423199978576412672', '423199978383474688', '01004', '123', '11');
INSERT INTO `t_order_detail` VALUES ('423200635538636801', '423200635538636800', '06001', '20', '4');
INSERT INTO `t_order_detail` VALUES ('423200635752546304', '423200635538636800', '01003', '123', '13');
INSERT INTO `t_order_detail` VALUES ('423200635903541248', '423200635538636800', '01005', '12', '3');
INSERT INTO `t_order_detail` VALUES ('423200636029370368', '423200635538636800', '01006', '35', '5');
INSERT INTO `t_order_detail` VALUES ('423200636155199488', '423200635538636800', '01004', '123', '1');

-- ----------------------------
-- Table structure for `t_sys_params`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_params`;
CREATE TABLE `t_sys_params` (
  `id` varchar(10) NOT NULL,
  `order_cill_amount` int(10) NOT NULL COMMENT '订单起送金额',
  `send_amount` int(10) NOT NULL COMMENT '配送费',
  `order_free_amount` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_params
-- ----------------------------
INSERT INTO `t_sys_params` VALUES ('1', '2000', '132000', '26500');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` varchar(10) NOT NULL DEFAULT '' COMMENT '用户编号',
  `user_id` varchar(30) DEFAULT NULL COMMENT '用户名',
  `user_name` varchar(100) DEFAULT NULL COMMENT '用户真实姓名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `sex` char(1) DEFAULT NULL COMMENT '性别（0：男   1：女）',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `status` char(1) DEFAULT '0' COMMENT '状态（0：启用   1：禁用）',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `groups` char(1) DEFAULT '1' COMMENT '用户群组（0:管理员  1:操作员）',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建账号时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('01', 'admin', '管理员', 'eabd8ce9404507aa8c22714d3f5eada9', '0', '18888888888', '0', '系统管理员', '0', '2018-03-27 16:13:27');
INSERT INTO `t_user` VALUES ('02', 'admins', '曾传保', 'eb0dff1c3d6739e71d7360c6e0aa4510', '0', '15999667339', '0', null, '1', '2018-03-27 14:33:44');
