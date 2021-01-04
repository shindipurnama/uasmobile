<?php

	include("koneksi.php");


        // array for json response
        $response = array();
        $response["data"] = array();

         
        // Mysql select query
        $data = mysqli_query($con,"SELECT NAMA_DOKTER, EMAIL_DOKTER, NO_TELP_DOKTER, GENDER_DOKTER, ALAMAT_DOKTER 
        FROM dokter WHERE ID_DOKTER='D002';");
         
        while($row = mysqli_fetch_array($data)){
            // temporary array to create single category
            $tmp = array();
            $tmp["NAMA_DOKTER"] = $row["NAMA_DOKTER"];
            $tmp["EMAIL_DOKTER"] = $row["EMAIL_DOKTER"];
            $tmp["NO_TELP_DOKTER"] = $row["NO_TELP_DOKTER"];
            
            if($row["GENDER_DOKTER"]==0){
                $tmp["GENDER_DOKTER"] = "Perempuan";
            }else{
                $tmp["GENDER_DOKTER"] = "Laki-Laki";
            }
            
            $tmp["ALAMAT_DOKTER"] = $row["ALAMAT_DOKTER"];

            // push category to final json array
            array_push($response["data"], $tmp);
        }
         
        // keeping response header to json
        header('Content-Type: application/json');
         
        // echoing json result
        echo json_encode($response);
    
		
?>