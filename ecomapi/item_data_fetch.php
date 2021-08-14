<?php
    $conn = mysqli_connect("localhost","root","");
    mysqli_select_db($conn,"ecom_db");

    $query = "SELECT * FROM `item_data`";

    $res = mysqli_query($conn,$query);

 
    $data_array = array();
    while($rows =mysqli_fetch_array($res))
    {
        $data_array[] = $rows;
    }

    echo json_encode($data_array);



?>