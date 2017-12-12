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
					<a class="navbar-brand" href="#">Vista de Administrador</a>
				</div>
			</div>
			<!-- TODO: adaptar esto siguiente a que puedas navegar a otras opciones desde la vista de admin
        como al resto de vistas -->
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">admin</a></li>
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

	<!-- Main jumbotron for primary info -->
	<div class="jumbotron">
		<div class="container">
			<h1>/admin</h1>
			<p>Vista de administrador desde donde puedes gestionar la Lista
				de jornadas, Jornada y fase actual y avanzar estado</p>
		</div>
	</div>

	<%
		List<Week> weeks = (List<Week>) session.getAttribute("weeksList");
	%>

	<div class="container">
		<!-- row of columns -->
		<div class="row">
			<div class="col-md-6">
				<h2>Lista de jornadas</h2>
				<h3>
					(tamaño del calendario:<%=weeks.size()%>)
				</h3>
				<table>
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
				<p>
					<a class="btn btn-default" href="#" role="button">Detalles
						&raquo;</a>
				</p>
			</div>
			<div class="col-md-6">
				<h2>Jornada y fase actual</h2>
				<p>
					<%
					Status status = (Status) session.getAttribute("status");
					%>
					Jornada actual:
					<%=status.getRound()%>. Fase actual:
					<%=status.getPhase()%>.
				</p>
				<p>
					<a class="btn btn-default" href="#" role="button">Detalles
						&raquo;</a>
				</p>
			</div>
			
		</div>
		<div class="row">
			<div class="col-md-2">
				<p>
					<a class="btn btn-default btn-lg" href="AdvanceStatus" role="button" 
						method="POST">Avanzar fase</a>
					
					<!--  form action="AdvanceStatus" method="POST">
					<input type="submit" value="Avanzar de fase" >
					</form -->
		
				</p>
			</div>
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
