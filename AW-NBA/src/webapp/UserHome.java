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
import webapp.Entities.League;
import webapp.Entities.Lineup;
import webapp.Entities.User;
import webapp.Entities.User.Role;

@WebServlet("/UserHomeServlet")
public class UserHome extends HttpServlet {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 4123631293507927467L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		Configuration configuration = new Configuration();
		configuration.configure(this.getClass().getResource("/hibernate.cfg.xml"));
		configuration.addAnnotatedClass(League.class);
		configuration.addAnnotatedClass(Lineup.class);
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session ses = sessionFactory.openSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			out.println("Usuario o contraseña incorrectas");
			response.sendRedirect("login.jsp");
		} else if (user.getRole() == Role.admin) {
			out.println("Rol de administrador, redireccionando.");
			response.sendRedirect("AdminHomeServlet");
		}
		String q = "select p.liga from plantilla p where p.usuario = :id";
		Query query = ses.createQuery(q);
		query.setParameter("id",user.getId());
		List<League> list = query.list();
		session.setAttribute("ligasUsuario", list);
		
		String hql = "select p.liga from plantilla p where p.usuario != :id";
		Query consulta = ses.createQuery(hql);
		consulta.setParameter("id",user.getId());
		List<League> lista = consulta.list();
		session.setAttribute("ligasDisponibles", lista);
    }
}
