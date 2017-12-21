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
import webapp.Entities.Player;
import webapp.Entities.Team;
import webapp.Entities.User;
import webapp.Entities.League.State;
import webapp.Entities.User.Role;

@WebServlet("/MarketHomeServlet")
public class MarketHome extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8417478344457698175L;

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
		configuration.addAnnotatedClass(Player.class);		
		configuration.addAnnotatedClass(Team.class);
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session ses = sessionFactory.openSession();
		User user = (User) session.getAttribute("user");
		League league = (League) session.getAttribute("league");
		Lineup lineup = (Lineup) session.getAttribute("lineupUser");
		
		if (user.getRole() != Role.jugador) {
			out.println("Usuario o contrase√±a incorrectas");
			response.sendRedirect("login.jsp");
		} else if (lineup.getLeague() != league.getId()) {
			out.println("Error, redireccionando a p·gina de error.");
			response.sendRedirect("error.jsp");
		} else if (league.getState() != State.Activa) {
			out.println("Liga no activa.");
			response.sendRedirect("error.jsp");
		}
		/*
		* Market associated to user & league
		* 	only access coming from a league once logged in
		* */
		
		String q1 = "from plantilladeportista where lineup = :lineup";
		Query query1 = ses.createQuery(q1);
		query1.setParameter("lineup", lineup.getId());
		List<Team> listTeam = (List<Team>) query1.list();
		
		String q2 = "from deportista where " ;
		for (Team team : listTeam) {
			q2 += "id != "+team.getPlayer()+" and ";
		}
		q2 += "1=1 order by value desc";
		Query query2 = ses.createQuery(q2);
		List<Player> marketPlayers = query2.list();
		session.setAttribute("marketPlayers", marketPlayers);
		//Players not belonging to current Lineup, hence, that can be bought

		RequestDispatcher rd = request.getRequestDispatcher("MarketHome.jsp");
		rd.forward(request, response);
		
		ses.close();
		out.close();
	}

}
