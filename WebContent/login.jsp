<%@page import="javax.sound.midi.Soundbank"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/login.css">
<script src="https://code.jquery.com/jquery-3.1.1.js"></script>
<script type="text/javascript" src="scripts/login.js"></script>
</head>
<body>
<div class="login-page">
  <div class="form">
    <form class="register-form" action="http://localhost:8080/ProiectPao/Register" method="POST">
      <input type="text" placeholder="username" name="username"/>
      <input type="password" placeholder="password" name="password"/>
      <input type="text" placeholder="email address" name="email"/>
      <input type="text" placeholder="first name" name="fist"/>
      <input type="text" placeholder="last name" name="last"/>
      <button>create</button>
      <p class="message">Already registered? <a href="#">Sign In</a></p>
    </form>
    <form class="login-form" action="http://localhost:8080/ProiectPao/Login" method="POST">
      <input type="text" placeholder="username" name="username"/>
      <input type="password" placeholder="password" name="password"/>
      <%
      	String failed=(String) request.getAttribute("failed");	
      	System.out.println(failed);	  
      	if (failed!=null) 
        	if(failed.equals("failed")) {%> 
      			<p class="message incorrect">Incorrect Username or Password </p>      				
       	    <%}%>
      <button type="submit">login</button>
      <p class="message"><a href="#">Continue as guest</a></p>
      <p class="message">Not registered? <a href="#">Create an account</a></p>
    </form>
  </div>
</div>
</body>
</html>