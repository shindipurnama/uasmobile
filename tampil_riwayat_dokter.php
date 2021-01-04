<?php

	include("koneksi.php");


        // array for json response
        $response = array();
        $response["data"] = array();

        $date = date('Y-m-d');
         
        // Mysql select query
        $data = mysqli_query($con,"SELECT pasien.NAMA AS NAMA_PASIEN, antrian.JAM_MASUK, antrian.JAM_KELUAR
        FROM antrian
        INNER JOIN pasien WHERE antrian.ID_PASIEN=pasien.ID_PASIEN AND antrian.STATUS=1 AND antrian.TANGGAL_PERIKSA='$date'
        ORDER BY antrian.JAM_MASUK DESC");
         
        while($row = mysqli_fetch_array($data)){
            // temporary array to create single category
            $tmp = array();
            $tmp["NAMA_PASIEN"] = $row["NAMA_PASIEN"];

            $tmp["JAM_MASUK"] = substr($row["JAM_MASUK"],-8);

            $tmp["JAM_KELUAR"] = substr($row["JAM_KELUAR"],-8);
             
            // push category to final json array
            array_push($response["data"], $tmp);
        }
         
        // keeping response header to json
        header('Content-Type: application/json');
         
        // echoing json result
        echo json_encode($response);
    
		
?>