package webapp;

import java.io.*;
import javax.persistence.EntityTransaction;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;
import java.util.List;

import webapp.*;
import org.hibernate.*;

@WebServlet("/login")
public class Login extends HttpServlet {
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        String email = (String) session.getAttribute("email");
        String pass = (String) session.getAttribute("contrasena");
                           
        if(Validate.checkUser(email,pass))   {
            out.println("Autenticación correcta.");
            RequestDispatcher rd = request.getRequestDispatcher("/** VISTA PRINCIPAL **/");
            rd.forward(request, response);
        } else {
           out.println("Usuario o contraseña incorrectas.");
           RequestDispatcher rd = request.getRequestDispatcher("index.html");
           rd.forward(request, response);
        }
    }
}
