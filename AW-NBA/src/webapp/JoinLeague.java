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
import webapp.Entities.League.State;
import webapp.Entities.User.Role;
import webapp.Entities.Lineup;
import webapp.Entities.User;

@WebServlet("/JoinLeague")
public class JoinLeague extends HttpServlet {

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
			out.println("Usuario o contrase√±a incorrectas");
			response.sendRedirect("login.jsp");
		}
		
		String q1 = "from liga where id = :id";
		Query query1 = ses.createQuery(q1);
		query1.setParameter("id",id);
		List<League> leaguesList = (List<League>) query1.list();
		League league = leaguesList.get(0);
		int users = league.getNMax();
		
		String q3 = "from plantilla";
		Query query3 = ses.createQuery(q3);
		List<Lineup> lineupsAll = (List<Lineup>) query3.list();
		
		String q2 = "from plantilla where league = :liga";
		Query query2 = ses.createQuery(q2);
		query2.setParameter("liga",league.getId());
		List<Lineup> lineupsList = (List<Lineup>) query2.list();
		
		if (lineupsList.size() < users && league.getState() == State.Inscripcion) {
			out.println("Se puede aÔøΩadir dicho usuario a la liga");
			// A√±adir usuario a BD, transacci√≥n.
			Transaction tx = ses.beginTransaction();
			Lineup lineup = new Lineup();
			lineup.setId(lineupsAll.size()+1);
			lineup.setLeague(league.getId());
			lineup.setBalance(league.getBalance()); //salary or balance
			lineup.setUser(user.getId());
			lineup.setTeamLineup(null);
			lineup.setPoints(0);
			ses.saveOrUpdate(lineup);
			tx.commit();
			session.setAttribute("league",league);
			response.sendRedirect("LeagueHomeServlet?id="+league.getId());
		} else {
			out.println("Ya se ha alcanzado el n˙mero m·ximo de usuarios");
			out.println("No se puede aÒadir dicho usuario");
			response.sendRedirect("error.jsp");
		}	
		
		ses.close();
		out.close();
	}
}
