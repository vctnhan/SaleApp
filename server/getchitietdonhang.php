<?php
	include  "connect.php";
	$query="SELECT * FROM chitietdonhang ";
	$data= mysqli_query($conn,$query);
	$mangdonhang = array();
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($mangdonhang, new Donhang(
			$row['id'],
			$row['madonhang'],
			$row['masanpham'],
			$row['tensanpham'],
			$row['giasanpham'],
			$row['soluongsanpham']));
	}
	echo json_encode($mangdonhang);
	class Donhang{
		function Donhang($id, $madonhang, $masanpham, $tensanpham,$giasanpham, $soluongsanpham){
			$this->id=$id;
			$this->madonhang=$madonhang;
			$this->masanpham=$masanpham;
			$this->tensanpham=$tensanpham;
			$this->giasanpham=$giasanpham;
			$this->soluongsanpham=$soluongsanpham;
		
		}
	}
	
?>