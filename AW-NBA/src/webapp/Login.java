package webapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.query.Query;
import webapp.User.Role;

@WebServlet("/LoginServlet")
public class Login extends HttpServlet {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 7653006066357612724L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        
        PrintWriter out = response.getWriter();
        String email = (String) request.getAttribute("email");
        String pass = (String) request.getAttribute("contraseña");
        Query query = ((Session) session).createQuery("from Usuario");
        List<User> books = query.list();
        int i;
        for (i=0; i<books.size(); i++) {
        	String correo = books.get(i).getEmail();
        	String clave = books.get(i).getPass();
        	Role rol = books.get(i).getRole();
        	if (pass==clave && email==correo) {
        		if(rol == Role.jugador) {
        			out.println("Usuario con rol de jugador entrando en la vista principal.");
        			response.sendRedirect("jugador.jsp");
        		} else if(rol == Role.admin) {
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
}
