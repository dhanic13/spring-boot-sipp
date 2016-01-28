/*
Navicat PGSQL Data Transfer

Source Server         : ConDBPostgre
Source Server Version : 90405
Source Host           : localhost:5432
Source Database       : sitasagroup
Source Schema         : sitasainvestama

Target Server Type    : PGSQL
Target Server Version : 90405
File Encoding         : 65001

Date: 2016-01-05 14:53:24
*/


-- ----------------------------
-- Table structure for s_roles
-- ----------------------------
DROP TABLE IF EXISTS "sitasainvestama"."s_roles";
CREATE TABLE "sitasainvestama"."s_roles" (
"id" char(36) COLLATE "default" NOT NULL,
"nama" varchar(255) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of s_roles
-- ----------------------------
INSERT INTO "sitasainvestama"."s_roles" VALUES ('adm                                 ', 'ROLE_ADMIN');
INSERT INTO "sitasainvestama"."s_roles" VALUES ('stf                                 ', 'ROLE_STAFF');

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------
