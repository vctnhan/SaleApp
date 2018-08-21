<?php
	include  "connect.php";
	$json = $_POST['json'];
	// $json = '[{"id":"1","madonhang":"1","masanpham":"1","tensanpham":"iPhone 7 Plus-128GB","giasanpham":"10000000","soluongsanpham":"1"}]';
	$data= json_decode($json, true);
	foreach ($data as $value) {
		$madonhang= $value['madonhang'];
		$masanpham= $value['masanpham'];
		$tensanpham= $value['tensanpham'];
		$giasanpham= $value['giasanpham'];
		$soluongsanpham= $value['soluongsanpham'];
		$query = "INSERT INTO chitietdonhang(id,madonhang,masanpham,tensanpham,giasanpham,soluongsanpham) VALUES(null,'$madonhang','$masanpham','$tensanpham','$giasanpham','$soluongsanpham')";
		$dta = mysqli_query($conn,$query);
	}
	if ($dta ) {
		echo "1";
	}else{
		echo "0";
	}
?>