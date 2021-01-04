<?php
	include "koneksi.php";
	
	$NAMA_PASIEN 	= $_POST['NAMA_PASIEN'];
	$EMAIL_PASIEN 	= $_POST['EMAIL_PASIEN'];
	$NO_TELP_PASIEN = $_POST['NO_TELP_PASIEN'];
	$TANGGAL_LAHIR = $_POST['TANGGAL_LAHIR'];
	$GENDER_PASIEN = $_POST['GENDER_PASIEN'];
	$ALAMAT_PASIEN = $_POST['ALAMAT_PASIEN'];
    
    if($GENDER_PASIEN=="Perempuan"){
        $GENDER_PASIEN=0;
    }else{
        $GENDER_PASIEN=1;
    }

    $tahun=substr($TANGGAL_LAHIR,-4);
	$bulan=substr($TANGGAL_LAHIR,3,2);
	$tgl=substr($TANGGAL_LAHIR,0,2);
	$TANGGAL_LAHIR=$tahun.'-'.$bulan.'-'.$tgl;

	class emp{}
	
    $query = mysqli_query($con,"UPDATE pasien 
    SET NAMA='".$NAMA_PASIEN."', EMAIL_PASIEN='".$EMAIL_PASIEN."', NO_TELP_PASIEN='".$NO_TELP_PASIEN."', 
    TANGGAL_LAHIR='".$TANGGAL_LAHIR."', GENDER_PASIEN='".$GENDER_PASIEN."', ALAMAT_PASIEN='".$ALAMAT_PASIEN."' WHERE ID_PASIEN=1;");
    
    if ($query) {
        $response = new emp();
        $response->status = "oke";
        die(json_encode($response));
    } else{ 
        $response = new emp();
        $response->success = 0;
        $response->message = "Error update Data";
        die(json_encode($response)); 
    }	
	
	mysqli_close($con);
?>