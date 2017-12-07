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
import webapp.Entities.Status;
import webapp.Entities.Team;
import webapp.Entities.User;

@WebServlet("/LeagueHomeServlet")
public class LeagueHome extends HttpServlet {


	/**
	 * 
	 */
	private static final long serialVersionUID = 4697563238079148919L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
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
		int id = Integer.parseInt(request.getParameter("id"));
		User user = (User) session.getAttribute("user");
		League league = (League) session.getAttribute("league");
		
		String q1 = "from plantilla where league = :id order by points desc";
		Query query1 = ses.createQuery(q1);
		query1.setParameter("id",league.getId());
		List<Lineup> lineupsLeague = (List<Lineup>) query1.list();
		session.setAttribute("lineupsLeague", lineupsLeague);
		
		String q2 = "from estado";
		Query query2 = ses.createQuery(q2);
		Status status = (Status) query2.list().get(0);
		session.setAttribute("status", status);
		
		try {
			String q3 = "from plantilla where user = :user and league = :league";
			Query query3 = ses.createQuery(q3);
			query3.setParameter("user",user.getId());
			query3.setParameter("league",league.getId());
			Lineup lineup = (Lineup) query3.list().get(0);
			session.setAttribute("lineupUser", lineup);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		
		String q4 = "from usuario where id = :creator";
		Query query4 = ses.createQuery(q4);
		query4.setParameter("creator", league.getCreator());
		User creator = (User) query4.list().get(0);
		session.setAttribute("creator", creator);
		
		String q5 = "from usuario";
		Query query5 = ses.createQuery(q5);
		List<User> listUsers = (List<User>) query5.list();
		session.setAttribute("listUsers", listUsers);
		try {
			Lineup lineupAux = (Lineup) session.getAttribute("lineupUser");
			String q6 = "from plantilladeportista where lineup = :lineup";
			Query query6 = ses.createQuery(q6);
			query6.setParameter("lineup", lineupAux.getId());
			List<Team> listTeam = (List<Team>) query6.list();
		
			List<Player> teamLineup = null;
			for (Team team : listTeam) {
				String q7 = "from deportista where id = :player";
				Query query7 = ses.createQuery(q7);
				query7.setParameter("player", team.getPlayer());
				if (teamLineup == null) {
					teamLineup = (List<Player>) query7.list();
				} else {
					Player player = (Player) query7.list().get(0);
					teamLineup.add(player);
				}
			}	
			lineupAux.setTeamLineup(teamLineup);
			session.setAttribute("lineupUser", lineupAux);
		} catch (Exception e) {
			e.getMessage();
		}
		if (user == null) {
			out.println("Usuario o contraseña incorrectas");
			response.sendRedirect("login.jsp");
		} else if (id != league.getId()) {
			out.println("Usuario no inscrito en dicha liga.");
			response.sendRedirect("error.jsp");
		} else {
			out.println("Mostrar liga con id "+id);
			RequestDispatcher rd = request.getRequestDispatcher("LeagueHome.jsp");
			rd.forward(request, response);
		}
		
		ses.close();
		out.close();
	}
}
