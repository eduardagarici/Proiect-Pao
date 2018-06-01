<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/style.css">

</head>
<body background="images/hotel.jpg" style="background-size: cover;">
     
        <p style = "text-align:center; font-family:Calibri; font-size:60px"> Welcome </p>
        <div style ="box-sizing: border-box;margin-left:30%;margin-right:30%;height:200px;padding-top:1%; 
              background-color: rgba(0, 0, 0, 0.5);text-align:center">
        <p class = "desc">   Stefan here comes your part </p>
        <form action="http://localhost:8080/ProiectPao/Presentation" method="POST" style="text-align:center;">
         <input id=date1 type="date" name="checkin">
         <input id=date2 type="date" name="checkout">
         <select name="roomType">
                 <option value="single">Single</option>
                 <option value="twin">Twin</option>
                 <option value="suite">Suita</option>
         </select>
         <input type="submit">
         </form>
         <%String complete=(String)request.getAttribute("reservationComplete");
          if(complete!=null)
           %><p class="desc">Reservation has been completed successfully</p><%
         %>
         </div>
	</body>
</html>