<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="overflow-y:scroll;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>Insert title here</title>
<style>
main#mainInputCkRoom{
    margin-top:-10px;
	box-sizing: border-box;
	margin-left:15%;
	margin-right:15%;
	height:100vh;
	background-color: rgba(0, 0, 0, 0.5);
	padding-top:1%;
	text-align:center;
}
</style>
</head>
<body background="images/backRec.png" style="background-size: cover; background-attachment:fixed;"> 
     
     <main id="mainInputCkRoom">
       <form action="http://localhost:8080/ProiectPao/CheckRoomStatus" method="post">  
         <input style="margin-right:2%;" type="date" name="date" >
         <input style="margin-right:2%;" type="number" name="noRoom">
          <input type="submit" name="Search">
        </form>
        </div>
        
       
        
        <%@ page import="java.util.List" %>
        <%@ page import="core.RoomStatus" %>
        <%@ page import="java.util.LinkedList" %>
        <% 
        if(request.getAttribute("invalid")!=null) 
        {%>	<span class="wrapperDetails" style="color:red; font-weight:bold"> Invalid room number </span>
        
        <%} if(request.getAttribute("rooms")!=null)
         { 
        	List<RoomStatus> rooms = (List<RoomStatus>)request.getAttribute("rooms"); %>
            <script>
            var inputsDate=document.querySelector('input[type="date"]');
            var inputsNumber=document.querySelector('input[type="number"]');
		    inputsDate.value=<%out.write("'"+  (String)request.getAttribute("date") + "'");%>
		   
            </script>
          <% for(int i = 0; i < rooms.size(); i++) {
              %>
             <hr>
             <div class="wrapperRoomRec" style="background:white;">
             <% 
             String pathImg ="images/"+ rooms.get(i).getId() + ".jpg";%>
             <figure>
             <img class="imgRoom" src="<%=pathImg%>" style="height:70px;">
             </figure> 
              <div class="wrapperDetails" style="margin-bottom:6%">
                <%  String status1 = rooms.get(i).getStatus1();
                    String status2 = rooms.get(i).getStatus2();
                    String id = rooms.get(i).getId(); %>
                    
                     <span> Room <% out.write(id); %>  </span>
                     <span>  | <% out.write(status1);%> </span>
                     <span>  | <%out.write(status2);%> </span>
               </div>
               </div>
             <%} 
             }%>  
       
        </div>
        
        </main>
</body>
</html>