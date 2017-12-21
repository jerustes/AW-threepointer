<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<link rel="icon" href="../../favicon.ico">

<title>Vista Mercado</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">
<!-- Custom styles for this template -->
<link href="css/markethome.css" rel="stylesheet">
</head>
<body>
	<%@ page import="java.util.List"%>
	<%@ page import="webapp.Entities.*"%>
	<!-- Status mainly -->
	
	<%
		Lineup lineupUser = (Lineup) session.getAttribute("lineupUser");
		User currentUser = (User) session.getAttribute("user");	
		List<Player> marketPlayers = (List<Player>) session.getAttribute("marketPlayers");
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
					<form action="MarketHomeServlet" method="POST">
						<div class = "navbar-brand">
							<button type="submit" class="btn btn-link btn-lg">Vista de Mercado</button>
						</div>
					</form>
				</div>
			</div>
			<!-- TODO: adaptar esto siguiente a que puedas navegar a otras opciones desde la vista de admin
        como al resto de vistas -->
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="UserHomeServlet?id=<%=currentUser.getId()%>">User</a></li>
					<%
						for (League l : leaguesUser) {
					%>
					<li><p>
						<form action="ViewLeague?id=<%=l.getId()%>" method="POST">
							<div class="button">
								<button type="submit" class="btn btn-link btn-sm">
									League:<%=l.getName()%></button>
							</div>
						</form>
					</p></li>
					<%
						}
					%>
					<li class="active"><p>
						<form action="MarketHomeServlet" method="POST">
							<div class="button">
								<button type="submit" class="btn btn-link btn-sm">Market</button>
							</div>
						</form>
					</p></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="UserHomeServlet?id=<%=currentUser.getId()%>"><span
							class="glyphicon glyphicon-user"></span> home </a></li>
					<li><a href="LogoutServlet"><span
							class="glyphicon glyphicon-log-in"></span> Salir</a></li>
				</ul>
			</div>
			<!--/.navbar-collapse -->
		</div>
	</nav>
	
	<!-- Main jumbotron for primary info -->
	<div class="jumbotron">
		<div class="container">
			<h1>Mercado</h1>
			<p>Vista del mercado donde puedes acceder a los jugadores que tienes
			en plantilla y venderlos; y también comprar otros. Solo disponible
			durante la fase 1.</p>
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
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><a href=UserHomeServlet?id=<%=currentUser.getId()%>><%= currentUser.getName() %></a></td>
						<td><%= lineupUser.getBalance() %></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	
	
	<div class="container">
		<div class="table-responsive">
			<h3>Jugadores de la Plantilla</h3>
			<% if (lineupUser.getTeamLineup() != null) { %>
			<table class="table table-stripped">
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
						<td><p>
							<form action="SellPlayer?id=<%= bballer.getId() %>" method="POST">
								<button type="submit" class="btn btn-danger btn-sm">Vender a <%= bballer.getName() %></button>		
							</form>
						</p></td>
					</tr>
				<% } %>
				</tbody>
			</table>
			<% } else { %>
			<p>No hay jugadores en plantilla</p>
			<% } %>
		</div>
	</div>
	
	<div class="container">
		<div class="table-responsive">
			<h2>Jugadores en el Mercado</h2>
			<table class="table table-stripped">
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
						<td><p>
							<form action="BuyPlayer?id=<%= marketPlayers.get(i).getId() %>" method="POST">
								<button type="submit" class="btn btn-success btn-sm">Comprar a <%= marketPlayers.get(i).getName() %></button>		
							</form>
						</p></td>
					</tr>
				<% } %>
				</tbody>
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