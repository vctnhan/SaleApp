<?php
	include  "connect.php";
	$query="SELECT * FROM account ";
	$data= mysqli_query($conn,$query);
	$mangtaikhoan = array();
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($mangtaikhoan, new Taikhoan(
			$row['user_id'],
			$row['tenkhachhang'],
			$row['user_name'],
			$row['password'],
			$row['email'],
			$row['iddonhang']));
	}
	echo json_encode($mangtaikhoan);
	class Taikhoan{
		function Taikhoan($user_id, $tenkhachhang, $user_name,$password, $email,$iddonhang){
			$this->user_id=$user_id;
			$this->tenkhachhang=$tenkhachhang;
			$this->user_name=$user_name;
			$this->password=$password;
			$this->email=$email;
			$this->iddonhang=$iddonhang;
		
		}
	}
	
?>