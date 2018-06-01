<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/admin.css">
<title>Admin mode</title>
</head>
<body>
	<h1>Welcome to admin mode!</h1>
	<div id="antet">Pick one from de below-listed options:</div>
	<form action="http://localhost:8080/ProiectPao/AdminWork" method="POST">
	<div id="primul">
		Insert floor:<input type="text" name="atfloor"
			value="Choose the preffered floor"><br> Select type of
		room:<select name="types">
			<option value="single">Single</option>
			<option value="twin">Twin</option>
			<option value="suite">Suite</option>
		</select><br> 
		Insert price:<input type="text" name="price"
			value="Insert price"><br>
			Select preferences for room:<br>
		Room service:<input type="radio" name="pref1"
			value="1"> Yes<input type="radio" name="pref1"
			value="0"> No<br>
			AC:<input type="radio" name="pref2"
			value="1"> Yes<input type="radio" name="pref2"
			value="0"> No<br>
			Parking:<input type="radio" name="pref3"
			value="1"> Yes<input type="radio" name="pref3"
			value="0"> No<br>
			TV:<input type="radio" name="pref4"
			value="1"> Yes<input type="radio" name="pref4"
			value="0"> No<br>
			Seaview:<input type="radio" name="pref5"
			value="1"> Yes<input type="radio" name="pref5"
			value="0"> No<br>
			Minibar:<input type="radio" name="pref6"
			value="1"> Yes<input type="radio" name="pref6"
			value="0"> No<br>
			Wifi:<input type="radio" name="pref7"
			value="1"> Yes<input type="radio" name="pref7"
			value="0"> No<br>
			Balcony:<input type="radio" name="pref8"
			value="1"> Yes<input type="radio" name="pref8"
			value="0"> No<br>
			<button type="submit">Create room</button>
	</div>
	</form>
	<form action="http://localhost:8080/ProiectPao/FloorWork" method="POST">
	<div id="doi">
	Insert floor:<input type="text" name="newfloor"
			value="Choose the preffered floor"><br> 
			
	Insert number of rooms for chosen floor:<input type="range" min="10" max="35" name="numberatfloor"><br>
			
			Select type of
		rooms for floor:<select name="typesfloor">
			<option value="single">Single</option>
			<option value="twin">Twin</option>
			<option value="suite">Suite</option>
		</select><br> 
		Insert price:<input type="text" name="pricefloor"
			value="Insert price"><br>
			Select preferences for room:<br>
		Room service:<input type="radio" name="pref11"
			value="1"> Yes<input type="radio" name="pref11"
			value="0"> No<br>
			AC:<input type="radio" name="pref22"
			value="1"> Yes<input type="radio" name="pref22"
			value="0"> No<br>
			Parking:<input type="radio" name="pref33"
			value="1"> Yes<input type="radio" name="pref33"
			value="0"> No<br>
			TV:<input type="radio" name="pref44"
			value="1"> Yes<input type="radio" name="pref44"
			value="0"> No<br>
			Seaview:<input type="radio" name="pref55"
			value="1"> Yes<input type="radio" name="pref55"
			value="0"> No<br>
			Minibar:<input type="radio" name="pref66"
			value="1"> Yes<input type="radio" name="pref66"
			value="0"> No<br>
			Wifi:<input type="radio" name="pref77"
			value="1"> Yes<input type="radio" name="pref77"
			value="0"> No<br>
			Balcony:<input type="radio" name="pref88"
			value="1"> Yes<input type="radio" name="pref88"
			value="0"> No<br>
			<button type="submit">Create floor</button>
	</div>
	</form>
</body>
</html>