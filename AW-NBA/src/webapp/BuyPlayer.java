package webapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import webapp.Entities.Team;
import webapp.Entities.User;
import webapp.Entities.User.Role;

/**
 * Servlet implementation class BuyPlayer
 */
@WebServlet("/BuyPlayer")
public class BuyPlayer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		Configuration configuration = new Configuration();
		configuration.configure(this.getClass().getResource("/hibernate.cfg.xml"));
		configuration.addAnnotatedClass(Player.class);
		configuration.addAnnotatedClass(Team.class);
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session ses = sessionFactory.openSession();
		Transaction tx = ses.beginTransaction();
		
		User user = (User) session.getAttribute("user");
		League league = (League) session.getAttribute("league");
		Lineup lineup = (Lineup) session.getAttribute("lineupUser");
		int id = Integer.parseInt(request.getParameter("id"));
		
		if (user.getRole() != Role.jugador) {
			out.println("Usuario o contraseÃ±a incorrectas");
			response.sendRedirect("login.jsp");
		} else if (lineup.getLeague() != league.getId()) {
			out.println("Error, redireccionar a página de error.");
			response.sendRedirect("error.jsp");
		}
		
		String q1 = "from deportista where id = :id";
		Query query1 = ses.createQuery(q1);
		query1.setParameter("id", id);
		Player player = (Player) query1.list().get(0);
		long balance = lineup.getBalance();
		List<Player> teamLineup = lineup.getTeamLineup();
		
		String q2 = "from plantilladeportista";
		Query query2 = ses.createQuery(q2);
		List<Team> listTeams = (List<Team>) query2.list();
		
		try {
			if (balance > player.getValue() && teamLineup.size() < 5) {
				teamLineup.add(player);
				lineup.setBalance(balance - (long) player.getValue());
				out.println("Se puede comprar dicho jugador.");
				Team team = new Team();
				team.setId(listTeams.size()+1);
				team.setPlayer(id);
				team.setLineup(lineup.getId());
				listTeams.add(team);
				ses.saveOrUpdate(team);
				ses.saveOrUpdate(lineup);
				tx.commit();
			} else if (balance < player.getValue()) {
				out.println("No hay suficiente saldo para comprar dicho jugador");
			} else if (teamLineup.size() >= 5) {
				out.println("El equipo ya cuenta con el número máximo de deportistas");
			}
		} catch (NullPointerException e) {
			// If the teamLineup is null, create new listPlayers.
			List<Player> teamPlayers = new ArrayList<Player>();
			teamPlayers.add(player);
			lineup.setTeamLineup(teamPlayers);
			lineup.setBalance(balance - (long) player.getValue());
			Team team = new Team();
			team.setId(listTeams.size()+1);
			team.setPlayer(id);
			team.setLineup(lineup.getId());
			listTeams.add(team);
			ses.saveOrUpdate(team);
			ses.saveOrUpdate(lineup);
			tx.commit();
			ses.close();
			RequestDispatcher rd = request.getRequestDispatcher("MarketHome.jsp");
			rd.forward(request, response);
		}
		
		ses.close();
		out.close();
		
		RequestDispatcher rd = request.getRequestDispatcher("MarketHome.jsp");
		rd.forward(request, response);
		
	}

}
