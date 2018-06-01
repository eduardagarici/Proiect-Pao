<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/style1.css">
<title>Web service</title>
</head>
<body>
<div id="wrapper">
<form action="http://localhost:8080/ProiectPao/RoomClean" method="POST">
Search room number: <input type="text" name="room" value="ex: 100,202,331"><br>
Search for given date: <input type="date" name="calendar"><br>
<button type="submit">Check</button>
</form>
<form action="http://localhost:8080/ProiectPao/GetDirtyRooms" method="POST">
<button type="submit">See all rooms</button>
</form>
</div>
</body>
</html>