<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/style.css">
<script src="https://code.jquery.com/jquery-3.1.1.js"></script>
<script src="scripts/range.js"> </script>
<style>
</style>
<title>Insert title here</title>
<style>
.imgRoom{
	height:150px;
	width:230px;
}
figure{
	display:inline-block;
	margin:3%;
}
</style>
</head>
<body background="images/bg.png" style="background-size: cover;  background-attachment: fixed;";> 
   
 <main>
   <form action="http://localhost:8080/ProiectPao/Presentation" method="POST" style="text-align:center;">
  <div id="mainInput">
         <input type="date" name="checkin" >
         <input  type="date" name="checkout" >
         <select  name="roomType">
                 <option value="single">Single</option>
                 <option value="twin">Twin</option>
                 <option value="suite">Suite</option>
         </select>
          <input type="submit">
          <%String reserved=(String) request.getAttribute("alreadyReserved");
          if(reserved!=null)
       	{%><p class="desc">Room was reserved in the meantime<% return;} 
    
            String checkin=(String)request.getAttribute("checkin");	
          	
         	if(checkin==null || checkin.equals("failed"))
         	{%><p class="desc">Invalid checkin date <% return;} 
         	
         	String checkout=(String)request.getAttribute("checkout");
         	if(checkout==null || checkout.equals("failed"))
         	{%><p class="desc">Invalid checkout date <% return;}
         	String dates=(String)request.getAttribute("dates");
         	if(dates!=null && dates.equals("failed"))
         	{%><p class="desc">Checkin cannot precede checkout </p> <%return;}
         	
         	String search=(String)request.getAttribute("search");
         	String no_rooms=(String)request.getAttribute("no_rooms");
         	List<Room>rooms = (List<Room>)request.getAttribute("rooms");
          	if(search!=null && search.equals("failed"))
        	{%><p class="desc"> Search failed.Please try again!<% return;} 
            if((no_rooms!=null && no_rooms.equals("failed"))||rooms==null ||rooms.size()==0)
          	{%> <p class="desc"> No rooms available <% return;}     
          
            
          %>
        <div class="range-container">
         <p style="position:absolute; left:-35% ;top:-95%; font-size:20px;color:white; font-family:Calibri"> Max Price</p>
                    <label class="range">
				    <input class="range-input" type="range" min="10" max="500"  step="1" value="500">
                   <span class="range-box">
                   <span class="range-counter">1000</span>
                   </span>
                   </label>
          </div>
        </div> 
   
   <div id ="details">
          <p class="TitleAddOpt"> Addition Options </p>
           <input type="checkbox" name="minibar" value="1">  mini bar<br>
           <input type="checkbox" name="parkingSpot" value="2">  parking spot <br>
           <input type="checkbox" name="AC" value="3">  air conditioner <br>
           <input type="checkbox" name="balcony" value="4"> balcony   <br>
           <input type="checkbox" name="roomService" value="5"> room_service   <br>
           <input type="checkbox" name="TV" value="6"> TV   <br>
           <input type="checkbox" name="seaview" value="7"> seaview   
           <input type="checkbox" value="WIFI">WIFI<br><br>
           <input type="hidden" name="checkin"  style="display:none" value=<%out.write(checkin);%> />
           <input type="hidden" name="checkout" style="display:none" value=<%out.write(checkout);%> />
           <input type="submit">
   </div>  	
  	<%
  		String TV=(String)request.getAttribute("TV");
    	String balcony=(String)request.getAttribute("balcony");
    	String WIFI=(String)request.getAttribute("WIFI");
    	String roomService=(String)request.getAttribute("roomService");
    	String minibar=(String)request.getAttribute("minibar");
    	String seaview=(String)request.getAttribute("seaview");
    	String parkingSpot=(String) request.getAttribute("parkingSpot");
    	String AC=(String) request.getAttribute("AC");
    	String price=(String) request.getAttribute("price");
    	String roomType=(String)request.getAttribute("roomType");
    	
    	%>
    		<script>
    			window.onload=function(){
    			var select=document.getElementsByTagName("select");
        		select[0].value=<%out.write("'"+ roomType+"'");%>	
    			
        		var inputsDate=document.querySelectorAll('input[type="date"]');
    			inputsDate[0].value=<%out.write("'"+  checkin + "'");%>;
    			inputsDate[1].value=<%out.write("'"+  checkout + "'");%>;
    			
    			<%price=(String)request.getAttribute("price");
    			if(price!=null)
    			{%>var range=document.querySelectorAll('input[type="range"]');
    			   range[0].value=<%out.write("'"+ price + "'");%>;  
    			<%}
    			%>
    			var checkBox=document.querySelectorAll('input[type="checkbox"]');
    			
    			<% if(minibar!=null) 
    			   %>checkBox[0].checked=true;<%
    			%>
    			
    			<% if(parkingSpot!=null) 
        		   %>checkBox[1].checked=true;<%
        		%>
        		
        		<% if(AC!=null) 
        		   %>checkBox[2].checked=true;<%
        		%>
        		
        		<% if(balcony!=null) 
        		   %>checkBox[3].checked=true;<%
        		%>
        		
        		<% if(roomService!=null) 
        		   %>checkBox[4].checked=true;<%
        	    %>
        		
        		<% if(TV!=null) 
        		   %>checkBox[5].checked=true;<%
        		%>
        		
        		<% if(seaview!=null) 
        		   %>checkBox[6].checked=true;<%
        	    %>
        		 
        		<% if(WIFI!=null) 
            		%>checkBox[7].checked=true;<%
            	%>
            	}
    		</script>
     	<% 
  		TV=null;
     	balcony=null;
     	WIFI=null;
     	roomService=null;
     	minibar=null;
     	seaview=null;
     	parkingSpot=null;
     	AC=null;
  	%>
   </form>
  
   <div id="wrapperPresentation">
     
        <%@ page import="java.util.List" %>
        <%@ page import="core.Room" %>
        <%@ page import="java.util.LinkedList" %>
        <% 
        
          if(rooms==null || rooms.size()==0)
        	  return;
          for(int i = 0; i < rooms.size(); i++) {
               %>
              <form action="http://localhost:8080/ProiectPao/Reserve" method="POST" style="text-align:center;">    
		       	<input type="hidden" name="checkin" value=<%out.write("'"+checkin+"'");%> />
		       <input type="hidden" name="checkout" value=<%out.write("'"+checkout+"'");%> />
              <div class="wrapperRoom">
              <% 
        	      String pathImg ="images/"+ rooms.get(i).getId_room() + ".jpg";%>
              <figure>
              <img class="imgRoom" src="<%=pathImg%>">
              </figure>
              <div class="wrapperDetails">
                 <% String type = rooms.get(i).getType();
                 	String price2=String.valueOf(rooms.get(i).getPrice());
                 	String idRoom=String.valueOf(rooms.get(i).getId_room());%>
                 <div class="typeRoom"> <% out.write(type); %> </div>
                 <div class="price"> <% out.write(price2);%></div>
                 <button name="reserve" type="submit" value=<%out.write("'"+idRoom+"'");%> class="BReserve"> RESERVE </button>
                 <br>
                 <%  if(rooms.get(i).isAc())
                  {
                	  %> <span> air conditioner  | </span> <% 
                  }
                  
                  if(rooms.get(i).isBalcony())
                  {  
                	  %> <span> balcony  | </span> <% 
                  }
                  if(rooms.get(i).isMinibar())
                  {
                	  %> <span> minibar  | </span> <% 
                  }
                  if(rooms.get(i).isParking())
                  {
                	  %> <span> parking spot | </span> <% 
                  }
                  if(rooms.get(i).isRoom_service())
                  {
                	  %> <span> room service | </span> <% 
                  }
                  if(rooms.get(i).isSeaview())
                  {
                	  %> <span> sea view  | </span> <% 
                  }
                  if(rooms.get(i).isTv())
                  {
                	  %> <span> TV |  </span> <% 
                  }
                  if(rooms.get(i).isWifi())
                  {
                	  %> <span> wifi | </span> <% 
                  }
                 
        	 %>
        	 </div>
   			 </form>
        	</div>  
         <% } 
         %> 
         
        
        </div>
        
   </div>
   
   </main>
  
  
</body>
</html>