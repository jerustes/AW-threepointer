<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import='webapp.Entities.League'%>
<%@ page import='webapp.Entities.Lineup'%>
<%@ page import='java.util.List'%>

<!-- Vista jugador con sesión inciada -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Vista del Jugador</title>
</head>
<body>
	<div>
		<h1>Ligas Inscritas</h1>
		<% List<League> leagues = (List<League>) session.getAttribute("ligas_usuario"); %>
		<table>
			<thead>
				<tr>
					<th>Leagues</th>
					<th>Status</th>
				</tr>
			</thead>
			<tbody>
				<% for (League league: leagues) { %>
				<tr>
					<td><%=league.getName()%></td>
					<td><%=league.getState()%></td>
				</tr>
				<% } %>
			</tbody>
		</table>
	</div>
	<div>
		<h2>Ligas Disponibles</h2>
		<%List<League> leaguesAvail = (List<League>) session.getAttribute("ligas_disponibles");%>
		<table>
			<thead>
				<tr>
					<th>Leagues</th>
					<th>Status</th>
				</tr>
			</thead>
			<tbody>
				<%
					for (League league : leaguesAvail) {
				%>
				<tr>
					<td><%=league.getName()%></td>
					<td><%=league.getState()%></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</div>
</body>
</html>
