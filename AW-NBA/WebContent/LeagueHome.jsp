<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vista de la Liga</title>
</head>
<body>
	<%@ page import="webapp.Entities.*"%>
	<!-- Status mainly -->

	<%@ page import="java.util.List"%>
	<h1>Vista de la Liga</h1>
	<%
		League league = (League) session.getAttribute()nos falta este atributo;
		Lineup lineupUser = (Lineup) session.getAttribute("lineupUser");
		List<Lineup> lineupsLeague = (List<Lineup>) session.getAttribute("lineupsLeague");
		//Estado de la liga: league.getState();
		
		User creator = league.getCeator();
		User currentUser = lineupUser.getUser();
	%>
	<h3>Información de la liga seleccionada</h3>

	<table>
		<!-- General info about selected league -->
		<thead>
			<tr>
				<th>ID liga</th>
				<th>Liga</th>
				<th>Estado</th>
				<!-- 
					<th>máx Participantes</th>
					<th>Saldo inicial</th>
				-->
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><%=league.getId()%></td>
				<td><%=league.getName()%></td>
				<td><%=league.getState()%></td>
			</tr>
		</tbody>
	</table>

	<table>
		<!-- Info about user's lineup in the selected league -->
		<thead>
			<tr>
				<th>Tu plantilla</th>
				<th>Saldo</th>
				<th>Clasificación</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><%=currentUser.getName()%></td>
				<td><%=lineupUser.getBalance() %></td>
				<td><%= posición en la liga %></td>
				<!-- ni puta idea chacho -->
			</tr>
		</tbody>
	</table>

	<table>
		<!-- Table containing the lineups in this league and its info -->
		<thead>
			<tr>
				<th>Clasificación</th>
				<th>Plantilla</th>
				<th>Saldo</th>
				<th>Puntos</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (Lineup lineup : lineupsLeague) {
			%>
			<tr>
				<td><%=   %></td>
				<td><%=lineup. /* getName() or getUser().getName() */%></td>
				<td><%=lineup.getBalance()%></td>
				<td><%=lineup.getPoints()%></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>

	<% if(currentUser == creator){ %>

	<form action="AdvanceStatus" method="POST">
		<input type="submit" value="Avanzar de fase">
	</form>
	<% } %>
</body>
</html>