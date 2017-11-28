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

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
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
        Configuration configuration = new Configuration();
        configuration.configure(this.getClass().getResource("/hibernate.cfg.xml"));
        configuration.addAnnotatedClass(User.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session ses = sessionFactory.openSession();
        PrintWriter out = response.getWriter();
        String email = (String) request.getParameter("email");
        String pass = (String) request.getParameter("password");
        try {
        	Query query = ses.createQuery("from usuario where usuario.email=? and usuario.password=?")
        	.setParameter(0, email).setParameter(1, pass);
        	System.out.println(query.toString());
        	//List<User> users = ses.createCriteria("usuario")
        	//		.add(Restrictions.eq("email", email))
        	//		.add(Restrictions.eq("password", pass))
        	//		.list();
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
