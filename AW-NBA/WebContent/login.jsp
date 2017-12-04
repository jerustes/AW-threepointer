<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>FormularioLogin</title>
  </head>
  <body>
    <h1>Formulario de entrada</h1> 
    <p><form action="LoginServlet" method="POST">
      	<div><label>Email:<input type="text" name="email"></label></div>
        <div><label>Password:<input type="password" name="password"></label></div>
        <input type = "submit" value="Login">
    </form></p>
  </body>
</html>