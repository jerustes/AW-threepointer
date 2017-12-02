package webapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
			out.println("Usuario o contrase�a incorrectas");
			response.sendRedirect("login.jsp");
		} else if (user.getRole() == Role.admin) {
			out.println("Rol de administrador, redireccionando.");
			response.sendRedirect("AdminHomeServlet");
		}
		int id = Integer.parseInt(request.getParameter("id"));
		String q1 = "from plantilla where usuario = :id";
		Query query1 = ses.createQuery(q1);
		query1.setParameter("id",id);
		List<Lineup> lineupList = (List<Lineup>) query1.list();
		List<League> leagueList = null;
		for (Lineup lineup: lineupList) {
			String q2 = "from liga where id = :id";
			Query query2 = ses.createQuery(q2);
			query2.setParameter("id",lineup.getLeague());
			if (leagueList == null) {
				leagueList = (List<League>) query2.list();
			} else {
				League league = (League) query2.list().get(0);
				leagueList.add(league);
			}
		}
		session.setAttribute("leaguesUser", leagueList);
		
		String c = "from plantilla where usuario = :id";
		Query query3 = ses.createQuery(c);
		query3.setParameter("id",id);
		List<Lineup> lineupList2 = (List<Lineup>) query3.list();
		String q3 = "from liga where ";
		for (Lineup lineup : lineupList2) {
			q3 += "id != "+ lineup.getLeague()+" and ";
		}
		q3 += "1=1";
		Query query4 = ses.createQuery(q3);
		List<League> leagueList2 = (List<League>) query4.list();
		session.setAttribute("leaguesAvail", leagueList2);
		
		RequestDispatcher rd = request.getRequestDispatcher("UserHome.jsp");
		rd.forward(request, response);
    }
}
