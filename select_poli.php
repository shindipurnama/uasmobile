<?php

	include("koneksi.php");


        // array for json response
        $response = array();
        $response["result"] = array();
         
        // Mysql select query
        $result = mysqli_query($con,"SELECT * FROM poli");
         
        while($row = mysqli_fetch_array($result)){
            // temporary array to create single category
            $tmp = array();
            $tmp["ID_POLI"] = $row["ID_POLI"];
            $tmp["NAMA_POLI"] = $row["NAMA_POLI"];
             
            // push category to final json array
            array_push($response["result"], $tmp);
        }
         
        // keeping response header to json
        header('Content-Type: application/json');
         
        // echoing json result
        echo json_encode($response);
    
		
?>