<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vista del Administrador</title>
</head>
<body>
	<%@ page import ="webapp.Entities.*" %>
	<%@ page import ="java.util.List" %>
	<h2>Vista del Administrador</h2>
	<% List<Week> weeks = (List<Week>) session.getAttribute("listaJornadas"); %>
	<h3>Lista de jornadas (tamaño del calendario: <%= weeks.size() %>)</h3>
      <table>
      	<tr><td>Id</td><td>Fecha Inicio</td><td>Fecha Final</td></tr>
          <% for(int i=0;i<weeks.size();i++) { %>
          <% Week week = weeks.get(i);    %>
        <tr><td><%= week.getId() %></td>
           <td><%= week.getStartDate() %></td>
           <td><%= week.getEndDate() %></td></tr>
         <%  } %>
     </table>
    <% Status status = (Status) session.getAttribute("status"); %>
    <h3>Muestra jornada y fase actual.</h3>
    	<p>Jornada actual: <%= status.getRound() %>. Fase actual: <%= status.getPhase() %>.</p>
	<h3><form action="AdvanceStatus" method="POST"><input type = "submit" value="Avanzar de fase"></form></h3>
</body>
</html>