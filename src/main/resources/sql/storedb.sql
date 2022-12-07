/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : storedb

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 07/12/2022 18:12:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for carousel
-- ----------------------------
DROP TABLE IF EXISTS `carousel`;
CREATE TABLE `carousel`  (
  `carousel_id` int(0) NOT NULL AUTO_INCREMENT,
  `imgPath` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `describes` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`carousel_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of carousel
-- ----------------------------
INSERT INTO `carousel` VALUES (1, 'public/imgs/cms_1.jpg', '123456');
INSERT INTO `carousel` VALUES (2, 'public/imgs/cms_2.jpg', '123456');
INSERT INTO `carousel` VALUES (3, 'public/imgs/cms_3.jpg', '123456');
INSERT INTO `carousel` VALUES (4, 'public/imgs/cms_4.jpg', '123456');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `category_id` int(0) NOT NULL AUTO_INCREMENT,
  `category_name` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (0, '全部');
INSERT INTO `category` VALUES (1, '手机');
INSERT INTO `category` VALUES (2, '电视机');
INSERT INTO `category` VALUES (3, '空调');
INSERT INTO `category` VALUES (4, '洗衣机');
INSERT INTO `category` VALUES (5, '保护套');
INSERT INTO `category` VALUES (6, '保护膜');
INSERT INTO `category` VALUES (7, '充电器');
INSERT INTO `category` VALUES (8, '充电宝');

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NOT NULL,
  `product_id` int(0) NOT NULL,
  `collect_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_collect_user_id`(`user_id`) USING BTREE,
  INDEX `FK_collect_id`(`product_id`) USING BTREE,
  CONSTRAINT `FK_collect_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_collect_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of collect
-- ----------------------------
INSERT INTO `collect` VALUES (11, 1, 3, '2022-11-22 11:14:26');
INSERT INTO `collect` VALUES (12, 1, 4, '2022-11-22 11:14:28');
INSERT INTO `collect` VALUES (15, 2, 1, '2022-11-26 12:21:50');
INSERT INTO `collect` VALUES (16, 2, 2, '2022-12-01 13:49:02');
INSERT INTO `collect` VALUES (17, 21, 1, '2022-12-02 09:52:33');

-- ----------------------------
-- Table structure for location
-- ----------------------------
DROP TABLE IF EXISTS `location`;
CREATE TABLE `location`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户id',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收货人姓名',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收获人手机号',
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址串',
  `tabs` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址标签 例如家 学校',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of location
-- ----------------------------
INSERT INTO `location` VALUES (2, '2', '李亚军', '11451422', '北京市市辖区朝阳区长兴中路八号', '学校');
INSERT INTO `location` VALUES (3, '2', '李亚军', '1919810', '内蒙古自治区通辽市开鲁县科大', '学校');
INSERT INTO `location` VALUES (8, '21', '李亚军', '15061701265', '北京市市辖区东城区江苏省苏州市张家港市长兴中路8号', '学校');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_id` int(0) NOT NULL,
  `product_id` int(0) NOT NULL,
  `product_num` int(0) NOT NULL,
  `product_price` double NOT NULL,
  `order_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `state` int(0) NULL DEFAULT NULL COMMENT '状态 0未支付 1 已支付 -1订单取消',
  `delay` bigint(0) NULL DEFAULT NULL COMMENT '延迟队列 -1为购买成功 变为0则为购买失败',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_order_user_id`(`user_id`) USING BTREE,
  INDEX `FK_order_id`(`product_id`) USING BTREE,
  CONSTRAINT `FK_order_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_order_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 77 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (40, '583156859284750336', 2, 1, 1, 1599, '2022-11-28 21:46:29', '2', 1, 0);
INSERT INTO `orders` VALUES (41, '583158067642761216', 2, 2, 1, 2599, '2022-11-28 21:51:17', '2', 1, 0);
INSERT INTO `orders` VALUES (42, '583158821724094464', 2, 2, 1, 2599, '2022-11-28 21:54:17', '2', -1, 0);
INSERT INTO `orders` VALUES (43, '583159517529767936', 2, 3, 1, 2599, '2022-11-28 21:57:03', '2', -1, 0);
INSERT INTO `orders` VALUES (44, '583367113457008640', 2, 2, 1, 2599, '2022-11-29 11:41:58', '2', -1, 0);
INSERT INTO `orders` VALUES (45, '583367113457008640', 2, 4, 1, 699, '2022-11-29 11:41:58', '2', 1, 0);
INSERT INTO `orders` VALUES (46, '583372748764479488', 2, 2, 1, 2599, '2022-11-29 12:04:21', '2', 1, 0);
INSERT INTO `orders` VALUES (47, '583373827984064512', 2, 2, 1, 2599, '2022-11-29 12:08:39', '2', -1, 0);
INSERT INTO `orders` VALUES (48, '583377712475799552', 2, 3, 1, 2599, '2022-11-29 12:24:05', '2', 1, 0);
INSERT INTO `orders` VALUES (49, '583378326769369088', 2, 2, 1, 2599, '2022-11-29 12:26:31', '2', 1, 0);
INSERT INTO `orders` VALUES (50, '583403625800269824', 2, 1, 1, 1599, '2022-11-29 14:07:48', '2', 1, 0);
INSERT INTO `orders` VALUES (51, '583405321246998528', 2, 1, 4, 6396, '2022-11-29 14:14:15', '3', 1, 0);
INSERT INTO `orders` VALUES (53, '583439872665059328', 2, 2, 5, 12995, '2022-11-29 16:31:05', '7', 1, 0);
INSERT INTO `orders` VALUES (54, '583439872665059328', 2, 4, 1, 699, '2022-11-29 16:31:05', '7', 1, 0);
INSERT INTO `orders` VALUES (55, '583454740453523456', 17, 8, 3, 1617, '2022-11-29 17:30:32', '0', -1, 0);
INSERT INTO `orders` VALUES (56, '583467623442808832', 17, 5, 1, 699, '2022-11-29 18:21:44', '0', -1, 0);
INSERT INTO `orders` VALUES (57, '583915193872941056', 2, 1, 1, 1599, '2022-11-30 23:59:50', '2', 1, 0);
INSERT INTO `orders` VALUES (58, '583915706920206336', 2, 2, 7, 18193, '2022-12-01 00:01:53', '2', 1, 0);
INSERT INTO `orders` VALUES (59, '583918034712788992', 2, 2, 1, 2599, '2022-12-01 00:11:08', '2', -1, 0);
INSERT INTO `orders` VALUES (60, '584137808407429120', 2, 6, 2, 2398, '2022-12-01 14:45:12', '2', 1, 0);
INSERT INTO `orders` VALUES (61, '584137933313802240', 2, 6, 1, 1199, '2022-12-01 14:45:42', '2', -1, 0);
INSERT INTO `orders` VALUES (62, '584148936495726592', 2, 3, 1, 2599, '2022-12-01 15:29:25', '3', 1, 0);
INSERT INTO `orders` VALUES (63, '584155082241605632', 2, 6, 1, 1199, '2022-12-01 15:53:50', '2', -1, 0);
INSERT INTO `orders` VALUES (64, '584155689354526720', 2, 6, 1, 1199, '2022-12-01 15:56:15', '2', 1, 0);
INSERT INTO `orders` VALUES (65, '584155934855528448', 2, 6, 1, 1199, '2022-12-01 15:57:14', '2', -1, 0);
INSERT INTO `orders` VALUES (66, '584207497531752448', 2, 1, 4, 6396, '2022-12-01 19:21:23', '2', -1, 0);
INSERT INTO `orders` VALUES (67, '584228515730161664', 2, 1, 1, 1599, '2022-12-01 20:44:52', '2', -1, 0);
INSERT INTO `orders` VALUES (68, '584278800095772672', 2, 2, 1, 2599, '2022-12-02 00:04:41', '2', -1, 0);
INSERT INTO `orders` VALUES (69, '584278933034237952', 2, 2, 5, 12995, '2022-12-02 00:05:13', '2', -1, 0);
INSERT INTO `orders` VALUES (70, '584281159509213184', 2, 4, 3, 2097, '2022-12-02 00:14:03', '2', 1, 0);
INSERT INTO `orders` VALUES (71, '584281718752542720', 2, 2, 4, 2599, '2022-12-02 00:16:17', '2', -1, 0);
INSERT INTO `orders` VALUES (72, '584281878526164992', 2, 2, 9, 2599, '2022-12-02 00:16:55', '3', 1, 0);
INSERT INTO `orders` VALUES (73, '584285606461308928', 2, 2, 1, 2599, '2022-12-02 00:31:44', '2', -1, 0);
INSERT INTO `orders` VALUES (74, '584419546773848064', 2, 3, 1, 2599, '2022-12-02 09:23:57', '2', 0, 1669951437941);
INSERT INTO `orders` VALUES (75, '584420185532792832', 2, 1, 1, 1599, '2022-12-02 09:26:30', '2', 0, 1669951590233);
INSERT INTO `orders` VALUES (76, '584420661405941760', 2, 6, 1, 1199, '2022-12-02 09:29:06', '2', 0, 1669951703690);
INSERT INTO `orders` VALUES (77, '584426872239030272', 21, 1, 1, 1599, '2022-12-02 09:53:04', '8', 0, 1669953184468);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `product_id` int(0) NOT NULL AUTO_INCREMENT,
  `product_name` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `category_id` int(0) NOT NULL,
  `product_title` char(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `product_intro` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `product_picture` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_price` double NOT NULL,
  `product_selling_price` double NOT NULL,
  `product_num` int(0) NOT NULL,
  `product_sales` int(0) NOT NULL,
  PRIMARY KEY (`product_id`) USING BTREE,
  INDEX `FK_product_category`(`category_id`) USING BTREE,
  CONSTRAINT `FK_product_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, 'Redmi K30', 1, '120Hz流速屏，全速热爱2', '120Hz高帧率流速屏/ 索尼6400万前后六摄 / 6.67\'小孔径全面屏 / 最高可选8GB+256GB大存储 / 高通骁龙730G处理器 / 3D四曲面玻璃机身 / 4500mAh+27W快充 / 多功能NFC', 'https://s2.loli.net/2022/11/30/tVxQgdoGwYFCyA8.png', 2000, 1599, 10, 12);
INSERT INTO `product` VALUES (2, 'Redmi K30 5G', 1, '双模5G,120Hz流速屏', '双模5G / 三路并发 / 高通骁龙765G / 7nm 5G低功耗处理器 / 120Hz高帧率流速屏 / 6.67\'小孔径全面屏 / 索尼6400万前后六摄 / 最高可选8GB+256GB大存储 / 4500mAh+30W快充 / 3D四曲面玻璃机身 / 多功能NFC', 'https://s2.loli.net/2022/11/30/k3c67NXpbQvaA9x.png', 2599, 2599, 10, 12);
INSERT INTO `product` VALUES (3, '小米CC9 Pro', 1, '1亿像素,五摄四闪', '1亿像素主摄 / 全场景五摄像头 / 四闪光灯 / 3200万自拍 / 10 倍混合光学变焦，50倍数字变焦 / 5260mAh ⼤电量 / 标配 30W疾速快充 / ⼩米⾸款超薄屏下指纹 / 德国莱茵低蓝光认证 / 多功能NFC / 红外万能遥控 / 1216超线性扬声器', 'public/imgs/phone/Mi-CC9.png', 2799, 2599, 20, 52);
INSERT INTO `product` VALUES (4, 'Redmi 8', 1, '5000mAh超长续航', '5000mAh超长续航 / 高通骁龙439八核处理器 / 4GB+64GB', 'public/imgs/phone/Redmi-8.png', 799, 699, 20, 25);
INSERT INTO `product` VALUES (5, 'Redmi 8A', 1, '5000mAh超长续航', '5000mAh超长续航 / 高通骁龙439八核处理器 / 4GB+64GB / 1200万AI后置相机', 'public/imgs/phone/Redmi-8A.png', 599, 699, 20, 546);
INSERT INTO `product` VALUES (6, 'Redmi Note8 Pro', 1, '6400万全场景四摄', '6400万四摄小金刚拍照新旗舰超强解析力，超震撼', 'public/imgs/phone/Redmi-Note8-pro.png', 1399, 1199, 20, 456);
INSERT INTO `product` VALUES (7, 'Redmi Note8', 1, '千元4800万四摄', '千元4800万四摄 | 高通骁龙665 | 小金刚品质保证', 'public/imgs/phone/Redmi-Note8.png', 999, 999, 20, 456);
INSERT INTO `product` VALUES (8, 'Redmi 7A', 1, '小巧大电量 持久流畅', '小巧大电量，持久又流畅骁龙8核高性能处理器 | 4000mAh超长续航 | AI人脸解锁 | 整机防泼溅', 'public/imgs/phone/Redmi-7A.png', 599, 539, 20, 456);
INSERT INTO `product` VALUES (9, '小米电视4A 32英寸', 2, '人工智能系统，高清液晶屏', '小米电视4A 32英寸 | 64位四核处理器 | 1GB+4GB大内存 | 人工智能系统', 'public/imgs/appliance/MiTv-4A-32.png', 799, 799, 10, 456);
INSERT INTO `product` VALUES (10, '小米全面屏电视E55A', 2, '全面屏设计，人工智能语音', '全面屏设计 | 内置小爱同学 | 4K + HDR | 杜比DTS | PatchWall | 海量内容 | 2GB + 8GB大存储 | 64位四核处理器', 'public/imgs/appliance/MiTv-E55A.png', 2099, 1899, 10, 35);
INSERT INTO `product` VALUES (11, '小米全面屏电视E65A', 2, '全面屏设计，人工智能语音', '人工智能语音系统 | 海量影视内容 | 4K 超高清屏 | 杜比音效 | 64位四核处理器 | 2GB + 8GB大存储', 'public/imgs/appliance/MiTv-E65A.png', 3999, 2799, 10, 0);
INSERT INTO `product` VALUES (12, '小米电视4X 43英寸', 2, 'FHD全高清屏，人工智能语音', '人工智能语音系统 | FHD全高清屏 | 64位四核处理器 | 海量片源 | 1GB+8GB大内存 | 钢琴烤漆', 'public/imgs/appliance/MiTv-4X-43.png', 1499, 1299, 10, 0);
INSERT INTO `product` VALUES (13, '小米电视4C 55英寸', 2, '4K HDR，人工智能系统', '人工智能 | 大内存 | 杜比音效 | 超窄边 | 4K HDR | 海量片源 | 纤薄机身| 钢琴烤漆', 'public/imgs/appliance/MiTv-4C-55.png', 1999, 1799, 10, 0);
INSERT INTO `product` VALUES (14, '小米电视4A 65英寸', 2, '4K HDR，人工智能系统', '人工智能 | 大内存 | 杜比音效 | 超窄边 | 4K HDR | 海量片源 | 纤薄机身| 钢琴烤漆', 'public/imgs/appliance/MiTv-4A-65.png', 2999, 2799, 10, 0);
INSERT INTO `product` VALUES (15, '小米壁画电视 65英寸', 2, '壁画外观，全面屏，远场语音', '纯平背板 | 通体13.9mm | 远场语音 | 4K+HDR | 杜比+DTS | 画框模式 | 内置小爱同学 | PatchWall | SoundBar+低音炮 | 四核处理器+大存储', 'public/imgs/appliance/MiTv-ArtTv-65.png', 6999, 6999, 10, 0);
INSERT INTO `product` VALUES (16, '米家互联网空调C1（一级能效）', 3, '变频节能省电，自清洁', '一级能效 | 1.5匹 | 全直流变频 | 高效制冷/热 | 静音设计 | 自清洁 | 全屋互联', 'public/imgs/appliance/AirCondition-V1C1.png', 2699, 2599, 20, 10);
INSERT INTO `product` VALUES (17, '米家空调', 3, '出众静音，快速制冷热', '大1匹 | 三级能效 | 静音 | 快速制冷热 | 广角送风 | 除湿功能 | 高密度过滤网 | 典雅外观', 'public/imgs/appliance/AirCondition-F3W1.png', 1799, 1699, 20, 8);
INSERT INTO `product` VALUES (18, '米家互联网洗烘一体机 Pro 10kg', 4, '智能洗烘，省心省力', '国标双A+级洗烘能力 / 22种洗烘模式 / 智能投放洗涤剂 / 支持小爱同学语音遥控 / 支持OTA在线智能升级 / 智能空气洗 / 除菌率达99.9%+', 'public/imgs/appliance/Washer-Pro-10.png', 2999, 2999, 20, 7);
INSERT INTO `product` VALUES (19, 'Redmi K20/ K20 Pro 怪力魔王保护壳', 5, '怪力魔王专属定制', '优选PC材料，强韧张力，经久耐用 / 精选开孔，全面贴合机身 / 手感轻薄细腻，舒适无负担 / 三款颜色可选，彰显个性，与众不同', 'public/imgs/accessory/protectingShell-RedMi-K20&pro.png', 39, 39, 20, 10);
INSERT INTO `product` VALUES (20, '小米9 ARE U OK保护壳', 5, '一个与众不同的保护壳', '彰显独特个性 / 轻薄无负担 / 优选PC材料 / 贴合机身 / 潮流五色', 'public/imgs/accessory/protectingShell-Mi-9.png', 49, 39, 20, 10);
INSERT INTO `product` VALUES (21, '小米CC9&小米CC9美图定制版 标准高透贴膜', 6, '高清透亮，耐磨性强', '耐磨性强，防护更出众 / 疏油疏水，有效抗水抗脏污 / 高清透亮，保留了原生屏幕的亮丽颜色和清晰度 / 操作灵敏，智能吸附 / 进口高端PET材质，裸机般手感', 'public/imgs/accessory/protectingMen-Mi-CC9.png', 19, 19, 20, 9);
INSERT INTO `product` VALUES (22, '小米CC9e 标准高透贴膜', 6, '高清透亮，耐磨性强', '耐磨性强，防护更出众 / 疏油疏水，有效抗水抗脏污 / 高清透亮，保留了原生屏幕的亮丽颜色和清晰度 / 操作灵敏，智能吸附 / 进口高端PET材质，裸机般手感', 'public/imgs/accessory/protectingMen-Mi-CC9e.png', 19, 19, 20, 9);
INSERT INTO `product` VALUES (23, '小米USB充电器30W快充版（1A1C）', 7, '多一种接口，多一种选择', '双口输出 / 30W 输出 / 可折叠插脚 / 小巧便携', 'public/imgs/accessory/charger-30w.png', 59, 59, 20, 8);
INSERT INTO `product` VALUES (24, '小米USB充电器快充版（18W）', 7, '安卓、苹果设备均可使用', '支持QC3.0设备充电 / 支持iOS设备充电/ 美观耐用', 'public/imgs/accessory/charger-18w.png', 29, 29, 20, 8);
INSERT INTO `product` VALUES (25, '小米USB充电器60W快充版（6口）', 7, '6口输出，USB-C输出接口', '6口输出 / USB-C输出接口 / 支持QC3.0快充协议 / 总输出功率60W', 'public/imgs/accessory/charger-60w.png', 129, 129, 20, 0);
INSERT INTO `product` VALUES (26, '小米USB充电器36W快充版（2口）', 7, '6多重安全保护，小巧便携', '双USB输出接口 / 支持QC3.0快充协议 / 多重安全保护 / 小巧便携', 'public/imgs/accessory/charger-36w.png', 59, 59, 20, 0);
INSERT INTO `product` VALUES (27, '小米车载充电器快充版', 7, '让爱车成为移动充电站', 'QC3.0 双口输出 / 智能温度控制 / 5重安全保护 / 兼容iOS&Android设备', 'public/imgs/accessory/charger-car.png', 69, 69, 20, 0);
INSERT INTO `product` VALUES (28, '小米车载充电器快充版(37W)', 7, '双口快充，车载充电更安全', '单口27W 快充 / 双口输出 / 多重安全保护', 'public/imgs/accessory/charger-car-37w.png', 49, 49, 20, 0);
INSERT INTO `product` VALUES (29, '小米二合一移动电源（充电器）', 7, '一个设备多种用途', '5000mAh充沛电量 / 多协议快充 / USB 口输出', 'public/imgs/accessory/charger-tio.png', 99, 99, 20, 0);
INSERT INTO `product` VALUES (30, '小米无线充电宝青春版10000mAh', 8, '能量满满，无线有线都能充', '10000mAh大容量 / 支持边充边放 / 有线无线都能充 / 双向快充', 'public/imgs/accessory/charger-10000mAh.png', 129, 129, 20, 8);
INSERT INTO `product` VALUES (31, '小米CC9 Pro/尊享版 撞色飘带保护壳', 5, '全面贴合，无感更舒适', '优选环保PC材质，强韧张力，全面贴合，无感更舒适', 'public/imgs/accessory/protectingShell-Mi-CC9Pro.png', 69, 69, 20, 0);
INSERT INTO `product` VALUES (32, 'Redmi K20系列 幻境之心保护壳', 5, '柔软坚韧,全面贴合', '优质环保材质，柔软坚韧 / 全面贴合，有效抵抗灰尘 / 鲜亮三种颜色可选，为爱机随时换装', 'public/imgs/accessory/protectingShell-RedMi-K20.png', 39, 39, 20, 0);
INSERT INTO `product` VALUES (33, '小米9 SE 街头风保护壳黑色', 5, '个性时尚,细节出众', '个性时尚 / 细节出众 / 纤薄轻巧 / 多彩时尚', 'public/imgs/accessory/protectingShell-Mi-9SE.png', 49, 49, 20, 0);
INSERT INTO `product` VALUES (34, '小米9 街头风保护壳 红色', 5, '个性时尚,细节出众', '时尚多彩 / 细节出众 / 纤薄轻巧 / 是腕带也是支架', 'public/imgs/accessory/protectingShell-Mi-9-red.png', 49, 49, 20, 0);
INSERT INTO `product` VALUES (35, '小米MIX 3 极简保护壳蓝色', 5, '时尚简约设计，手感细腻顺滑', '时尚简约设计，手感细腻顺滑 / 优质环保PC原材料，强韧张力，经久耐用 / 超薄 0.8MM厚度', 'public/imgs/accessory/protectingShell-Mix-3.png', 49, 12.9, 20, 0);

-- ----------------------------
-- Table structure for product_picture
-- ----------------------------
DROP TABLE IF EXISTS `product_picture`;
CREATE TABLE `product_picture`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `product_id` int(0) NOT NULL,
  `product_picture` char(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `intro` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_product_id`(`product_id`) USING BTREE,
  CONSTRAINT `FK_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 110 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_picture
-- ----------------------------
INSERT INTO `product_picture` VALUES (1, 1, 'public/imgs/phone/picture/Redmi-k30-1.png', NULL);
INSERT INTO `product_picture` VALUES (2, 1, 'public/imgs/phone/picture/Redmi-k30-2.png', NULL);
INSERT INTO `product_picture` VALUES (3, 1, 'public/imgs/phone/picture/Redmi-k30-3.png', NULL);
INSERT INTO `product_picture` VALUES (4, 1, 'public/imgs/phone/picture/Redmi-k30-4.png', NULL);
INSERT INTO `product_picture` VALUES (5, 1, 'public/imgs/phone/picture/Redmi-k30-5.png', NULL);
INSERT INTO `product_picture` VALUES (6, 2, 'public/imgs/phone/picture/Redmi K30 5G-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (7, 2, 'public/imgs/phone/picture/Redmi K30 5G-2.jpg', NULL);
INSERT INTO `product_picture` VALUES (8, 2, 'public/imgs/phone/picture/Redmi K30 5G-3.jpg', NULL);
INSERT INTO `product_picture` VALUES (9, 2, 'public/imgs/phone/picture/Redmi K30 5G-4.jpg', NULL);
INSERT INTO `product_picture` VALUES (10, 2, 'public/imgs/phone/picture/Redmi K30 5G-5.jpg', NULL);
INSERT INTO `product_picture` VALUES (11, 3, 'public/imgs/phone/picture/MI CC9 Pro-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (12, 3, 'public/imgs/phone/picture/MI CC9 Pro-2.jpg', NULL);
INSERT INTO `product_picture` VALUES (13, 3, 'public/imgs/phone/picture/MI CC9 Pro-3.jpg', NULL);
INSERT INTO `product_picture` VALUES (14, 3, 'public/imgs/phone/picture/MI CC9 Pro-4.jpg', NULL);
INSERT INTO `product_picture` VALUES (15, 3, 'public/imgs/phone/picture/MI CC9 Pro-5.jpg', NULL);
INSERT INTO `product_picture` VALUES (16, 3, 'public/imgs/phone/picture/MI CC9 Pro-6.jpg', NULL);
INSERT INTO `product_picture` VALUES (17, 4, 'public/imgs/phone/picture/Redmi 8-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (18, 4, 'public/imgs/phone/picture/Redmi 8-2.jpg', NULL);
INSERT INTO `product_picture` VALUES (19, 4, 'public/imgs/phone/picture/Redmi 8-3.jpg', NULL);
INSERT INTO `product_picture` VALUES (20, 4, 'public/imgs/phone/picture/Redmi 8-4.jpg', NULL);
INSERT INTO `product_picture` VALUES (21, 4, 'public/imgs/phone/picture/Redmi 8-5.jpg', NULL);
INSERT INTO `product_picture` VALUES (22, 5, 'public/imgs/phone/picture/Redmi-8A-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (23, 6, 'public/imgs/phone/picture/Redmi Note8 Pro-1.png', NULL);
INSERT INTO `product_picture` VALUES (24, 6, 'public/imgs/phone/picture/Redmi Note8 Pro-2.png', NULL);
INSERT INTO `product_picture` VALUES (25, 6, 'public/imgs/phone/picture/Redmi Note8 Pro-3.png', NULL);
INSERT INTO `product_picture` VALUES (26, 6, 'public/imgs/phone/picture/Redmi Note8 Pro-4.png', NULL);
INSERT INTO `product_picture` VALUES (27, 6, 'public/imgs/phone/picture/Redmi Note8 Pro-5.png', NULL);
INSERT INTO `product_picture` VALUES (28, 7, 'public/imgs/phone/picture/Redmi Note8-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (29, 7, 'public/imgs/phone/picture/Redmi Note8-2.jpg', NULL);
INSERT INTO `product_picture` VALUES (30, 7, 'public/imgs/phone/picture/Redmi Note8-3.jpg', NULL);
INSERT INTO `product_picture` VALUES (31, 7, 'public/imgs/phone/picture/Redmi Note8-4.jpg', NULL);
INSERT INTO `product_picture` VALUES (32, 7, 'public/imgs/phone/picture/Redmi Note8-5.jpg', NULL);
INSERT INTO `product_picture` VALUES (33, 8, 'public/imgs/phone/picture/Redmi 7A-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (34, 8, 'public/imgs/phone/picture/Redmi 7A-2.jpg', NULL);
INSERT INTO `product_picture` VALUES (35, 8, 'public/imgs/phone/picture/Redmi 7A-3.jpg', NULL);
INSERT INTO `product_picture` VALUES (36, 8, 'public/imgs/phone/picture/Redmi 7A-4.jpg', NULL);
INSERT INTO `product_picture` VALUES (37, 8, 'public/imgs/phone/picture/Redmi 7A-5.jpg', NULL);
INSERT INTO `product_picture` VALUES (38, 9, 'public/imgs/phone/picture/MiTv-4A-32-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (39, 9, 'public/imgs/phone/picture/MiTv-4A-32-2.jpg', NULL);
INSERT INTO `product_picture` VALUES (40, 9, 'public/imgs/phone/picture/MiTv-4A-32-3.jpg', NULL);
INSERT INTO `product_picture` VALUES (41, 9, 'public/imgs/phone/picture/MiTv-4A-32-4.jpg', NULL);
INSERT INTO `product_picture` VALUES (42, 10, 'public/imgs/phone/picture/MiTv-E55A-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (43, 10, 'public/imgs/phone/picture/MiTv-E55A-2.jpg', NULL);
INSERT INTO `product_picture` VALUES (44, 10, 'public/imgs/phone/picture/MiTv-E55A-3.jpg', NULL);
INSERT INTO `product_picture` VALUES (45, 10, 'public/imgs/phone/picture/MiTv-E55A-4.jpg', NULL);
INSERT INTO `product_picture` VALUES (46, 11, 'public/imgs/phone/picture/MiTv-E65A-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (47, 11, 'public/imgs/phone/picture/MiTv-E65A-2.jpg', NULL);
INSERT INTO `product_picture` VALUES (48, 11, 'public/imgs/phone/picture/MiTv-E65A-3.jpg', NULL);
INSERT INTO `product_picture` VALUES (49, 11, 'public/imgs/phone/picture/MiTv-E65A-4.jpg', NULL);
INSERT INTO `product_picture` VALUES (50, 12, 'public/imgs/phone/picture/MiTv-4X 43-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (51, 12, 'public/imgs/phone/picture/MiTv-4X 43-2.jpg', NULL);
INSERT INTO `product_picture` VALUES (52, 12, 'public/imgs/phone/picture/MiTv-4X 43-3.jpg', NULL);
INSERT INTO `product_picture` VALUES (53, 13, 'public/imgs/phone/picture/MiTv-4C 55-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (54, 13, 'public/imgs/phone/picture/MiTv-4C 55-2.jpg', NULL);
INSERT INTO `product_picture` VALUES (55, 13, 'public/imgs/phone/picture/MiTv-4C 55-3.jpg', NULL);
INSERT INTO `product_picture` VALUES (56, 14, 'public/imgs/phone/picture/MiTv-4A 65-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (57, 15, 'public/imgs/phone/picture/MiTv-ArtTv-65-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (58, 16, 'public/imgs/phone/picture/AirCondition-V1C1-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (59, 17, 'public/imgs/phone/picture/AirCondition-F3W1-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (60, 18, 'public/imgs/phone/picture/Washer-Pro-10-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (61, 18, 'public/imgs/phone/picture/Washer-Pro-10-2.jpg', NULL);
INSERT INTO `product_picture` VALUES (62, 18, 'public/imgs/phone/picture/Washer-Pro-10-3.jpg', NULL);
INSERT INTO `product_picture` VALUES (63, 18, 'public/imgs/phone/picture/Washer-Pro-10-4.jpg', NULL);
INSERT INTO `product_picture` VALUES (64, 19, 'public/imgs/phone/picture/protectingShell-RedMi-K20&pro-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (65, 20, 'public/imgs/phone/picture/protectingShell-Mi-9-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (66, 20, 'public/imgs/phone/picture/protectingShell-Mi-9-2.jpg', NULL);
INSERT INTO `product_picture` VALUES (67, 21, 'public/imgs/phone/picture/protectingMen-Mi-CC9-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (68, 22, 'public/imgs/phone/picture/protectingMen-Mi-CC9e-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (69, 23, 'public/imgs/phone/picture/charger-30w-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (70, 23, 'public/imgs/phone/picture/charger-30w-2.jpg', NULL);
INSERT INTO `product_picture` VALUES (71, 23, 'public/imgs/phone/picture/charger-30w-3.jpg', NULL);
INSERT INTO `product_picture` VALUES (72, 23, 'public/imgs/phone/picture/charger-30w-4.jpg', NULL);
INSERT INTO `product_picture` VALUES (73, 24, 'public/imgs/phone/picture/charger-18w-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (74, 24, 'public/imgs/phone/picture/charger-18w-2.jpg', NULL);
INSERT INTO `product_picture` VALUES (75, 24, 'public/imgs/phone/picture/charger-18w-3.jpg', NULL);
INSERT INTO `product_picture` VALUES (76, 25, 'public/imgs/phone/picture/charger-60w-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (77, 25, 'public/imgs/phone/picture/charger-60w-2.jpg', NULL);
INSERT INTO `product_picture` VALUES (78, 25, 'public/imgs/phone/picture/charger-60w-3.jpg', NULL);
INSERT INTO `product_picture` VALUES (79, 25, 'public/imgs/phone/picture/charger-60w-4.jpg', NULL);
INSERT INTO `product_picture` VALUES (80, 26, 'public/imgs/phone/picture/charger-36w-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (81, 26, 'public/imgs/phone/picture/charger-36w-2.jpg', NULL);
INSERT INTO `product_picture` VALUES (82, 26, 'public/imgs/phone/picture/charger-36w-3.jpg', NULL);
INSERT INTO `product_picture` VALUES (83, 26, 'public/imgs/phone/picture/charger-36w-4.jpg', NULL);
INSERT INTO `product_picture` VALUES (84, 26, 'public/imgs/phone/picture/charger-36w-5.jpg', NULL);
INSERT INTO `product_picture` VALUES (85, 27, 'public/imgs/phone/picture/charger-car-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (86, 27, 'public/imgs/phone/picture/charger-car-2.jpg', NULL);
INSERT INTO `product_picture` VALUES (87, 27, 'public/imgs/phone/picture/charger-car-3.jpg', NULL);
INSERT INTO `product_picture` VALUES (88, 27, 'public/imgs/phone/picture/charger-car-4.jpg', NULL);
INSERT INTO `product_picture` VALUES (89, 27, 'public/imgs/phone/picture/charger-car-5.jpg', NULL);
INSERT INTO `product_picture` VALUES (90, 27, 'public/imgs/phone/picture/charger-car-6.jpg', NULL);
INSERT INTO `product_picture` VALUES (91, 28, 'public/imgs/phone/picture/charger-car-37w-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (92, 28, 'public/imgs/phone/picture/charger-car-37w-2.jpg', NULL);
INSERT INTO `product_picture` VALUES (93, 28, 'public/imgs/phone/picture/charger-car-37w-3.jpg', NULL);
INSERT INTO `product_picture` VALUES (94, 28, 'public/imgs/phone/picture/charger-car-37w-4.jpg', NULL);
INSERT INTO `product_picture` VALUES (95, 28, 'public/imgs/phone/picture/charger-car-37w-5.jpg', NULL);
INSERT INTO `product_picture` VALUES (96, 29, 'public/imgs/phone/picture/charger-tio-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (97, 29, 'public/imgs/phone/picture/charger-tio-2.jpg', NULL);
INSERT INTO `product_picture` VALUES (98, 29, 'public/imgs/phone/picture/charger-tio-3.jpg', NULL);
INSERT INTO `product_picture` VALUES (99, 29, 'public/imgs/phone/picture/charger-tio-4.jpg', NULL);
INSERT INTO `product_picture` VALUES (100, 29, 'public/imgs/phone/picture/charger-tio-5.jpg', NULL);
INSERT INTO `product_picture` VALUES (101, 30, 'public/imgs/phone/picture/charger-10000mAh-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (102, 30, 'public/imgs/phone/picture/charger-10000mAh-2.jpg', NULL);
INSERT INTO `product_picture` VALUES (103, 30, 'public/imgs/phone/picture/charger-10000mAh-3.jpg', NULL);
INSERT INTO `product_picture` VALUES (104, 30, 'public/imgs/phone/picture/charger-10000mAh-4.jpg', NULL);
INSERT INTO `product_picture` VALUES (105, 30, 'public/imgs/phone/picture/charger-10000mAh-5.jpg', NULL);
INSERT INTO `product_picture` VALUES (106, 31, 'public/imgs/phone/picture/protectingShell-Mi-CC9Pro-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (107, 32, 'public/imgs/phone/picture/protectingShell-RedMi-K20-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (108, 33, 'public/imgs/phone/picture/protectingShell-Mi-9SE-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (109, 34, 'public/imgs/phone/picture/protectingShell-Mi-9-red-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (110, 35, 'public/imgs/phone/picture/protectingShell-Mix-3-1.jpg', NULL);

-- ----------------------------
-- Table structure for shoppingcart
-- ----------------------------
DROP TABLE IF EXISTS `shoppingcart`;
CREATE TABLE `shoppingcart`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NOT NULL,
  `product_id` int(0) NOT NULL,
  `num` int(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_user_id`(`user_id`) USING BTREE,
  INDEX `FK_shoppingCart_id`(`product_id`) USING BTREE,
  CONSTRAINT `FK_shoppingCart_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 117 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shoppingcart
-- ----------------------------
INSERT INTO `shoppingcart` VALUES (3, 1, 4, 1);
INSERT INTO `shoppingcart` VALUES (4, 1, 1, 100);
INSERT INTO `shoppingcart` VALUES (115, 2, 1, 2);
INSERT INTO `shoppingcart` VALUES (116, 2, 3, 4);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `user_id` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `is_del` tinyint(1) NOT NULL COMMENT '是否被删除 1表示存在0表示删除',
  `role` tinyint(1) NOT NULL COMMENT '角色 默认注册为1用户 0为管理员',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `userName`(`username`) USING BTREE,
  UNIQUE INDEX `userName_2`(`username`) USING BTREE,
  UNIQUE INDEX `userName_3`(`username`) USING BTREE,
  UNIQUE INDEX `userName_4`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'admin', 'dawda', '123456', '15061701265', '2022-11-22 13:57:53', '2022-11-29 17:28:07', 0, 0);
INSERT INTO `users` VALUES (2, 'zarttic', 'admin', '21232f297a57a5a743894a0e4a801fc3', '15061701265', '2022-11-22 14:09:54', '2022-12-02 09:10:08', 1, 0);
INSERT INTO `users` VALUES (3, 'user_91065915346699', 'ad', '$2a$12$I.Z72IMNFCW5w2MHDAQjkO.6PHq7VPMirpYMYEPOTGZWS1oRgpa.K', '15061701265', '2022-11-22 14:24:42', '2022-11-23 17:49:21', 1, 1);
INSERT INTO `users` VALUES (11, 'user_89726416689', 'ww', '$2a$12$4QyCVyViDsX55UM9M52fA.EkPV9sGtQEcmwpZ0J.tjzf/jEefkXzW', '15061701265', '2022-11-23 20:47:10', '2022-11-29 18:51:36', 1, 1);
INSERT INTO `users` VALUES (12, 'user_7317491864', 'aa', '4124bc0a9335c27f086f24ba207a4912', '6666333', '2022-11-23 23:31:04', '2022-11-29 18:51:43', 1, 1);
INSERT INTO `users` VALUES (13, 'user_0935904895', '605736382', '84eb71db6e08010ca076cf2e562b2251', '42452', '2022-11-28 16:24:35', '2022-11-29 18:52:21', 1, 1);
INSERT INTO `users` VALUES (14, 'user_8130580332', 's605736382', '84eb71db6e08010ca076cf2e562b2251', '42552452', '2022-11-28 16:25:10', '2022-11-29 18:52:26', 1, 1);
INSERT INTO `users` VALUES (15, 'user_8727571952', 'adadad', '26460f32a3164e6382436aba45eaf862', '24524', '2022-11-28 16:25:49', '2022-11-29 18:52:24', 1, 1);
INSERT INTO `users` VALUES (16, 'user_1960501634', 'admin1', '21232f297a57a5a743894a0e4a801fc3', '4524524', '2022-11-28 19:47:52', '2022-11-29 18:52:29', 1, 1);
INSERT INTO `users` VALUES (17, 'user_6044002092', 'lipengyu', 'b86f921c6e2a766f4eccb3b29233678c', '13382931419', '2022-11-29 14:20:18', '2022-11-29 14:20:18', 1, 1);
INSERT INTO `users` VALUES (18, 'user_4588273830', 'li123456', 'fe1a10e4576c9db0b40e26b9ffa38ea5', NULL, '2022-11-30 15:29:42', '2022-11-30 15:29:42', 1, 1);
INSERT INTO `users` VALUES (19, 'user_5222673898', 'ccccc', '67c762276bced09ee4df0ed537d164ea', NULL, '2022-12-02 09:30:41', '2022-12-02 09:30:41', 1, 1);
INSERT INTO `users` VALUES (20, 'user_0499376133', 'wwwwww', 'd785c99d298a4e9e6e13fe99e602ef42', NULL, '2022-12-02 09:42:20', '2022-12-02 09:42:20', 1, 1);
INSERT INTO `users` VALUES (21, 'user_1878156123', 'rrrrrr', 'ff2f24f8b6d253bb5a8bc55728ca7372', NULL, '2022-12-02 09:51:54', '2022-12-02 09:51:54', 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
