<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Vista Mercado</title>
</head>
<body>
	<%@ page import="webapp.Entities.*"%>
	<!-- Status mainly -->

	<%@ page import="java.util.List"%>
	<h2>Vista del Mercado</h2>
	<%
		Lineup lineupUser = (Lineup) session.getAttribute("lineupUser");
		User currentUser = (User) session.getAttribute("user");	
		List<Player> marketPlayers = (List<Player>) session.getAttribute("marketPlayers");
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
	
	<h4>Jugadores de la Plantilla</h4>
	<% if (lineupUser.getTeamLineup() != null) { %>
	<table>
		<thead>
			<tr>
				<th>Nombre</th>
				<th>Equipo</th>
				<th>Posición</th>
				<th>Precio</th>
				<th>Puntos</th>
				<th>Vender Jugador</th>
			</tr>
		</thead>
		<tbody>
		<% for (int i=0; i < lineupUser.getTeamLineup().size(); i++) { %>
			<tr>
			<% Player bballer = lineupUser.getTeamLineup().get(i); %>
				<td><%= bballer.getName() %></td>
				<td><%= bballer.getTeam() %></td>
				<td><%= bballer.getPosition() %></td>
				<td><%= bballer.getValue() %></td>
				<td><%= bballer.getPointsGlobal() %></td>
				<td><a href=SellPlayer?id=<%= bballer.getId() %>>
				Vender a <%= bballer.getName() %></a></td>
			</tr>
		<% } %>
		</tbody>
	</table>
	<% } else { %>
	<p>No hay jugadores en plantilla</p>
	<% } %>
	
	<h3>Jugadores en el mercado</h3>
	<table>
		<% if (marketPlayers.size()>0) { %>
			<thead>
				<tr>
					<th>Nombre</th>
					<th>Equipo</th>
					<th>Posición</th>
					<th>Precio</th>
					<th>Puntos</th>
					<th>Comprar Jugador</th>
				</tr>
			</thead>
		<% } %>
		<tbody>
		<% for (int i=0; i < marketPlayers.size(); i++) { %>
			<tr>
				<td><%= marketPlayers.get(i).getName() %></td>
				<td><%= marketPlayers.get(i).getTeam() %></td>
				<td><%= marketPlayers.get(i).getPosition() %></td>
				<td><%= marketPlayers.get(i).getValue() %></td>
				<td><%= marketPlayers.get(i).getPointsGlobal() %></td>
				<td><a href=BuyPlayer?id=<%= marketPlayers.get(i).getId() %>>
				Comprar a <%= marketPlayers.get(i).getName() %></a></td>
			</tr>
		<% } %>
		</tbody>
	</table>
	
	
</body>
</html>