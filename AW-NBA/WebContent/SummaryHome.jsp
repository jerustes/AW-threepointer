<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <link rel="icon" href="../../favicon.ico">
    
	<title>Vista Resumen</title>

	<!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
	<!-- Custom styles for this template -->
    <link href="css/summaryhome.css" rel="stylesheet">
</head>

<body>
	<!-- Status mainly -->
	<%@ page import="webapp.Entities.*"%>
	<%@ page import="java.util.List"%>

	<%
		List<Player> listPlayers = (List<Player>) session.getAttribute("ListPlayerswithPoints");
		Lineup lineupUser = (Lineup) session.getAttribute("lineupUser");
		User currentUser = (User) session.getAttribute("user");	
		Status status = (Status) session.getAttribute("status");
		int puntuacion = (int) session.getAttribute("puntuacion");
		List<League> leaguesUser = (List<League>) session.getAttribute("leaguesUser");
	%>
	
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<!-- When collapsed, menu icon & button with three lines -->
					<span class="sr-only">Toggle menu</span> 
					<span class="icon-bar"></span>
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span>
				</button>
				<div class="navbar-header">
					<form action="SummaryHomeServlet" method="POST">
						<div class = "navbar-brand">
							<button type="submit" class="btn btn-link btn-lg">Vista de Resumen</button>
						</div>
					</form></p>
				</div>
			</div>
			<!-- TODO: adaptar esto siguiente a que puedas navegar a otras opciones desde la vista de admin
        como al resto de vistas -->
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="UserHomeServlet?id=<%=currentUser.getId() %>">User</a></li>
					<% for (League l : leaguesUser) { %>
					<li><p><form action="ViewLeague?id=<%=l.getId() %>" method="POST">
						<div class = "button">
							<button type="submit" class="btn btn-link btn-sm">League: <%=l.getName() %></button>
						</div>
					</form></p>	</li>
					<% } %>
					<li class="active"><p><form action="SummaryHomeServlet" method="POST">
						<div class = "button">
							<button type="submit" class="btn btn-link btn-sm">Summary</button>
						</div>
					</form></p>	</li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="UserHomeServlet?id=<%=currentUser.getId() %>"><span class="glyphicon glyphicon-user"></span>
							home </a></li>
					<li><a href="LogoutServlet"><span class="glyphicon glyphicon-log-in"></span>
							Salir</a></li>
				</ul>
			</div>
			<!--/.navbar-collapse -->
		</div>
	</nav>
	
	<!-- Main jumbotron for primary info -->
	<div class="jumbotron">
		<div class="container">
			<h1>Vista de Resumen de la Jornada</h1>
			<p>Vista de resumen de la jornada donde puedes acceder a tus resultados
			de la última jornada, tanto de jugadores como de plantilla. Además,
			puedes ver otros jugadores que han puntuado. Solo disponible durante
			la fase 3.</p>
		</div>
	</div>
	
	
	<div class="container">
		<h2>Mi plantilla</h2>
	</div>
	
	<div class="container">
		<div class="table-responsive">
			<h3>Información de plantilla</h3>
			<table class="table table-stripped">
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
						<td><%= puntuacion %></td>
						<td><%= lineupUser.getPoints() %></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div class="container">
		<div class="table-responsive">
		<h3>Jugadores de la Plantilla</h3>
		<table class="table table-stripped">
			<thead>
				<tr>
					<th>Nombre</th>
					<th>Equipo</th>
					<th>Posición</th>
					<th>Precio</th>
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
					<td><%= bballer.getValue() %></td>
					<td><%= bballer.getPointsWeek() %></td>
				</tr>
			<% } %>
			</tbody>
		</table>
		</div>
	</div>
	<div class="container">
		<h2>Otros deportistas</h2>
	</div>
	<div class="container">
		<div class="table-responsive">
		<h3>Deportistas que han puntuado en esta jornada</h3>
		<table class="table table-stripped">
			<% if (listPlayers.size()>0) { %>
			<thead>
				<tr>
					<th>Nombre</th>
					<th>Equipo</th>
					<th>Posición</th>
					<th>Precio</th>
					<th>Puntos Jornada</th>
				</tr>
			</thead>
			
			<tbody>
				<% for (int i=0; i < listPlayers.size(); i++) { %>
				<tr>
					<td><%= listPlayers.get(i).getName() %></td>
					<td><%= listPlayers.get(i).getTeam() %></td>
					<td><%= listPlayers.get(i).getPosition() %></td>
					<td><%= listPlayers.get(i).getValue() %></td>
					<td><%= listPlayers.get(i).getPointsWeek() %></td>
				</tr>
				<% } %>
			</tbody>
			<% } %>
		</table>
		</div>
	
	
		<hr>

		<footer>
			<p>&copy; 2017 threepointer, org.</p>
		</footer>
	</div>	
	<!-- /container -->

	<!-- Bootstrap core JavaScript
    ================================================== -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="../../dist/js/bootstrap.min.js"></script>
	
</body>
</html>