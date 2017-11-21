import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import User;

public class Login extends HttpServlet {
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        String email = session.getParameter("email");
        String pass = session.getParameter("contrasena");
        
        if(checkUser(email, pass))
        {
            out.println("Autenticación correcta.");
            RequestDispatcher rd = request.getRequestDispatcher("/** VISTA PRINCIPAL **/");
            rd.forward(request, response);
        }
        else
        {
           out.println("Usuario o contraseña incorrectas.");
           RequestDispatcher rd = request.getRequestDispatcher("index.html");
           rd.forward(request, response);
        }
    }
}
