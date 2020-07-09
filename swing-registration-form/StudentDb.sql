-- Adminer 4.5.0 MySQL dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

SET NAMES utf8mb4;

DROP TABLE IF EXISTS `StudentMaster`;
CREATE TABLE `StudentMaster` (
  `StudentId` int(11) NOT NULL AUTO_INCREMENT,
  `StudentName` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
  `UserName` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Password` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Gender` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
  `MobileNo` varchar(180) COLLATE utf8mb4_unicode_ci NOT NULL,
  `City` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`StudentId`),
  UNIQUE KEY `MobileNo` (`MobileNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- 2020-07-09 17:16:08
