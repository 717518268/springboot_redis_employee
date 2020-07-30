/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50561
Source Host           : localhost:3306
Source Database       : employee

Target Server Type    : MYSQL
Target Server Version : 50561
File Encoding         : 65001

Date: 2020-07-30 10:52:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `dname` varchar(15) NOT NULL DEFAULT '' COMMENT '部门名字',
  `nid` int(11) DEFAULT NULL COMMENT '部门外键did',
  PRIMARY KEY (`id`,`dname`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', '总经办', '1');
INSERT INTO `department` VALUES ('2', '仓储部\r\n', '2');
INSERT INTO `department` VALUES ('3', '人力资源部', '3');
INSERT INTO `department` VALUES ('4', '设备部\r\n', '4');
INSERT INTO `department` VALUES ('5', '仪表部', '5');
INSERT INTO `department` VALUES ('6', '生产部\r\n', '6');
INSERT INTO `department` VALUES ('7', '研发部', '7');
INSERT INTO `department` VALUES ('8', '营业管理部', '8');
INSERT INTO `department` VALUES ('9', '安全环保部', '9');
INSERT INTO `department` VALUES ('10', '市场部', '10');
INSERT INTO `department` VALUES ('11', '工艺部', '11');
INSERT INTO `department` VALUES ('12', '财务部\r\n', '12');
INSERT INTO `department` VALUES ('13', '发展部', '13');
INSERT INTO `department` VALUES ('14', '采购部', '14');
INSERT INTO `department` VALUES ('15', '华东区销售部', '15');
INSERT INTO `department` VALUES ('16', '客户服务部', '16');
INSERT INTO `department` VALUES ('17', '总务部', '17');
INSERT INTO `department` VALUES ('18', '品保部', '18');
INSERT INTO `department` VALUES ('19', '计划部', '19');
INSERT INTO `department` VALUES ('20', '销售部', '20');
INSERT INTO `department` VALUES ('21', '测试部门', '21');
INSERT INTO `department` VALUES ('22', '投行部', '22');

-- ----------------------------
-- Table structure for leave_records
-- ----------------------------
DROP TABLE IF EXISTS `leave_records`;
CREATE TABLE `leave_records` (
  `lid` varchar(50) NOT NULL,
  `uid` varchar(50) NOT NULL COMMENT '引用user表中的id',
  `lcount` int(10) DEFAULT NULL COMMENT '天数',
  `leave_reason` varchar(150) DEFAULT NULL,
  `leave_yearstart` timestamp NULL DEFAULT NULL COMMENT '请假开始时间',
  `leave_yearend` timestamp NULL DEFAULT NULL COMMENT '请假结束时间',
  `input_time` datetime DEFAULT NULL COMMENT '录入时间',
  `stauts` varchar(10) DEFAULT NULL COMMENT '请假状态',
  PRIMARY KEY (`lid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of leave_records
-- ----------------------------
INSERT INTO `leave_records` VALUES ('02a63c64-dec0-4d4f-89ae-db69687a791d', '0a4fcfec-fd1b-4628-a04d-8ef30922e4e2', '1', '感冒', '2022-07-22 08:15:10', '2020-07-23 08:15:15', '2020-07-15 08:18:41', '批准');
INSERT INTO `leave_records` VALUES ('21616973-761b-4805-babd-7bb67758863f', '226ec4c5-fef3-449a-825b-96ee8574a252', '2', '生病', '2020-07-13 16:00:00', '2020-07-15 16:00:00', '2020-07-24 01:41:33', '批准');
INSERT INTO `leave_records` VALUES ('bbd413be-a6c1-47ca-a3fa-4899f500759c', '37f44bed-7629-479a-895b-3dd9abcb24b4', '1', '回家探亲', '2020-07-13 16:00:00', '2020-07-14 16:00:00', '2020-07-22 07:14:45', null);
INSERT INTO `leave_records` VALUES ('c789beb9-544f-4bfc-bf99-d19ebf10d3ee', '226ec4c5-fef3-449a-825b-96ee8574a252', '2', '生病', '2020-07-16 16:00:00', '2020-07-18 16:00:00', '2020-07-24 02:58:28', '批准');

-- ----------------------------
-- Table structure for login_user
-- ----------------------------
DROP TABLE IF EXISTS `login_user`;
CREATE TABLE `login_user` (
  `lid` varchar(50) NOT NULL,
  `user_name` varchar(20) DEFAULT NULL COMMENT '账号',
  `pass_word` varchar(50) DEFAULT NULL COMMENT '密码',
  `count` int(10) DEFAULT NULL,
  `creattime` datetime DEFAULT NULL COMMENT '创建时间',
  `ruler` varchar(10) DEFAULT NULL COMMENT '权限',
  PRIMARY KEY (`lid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of login_user
-- ----------------------------
INSERT INTO `login_user` VALUES ('13150670-6b4d-45eb-b013-b9e1b42602d5', 'admin', 'e35cf7b66449df565f93c607d5a81d09', '0', '2020-07-25 08:49:26', 'user');
INSERT INTO `login_user` VALUES ('aa3d37e1-fd5e-4437-899d-53caaf8a48fb', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '0', '2020-07-25 08:39:28', 'user');
INSERT INTO `login_user` VALUES ('d1b91ffd-c18c-40f5-8943-72314802da8e', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '0', '2020-07-25 08:37:57', 'user');
INSERT INTO `login_user` VALUES ('e34b2f70-a6a3-4b91-ae40-2dfb828d6f2c', '717518268', '759fee1b1d275f9c6682cf0510abed8f', '6', '2020-07-25 07:46:30', 'root');

-- ----------------------------
-- Table structure for resignation_table
-- ----------------------------
DROP TABLE IF EXISTS `resignation_table`;
CREATE TABLE `resignation_table` (
  `q_id` varchar(45) NOT NULL COMMENT '唯一性id',
  `u_id` varchar(45) DEFAULT NULL COMMENT '用户uid，在员工进行离职的时候就设置进来',
  `user_name` varchar(15) DEFAULT NULL COMMENT '员工名字',
  `departmentid` varchar(11) DEFAULT NULL COMMENT '部门id',
  `working_years` varchar(10) DEFAULT NULL COMMENT '工龄',
  `entry_time` datetime DEFAULT NULL COMMENT '入职时间',
  `exit_time` datetime DEFAULT NULL COMMENT '离职时间',
  `endupdatetime` datetime DEFAULT NULL COMMENT '最后一次修改时间',
  PRIMARY KEY (`q_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resignation_table
-- ----------------------------
INSERT INTO `resignation_table` VALUES ('489cfe9c-311a-4079-9842-de174c048c35', '194cbe1a-4f5a-42a8-afa9-8a4fad721c84', '站长', '测试部门', '1年|共：655天', '2018-10-08 16:00:00', '2020-07-24 16:00:00', '2020-07-25 02:09:39');
INSERT INTO `resignation_table` VALUES ('66b7eb38-a5a5-4c78-8afe-59e64e5a445e', 'ba28b9e7-915e-4f8f-9358-3176780e8c7c', '我是', '测试部门', '0年|共：0天', '2020-07-27 16:00:00', '2020-07-27 16:00:00', '2020-07-27 02:51:26');
INSERT INTO `resignation_table` VALUES ('ad2dfce6-1017-434b-90c9-445be525e0d5', '129cdb0d-e534-4870-8b7a-b7756c65d65c', '单位', '测试部门', '0年|共：0天', '2020-07-26 16:00:00', '2020-07-27 16:00:00', '2020-07-27 02:08:56');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(255) NOT NULL DEFAULT '',
  `number` int(11) NOT NULL COMMENT '员工编号',
  `username` varchar(10) DEFAULT NULL COMMENT '员工名字',
  `sex` varchar(4) DEFAULT NULL,
  `registertime` datetime DEFAULT NULL COMMENT '入职时间',
  `did` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`,`number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('001615c9-4ae6-46da-be34-479d4a06fa14', '321', '陈夏妍\r\n', '男', '2020-04-06 00:00:00', '18');
INSERT INTO `user` VALUES ('016ed9da-ba5a-4233-bcfc-be370cb7d246', '347', '朱文锋\r\n', '男', '2020-06-27 00:00:00', '13');
INSERT INTO `user` VALUES ('02a63c64-dec0-4d4f-89ae-db69687a791d', '305', '许先稳\r\n', '男', '2020-03-12 00:00:00', '13');
INSERT INTO `user` VALUES ('03399142-f947-41eb-82ab-9d5988264bb3', '147', '张丙新\r\n', '男', '2018-03-06 00:00:00', '6');
INSERT INTO `user` VALUES ('0348a022-6cb3-43f8-9868-73aedea0be9c', '212', '张朝军\r\n', '男', '2019-02-18 00:00:00', '7');
INSERT INTO `user` VALUES ('05e218d5-4b62-4e2c-81a3-c2ad8177713b', '341', '周达强\r\n', '男', '2020-06-13 00:00:00', '13');
INSERT INTO `user` VALUES ('06e18dd4-a507-43e8-83e3-e9f3ebcd3917', '211', '陆钦展\r\n', '男', '2018-12-24 00:00:00', '7');
INSERT INTO `user` VALUES ('07bb0047-a336-4161-b34f-6b5c49773fe7', '345', '肖浩强\r\n', '男', '2020-06-29 00:00:00', '13');
INSERT INTO `user` VALUES ('0a4fcfec-fd1b-4628-a04d-8ef30922e4e2', '3', '苏式国', '男', '2015-12-23 00:00:00', '3');
INSERT INTO `user` VALUES ('0aefd12d-5ea6-4405-a080-522bf9c4ab5d', '20', '吴景瑶', '男', '2016-03-03 00:00:00', '8');
INSERT INTO `user` VALUES ('0b69516a-da12-489b-99f6-948e0c2eb60f', '145', '胡展东\r\n', '男', '2015-12-23 00:00:00', '15');
INSERT INTO `user` VALUES ('11eebbc9-9c7a-4493-8aca-ccc4a2e2c3cf', '355', '徐紫暑\r\n', '男', '2020-06-28 00:00:00', '13');
INSERT INTO `user` VALUES ('1243cec4-0c78-4a51-aa51-947c3ff2ee60', '241', '刘亦祥\r\n', '男', '2019-05-29 00:00:00', '2');
INSERT INTO `user` VALUES ('1246c9c5-538d-4f5c-b97c-42085a28b6d3', '129', '刘洁冰\r\n', '男', '2017-09-04 00:00:00', '2');
INSERT INTO `user` VALUES ('1283d8b0-5c1d-4bc8-a68a-840f65bda365', '21', '彭昌文\r\n', '男', '2016-03-03 00:00:00', '9');
INSERT INTO `user` VALUES ('13645bed-f626-4fed-b42a-6f6280c99526', '358', '李啟健\r\n', '男', '2020-06-29 00:00:00', '13');
INSERT INTO `user` VALUES ('1716682e-015a-4f31-baee-966d869d5861', '72', '李智超\r\n', '男', '2016-09-05 00:00:00', '7');
INSERT INTO `user` VALUES ('1b8ad0c4-8e71-47a3-aa66-51ef7b36742e', '89', '章芸\r\n', '男', '2017-01-05 00:00:00', '12');
INSERT INTO `user` VALUES ('1c87f267-b89c-4256-aada-8b6322c671c9', '346', '梁梓健\r\n', '男', '2020-06-27 00:00:00', '13');
INSERT INTO `user` VALUES ('1d03f231-1835-4ee6-8049-0fcb7b41045e', '140', '李兵\r\n', '男', '2017-12-13 00:00:00', '17');
INSERT INTO `user` VALUES ('1e9cabb0-d0de-4f42-a666-f43dabf2d087', '313', '张辉勇\r\n', '男', '2020-03-19 00:00:00', '6');
INSERT INTO `user` VALUES ('1fd8d728-ec83-47be-9dd6-9e003c242d6d', '351', '吴洪绵\r\n', '男', '2020-06-27 00:00:00', '13');
INSERT INTO `user` VALUES ('21101dca-3b95-4fb0-87fc-dc79f5a35b01', '350', '段锟\r\n', '男', '2020-06-27 00:00:00', '13');
INSERT INTO `user` VALUES ('226ec4c5-fef3-449a-825b-96ee8574a252', '9527', '粟盈龙\r\n', '男', '2018-03-01 00:00:00', '1');
INSERT INTO `user` VALUES ('240b57b0-51fe-4d12-b961-737c58096d65', '250', '谢银燕\r\n', '男', '2019-06-25 00:00:00', '18');
INSERT INTO `user` VALUES ('2421f655-1ca9-44c4-bf50-3d1911c924b2', '301', '陈奕金\r\n', '男', '2020-03-10 00:00:00', '13');
INSERT INTO `user` VALUES ('265273c5-dc64-45f4-bfd4-bf9ea5d52848', '328', '谭锦伦\r\n', '男', '2020-04-27 00:00:00', '13');
INSERT INTO `user` VALUES ('2890616c-0949-4773-a615-8d795c04d106', '277', '戴志森\r\n', '男', '2019-10-24 00:00:00', '6');
INSERT INTO `user` VALUES ('2ad99c58-b0ad-49da-b189-b0597af2c6a5', '202', '杨艳庆\r\n', '男', '2018-11-08 00:00:00', '7');
INSERT INTO `user` VALUES ('2e86e599-d469-48bb-a0eb-886e8a8a171b', '65', '韦承志\r\n', '男', '2016-07-13 00:00:00', '6');
INSERT INTO `user` VALUES ('311d0f90-f7a3-4087-95e6-a4ab810ecfcc', '339', '卢慧\r\n', '男', '2020-06-09 00:00:00', '13');
INSERT INTO `user` VALUES ('329a57b4-bcc0-436a-9fd9-57111766b62a', '10', '杜鹏\r\n', '男', '2016-02-15 00:00:00', '5');
INSERT INTO `user` VALUES ('3305a4ef-e893-4204-820a-9b48d4384612', '8', '苏文萍', '男', '2016-01-01 00:00:00', '3');
INSERT INTO `user` VALUES ('331bfdb2-896d-4384-b481-87a8e8dc5413', '348', '李华卉\r\n', '男', '2020-06-27 00:00:00', '13');
INSERT INTO `user` VALUES ('341ddda4-ae6e-4a53-9918-d636dec52e31', '320', '邝琳娜\r\n', '男', '2020-04-06 00:00:00', '19');
INSERT INTO `user` VALUES ('3793e80a-8607-468d-b1d4-d32c508bbc90', '344', '屈敏\r\n', '男', '2020-06-22 00:00:00', '11');
INSERT INTO `user` VALUES ('380faac6-5763-4ce1-981f-7bfc00eefc53', '354', '蔡凯\r\n', '男', '2020-06-28 00:00:00', '13');
INSERT INTO `user` VALUES ('3cd5ff05-e070-478c-929b-3546049536b4', '96', '李诗\r\n', '男', '2017-02-28 00:00:00', '11');
INSERT INTO `user` VALUES ('3cf36516-0b09-4b72-8614-2513c2c968e0', '9', '牙举定\r\n', '男', '2016-01-08 00:00:00', '4');
INSERT INTO `user` VALUES ('4155259a-5443-4099-a858-d15f46425184', '196', '黄崇祥\r\n', '男', '2018-09-12 00:00:00', '11');
INSERT INTO `user` VALUES ('41fea5b9-d9a5-4ef5-8761-46d265bf530b', '206', '谢琪\r\n', '男', '2018-12-04 00:00:00', '18');
INSERT INTO `user` VALUES ('48a7c7e2-250f-44b7-bbad-0bbc15a5337e', '164', '严志强\r\n', '男', '2018-04-17 00:00:00', '18');
INSERT INTO `user` VALUES ('48cdf1d4-cdf8-4eb8-b769-dcc238e77027', '329', '张奇蝶\r\n', '男', '2020-05-13 00:00:00', '18');
INSERT INTO `user` VALUES ('49ad7bbf-f75c-403d-a429-26f94cb7a984', '221', '胡帅\r\n', '男', '2019-03-06 00:00:00', '6');
INSERT INTO `user` VALUES ('49bc17b7-5063-4093-8f8e-358eb9550bca', '337', '冉威威\r\n', '男', '2020-06-04 00:00:00', '6');
INSERT INTO `user` VALUES ('4b66fc86-49ad-4b6f-96e7-811700b00c36', '75', '唐海涯\r\n', '男', '2016-09-23 00:00:00', '6');
INSERT INTO `user` VALUES ('4c496b43-abf1-423e-8f6c-e81d503303f7', '285', '江信求\r\n', '男', '2019-11-13 00:00:00', '16');
INSERT INTO `user` VALUES ('4c95d6fb-863c-441e-b917-7c27b6908228', '352', '蔡范均\r\n', '男', '2020-06-28 00:00:00', '13');
INSERT INTO `user` VALUES ('4e1d384c-091c-427c-9128-19f38f74e66e', '197', '陈仕威\r\n', '男', '2018-09-25 00:00:00', '16');
INSERT INTO `user` VALUES ('4f695c98-d3b6-4ea8-aa05-fe026aa8488b', '245', '徐勤发\r\n', '男', '2019-06-13 00:00:00', '6');
INSERT INTO `user` VALUES ('4f76d416-75ce-458c-b74c-0b3c3e3f2318', '31', '李盛琨\r\n', '男', '2016-03-24 00:00:00', '6');
INSERT INTO `user` VALUES ('52805eb2-f4b1-4125-a88e-924fc09d338e', '19', '徐国正', '男', '2016-03-01 00:00:00', '7');
INSERT INTO `user` VALUES ('528a28f8-e205-4f49-8112-2b044e6a5c06', '261', '冯奇炎\r\n', '男', '2019-08-05 00:00:00', '5');
INSERT INTO `user` VALUES ('5329f652-97c5-457d-9195-973ecb0aa196', '357', '梁志杰\r\n', '男', '2020-06-28 00:00:00', '13');
INSERT INTO `user` VALUES ('542d8597-ddbb-49c5-80a4-5a354fcd1c1a', '287', '官细红\r\n', '男', '2019-12-09 00:00:00', '19');
INSERT INTO `user` VALUES ('548ec6a3-ceb1-46e3-9fd6-cd7a039c4982', '14', '黄勇', '男', '2016-02-15 00:00:00', '4');
INSERT INTO `user` VALUES ('55ffc8fb-d94b-4e65-9956-e50e0fe38b45', '317', '李忠鹏\r\n', '男', '2020-03-26 00:00:00', '13');
INSERT INTO `user` VALUES ('5b27dad4-228d-49aa-8ccf-f53944672047', '319', '赵海霞\r\n', '男', '2020-04-06 00:00:00', '1');
INSERT INTO `user` VALUES ('608e6d12-e3de-4674-8045-dd9e745c7f1c', '330', '陈柳生\r\n', '男', '2020-05-18 00:00:00', '13');
INSERT INTO `user` VALUES ('60ac1ce5-3c13-4e38-82e6-13cdae5ef502', '356', '任嘉琪\r\n', '男', '2020-06-28 00:00:00', '13');
INSERT INTO `user` VALUES ('64a8dca6-3b84-40c9-9325-93e36b0c931e', '353', '蔡景健\r\n', '男', '2020-06-28 00:00:00', '13');
INSERT INTO `user` VALUES ('67e47e55-e0aa-422a-bd96-501aa7a92b74', '235', '梁兆彬\r\n', '男', '2019-04-17 00:00:00', '18');
INSERT INTO `user` VALUES ('6aac6ce9-34de-42f4-b07b-13fbfd0b13a5', '92', '孙光志', '男', '2017-02-25 00:00:00', '6');
INSERT INTO `user` VALUES ('6f04d6f7-d977-4af1-b796-d06d9e9045b7', '201', '谭圣华\r\n', '男', '2018-10-19 00:00:00', '2');
INSERT INTO `user` VALUES ('6f314cfb-c01c-40e5-a192-ab995d0f3d78', '227', '李高庆\r\n', '男', '2019-03-15 00:00:00', '13');
INSERT INTO `user` VALUES ('7016fd04cc-0c07-4e3a-8c8b-cf65d1589ca0', '244', '谭裕刚\r\n', '男', '2019-06-13 00:00:00', '6');
INSERT INTO `user` VALUES ('73dd59b3-877c-43fb-8c01-77dde84f4b96', '161', '邵诗玲\r\n', '男', '2018-04-16 00:00:00', '6');
INSERT INTO `user` VALUES ('74a54800-e64e-4a47-8f60-aaae1ccef16c', '104', '张治\r\n', '男', '2017-03-01 00:00:00', '1');
INSERT INTO `user` VALUES ('7650d4ae-b512-4536-8fbc-503a287ac6d6', '6', '林辽远', '男', '2016-01-01 00:00:00', '2');
INSERT INTO `user` VALUES ('77f6fcdb-1a26-470d-8c20-eb50a422cbe1', '327', '贝佳\r\n', '男', '2020-04-22 00:00:00', '1');
INSERT INTO `user` VALUES ('78b9bb6e-1019-480a-8469-0f563059f619', '101', '欧佩清\r\n', '男', '2017-03-14 00:00:00', '19');
INSERT INTO `user` VALUES ('797b3c2d9d-e428-4a7b-bcef-9687ec06d8cd', '268', '潘华彬\r\n', '男', '2019-08-27 00:00:00', '6');
INSERT INTO `user` VALUES ('817f1dac-3086-4890-b1b5-db38a64afb9c', '278', '董良善\r\n', '男', '2019-10-24 00:00:00', '6');
INSERT INTO `user` VALUES ('846bf6ca-e438-48da-a4fb-24fbc94968d1', '303', '叶剑聪\r\n', '男', '2020-03-09 00:00:00', '13');
INSERT INTO `user` VALUES ('871aedf5-cdef-444f-b5ed-db04ed6731ba', '44', '李威\r\n', '男', '2015-12-23 00:00:00', '2');
INSERT INTO `user` VALUES ('88a59093-c97c-4e4f-848e-17c7e0d627b3', '90', '郑业梅\r\n', '男', '2017-02-13 00:00:00', '1');
INSERT INTO `user` VALUES ('8946ab06-4d21-4491-bc29-435c2ae71d6e', '323', '宗志安\r\n', '男', '2020-04-06 00:00:00', '6');
INSERT INTO `user` VALUES ('89ce9aa4-f0fa-457b-ba07-2eb7d7fdfbd5', '17', '石远见', '男', '2016-02-19 00:00:00', '6');
INSERT INTO `user` VALUES ('8cd99fcf-a39d-40fc-ba0c-83eab4307580', '342', '刘志军\r\n', '男', '2020-06-17 00:00:00', '13');
INSERT INTO `user` VALUES ('8d7944f1-731a-40b7-9f1c-b745828d69ea', '322', '朱建斌\r\n', '男', '2020-04-06 00:00:00', '4');
INSERT INTO `user` VALUES ('8d8877b7-127a-431c-ae6b-1e1f4eef8996', '297', '吴彦平\r\n', '男', '2020-03-02 00:00:00', '2');
INSERT INTO `user` VALUES ('8e4a841a-b446-4f22-9712-67d247c11e9f', '272', '戴泽精\r\n', '男', '2019-09-25 00:00:00', '6');
INSERT INTO `user` VALUES ('90322ac9-9083-4786-a415-b1d6ebd7dee1', '343', '张旭\r\n', '男', '2020-06-17 00:00:00', '7');
INSERT INTO `user` VALUES ('909b44fc-aa0a-4b0d-84f3-b7cb8b3b1f12', '83', '邓佩红\r\n', '男', '2016-11-09 00:00:00', '12');
INSERT INTO `user` VALUES ('95049d9a-1498-478d-b234-dde55faa2903', '304', '王志明\r\n', '男', '2020-03-09 00:00:00', '6');
INSERT INTO `user` VALUES ('95a48bc9-1616-4f9c-9399-32eae6ec26f3', '40', '谢剑清', '男', '2016-04-12 00:00:00', '10');
INSERT INTO `user` VALUES ('95a8dfa2-68c0-4b2b-b59b-7744c9f4c1c9', '30', '刘双如', '男', '2016-03-23 00:00:00', '2');
INSERT INTO `user` VALUES ('98c6383e-0e51-408d-916c-2e9ca017839e', '126', '何伟洪\r\n', '男', '2017-08-16 00:00:00', '2');
INSERT INTO `user` VALUES ('9c70620d-bbad-4d51-8bac-3681972f5b41', '110', '黄汝娴\r\n', '男', '2017-06-13 00:00:00', '14');
INSERT INTO `user` VALUES ('9ee81c32-9120-4348-99f4-4c1747bddc19', '238', '欧凌斌\r\n', '男', '2019-05-07 00:00:00', '4');
INSERT INTO `user` VALUES ('a0d22bd7-3a44-436a-8cb9-bd647489f10e', '309', '冯燕东\r\n', '男', '2020-03-19 00:00:00', '13');
INSERT INTO `user` VALUES ('a37fb60f-a232-4b8a-90d0-7b1c1f4839af', '55', '关锦超\r\n', '男', '2016-06-27 00:00:00', '4');
INSERT INTO `user` VALUES ('a3a9c14d-e7c3-4d9d-b336-88e3627577f7', '226', '岑加克\r\n', '男', '2019-03-15 00:00:00', '6');
INSERT INTO `user` VALUES ('a3da3804-7b58-4752-bb86-adf79ac5ae53', '43', '章星\r\n', '男', '2015-12-23 00:00:00', '1');
INSERT INTO `user` VALUES ('a457de53-22e9-4a34-bd0f-6b114f9536b2', '334', '何卓权\r\n', '男', '2020-06-01 00:00:00', '6');
INSERT INTO `user` VALUES ('a5cff726-7399-4afa-a741-d5f3bb2d44c6', '5', '刘家良', '男', '2015-12-23 00:00:00', '2');
INSERT INTO `user` VALUES ('ad242b8b-2a2d-45db-b9bc-df79ae3ff6e0', '284', '齐光前\r\n', '男', '2019-11-12 00:00:00', '13');
INSERT INTO `user` VALUES ('af41565c-d604-426d-8ede-a294966aaa2c', '326', '尹志武\r\n', '男', '2020-04-16 00:00:00', '13');
INSERT INTO `user` VALUES ('b0bb879f-e707-4866-834e-dd97503672f1', '209', '董国念\r\n', '男', '2018-12-24 00:00:00', '6');
INSERT INTO `user` VALUES ('b3ea0816-a5bc-4163-b825-ec40c0159e88', '91', '秦梅仙\r\n', '男', '2017-02-21 00:00:00', '18');
INSERT INTO `user` VALUES ('b404cae4-e4ac-4651-bee9-af748870665b', '270', '罗荣\r\n', '男', '2019-09-23 00:00:00', '3');
INSERT INTO `user` VALUES ('b714d4a6-3ba6-4f56-8594-81f6f188e3f3', '349', '黄宏咏\r\n', '男', '2020-06-27 00:00:00', '13');
INSERT INTO `user` VALUES ('b7730816-4d86-4244-977a-5714c843f2d4', '187', '陆顺宁\r\n', '男', '2018-08-03 00:00:00', '11');
INSERT INTO `user` VALUES ('b7883f6d-4321-4982-acb7-768ab96eb489', '331', '吴永光\r\n', '男', '2020-05-18 00:00:00', '18');
INSERT INTO `user` VALUES ('b845c411-874a-43ac-9c44-ed31a224781d', '168', '邓焕权\r\n', '男', '2018-05-30 00:00:00', '4');
INSERT INTO `user` VALUES ('b88e1133-522f-4999-b7e2-12a15b160828', '248', '黄铝\r\n', '男', '2019-06-25 00:00:00', '13');
INSERT INTO `user` VALUES ('bae54d98-d580-4b4e-a604-1323a0b28407', '153', '汪海豹\r\n', '男', '2018-03-13 00:00:00', '20');
INSERT INTO `user` VALUES ('c1028591-5a5b-4e56-bca2-b090a79d0e59', '336', '陈黔勇\r\n', '男', '2020-06-01 00:00:00', '17');
INSERT INTO `user` VALUES ('c36982e8-d191-44aa-a5e0-8db84d674e2e', '230', '陆晓琪\r\n', '男', '2019-03-20 00:00:00', '8');
INSERT INTO `user` VALUES ('c662d6c7-8768-4102-aacb-bb3639f5c861', '275', '张翠珊\r\n', '男', '2019-10-14 00:00:00', '3');
INSERT INTO `user` VALUES ('c73c6bdc-85f7-458a-858f-db1762ae209c', '167', '黄婉平\r\n', '男', '2018-05-14 00:00:00', '2');
INSERT INTO `user` VALUES ('c7b77707-4100-460e-b62e-20abd54d9244', '4', '邓凯华', '男', '2015-12-23 00:00:00', '1');
INSERT INTO `user` VALUES ('ca13d0bf-e8ee-4314-93fe-52fef434c15f', '1', '张驰', '男', '2015-12-23 00:00:00', '1');
INSERT INTO `user` VALUES ('cbbee0ed-35fe-4f57-b9c0-c6da8faa86a8', '311', '黄宇耀\r\n', '男', '2020-03-19 00:00:00', '13');
INSERT INTO `user` VALUES ('cf3efc54-f3bf-4116-ba9e-482dca07e46c', '247', '孔繁钦\r\n', '男', '2019-06-25 00:00:00', '11');
INSERT INTO `user` VALUES ('cff18e2b-7013-4a20-adcf-241278aaf302', '170', '李富兵', '男', '2018-06-05 00:00:00', '18');
INSERT INTO `user` VALUES ('d1c4fabf-25b7-4443-bcd6-1e3dd58e5c09', '286', '温松英\r\n', '男', '2019-12-02 00:00:00', '12');
INSERT INTO `user` VALUES ('d5756830-5eac-461e-844e-c935eccd9d4a', '183', '莫伟健\r\n', '男', '2018-07-31 00:00:00', '18');
INSERT INTO `user` VALUES ('d587b296-d253-4801-adaa-599caaacbb87', '256', '梁逢国\r\n', '男', '2019-07-24 00:00:00', '11');
INSERT INTO `user` VALUES ('d8836b81-b639-4a11-8c8e-e1dca2baac34', '12453', '李四是', '男', '2019-04-14 16:00:00', '21');
INSERT INTO `user` VALUES ('d90815a7-1dd7-4d5c-ba73-4a0d325fdd9a', '316', '黄耀坚\r\n', '男', '2020-03-26 00:00:00', '13');
INSERT INTO `user` VALUES ('db080979-8475-45d0-bb2f-bb46e0741478', '53', '廖叶童\r\n', '男', '2016-06-06 00:00:00', '11');
INSERT INTO `user` VALUES ('dc46bb46-b911-4a41-b6d1-3b4ed463efd8', '97', '万涛\r\n', '男', '2017-03-01 00:00:00', '6');
INSERT INTO `user` VALUES ('dddaa298-5cfc-4175-b58a-123933d55633', '233', '张瑶欢\r\n', '男', '2019-03-25 00:00:00', '7');
INSERT INTO `user` VALUES ('dshjdghhjsgdhkjsghga', '456', '王海', '男', '1999-07-24 17:00:23', '21');
INSERT INTO `user` VALUES ('e2547f2a-00ef-48cd-92e0-c193b8bea910', '7844', '小敏', '男', '2020-07-26 16:00:00', '21');
INSERT INTO `user` VALUES ('e3b47e61-93da-4331-9de2-249d04206188', '243', '刘雅欢\r\n', '男', '2019-06-11 00:00:00', '12');
INSERT INTO `user` VALUES ('e788bdaa-5ba6-4309-89b9-6adba76a9c2d', '293', '邓志伟\r\n', '男', '2020-03-02 00:00:00', '2');
INSERT INTO `user` VALUES ('e8cf59f0-965c-4dfc-80fe-9ce31c2dbdff', '242', '李祥然\r\n', '男', '2019-05-29 00:00:00', '6');
INSERT INTO `user` VALUES ('eca52ab0-c6d3-44b1-9bab-995f2efa8e3e', '12', '黄家伟', '男', '2016-02-15 00:00:00', '4');
INSERT INTO `user` VALUES ('eec7543a-627c-414e-b7c7-91416c6a4f81', '264', '莫志锋\r\n', '男', '2019-08-12 00:00:00', '2');
INSERT INTO `user` VALUES ('f2986b7e-ab7d-4c96-b08f-3cc80d9f73e3', '325', '杨清\r\n', '男', '2020-04-13 00:00:00', '2');
INSERT INTO `user` VALUES ('f7c2abaa-821f-4f15-b614-ba46e760220c', '266', '黄国凤\r\n', '男', '2019-08-26 00:00:00', '9');
INSERT INTO `user` VALUES ('f7c635b0-3316-4229-a79d-26480c708a9c', '85', '牛习普\r\n', '男', '2016-12-06 00:00:00', '13');
INSERT INTO `user` VALUES ('f8e703b3-512f-47d8-bcf4-e4d1f7f66eea', '156', '陈东毅\r\n', '男', '2018-03-20 00:00:00', '6');
INSERT INTO `user` VALUES ('fbf3062b-6c9c-4b46-92e8-16114c0998a8', '119', '张洁曼\r\n', '男', '2017-07-31 00:00:00', '18');
INSERT INTO `user` VALUES ('feb4d556-5cc9-4bbc-92f5-33185f77bec0', '314', '凌海\r\n', '男', '2020-03-24 00:00:00', '13');

-- ----------------------------
-- Table structure for year
-- ----------------------------
DROP TABLE IF EXISTS `year`;
CREATE TABLE `year` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `year` int(10) DEFAULT NULL,
  `menth` int(10) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of year
-- ----------------------------
INSERT INTO `year` VALUES ('1', '1', '1', '5', '1');
