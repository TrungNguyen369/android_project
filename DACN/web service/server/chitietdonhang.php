<?php
	include "connect.php";
	$json = $_POST['json'];
//	$json = '{	"madonhang":"13",	"masanpham":11,	"tensanpham":"Điện thoại OPPO Find X2";	"giasanpham":23990000,"soluongsanpham":2}';

	$data = json_decode($json,true);
	foreach ($data as $value) {
		$madonhang = $value['madonhang'];
		$masanpham = $value['masanpham'];
		$tensanpham = $value['tensanpham'];
		$giasanpham = $value['giasanpham'];
		$soluongsanpham = $value['soluongsanpham'];
		$query = "INSERT INTO chitietdonhang (id,madonhang,masanpham,tensanpham,giasanpham,soluongsanpham) VALUES (null,'$madonhang','$masanpham','$tensanpham','$giasanpham','$soluongsanpham')";
		$Dta = mysqli_query($conn,$query);
	}
	if ($Dta) {
		echo "1";
	}else{
		echo "0";
	}
?>