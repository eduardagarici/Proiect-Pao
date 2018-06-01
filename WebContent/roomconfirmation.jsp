<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/confirmationstyles.css">
<title>Room confirmation</title>
</head>
<body>
<div>
		Congratulantions on creating the room
		<%
		String floor = (String) request.getAttribute("newroom");
		out.write(floor);
	%>
		at floor
		<%
		String rooms = (String) request.getAttribute("floorz");
		out.write(rooms);
	%>
	!!!
	</div>
</body>
</html>