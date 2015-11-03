-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 30, 2015 at 10:17 PM
-- Server version: 5.5.46-0ubuntu0.14.04.2
-- PHP Version: 5.5.9-1ubuntu4.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `dvd_list`
--

-- --------------------------------------------------------

--
-- Table structure for table `dvds`
--

CREATE TABLE IF NOT EXISTS `dvds` (
  `dvd_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `release_date` varchar(50) NOT NULL,
  `mpaa_rating` varchar(50) NOT NULL,
  `director` varchar(50) NOT NULL,
  `studio` varchar(50) NOT NULL,
  `note` varchar(50) NOT NULL,
  PRIMARY KEY (`dvd_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=90 ;

--
-- Dumping data for table `dvds`
--

INSERT INTO `dvds` (`dvd_id`, `title`, `release_date`, `mpaa_rating`, `director`, `studio`, `note`) VALUES
(86, 'asdf', 'sdf', 'asdf', 'asdf', 'asdf', 'asdf'),
(87, 'adsf', 'dfasdf', 'asdf', '', '', ''),
(88, 'asd', '', '', '', '', ''),
(89, 'asd', '', '', '', '', '');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
