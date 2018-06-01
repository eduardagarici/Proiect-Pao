<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>Insert title here</title>
</head>
<body background="images/backRec.png" style="background-size: cover; background-repeat:no-repeat;"> 
    
     <div style ="box-sizing: border-box;margin-top:14%;margin-left:30%; margin-right:25%;height:200px;padding-top:1%; 
              background-color: rgba(0, 0, 0, 0.5);text-align:center">
    <form action="MainPage.jsp" method="post" style="text-align:center; display:inline-block;margin-top:10%; margin-right:10%"">
    <input  type="submit" value="Reserve" name="ReserveRec" />
    </form>
     <form action="CheckRoomStatus.jsp" method="post" style="text-align:center; display:inline-block;">
    <input type="submit" value="Check Status Rooms" name="CheckStatus" />
    </form>
    
    </div>
</body>
</html>