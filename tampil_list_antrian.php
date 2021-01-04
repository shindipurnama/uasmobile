<?php

	include("koneksi.php");


        // array for json response
        $response = array();
        $response["data"] = array();

        $date = date('Y-m-d');
         
        // Mysql select query
        $data = mysqli_query($con,"SELECT pasien.NAMA AS NAMA_PASIEN, antrian.NO_ANTRIAN, antrian.WAKTU_PERIKSA, antrian.STATUS
        FROM antrian
        INNER JOIN pasien WHERE antrian.ID_PASIEN=pasien.ID_PASIEN AND antrian.TANGGAL_PERIKSA='$date' AND (antrian.STATUS=3 OR antrian.STATUS=0)
        ORDER BY antrian.NO_ANTRIAN, antrian.WAKTU_PERIKSA;");
         
        while($row = mysqli_fetch_array($data)){
            // temporary array to create single category
            $tmp = array();
            $tmp["NAMA_PASIEN"] = $row["NAMA_PASIEN"];

            $tmp["NO_ANTRIAN"] = $row["NO_ANTRIAN"];

            if($row["WAKTU_PERIKSA"] == "1"){
                $tmp["WAKTU_PERIKSA"] = "09.00";
            }else if($row["WAKTU_PERIKSA"] == "2"){
                $tmp["WAKTU_PERIKSA"] = "15.00";
            }

            if($row["STATUS"] == "0"){
                $tmp["STATUS"] = "MENUNGGU";
            }else if($row["STATUS"] == "3"){
                $tmp["STATUS"] = "PROSES";
            }
             
            // push category to final json array
            array_push($response["data"], $tmp);
        }
         
        // keeping response header to json
        header('Content-Type: application/json');
         
        // echoing json result
        echo json_encode($response);
    
		
?>