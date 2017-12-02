<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import='webapp.Entities.League'%>
<%@ page import='webapp.Entities.Lineup'%>
<%@ page import='java.util.List'%>

<!-- Vista usuario con sesiÃÂ³n inciada -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Vista del Usuario</title>
</head>
<body>
	<div>
		<h2>Ligas Inscritas</h2>
		<% List<League> leagues = (List<League>) session.getAttribute("leaguesUser"); %>
		<table>
			<thead>
				<tr>
					<th>Leagues</th>
					<th>Status</th>
					<th>Ver Liga</th>
				</tr>
			</thead>
			<tbody>
				<% for (League league: leagues) { %>
				<tr>
					<td><%=league.getName()%></td>
					<td><%=league.getState()%></td>
					<td><a href="ViewLeague?id=<%= league.getId() %>">Ver liga <%= league.getId() %></td>
				</tr>
				<% } %>
			</tbody>
		</table>
	</div>
	<div>
		<h2>Ligas Disponibles</h2>
		<%List<League> leaguesAvail = (List<League>) session.getAttribute("leaguesAvail");%>
		<table>
			<thead>
				<tr>
					<th>Leagues</th>
					<th>Status</th>
					<th>Inscribirse</th>
				</tr>
			</thead>
			<tbody>
				<% for (League league : leaguesAvail) { %>
				<tr>
					<td> <%= league.getName() %> </td>
					<td> <%= league.getState() %> </td>
					<td> <a href="JoinLeague?id=<%= league.getId() %>">Unirse a liga <%= league.getId() %></a> </form> </td>
				</tr>
				<% } %>
			</tbody>
		</table>
	</div>
	<div>
		<h2>Crear nueva liga</h2>
		<form action="CreateLeague" method="POST">
			<div><label>Nombre: <input type="text" name="name"></label></div>
			<div><label>Número máximo de usuarios: <input type="text" name="maxUsers"></label></div>
			<div><label>Saldo inicial: <input type="text" name="initBalance"> </label></div>
			<input type="submit" value="Crear liga"></form>
	</div>
</body>
</html>
