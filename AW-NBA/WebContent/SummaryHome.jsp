<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Vista Resumen</title>
</head>
<body>
	<%@ page import="webapp.Entities.*"%>
	<!-- Status mainly -->

	<%@ page import="java.util.List"%>
	<h2>Vista de Resumen de la Jornada</h2>
	<%
		List<Player> listPlayers = (List<Player>) session.getAttribute("ListPlayerswithPoints");
		Lineup lineupUser = (Lineup) session.getAttribute("lineupUser");
		User currentUser = (User) session.getAttribute("user");	
		Status status = (Status) session.getAttribute("status");
	%>
	
	<h3>Mi plantilla</h3>
	
	<h4>Información de plantilla</h4>
	<table>
		<!-- Info about user's lineup in the selected league -->
		<thead>
			<tr>
				<th>Nombre Usuario</th>
				<th>Saldo</th>
				<th>Puntuación semanal</th>
				<th>Puntuación</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><a href=UserHomeServlet?id=<%=currentUser.getId()%>><%= currentUser.getName() %></a></td>
				<td><%= lineupUser.getBalance() %></td>
				<% 	
					int puntuacion = 0;
					for (int i=0; i<lineupUser.getTeamLineup().size(); i++) { 
						Player bballer = lineupUser.getTeamLineup().get(i);
						puntuacion = puntuacion + bballer.getPointsWeek();
					}
				%>
				<td><%= puntuacion %></td>
				<td><%= lineupUser.getPoints() %></td>
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
				<th>Puntos Jornada</th>
			</tr>
		</thead>
		<tbody>
		<% for (int i=0; i < lineupUser.getTeamLineup().size(); i++) { %>
			<tr>
			<% Player bballer = lineupUser.getTeamLineup().get(i); %>
				<td><%= bballer.getName() %></td>
				<td><%= bballer.getTeam() %></td>
				<td><%= bballer.getPosition() %></td>
				<td><%= bballer.getPointsWeek() %></td>
			</tr>
		<% } %>
		</tbody>
	</table>
	
	<h3>Otros deportistas</h3>
	
	<h4>Deportistas que han puntuado en esta jornada</h4>
	<table>
		<% if (listPlayers.size()>0) { %>
			<thead>
				<tr>
					<th>Nombre</th>
					<th>Equipo</th>
					<th>Posición</th>
					<th>Puntos Jornada</th>
				</tr>
			</thead>
		<% } %>
		<tbody>
		<% for (int i=0; i < listPlayers.size(); i++) { %>
			<tr>
				<td><%= listPlayers.get(i).getName() %></td>
				<td><%= listPlayers.get(i).getTeam() %></td>
				<td><%= listPlayers.get(i).getPosition() %></td>
				<td><%= listPlayers.get(i).getPointsWeek() %></td>
			</tr>
		<% } %>
		</tbody>
	</table>
	
</body>
</html>