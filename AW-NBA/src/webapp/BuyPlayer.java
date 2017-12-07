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

import webapp.Entities.Lineup;
import webapp.Entities.Player;
import webapp.Entities.Team;
import webapp.Entities.User;

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
		Lineup lineup = (Lineup) session.getAttribute("lineupUser");
		int id = Integer.parseInt(request.getParameter("id"));
		
		if (user == null) {
			out.println("Usuario o contraseÃ±a incorrectas");
			response.sendRedirect("login.jsp");
		}
		
		String q1 = "from deportista where id = :player";
		Query query1 = ses.createQuery(q1);
		query1.setParameter("player", id);
		Player player = (Player) query1.list().get(0);
		List<Player> teamLineup = lineup.getTeamLineup();
		long balance = lineup.getBalance();
		if (balance > player.getValue() && teamLineup.size() < 5) {
			teamLineup.add(player);
			lineup.setBalance(balance - player.getValue());
			out.println("Se puede comprar dicho jugador.");
			String q2 = "from plantilladeportista";
			Query query2 = ses.createQuery(q2);
			List<Team> listTeams = (List<Team>) query2.list();
			Team team = new Team();
			team.setId(listTeams.size()+1);
			team.setPlayer(id);
			team.setLineup(lineup.getId());
			listTeams.add(team);
			ses.saveOrUpdate(listTeams);
			tx.commit();
		} else if (balance < player.getValue()) {
			out.println("No hay suficiente saldo para comprar dicho jugador");
		} else if (teamLineup.size() >= 5) {
			out.println("El equipo ya cuenta con el número máximo de deportistas");
		}
		
		ses.close();
		out.close();
		
		RequestDispatcher rd = request.getRequestDispatcher("MarketHome.jsp");
		rd.forward(request, response);
		
	}

}
