-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 30, 2020 at 03:38 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.3.22

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
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
  `TANGGAL` timestamp NOT NULL DEFAULT current_timestamp(),
  `NO_ANTRIAN` int(11) DEFAULT NULL,
  `TANGGAL_PERIKSA` date NOT NULL,
  `WAKTU_PERIKSA` char(1) NOT NULL,
  `STATUS` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `antrian`
--

INSERT INTO `antrian` (`ID_ANTRIAN`, `ID_POLI`, `ID_PASIEN`, `TANGGAL`, `NO_ANTRIAN`, `TANGGAL_PERIKSA`, `WAKTU_PERIKSA`, `STATUS`) VALUES
('1', 'PL01', '3', '2020-12-29 04:55:23', 1, '2020-12-30', '1', '0');

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
  `PASSWOR_DOKTER` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `dokter`
--

INSERT INTO `dokter` (`ID_DOKTER`, `ID_POLI`, `NAMA_DOKTER`, `GENDER_DOKTER`, `ALAMAT_DOKTER`, `EMAIL_DOKTER`, `NO_TELP_DOKTER`, `PASSWOR_DOKTER`) VALUES
('D001', 'PL01', 'Shindi Purnama Putri', 0, 'Jl. Menganti Babatan 2a no 12', 'shindi@gmail.com', '081343340102', '12345'),
('D002', 'PL02', 'Nur Laila Rahmadani', 0, 'Jl. Benjeng no 2 gresik', 'ella@gmail.com', '082134421290', '12345');

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
  `RIWAYAT_KESEHATAN` text DEFAULT NULL,
  `PASSWORD_PASIEN` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pasien`
--

INSERT INTO `pasien` (`ID_PASIEN`, `NO_KTP`, `NO_KK`, `NAMA`, `TANGGAL_LAHIR`, `GENDER_PASIEN`, `ALAMAT_PASIEN`, `EMAIL_PASIEN`, `NO_TELP_PASIEN`, `RIWAYAT_KESEHATAN`, `PASSWORD_PASIEN`) VALUES
('0', NULL, NULL, 'ella', NULL, NULL, NULL, 'ella@gmail.com', NULL, NULL, 'ella'),
('1', NULL, NULL, 'shindi', NULL, NULL, NULL, 'shindi@gmail.com', NULL, NULL, 'shindi'),
('2', NULL, NULL, 'shindi ella', NULL, NULL, NULL, 'shindiella@gmail.com', NULL, NULL, '123'),
('3', NULL, NULL, 'shindi', NULL, NULL, NULL, 'shindi@gmail.com', NULL, NULL, '123'),
('P0001', '5987621334812781', '5987621334811234', 'Lailaut Elpantul', '2000-11-14', 0, 'Jl. Cerme no 15', 'elpantul@gmail.com', '08124516281', 'Patah Hati', '12345'),
('P0002', '5987621334810928', '5987621334818728', 'Salsagil ', '2000-04-14', 0, 'Jl. Pasuaran Maju 98', 'salsagil@gmail.com', '089182311212', 'Ganguan Jiwa', '12345'),
('P0003', '5987621334810765', '5987621334810986', 'Yovintul', '2000-05-17', 0, 'Jl. Ngawi Indah 01', 'yovimantul@gmail.com', '086187212912', 'Sehat walafiat', '12345'),
('P0004', '5987621334812345', '5987621334816521', 'Shintasof', '2000-02-18', 0, 'Jl. Mojokerto Bypass 06', 'shintasof@gmail.com', '081292837129', 'Patah Hati', '12345'),
('P0005', '5987624356810928', '5987621334813421', 'Annisakar', '2000-02-03', 0, 'Jl. Mojokerto Pucuk 89', 'nisakar@gmail.com', '087261382123', 'Digantungin', '12345');

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

-- --------------------------------------------------------

--
-- Table structure for table `riwayatpemeriksaan`
--

CREATE TABLE `riwayatpemeriksaan` (
  `ID_RIWAYAT` int(11) NOT NULL,
  `ID_ANTRIAN` char(5) DEFAULT NULL,
  `JAM_MASUK` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `JAM_KELUAR` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
-- Indexes for table `riwayatpemeriksaan`
--
ALTER TABLE `riwayatpemeriksaan`
  ADD PRIMARY KEY (`ID_RIWAYAT`),
  ADD KEY `FK_MEMILIKI2` (`ID_ANTRIAN`);

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

--
-- Constraints for table `riwayatpemeriksaan`
--
ALTER TABLE `riwayatpemeriksaan`
  ADD CONSTRAINT `FK_MEMILIKI2` FOREIGN KEY (`ID_ANTRIAN`) REFERENCES `antrian` (`ID_ANTRIAN`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
