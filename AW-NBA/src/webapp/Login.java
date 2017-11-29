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
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import webapp.Entities.*;
import webapp.Entities.User.Role;

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
        Configuration configuration = new Configuration();
        configuration.configure(this.getClass().getResource("/hibernate.cfg.xml"));
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(League.class);
        configuration.addAnnotatedClass(Player.class);
        configuration.addAnnotatedClass(Lineup.class);
        configuration.addAnnotatedClass(Week.class);
        configuration.addAnnotatedClass(Status.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session ses = sessionFactory.openSession();
        PrintWriter out = response.getWriter();
        String email = (String) request.getParameter("email");
        String pass = (String) request.getParameter("password");
        try {
        	String hql = "from usuario u where u.email = :mail and u.password = :pwd ";
	        Query query = ses.createQuery(hql);
	        query.setParameter("mail",email);
	        query.setParameter("pwd",pass);
	        List<User> users = query.list();
	        User user = users.get(0);
	        Role rol = user.getRole();
	        if(rol == Role.jugador) {
	    		session.setAttribute("user",user);
	    		out.println("Usuario con rol de jugador entrando en la vista principal.");
	    		response.sendRedirect("jugador.jsp");
	    	} else if(rol == Role.admin) {
	    		session.setAttribute("user",user);
	    		out.println("Usuario con rol de admin entrando en la vista de administrador.");
	    		response.sendRedirect("admin.jsp");
	    	} else {
	    		out.println("Error de la Base de Datos");
	    		response.sendError(100);
	    	}
        } catch (NullPointerException e) {
        	e.printStackTrace();
        }
        if (session.getAttribute("user")==null) {
    		out.println("Usuario o contraseña incorrectas");
    		response.sendRedirect("login.jsp");
        }
    }
}
