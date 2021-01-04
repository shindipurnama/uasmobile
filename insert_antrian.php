<?php
	include "koneksi.php";
    
    $NAMA_POLI = $_POST['NAMA_POLI'];
    $TANGGAL_PERIKSA = $_POST['TANGGAL_PERIKSA'];
	$WAKTU_PERIKSA = $_POST['WAKTU_PERIKSA'];
	$tahun=substr($TANGGAL_PERIKSA,-4);
	$bulan=substr($TANGGAL_PERIKSA,3,2);
	$tgl=substr($TANGGAL_PERIKSA,0,2);
	$TANGGAL_PERIKSA=$tahun.'-'.$bulan.'-'.$tgl;

    $id_poli="";
	$result = mysqli_query($con,"SELECT * FROM poli WHERE NAMA_POLI='$NAMA_POLI'");
	$row = mysqli_fetch_row($result);
	$id_poli = $row[0];

	$id="";
	$result = mysqli_query($con,"SELECT COUNT(*) FROM antrian");
	$row = mysqli_fetch_row($result);
	$id = $row[0];
	$id = $id+1;


	if($WAKTU_PERIKSA=="09:00"){
		$waktu=1;
	}else if($WAKTU_PERIKSA=="15:00"){
		$waktu=2;
	}

	$no_antrian="";
	$result = mysqli_query($con,"SELECT COUNT(*) FROM antrian WHERE TANGGAL_PERIKSA='$TANGGAL_PERIKSA'");
	$row = mysqli_fetch_row($result);
	$no_antrian = $row[0];
	$no_antrian = $no_antrian+1;
	
	class emp{}
	
	if (empty($NAMA_POLI) || empty($TANGGAL_PERIKSA) || empty($WAKTU_PERIKSA)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Kolom isian tidak boleh kosong"; 
		die(json_encode($response));
	} else {
		$query = mysqli_query($con,"INSERT INTO antrian (ID_ANTRIAN,ID_POLI,ID_PASIEN,TANGGAL_PERIKSA,WAKTU_PERIKSA,NO_ANTRIAN,STATUS) VALUES('".$id."','".$id_poli."',2,'".$TANGGAL_PERIKSA."','".$waktu."','".$no_antrian."',0)");
		
		if ($query) {
			$response = new emp();
			$response->status = "oke";
			die(json_encode($response));
		} else{ 
			$response = new emp();
			$response->status = "Error simpan Data";
			die(json_encode($response)); 
		}	
	}
	mysqli_close($con);
?>