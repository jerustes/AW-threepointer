package webapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.query.Query;

@SuppressWarnings("serial")
public class Login extends HttpServlet {
 
	@SuppressWarnings({ "rawtypes", "deprecation" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        String email = (String) request.getAttribute("email");
        String pass = (String) request.getAttribute("contraseña");
        Query query = ((Session) session).createQuery("SELECT * FROM Usuario WHERE mail=:correo AND contrasena=:pwd");
        query.setString("correo",email);
        query.setString("pwd",pass);
        List<?> list = query.list();
        //El argumento del método get() es la posición de la columna en la tabla MySQL.
        String correo = (String) list.get(1);
        String clave = (String) list.get(2);
        String rol = (String) list.get(4);
        if (pass==clave && email==correo) {
        	if(rol=="jugador") {
        		out.println("Usuario con rol de jugador entrando en la vista principal.");
        		response.sendRedirect("jugador.jsp");
        	} else if(rol=="admin") {
        		out.println("Usuario con rol de admin entrando en la vista de administrador.");
        		response.sendRedirect("admin.jsp");
        	} else {
        		out.println("Error de la Base de Datos");
        		response.sendError(100);
        	}
        } else {
        	out.println("Usuario o contraseña incorrectas");
        	response.sendRedirect("/LoginServlet");
        }
        
    }
}
