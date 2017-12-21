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
import webapp.Entities.User.Role;
import webapp.Entities.Week;

@WebServlet("/AdvanceStatus")
public class AdvanceStatus extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2642528257958171662L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
		configuration.addAnnotatedClass(Player.class);
		configuration.addAnnotatedClass(Lineup.class);
		configuration.addAnnotatedClass(Team.class);
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session ses = sessionFactory.openSession();
		User user = (User) session.getAttribute("user");

		if (user == null) {
			out.println("Usuario o contraseña incorrectas.");
			response.sendRedirect("login.jsp");
		} else if (user.getRole() == Role.jugador) {
			out.println("Error, redireccionando a página de error.");
			response.sendRedirect("error.jsp");
		}
		
		String q1 = "from liga where state = :state";
		Query query1 = ses.createQuery(q1);
		query1.setParameter("state",State.Preparada);
		List<League> preparedLeagues = (List<League>) query1.list();
		Status status = (Status) session.getAttribute("status");
		Transaction tx = ses.beginTransaction();
		int phase = status.getPhase();
		int week = status.getRound();
		
		if (week == 0 && phase == 0) {
			status.setPhase(1);
			status.setRound(1);
		} else if (phase == 1) {
			status.setPhase(2);
		} else if (phase == 2) {
			
			status.setPhase(3);
			
			// Actualización puntos de la plantilla.
			String q2 = "from liga where state = :state";
			Query query2 = ses.createQuery(q2);
			query2.setParameter("state", State.Activa);
			List<League> activeLeagues = (List<League>) query2.list();
			
			String q3 = "from plantilla where ";
			for (League lg : activeLeagues) {
				q3 += "league = "+lg.getId()+" or ";
			}
			q3 += "1=0";
			Query query3 = ses.createQuery(q3);
			List<Lineup> activeLineups = (List<Lineup>) query3.list();
			
			String q4 = "from plantilladeportista where ";
			for (Lineup lp : activeLineups) {
				q4 += "lineup = "+lp.getId()+" or ";
			}
			q4 += "1=0";
			Query query4 = ses.createQuery(q4);
			List<Team> allTeams = (List<Team>) query4.list();
			
			for (Team team : allTeams) {
				String q5 = "from plantilla where id = :lineup";
				Query query5 = ses.createQuery(q5);
				query5.setParameter("lineup", team.getLineup());
				Lineup lineup = (Lineup) query5.list().get(0);
				
				String q6 = "from deportista where id = :player";
				Query query6 = ses.createQuery(q6);
				query6.setParameter("player", team.getPlayer());
				Player player = (Player) query6.list().get(0);
				int pointsLineup = lineup.getPoints();
				int weekPoints = player.getPointsWeek();
				lineup.setPoints(pointsLineup + weekPoints);
				ses.saveOrUpdate(lineup);
			}
			
			//Actualización puntos de los jugadores y del precio.
			String q7 = "from deportista";
			Query query7 = ses.createQuery(q7);
			List<Player> allPlayers = (List<Player>) query7.list();
			for (Player player : allPlayers) {
				int pointsPlayer = player.getPointsGlobal();
				int weekPoints = player.getPointsWeek();
				
				// Actualización del precio del jugador.
				int value = player.getValue();
				if (value > 60000) {
					player.setValue((int) (value*(1+0.01*(weekPoints-70))));
				} else if (value > 50000) {
					player.setValue((int) (value*(1+0.01*(weekPoints-60))));
				} else if (value > 40000) {
					player.setValue((int) (value*(1+0.01*(weekPoints-50))));
				} else if (value > 30000) {
					player.setValue((int) (value*(1+0.01*(weekPoints-40))));
				} else if (value > 25000){
					player.setValue((int) (value*(1+0.01*(weekPoints-25))));
				}
				player.setPointsGlobal(pointsPlayer + weekPoints);
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
