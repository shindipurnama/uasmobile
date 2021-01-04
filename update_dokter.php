<?php
	include "koneksi.php";
	
	$NAMA_DOKTER 	= $_POST['NAMA_DOKTER'];
	$EMAIL_DOKTER 	= $_POST['EMAIL_DOKTER'];
	$NO_TELP_DOKTER = $_POST['NO_TELP_DOKTER'];
	$GENDER_DOKTER = $_POST['GENDER_DOKTER'];
	$ALAMAT_DOKTER = $_POST['ALAMAT_DOKTER'];
    
    if($GENDER_DOKTER=="Perempuan"){
        $GENDER_DOKTER=0;
    }else{
        $GENDER_DOKTER=1;
    }

	class emp{}
	
    $query = mysqli_query($con,"UPDATE dokter 
    SET NAMA_DOKTER='".$NAMA_DOKTER."', EMAIL_DOKTER='".$EMAIL_DOKTER."', NO_TELP_DOKTER='".$NO_TELP_DOKTER."', 
    GENDER_DOKTER='".$GENDER_DOKTER."', ALAMAT_DOKTER='".$ALAMAT_DOKTER."' WHERE ID_DOKTER='D002';");
    
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