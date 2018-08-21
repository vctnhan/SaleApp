<?php
	include  "connect.php";
	$query="SELECT * FROM sanpham WHERE idloaisanpham = 4 ";
	$data= mysqli_query($conn,$query);
	$mangspmoinhat = array();
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($mangspmoinhat, new Sanpham(
			$row['id'],
			$row['tensanpham'],
			$row['giasanpham'],
			$row['hinhanhsanpham'],
			$row['motasanpham'],
			$row['idloaisanpham'] ));
	}
	echo json_encode($mangspmoinhat);
	class Sanpham{
		function Sanpham($id, $tensanpham, $giasanpham, $hinhanhsanpham,$motasanpham, $idloaisanpham){
			$this->id=$id;
			$this->tensanpham=$tensanpham;
			$this->giasanpham=$giasanpham;
			$this->hinhanhsanpham=$hinhanhsanpham;
			$this->motasanpham=$motasanpham;
			$this->idloaisanpham=$idloaisanpham;
		}
	}
?>