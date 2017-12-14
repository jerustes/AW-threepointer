<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import='webapp.Entities.League'%>
<%@ page import='webapp.Entities.Lineup'%>
<%@ page import='webapp.Entities.User'%>
<%@ page import='webapp.Entities.Status'%>
<%@ page import='java.util.List'%>

<!-- Vista usuario con sesi�n inciada -->
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<link rel="icon" href="../../favicon.ico">

<title>Vista del Usuario</title>

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
<link href="css/userhome.css" rel="stylesheet">
</head>
<body>
	<%
		List<League> leagues = (List<League>) session.getAttribute("leaguesUser");
		Status status = (Status) session.getAttribute("status");
		League league = (League) session.getAttribute("league");
		User user = (User) session.getAttribute("user");
		List<User> listUsers = (List<User>) session.getAttribute("listUsers");
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
					<a class="navbar-brand" href="UserHomeServlet?id=<%=user.getId() %>">Vista de Usuario</a>
				</div>
			</div>
			<!-- TODO: adaptar esto siguiente a que puedas navegar a otras opciones desde la vista de admin
        como al resto de vistas -->
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="UserHomeServlet?id=<%=user.getId() %>">User</a></li>
					<% for (League l : leaguesUser) { %>
					<li><a href="ViewLeague?id=<%=l.getId() %>">League: <%=l.getName() %></a></li>
					<% } %>
					<% if (status.getPhase() == 1) { %>
					<li><a href="MarketHomeServlet">Market</a></li>
					<% } else if (status.getPhase() == 3) { %>
					<li><a href="SummaryHomeServlet">Summary</a></li>
					<% } %>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="UserHomeServlet?id=<%=user.getId() %>"><span class="glyphicon glyphicon-user"></span>
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
			<h1>Vista de Usuario</h1>
			<p>Vista de usuario desde donde puedes navegar tus ligas y ver
			otras ligas disponibles en las que perder m�s de tu tiempo.</p>
		</div>
	</div>

	<div class="container">
		<!-- row of columns -->
		<div class="row">
			<div class="col-md-6">
				<h2>Ligas Inscritas</h2>

				<table class="table table-stripped">
					<thead>
						<tr>
							<th>Leagues</th>
							<th>Status</th>
							<th>Ver Liga</th>
						</tr>
					</thead>
					<tbody>
						<%
							for (League leg : leagues) {
						%>
						<tr>
							<td><%=leg.getName()%></td>
							<td><%=leg.getState()%></td>
							<td><a href="ViewLeague?id=<%=leg.getId()%>">Ver
									liga <%=leg.getId()%></td>
						</tr>
						<% } %>
					</tbody>
				</table>
			</div>
			<div class="col-md-6">
				<h2>Ligas Disponibles</h2>
				
				<table class="table table-stripped">
					<thead>
						<tr>
							<th>Leagues</th>
							<th>Status</th>
							<th>Inscribirse</th>
						</tr>
					</thead>
					<tbody>
						<%
							if (session.getAttribute("leagueSubs") != null) {
								List<League> leaguesAvail = (List<League>) session.getAttribute("leaguesSubs");
								for (League lg : leaguesAvail) {
						%>
						<tr>
							<td><%=lg.getName()%></td>
							<td><%=lg.getState()%></td>
							<td><a href="JoinLeague?id=<%=lg.getId()%>">Unirse
									a liga <%=lg.getId()%></a>
								</form></td>
						</tr>
						<% 		}
							}	%>
					</tbody>
				</table>
			</div>
			
		</div>
		<div class="row">
			<div class="col-md-6">
				<h2>Crear nueva liga</h2>
				<p>
					<div class="form-group">
						<label class="control-label col-sm-3" for="leaguename">Nombre:</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="leaguename">
							<span class="help-block">Nombre de tu liga</span>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3" for="max-usr">M�ximo n�mero de usuarios</label> 
						<div class="col-sm-6">
							<input type="text" class="form-control" id="max-usr"> <span
								class="help-block">Entre 2 y 20 usuarios</span>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3" for="init-bal">Saldo inicial:</label> 
						<div class="col-sm-6">
							<input type="text" class="form-control" id="initbal"> <span
								class="help-block">Saldo inicial m�ximo: 200.000</span>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-3 col-sm-10">
							<a class="btn btn-success btn-lg" href="CreateLeague"
								role="button" method="POST">Crear liga</a>
						</div>
					</div>
				</form>
			</div>
		</div>

		<hr>

		<footer>
			<p><a href="#" data-toggle="tooltip" data-placement="top" title="Dowie & JErus">
			&copy; 2017 threepointer, org</a></p>
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

