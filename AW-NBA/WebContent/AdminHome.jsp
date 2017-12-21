<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
  
<%@ page import="webapp.Entities.*"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html lang="es">
  <head>
  	<meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <link rel="icon" href="../../favicon.ico">
    
	<title>Vista del Administrador</title>
	
	<!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
	<!-- Custom styles for this template -->
    <link href="css/adminhome.css" rel="stylesheet">
  </head>
  <body>
	<% 
		List<Week> weeks = (List<Week>) session.getAttribute("weeksList");
		Status status = (Status) session.getAttribute("status");
		User user = (User) session.getAttribute("user");
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
					<a class="navbar-brand" href="AdminHomeServlet">Vista de Administrador</a>
				</div>
			</div>
			<!-- TODO: adaptar esto siguiente a que puedas navegar a otras opciones desde la vista de admin
        como al resto de vistas -->
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="AdminHomeServlet">Admin</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="AdminHomeServlet"><span class="glyphicon glyphicon-user"></span>
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
			<h1>Vista del Administrador</h1>
			<p>Vista de administrador desde donde puedes gestionar la Lista
				de jornadas, Jornada y fase actual y avanzar estado</p>
		</div>
	</div>



		<div class="container">
			<div class="table-responsive">
				<h2>Lista de jornadas</h2>
				<table class="table table-stripped">
					<thead>
						<tr>
							<th>Id</th>
							<th>Fecha Inicio</th>
							<th>Fecha Final</th>
						</tr>
					</thead>
					<tbody>
						<%
							for (Week week : weeks) {
						%>
						<tr>
							<td><%=week.getId()%></td>
							<td><%=week.getStartDate()%></td>
							<td><%=week.getEndDate()%></td>
						</tr>
						<%	} %>
					</tbody>
				</table>
			</div>
			<div class="col-md-12">
				<h2>Jornada y fase actual</h2>
				<p>
					Jornada actual:
					<%=status.getRound()%>. Fase actual:
					<%=status.getPhase()%>.
				</p>
			</div>
			<div class="col-md-12">
				<p><form action="AdvanceStatus" method="POST">
					<div class = "button">
						<button type="submit" class="btn btn-default btn-lg">Avanzar fase</button>
					</div>		
				</form></p>
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
