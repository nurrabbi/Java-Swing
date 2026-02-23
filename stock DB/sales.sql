-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 05, 2018 at 08:16 AM
-- Server version: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `goldsmith`
--

-- --------------------------------------------------------

--
-- Table structure for table `sales`
--

CREATE TABLE IF NOT EXISTS `sales` (
`ID` bigint(20) NOT NULL,
  `pur_no` bigint(20) NOT NULL,
  `inv_no` varchar(250) NOT NULL,
  `Cus_name` varchar(255) DEFAULT NULL,
  `Cus_no` varchar(255) DEFAULT NULL,
  `Sub_total` double DEFAULT NULL,
  `DisPer` double DEFAULT NULL,
  `DisAmount` double DEFAULT NULL,
  `Tax_per` double DEFAULT NULL,
  `Tax` double DEFAULT NULL,
  `Total` double DEFAULT NULL,
  `Payment` double DEFAULT NULL,
  `DueAmt` double DEFAULT NULL,
  `Po_date` date DEFAULT NULL,
  `ChooseDate` date DEFAULT NULL,
  `description` text,
  `usertype` varchar(250) DEFAULT NULL,
  `username` varchar(250) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sales`
--

INSERT INTO `sales` (`ID`, `pur_no`, `inv_no`, `Cus_name`, `Cus_no`, `Sub_total`, `DisPer`, `DisAmount`, `Tax_per`, `Tax`, `Total`, `Payment`, `DueAmt`, `Po_date`, `ChooseDate`, `description`, `usertype`, `username`) VALUES
(1, 1, '1-18-08-19', ' ', '3', 4500, 0, 0, 2, 90, 4590, 0, 0, '2018-08-19', '2018-08-19', NULL, 'Admin', '3'),
(2, 2, '2-18-09-01', 'Amin', '1', 56000, 0, 0, 2, 1120, 57120, 0, 0, '2018-09-01', '2018-09-01', NULL, 'Admin', '3'),
(3, 3, '3-18-09-01', 'Amin', '1', 4500, 0, 0, 2, 90, 4590, 0, 0, '2018-09-01', '2018-09-01', NULL, 'Admin', '3'),
(4, 4, '4-18-09-06', '', NULL, 56000, 0, 0, 2, 1120, 57120, 0, 0, '2018-09-06', '2018-09-06', NULL, 'Admin', '3'),
(5, 5, '5-18-09-25', 'Amin', '1', 4500, 0, 0, 2, 90, 4590, 0, 0, '2018-09-25', '2018-09-25', NULL, 'Admin', 'a'),
(6, 6, '6-18-10-20', 'Amin', '1', 46500, 0, 0, 5, 2325, 48825, 0, 0, '2018-10-20', '2018-10-20', NULL, 'Admin', 'a');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `sales`
--
ALTER TABLE `sales`
 ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `sales`
--
ALTER TABLE `sales`
MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
