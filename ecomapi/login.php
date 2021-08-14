<?php

$conn = mysqli_connect("localhost","root","");
mysqli_select_db($conn,"ecom_db");

$email = trim($_POST['email']);
$password = trim($_POST['password']);

$query = "SELECT * FROM `tbl_user` WHERE email='$email' and password='$password'";

$row = mysqli_query($conn,$query);

$count = mysqli_num_rows($row);

// varify user exist or not
if($count>0)
    $response['message'] = "Exist";
else
    $response['message'] = "NotExist";

echo json_encode($response);

?>