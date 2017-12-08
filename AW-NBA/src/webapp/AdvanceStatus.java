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
import webapp.Entities.League.State;
import webapp.Entities.Status;
import webapp.Entities.Team;
import webapp.Entities.User;
import webapp.Entities.Week;

@WebServlet("/AdvanceStatus")
public class AdvanceStatus extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2642528257958171662L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		Configuration configuration = new Configuration();
		configuration.configure(this.getClass().getResource("/hibernate.cfg.xml"));
		configuration.addAnnotatedClass(Week.class);
		configuration.addAnnotatedClass(Status.class);
		configuration.addAnnotatedClass(League.class);
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session ses = sessionFactory.openSession();

		if (session.getAttribute("user") == null) {
			out.println("Usuario o contrase�a incorrectas");
			response.sendRedirect("login.jsp");
		}
		String q1 = "from liga where state = :state";
		Query query1 = ses.createQuery(q1);
		query1.setParameter("state",State.Preparada);
		List<League> preparedLeagues = (List<League>) query1.list();
		Status status = (Status) session.getAttribute("status");
		Transaction tx = ses.beginTransaction();
		int phase = status.getPhase();
		int week = status.getRound();
		
		
		if (phase == 1) {
			status.setPhase(2);
		} else if (phase == 2) {
			
			status.setPhase(3);
			String q3 = "from plantilladeportista";
			Query query3 = ses.createQuery(q3);
			List<Team> allTeams = (List<Team>) query1.list();
			
			for (Team team : allTeams) {
				String q4 = "from plantilla where id = :lineup";
				Query query4 = ses.createQuery(q4);
				query4.setParameter("lineup", team.getLineup());
				Lineup lineup = (Lineup) query4.list().get(0);
				String q5 = "from deportista where id = :player";
				Query query5 = ses.createQuery(q5);
				query5.setParameter("player", team.getPlayer());
				Player player = (Player) query5.list().get(0);
				int pointsLineup = lineup.getPoints();
				lineup.setPoints(pointsLineup + player.getPointsWeek());
				ses.saveOrUpdate(lineup);
			}
			String q2 = "from deportista";
			Query query2 = ses.createQuery(q2);
			List<Player> allPlayers = (List<Player>) query2.list();
			for (Player player : allPlayers) {
				int pointsPlayer = player.getPointsGlobal();
				player.setPointsGlobal(pointsPlayer + player.getPointsWeek());
				ses.saveOrUpdate(player);
			}
			
			
		} else if (phase == 3 && week < 26) {
			status.setRound(week + 1);
			for (League league: preparedLeagues) {
				league.setState(State.Activa);
			}
			status.setPhase(1);
		} else if (phase == 3 && week == 26) {
			out.println("La liga ha concluido.");
			status.setRound(0);
			status.setPhase(0);
		}
		ses.saveOrUpdate(status);
		tx.commit();
		RequestDispatcher rd = request.getRequestDispatcher("AdminHome.jsp");
		rd.forward(request, response);
		
		ses.close();
		out.close();
	}
}
