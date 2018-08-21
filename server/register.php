<?php
	include  "connect.php";
	/** Array for JSON response*/
	$response = array();
	if($_SERVER['REQUEST_METHOD'] == 'POST'){
		$username = $_POST['username'];
		$password = $_POST['password'];
		$email = $_POST['email'];
		$sqlCheck = "SELECT user_name FROM account WHERE user_name = '$username'";
		$sqlCheckMail = "SELECT email FROM account WHERE email = '$email'";
		$result = @mysqli_query($conn,$sqlCheck);
		$resultMail = @mysqli_query($conn,$sqlCheckMail);
		if (mysqli_num_rows($result) != 0 || mysqli_num_rows($resultMail) != 0 ) {	
				$response["success"] = 0;
				$response["message"] = "Tên đăng nhập hoặc email đã có người sử dụng!";
			}
			else {	
				$sqlInsert = "INSERT INTO account (user_name,password,email) VALUES ('$username','$password','$email')";
				$resultInsert = @mysqli_query($conn,$sqlInsert);
				if ($resultInsert) {
						$sqlGetInfo = "SELECT user_name, email FROM account WHERE user_name = '$username' AND password = '$password'";
						$result = mysqli_query($conn,$sqlGetInfo);
						$row = mysqli_fetch_array($result);
						
						$response["success"] = 1;
						$response["message"] = "Đăng ký thành công!";
						$response["user_name"] = $user_name;
						$response["email"] = $email;
				} else {
					$response["success"] = 0;
					$response["message"] = "Đăng ký thất bại!";
				}
			}	
			/**Return json*/
		echo json_encode($response);
	}
?>