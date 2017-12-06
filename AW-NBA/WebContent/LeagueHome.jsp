<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Vista de la Liga</title>
</head>
<body>
	<%@ page import="webapp.Entities.*"%>
	<!-- Status mainly -->

	<%@ page import="java.util.List"%>
	<h2>Vista de la Liga</h2>
	<%
		League league = (League) session.getAttribute("league");
		Lineup lineupUser = (Lineup) session.getAttribute("lineupUser");
		List<Lineup> lineupsLeague = (List<Lineup>) session.getAttribute("lineupsLeague");
		User currentUser = (User) session.getAttribute("user");
		User creator = (User) session.getAttribute("creator");	
		Status status = (Status) session.getAttribute("status");
		List<User> listUsers = (List<User>) session.getAttribute("listUsers");
	%>
	
	<h3>Mi plantilla</h3>
	
	<h4>Información de plantilla</h4>
	<table>
		<!-- Info about user's lineup in the selected league -->
		<thead>
			<tr>
				<th>Nombre Usuario</th>
				<th>Saldo</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><a href=UserHomeServlet?id=<%=currentUser.getId()%>><%= currentUser.getName() %></a></td>
				<td><%= lineupUser.getBalance() %></td>
			</tr>
		</tbody>
	</table>
	
	<h4>Jugadores</h4>
	<table>
		<thead>
			<tr>
				<th>Nombre</th>
				<th>Equipo</th>
				<th>Posición</th>
				<th>Precio</th>
				<th>Puntos Globales</th>
			</tr>
		</thead>
		<tbody>
		<% for (int i=0; i<lineupUser.getTeamLineup().size(); i++) { %>
			<tr>
			<% Player bballer = lineupUser.getTeamLineup().get(i); %>
				<td><%= bballer.getName() %></td>
				<td><%= bballer.getTeam() %></td>
				<td><%= bballer.getPosition() %></td>
				<td><%= bballer.getValue() %></td>
				<td><%= bballer.getPointsGlobal() %></td>
			</tr>
		<% } %>
		</tbody>
	</table>
	<h3>Información de la liga seleccionada</h3>

	<table>
		<!-- General info about selected league -->
		<thead>
			<tr>
				<th>ID liga</th>
				<th>Liga</th>
				<th>Creador</th>
				<th>Estado</th>
				<th>máx Participantes</th>
				<th>Saldo inicial</th>
				<th>Jornada</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><%= league.getId() %></td>
				<td><%= league.getName() %></td>
				<td><%= league.getCreator() %></td>
				<td><%= league.getState() %></td>
				<td><%= league.getNMax() %></td>
				<td><%= league.getBalance() %></td>
				<td><%= status.getRound() %></td>
			</tr>
		</tbody>
	</table>
	
	<h3>Clasificación de la liga seleccionada.</h3>
	<table>
		<!-- Table containing the lineups in this league and its info -->
		<thead>
			<tr>
				<th>Id Plantilla</th>
				<th>Puntos</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (Lineup lineup : lineupsLeague) {
					userloop:
					for (User user : listUsers) {
						if (lineup.getUser() == user.getId()) {
			%>
			<tr>
				<td><%=user.getName() %></td>
				<td><%=lineup.getPoints()%></td>
			</tr>
			<%
							break userloop;
						}
					}
				}
			%>
		</tbody>
	</table>
	<% if(status.getPhase()==1){ %>

	<form action="MarketHomeServlet" method="POST">
		<input type="submit" value="Vista Mercado">
	</form>
	<% } else if (status.getPhase()==3){ %>
	<form action="SummaryHomeServlet" method="POST">
		<input type="submit" value="Vista Resumen">
	</form>
	<% } %>
	<% if(currentUser.getId() == creator.getId()){ %>

	<form action="AdvanceLeagueStatus" method="POST">
		<input type="submit" value="Cambiar estado de liga">
	</form>
	<% } %>
</body>
</html>