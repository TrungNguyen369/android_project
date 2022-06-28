<?php

	$connect = mysqli_connect("localhost","root","","thietbi");
	mysqli_query($connect,"SET NAME 'utf8'");
	$sql=mysqli_query($connect,"select * from chitietdonhang");
	if($sql === FALSE) 
	{
		die(mysqli_error());
	}
	while($row=mysqli_fetch_assoc($sql))
		$output[]=$row;
	echo json_encode($output);
	mysqli_close($connect);
?>