<?php

	include("koneksi.php");


        // array for json response
        $response = array();
        $response["data"] = array();
         
        // Mysql select query
        $data = mysqli_query($con,"SELECT poli.NAMA_POLI, antrian.TANGGAL_PERIKSA, antrian.WAKTU_PERIKSA, antrian.STATUS
        FROM antrian
        INNER JOIN poli WHERE antrian.ID_POLI=poli.ID_POLI AND antrian.ID_PASIEN=1 AND antrian.STATUS!=0;");
         
        while($row = mysqli_fetch_array($data)){
            // temporary array to create single category
            $tmp = array();
            $tmp["NAMA_POLI"] = $row["NAMA_POLI"];

            $TANGGAL_PERIKSA = $row["TANGGAL_PERIKSA"];
            $thn=substr($TANGGAL_PERIKSA,0,4);
            $bulan=substr($TANGGAL_PERIKSA,5,2);
            $tgl=substr($TANGGAL_PERIKSA,-2);
            $TANGGAL_PERIKSA=$tgl.'-'.$bulan.'-'.$thn;
            $tmp["TANGGAL_PERIKSA"] = $TANGGAL_PERIKSA;

            if($row["WAKTU_PERIKSA"] == "1"){
                $tmp["WAKTU_PERIKSA"] = "09.00";
            }else if($row["WAKTU_PERIKSA"] == "2"){
                $tmp["WAKTU_PERIKSA"] = "15.00";
            }

            if($row["STATUS"] == "1"){
                $tmp["STATUS"] = "SELESAI";
            }else if($row["STATUS"] == "2"){
                $tmp["STATUS"] = "BATAL";
            }
             
            // push category to final json array
            array_push($response["data"], $tmp);
        }
         
        // keeping response header to json
        header('Content-Type: application/json');
         
        // echoing json result
        echo json_encode($response);
    
		
?>