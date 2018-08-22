<?php
	include  "connect.php";
	$tenkhachhang = $_POST['tenkhachhang'];
	$sodienthoai = $_POST['sodienthoai'];
	$email =$_POST['email'];
	$user_id =$_POST['user_id'];

	if (strlen($tenkhachhang) > 0 && strlen($email) >0 && strlen($sodienthoai) >0) {
		$query = "INSERT INTO donhang(id,tenkhachhang,sodienthoai,email,user_id) VALUES(null, '$tenkhachhang', '$sodienthoai','$email','$user_id')";
		if (mysqli_query($conn, $query)) {
			$iddonhang= $conn->insert_id;
			echo $iddonhang;
		}else{
			echo "Thất bại";
		}

	}else{
		echo "Kiểm tra lại dữ liệu";
	}
?>