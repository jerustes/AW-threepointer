<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="webapp.Entities.*"%>
<%@ page import="webapp.Entities.League.State"%>

<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<link rel="icon" href="../../favicon.ico">

<title>Vista Liga</title>

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
<link href="css/leaguehome.css" rel="stylesheet">

</head>
<body>	
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
					<a class="navbar-brand" href="#">Vista de Liga</a>
				</div>
			</div>
			<!-- TODO: adaptar esto siguiente a que puedas navegar a otras opciones desde la vista de admin
        como al resto de vistas -->
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Ligas</a></li>
					<li><a href="#">Vista 1</a></li>
					<li><a href="#">Vista 2</a></li>
					<li><a href="#">Vista 3</a></li>
					<li><a href="#">About</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#"><span class="glyphicon glyphicon-user"></span>
							/home </a></li>
					<li><a href="#"><span class="glyphicon glyphicon-log-in"></span>
							Salir</a></li>
					<li><a href="#" class="btn btn-danger" role="button"><span
							class="glyphicon glyphicon-off"></span></a></li>
				</ul>
			</div>
			<!--/.navbar-collapse -->
		</div>
	</nav>
	
	<%
		League league = (League) session.getAttribute("league");
		Lineup lineupUser = (Lineup) session.getAttribute("lineupUser");
		List<Lineup> lineupsLeague = (List<Lineup>) session.getAttribute("lineupsLeague");
		User currentUser = (User) session.getAttribute("user");
		User creator = (User) session.getAttribute("creator");	
		Status status = (Status) session.getAttribute("status");
		List<User> listUsers = (List<User>) session.getAttribute("listUsers");
		List<League> leaguesUser = (List<League>) session.getAttribute("leaguesUser");
	%>
	
	<div class="container-fluid">
		<div class="row">
	        <div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li class="active"><a href="#"><%=league.getName() %><span
							class="sr-only">(current)</span></a></li>
					<li><a href="#">League2</a></li>
					<li><a href="#">League3</a></li>
					<li><a href="#">League4</a></li>
					<!-- TODO: turn static examples into list with actual leagues of the user -->
				</ul>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          		<h1 class="page-header"><strong><%=league.getName() %> / resumen</strong></h1>

				<div class="row placeholders">
					<div class="col-xs-6 col-sm-3 placeholder">
						<img
							src=""
							width="200" height="200" class="img-responsive"
							alt="Generic placeholder thumbnail">
	<!-- src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" -->
						<h4>Logo o algo</h4>
						<span class="text-muted">Something else</span>
					</div>
					<div class="col-xs-6 col-sm-3 placeholder">
						<img
							src=""
							width="200" height="200" class="img-responsive"
							alt="Generic placeholder thumbnail">
	<!-- src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" -->
						<h4>Logo o algo</h4>
						<span class="text-muted">Something else</span>
					</div>
				</div>
				
				<h3 class="sub-header">Mi plantilla / información general</h3>
				<!-- Info about user's lineup in the selected league -->
				<div class="table-responsive">
					<table class="table table-stripped">
						<thead>
							<tr>
								<th>Nombre Usuario</th>
								<th>Saldo</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><a href=UserHomeServlet?id= <%=currentUser.getId()%>>
										<%=currentUser.getName()%></a></td>
								<td><%=lineupUser.getBalance()%></td>
							</tr>
						</tbody>
					</table>
				</div>

				<h2 class="sub-header">Jugadores</h2>
				<div class="table-responsive">
					<%
						if (lineupUser.getTeamLineup() != null) {
					%>
					<table class="table table-stripped">
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
							<%
								for (int i = 0; i < lineupUser.getTeamLineup().size(); i++) {
							%>
							<tr>
								<%
									Player bballer = lineupUser.getTeamLineup().get(i);
								%>
								<td><%=bballer.getName()%></td>
								<td><%=bballer.getTeam()%></td>
								<td><%=bballer.getPosition()%></td>
								<td><%=bballer.getValue()%></td>
								<td><%=bballer.getPointsGlobal()%></td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
					<%
						} else {
					%>
					<p>No hay jugadores en plantilla</p>
					<%
						}
					%>
				</div>

				<h2 class="sub-header">Información de la liga seleccionada</h2>
				<div class="table-responsive">
					<table class="table table-stripped">
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
								<td><%=league.getId()%></td>
								<td><%=league.getName()%></td>
								<td><%=league.getCreator()%></td>
								<td><%=league.getState()%></td>
								<td><%=league.getNMax()%></td>
								<td><%=league.getBalance()%></td>
								<td><%= status.getRound() %></td>
							</tr>
						</tbody>
					</table>
				</div>
				
				<h2 class="sub-header">Clasificación de la liga seleccionada.</h2>
				<div class="table-responsive">
					<table class="table table-stripped">
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
									userloop: for (User user : listUsers) {
										if (lineup.getUser() == user.getId()) {
							%>
							<tr>
								<td><%=user.getName()%></td>
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
				</div>
			</div>
        </div>
	</div>
	
	<% if(status.getPhase()==1 && league.getState() == State.Activa){ %>

	<form action="MarketHomeServlet" method="POST">
		<input type="submit" value="Vista Mercado">
	</form>
	<% } else if (status.getPhase() == 3 && league.getState() == State.Activa){ %>
	<form action="SummaryHomeServlet" method="POST">
		<input type="submit" value="Vista Resumen">
	</form>
	<% } %>
	<% if(currentUser.getId() == creator.getId()){ %>

	<form action="AdvanceLeagueStatus" method="POST">
		<input type="submit" value="Cambiar estado de liga">
	</form>
	<% } %>
	
	    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="../../dist/js/bootstrap.min.js"></script>

</body>
</html>