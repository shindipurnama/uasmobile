<?php
	include "koneksi.php";
    
    $NAMA = $_POST['NAMA'];
    $EMAIL_PASIEN = $_POST['EMAIL_PASIEN'];
    $PASSWORD_PASIEN = $_POST['PASSWORD_PASIEN'];
	
	class emp{}
	
	if (empty($NAMA) || empty($EMAIL_PASIEN) || empty($PASSWORD_PASIEN)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Kolom isian tidak boleh kosong"; 
		die(json_encode($response));
	} else {
		$query = mysqli_query($con,"INSERT INTO pasien (ID_PASIEN,NAMA,EMAIL_PASIEN,PASSWORD_PASIEN) VALUES(3,'".$NAMA."','".$EMAIL_PASIEN."','".$PASSWORD_PASIEN."')");
		
		if ($query) {
			$response = new emp();
			$response->status = "oke";
			die(json_encode($response));
		} else{ 
			$response = new emp();
			$response->success = 0;
			$response->message = "Error simpan Data";
			die(json_encode($response)); 
		}	
	}
	mysqli_close($con);
?>