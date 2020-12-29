<?php
	include "koneksi.php";
    
    $NAMA_POLI = $_POST['NAMA_POLI'];
    $TANGGAL_PERIKSA = $_POST['TANGGAL_PERIKSA'];
    $WAKTU_PERIKSA = $_POST['WAKTU_PERIKSA'];

    $id_poli=0;

    $result = mysqli_query($con,"SELECT ID_POLI FROM poli WHERE NAMA_POLI=$NAMA_POLI");
	$row = mysqli_fetch_object($result) ;
    $id_poli = $row->ID_POLI;
	class emp{}
	
	if (empty($NAMA_POLI) || empty($TANGGAL_PERIKSA) || empty($WAKTU_PERIKSA)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Kolom isian tidak boleh kosong"; 
		die(json_encode($response));
	} else {
		$query = mysqli_query($con,"INSERT INTO antrian (ID_ANTRIAN,ID_POLI,ID_PASIEN,TANGGAL_PERIKSA,WAKTU_PERIKSA) 
        VALUES(1,$id_poli,1,'".$TANGGAL_PERIKSA."','".$WAKTU_PERIKSA."')");
		
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