<?php
	include  "connect.php";
	$query="SELECT * FROM donhang ";
	$data= mysqli_query($conn,$query);
	$mangdonhang = array();
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($mangdonhang, new Donhang(
			$row['id'],
			$row['tenkhachhang'],
			$row['sodienthoai'],
			$row['email']));
	}
	echo json_encode($mangdonhang);
	class Donhang{
		function Donhang($id, $tenkhachhang, $sodienthoai, $email){
			$this->id=$id;
			$this->tenkhachhang=$tenkhachhang;
			$this->sodienthoai=$sodienthoai;
			$this->email=$email;
		
		}
	}
	
?>