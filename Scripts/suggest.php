<?php
$servername = "localhost";
$username = "root";
$password = ""; //set your password
$database = "ACM_SVNIT";
$conn = new mysqli($servername,$username,$password,$database);
	if($conn->connect_error)
	{
		die("Connection failed:".$conn->connect_error);
	}
	if(isset($_POST['uid']) && isset($_POST['mail']) && isset($_POST['suggestion']))
	{
		$addmNo = $_POST['uid'];
		$mailID = $_POST['mail'];
		$suggestion = $_POST['suggestion'];
		$sql = "INSERT INTO SUGGEST (uid, mail, suggestion) values('".$addmNo."', '".$mailID."', '".$suggestion."');";
		echo $sql."<br>";
		if($conn->query($sql)==true)
			echo "record inserted successfully<br>";
		else
			echo "Error inserting record:".$conn->error."<br>";
	}
	else
		echo "Invalid Input<br>";
?>
