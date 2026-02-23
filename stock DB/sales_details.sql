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
-- Table structure for table `sales_details`
--

CREATE TABLE IF NOT EXISTS `sales_details` (
`ID` bigint(20) NOT NULL,
  `Invoice_no` varchar(250) NOT NULL,
  `product_No` varchar(250) DEFAULT NULL,
  `product_title` varchar(255) DEFAULT NULL,
  `CostPrice` double DEFAULT NULL,
  `Sell_Price` double DEFAULT NULL,
  `Unit_Quantity` double(10,5) NOT NULL DEFAULT '0.00000',
  `Total` double DEFAULT NULL,
  `AddDate` date NOT NULL,
  `ChooseDate` date DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sales_details`
--

INSERT INTO `sales_details` (`ID`, `Invoice_no`, `product_No`, `product_title`, `CostPrice`, `Sell_Price`, `Unit_Quantity`, `Total`, `AddDate`, `ChooseDate`) VALUES
(1, '1-18-08-19', '4', 'Gold Chips', 1500, 1600, 3.00000, 4500, '2018-08-19', '2018-08-19'),
(2, '2-18-09-01', '3', 'Goldbar', 14000, 15000, 4.00000, 56000, '2018-09-01', '2018-09-01'),
(3, '3-18-09-01', '4', 'Gold Chips', 1500, 1600, 3.00000, 4500, '2018-09-01', '2018-09-01'),
(4, '4-18-09-06', '3', 'Goldbar', 14000, 15000, 4.00000, 56000, '2018-09-06', '2018-09-06'),
(5, '5-18-09-25', '4', 'Gold Chips', 1500, 1600, 3.00000, 4500, '2018-09-25', '2018-09-25'),
(6, '6-18-10-20', '4', 'Gold Chips', 1500, 1600, 3.00000, 4500, '2018-10-20', '2018-10-20'),
(7, '6-18-10-20', '3', 'Goldbar', 14000, 15000, 3.00000, 42000, '2018-10-20', '2018-10-20');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `sales_details`
--
ALTER TABLE `sales_details`
 ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `sales_details`
--
ALTER TABLE `sales_details`
MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
