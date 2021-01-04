-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 31, 2020 at 07:04 PM
-- Server version: 10.1.34-MariaDB
-- PHP Version: 7.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_mobile`
--

-- --------------------------------------------------------

--
-- Table structure for table `antrian`
--

CREATE TABLE `antrian` (
  `ID_ANTRIAN` char(5) NOT NULL,
  `ID_POLI` char(5) NOT NULL,
  `ID_PASIEN` char(5) NOT NULL,
  `TANGGAL` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `NO_ANTRIAN` int(11) DEFAULT NULL,
  `TANGGAL_PERIKSA` date NOT NULL,
  `WAKTU_PERIKSA` char(1) NOT NULL,
  `STATUS` char(1) DEFAULT NULL,
  `JAM_MASUK` timestamp NULL DEFAULT NULL,
  `JAM_KELUAR` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `antrian`
--

INSERT INTO `antrian` (`ID_ANTRIAN`, `ID_POLI`, `ID_PASIEN`, `TANGGAL`, `NO_ANTRIAN`, `TANGGAL_PERIKSA`, `WAKTU_PERIKSA`, `STATUS`, `JAM_MASUK`, `JAM_KELUAR`) VALUES
('1', 'PL01', '1', '2020-12-31 08:30:49', 1, '2020-12-31', '1', '1', '2020-12-31 02:00:24', '2020-12-31 02:15:27'),
('2', 'PL01', '1', '2020-12-31 08:37:06', 2, '2020-12-31', '1', '0', NULL, NULL),
('3', 'PL01', '2', '2020-12-31 14:55:24', 3, '2020-12-31', '2', '0', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `dokter`
--

CREATE TABLE `dokter` (
  `ID_DOKTER` char(8) NOT NULL,
  `ID_POLI` char(5) NOT NULL,
  `NAMA_DOKTER` varchar(50) NOT NULL,
  `GENDER_DOKTER` tinyint(1) NOT NULL,
  `ALAMAT_DOKTER` varchar(100) NOT NULL,
  `EMAIL_DOKTER` varchar(50) NOT NULL,
  `NO_TELP_DOKTER` varchar(13) NOT NULL,
  `PASSWORD_DOKTER` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `dokter`
--

INSERT INTO `dokter` (`ID_DOKTER`, `ID_POLI`, `NAMA_DOKTER`, `GENDER_DOKTER`, `ALAMAT_DOKTER`, `EMAIL_DOKTER`, `NO_TELP_DOKTER`, `PASSWORD_DOKTER`) VALUES
('D001', 'PL01', 'Shindi Purnama Putri', 0, 'Jl. Menganti Babatan 2a no 12', 'shindi@gmail.com', '081343340102', '12345'),
('D002', 'PL02', 'Ella', 0, 'Gresik', 'ella@gmail.com', '082277380119', '12345');

-- --------------------------------------------------------

--
-- Table structure for table `pasien`
--

CREATE TABLE `pasien` (
  `ID_PASIEN` char(5) NOT NULL,
  `NO_KTP` char(16) DEFAULT NULL,
  `NO_KK` char(16) DEFAULT NULL,
  `NAMA` varchar(50) NOT NULL,
  `TANGGAL_LAHIR` date DEFAULT NULL,
  `GENDER_PASIEN` tinyint(1) DEFAULT NULL,
  `ALAMAT_PASIEN` varchar(100) DEFAULT NULL,
  `EMAIL_PASIEN` varchar(50) NOT NULL,
  `NO_TELP_PASIEN` varchar(13) DEFAULT NULL,
  `RIWAYAT_KESEHATAN` text,
  `PASSWORD_PASIEN` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pasien`
--

INSERT INTO `pasien` (`ID_PASIEN`, `NO_KTP`, `NO_KK`, `NAMA`, `TANGGAL_LAHIR`, `GENDER_PASIEN`, `ALAMAT_PASIEN`, `EMAIL_PASIEN`, `NO_TELP_PASIEN`, `RIWAYAT_KESEHATAN`, `PASSWORD_PASIEN`) VALUES
('1', NULL, NULL, 'Ella', '1999-12-30', 0, 'Gresik', 'ella@gmail.com', '082277380119', NULL, '12345'),
('2', NULL, NULL, 'Shindi', '1999-03-03', 0, 'Surabaya', 'shindi@gmail.com', '082273368892', NULL, '12345');

-- --------------------------------------------------------

--
-- Table structure for table `poli`
--

CREATE TABLE `poli` (
  `ID_POLI` char(5) NOT NULL,
  `NAMA_POLI` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `poli`
--

INSERT INTO `poli` (`ID_POLI`, `NAMA_POLI`) VALUES
('PL01', 'Poli Gigi'),
('PL02', 'Poli Mata'),
('PL03', 'Poli Umum'),
('PL04', 'Poli Kandungan'),
('PL05', 'Poli THT'),
('PL06', 'Poli Anak'),
('PL07', 'Poli Penyakit Dalam');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `antrian`
--
ALTER TABLE `antrian`
  ADD PRIMARY KEY (`ID_ANTRIAN`),
  ADD KEY `FK_MEMILIH` (`ID_POLI`),
  ADD KEY `FK_MENDAFTAR` (`ID_PASIEN`);

--
-- Indexes for table `dokter`
--
ALTER TABLE `dokter`
  ADD PRIMARY KEY (`ID_DOKTER`),
  ADD KEY `FK_MEMILIKI` (`ID_POLI`);

--
-- Indexes for table `pasien`
--
ALTER TABLE `pasien`
  ADD PRIMARY KEY (`ID_PASIEN`);

--
-- Indexes for table `poli`
--
ALTER TABLE `poli`
  ADD PRIMARY KEY (`ID_POLI`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `antrian`
--
ALTER TABLE `antrian`
  ADD CONSTRAINT `FK_MEMILIH` FOREIGN KEY (`ID_POLI`) REFERENCES `poli` (`ID_POLI`),
  ADD CONSTRAINT `FK_MENDAFTAR` FOREIGN KEY (`ID_PASIEN`) REFERENCES `pasien` (`ID_PASIEN`);

--
-- Constraints for table `dokter`
--
ALTER TABLE `dokter`
  ADD CONSTRAINT `FK_MEMILIKI` FOREIGN KEY (`ID_POLI`) REFERENCES `poli` (`ID_POLI`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
