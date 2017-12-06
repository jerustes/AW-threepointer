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
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import webapp.Entities.League;
import webapp.Entities.Lineup;
import webapp.Entities.Player;
import webapp.Entities.Status;
import webapp.Entities.Team;
import webapp.Entities.User;
import webapp.Entities.Week;
import webapp.Entities.League.State;

@WebServlet("/SummaryHomeServlet")
public class SummaryHome extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -344043865093852110L;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		Configuration configuration = new Configuration();
		configuration.configure(this.getClass().getResource("/hibernate.cfg.xml"));
		configuration.addAnnotatedClass(Lineup.class);
		configuration.addAnnotatedClass(Status.class);
		configuration.addAnnotatedClass(User.class);
		configuration.addAnnotatedClass(Team.class);
		configuration.addAnnotatedClass(Player.class);
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session ses = sessionFactory.openSession();
		User user = (User) session.getAttribute("user");
		League league = (League) session.getAttribute("league");
		Lineup lineup = (Lineup) session.getAttribute("lineupUser");
		
		int puntuacion = 0;
		for (int i=0; i<lineup.getTeamLineup().size(); i++) { 
			Player bballer = lineup.getTeamLineup().get(i);
			puntuacion = puntuacion + bballer.getPointsWeek();
		}
		session.setAttribute("puntuacion", puntuacion);
		
		String q1 = "from deportista where pointsWeek > 0";
		Query query1 = ses.createQuery(q1);
		List<Week> listPlayers = (List<Week>) query1.list();
		session.setAttribute("ListPlayerswithPoints", listPlayers);

		if (user == null) {
			out.println("Usuario o contraseña incorrectas");
			response.sendRedirect("login.jsp");
		} else if (lineup.getLeague() != league.getId()) {
			out.println("Usuario no inscrito en dicha liga.");
			response.sendRedirect("LeagueHomeServlet?id="+league.getId());
		} else {
			out.println("Mostrar resumen");
			RequestDispatcher rd = request.getRequestDispatcher("SummaryHome.jsp");
			rd.forward(request, response);
		}	
	}
}
