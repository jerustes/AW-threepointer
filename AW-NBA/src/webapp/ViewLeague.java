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

@WebServlet("/ViewLeague")
public class ViewLeague extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 939335133858407229L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		Configuration configuration = new Configuration();
		configuration.configure(this.getClass().getResource("/hibernate.cfg.xml"));
		configuration.addAnnotatedClass(League.class);
		configuration.addAnnotatedClass(Lineup.class);
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session ses = sessionFactory.openSession();
		
		User user = (User) session.getAttribute("user");
		int id = Integer.parseInt(request.getParameter("id"));
		
		if (user.getRole() != Role.jugador) {
			out.println("Usuario o contraseña incorrectas");
			response.sendRedirect("login.jsp");
		}
		
		String q1 = "from liga where id = :id";
		Query query1 = ses.createQuery(q1);
		query1.setParameter("id",id);
		List<League> leagueList = (List<League>) query1.list();
		League league = leagueList.get(0);
		session.setAttribute("league",league);
		response.sendRedirect("LeagueHomeServlet?id="+id);
		
		ses.close();
		out.close();
	}
}
