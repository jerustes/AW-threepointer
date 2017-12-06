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
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import webapp.Entities.League;
import webapp.Entities.Lineup;
import webapp.Entities.Player;
import webapp.Entities.Team;
import webapp.Entities.User;

@WebServlet("/SellPlayer")
public class SellPlayer extends HttpServlet {



	/**
	 * 
	 */
	private static final long serialVersionUID = -3503473401494936601L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		Configuration configuration = new Configuration();
		configuration.configure(this.getClass().getResource("/hibernate.cfg.xml"));
		configuration.addAnnotatedClass(Player.class);
		configuration.addAnnotatedClass(Lineup.class);
		configuration.addAnnotatedClass(Team.class);
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session ses = sessionFactory.openSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			out.println("Usuario o contraseña incorrectas");
			response.sendRedirect("login.jsp");
		}
		Transaction tx = ses.beginTransaction();
		int id = Integer.parseInt(request.getParameter("id"));
		Lineup lineup = (Lineup) session.getAttribute("lineupUser");
		String q1 = "delete plantilladeportista where lineup = :lineup and player = :player";
		Query query1 = ses.createQuery(q1);
		query1.setParameter("player",id);
		query1.setParameter("lineup",lineup.getId());
		// Could not execute update.
		int result = query1.executeUpdate();
		String q2 = "from deportista where id =: player";
		Query query2 = ses.createQuery(q2);
		query2.setParameter("player", id);
		Player player = (Player) query2.list().get(0);
		lineup.setBalance(lineup.getBalance()+player.getValue());
		ses.saveOrUpdate(lineup);
		tx.commit();
		response.sendRedirect("MarketHomeServlet");
	}
}