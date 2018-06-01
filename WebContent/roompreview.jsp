<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.LinkedList,core.Rooms"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/cssindex.css">
<title>Room preview</title>
</head>
<body>
	<%
		LinkedList<Rooms> r = (LinkedList<Rooms>) request.getAttribute("camere");
		for (Rooms i : r) {
			if (i.getStatus().equals("clean") == true) {
	%>
	<div>
		<p>
			Room number:<%
			out.write(String.valueOf(i.getNrcam()));
		%>
		</p>
		<p>
			Room status:<%
			out.write(i.getStatus());
		%>
		</p>
		<p>
			Last cleaning date:<%
			out.write(i.getCldate());
		%>
		</p>
	</div>
	<%
		} else {
	%>
	<form action="http://localhost:8080/ProiectPao/DeleteRom" method="POST">
	<div>
		<p>
			Room number:<%
			out.write(String.valueOf(i.getNrcam()));
		%>
		<input name="numar" value="<%out.write(String.valueOf(i.getNrcam()));%>" type="hidden" />
		</p>
		<p>
			Room status:<%
			out.write(i.getStatus());
		%>
		<input name="status" value="<%out.write(i.getStatus());%>" type="hidden" />
		</p>
		<p>
			Last cleaning date:<%
			out.write(i.getCldate());
		%>
		</p>
			<button type="submit">Clean room</button>
	</div>
	</form>
	<%
		}
		}
	%>
</body>
</html>