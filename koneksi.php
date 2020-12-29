<?php
	
	$server		= "localhost"; // sesuaikan alamat server anda
	$user		= "root"; // sesuaikan user web server anda
	$password	= ""; // sesuaikan password web server anda
	$database	= "db_mobile"; // sesuaikan database web server anda
	
	$con = mysqli_connect($server, $user, $password, $database) or die ("Koneksi gagal!");
?>