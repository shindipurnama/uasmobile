<?php

	include("koneksi.php");


        // array for json response
        $response = array();
        $response["data"] = array();

         
        // Mysql select query
        $data = mysqli_query($con,"SELECT NAMA AS NAMA_PASIEN, EMAIL_PASIEN, NO_TELP_PASIEN, TANGGAL_LAHIR, GENDER_PASIEN, ALAMAT_PASIEN 
        FROM pasien WHERE ID_PASIEN=1;");
         
        while($row = mysqli_fetch_array($data)){
            // temporary array to create single category
            $tmp = array();
            $tmp["NAMA_PASIEN"] = $row["NAMA_PASIEN"];
            $tmp["EMAIL_PASIEN"] = $row["EMAIL_PASIEN"];
            $tmp["NO_TELP_PASIEN"] = $row["NO_TELP_PASIEN"];

            $TANGGAL_LAHIR = $row["TANGGAL_LAHIR"];
            $thn=substr($TANGGAL_LAHIR,0,4);
            $bulan=substr($TANGGAL_LAHIR,5,2);
            $tgl=substr($TANGGAL_LAHIR,-2);
            $TANGGAL_LAHIR=$tgl.'-'.$bulan.'-'.$thn;
            $tmp["TANGGAL_LAHIR"] = $TANGGAL_LAHIR;
            
            if($row["GENDER_PASIEN"]==0){
                $tmp["GENDER_PASIEN"] = "Perempuan";
            }else{
                $tmp["GENDER_PASIEN"] = "Laki-Laki";
            }
            
            $tmp["ALAMAT_PASIEN"] = $row["ALAMAT_PASIEN"];

            // push category to final json array
            array_push($response["data"], $tmp);
        }
         
        // keeping response header to json
        header('Content-Type: application/json');
         
        // echoing json result
        echo json_encode($response);
    
		
?>