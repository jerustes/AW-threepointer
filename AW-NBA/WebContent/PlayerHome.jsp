<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import='... .League'%>
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
            <% List<League> LeaguesUser = (List<League>) session.getAttribute("leagues_user"); %>    
                <table>
                    <thead>
                        <tr><th>Leagues</th><th>Status</th></tr>
                    </thead>
                    <tbody>
            <% for(League league: LeaguesUser ){ %>
                    <tr>
                        <td><%= league.getName() %></td>
                        <td><%= league.getStatus() %></td>
                    </tr>    
            <% } %>    
                    </tbody>
                </table>
        </div>
    </body>
</html>
