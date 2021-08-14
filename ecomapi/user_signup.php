<?php

    $conn = mysqli_connect("localhost","root","");
    mysqli_select_db($conn,"ecom_db");

    $name = trim($_POST['name']);
    $email = trim($_POST['email']);
    $password = trim($_POST['password']);
    $mobile = trim($_POST['mobile']);
    $address = trim($_POST['address']);

    // check user already exist or not

    $query1 = "SELECT * FROM `tbl_user` WHERE email='$email'";

    // run query
    $row = mysqli_query($conn,$query1);

    // count value
    $count = mysqli_num_rows($row);

    if($count>0)
    {
        $response['message'] = "exists";
    }
    else{
        $query2 = "INSERT INTO `tbl_user` (`id`, `name`, `email`, `password`, `mobile`, `address`) 
            VALUES (NULL, '$name', '$email', '$password', '$mobile', '$address')";

        $res = mysqli_query($conn,$query2);  // true or false

        if($res == true)
        {
            $response['message'] = "inserted";
        }
        else
        {
            $response['message'] = "failed";
        }

    }
    echo json_encode($response);

?>