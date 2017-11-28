<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import='webapp.Entities.League'%>
<%@ page import='java.util.List' %>

<!-- Vista jugador con sesión inciada -->
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Vista del Jugador</title>
    </head>
    <body>
        <div>
            <h1>Ligas Inscritas</h1>
            <% List<League> leaguesUser = (List<League>) session.getAttribute("leagues_user"); %>    
                <table>
                    <thead>
                        <tr><th>Leagues</th><th>Status</th></tr>
                    </thead>
                    <tbody>
            <% for(League league: leaguesUser ){ %>
                    <tr>
                        <td><%= league.getName() %></td>
                        <td><%= league.getState() %></td>
                    </tr>    
            <% } %>    
                    </tbody>
                </table>
        </div>
        <div>
            <h2>Ligas Disponibles</h2>
            <% List<League> leaguesAvail = (List<League>) session.getAttribute("leagues_available"); %>    
                <table>
                    <thead>
                        <tr><th>Leagues</th></tr>
                    </thead>
                    <tbody>
            <% for(League league: leaguesAvail ){ %>
                    <tr>
                        <td><%= league.getName() %></td>
                    </tr>    
            <% } %>    
                    </tbody>
                </table>
        </div>
    </body>
</html>
