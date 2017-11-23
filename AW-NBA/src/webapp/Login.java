package webapp;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/login")
public class Login extends HttpServlet {
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        String email = (String) session.getAttribute("email");
        String pass = (String) session.getAttribute("contrasena");
                           
        if(Validate.checkUser(email,pass,session))   {
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
