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
import webapp.Entities.Lineup;
import webapp.Entities.User;

@WebServlet("/CreateLeague")
public class CreateLeague extends HttpServlet {



	/**
	 * 
	 */
	private static final long serialVersionUID = -1323483241640296123L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		Configuration configuration = new Configuration();
		configuration.configure(this.getClass().getResource("/hibernate.cfg.xml"));
		configuration.addAnnotatedClass(League.class);
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session ses = sessionFactory.openSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			out.println("Usuario o contraseña incorrectas");
			response.sendRedirect("login.jsp");
		}
		Transaction tx = ses.beginTransaction();
		String q1 = "from liga";
		Query query1 = ses.createQuery(q1);
		List<League> leaguesList = (List<League>) query1.list();
		
		
		String q2 = "from plantilla";
		Query query2 = ses.createQuery(q2);
		List<Lineup> lineupsList = (List<Lineup>) query2.list();
		
		String name = request.getParameter("name");
		int nmax = Integer.parseInt(request.getParameter("maxUsers"));
		int balance = Integer.parseInt(request.getParameter("initBalance"));
		if (balance > 200000 || balance < 50000 || name == null || nmax > 20 || nmax < 2) {
			out.println("Parámetros incorrectos, inténtelo de nuevo.");
			response.sendRedirect("error.jsp");
		} else {
			League league = new League();
			league.setId(leaguesList.size()+1);
			league.setCreator(user.getId());
			league.setName(name);
			league.setNMax(nmax);
			league.setBalance(balance);
			league.setState(State.Inscripcion);
			leaguesList.add(league);
			ses.saveOrUpdate(league);
			Lineup lineup = new Lineup();
			lineup.setId(lineupsList.size()+1);
			lineup.setLeague(league.getId());
			lineup.setBalance(league.getBalance()); //salary or balance
			lineup.setUser(user.getId());
			lineup.setTeamLineup(null);
			lineup.setPoints(0);
			lineupsList.add(lineup);
			ses.saveOrUpdate(lineup);
			tx.commit();
			session.setAttribute("league",league);
			response.sendRedirect("LeagueHomeServlet?id="+league.getId());
		}
		
		ses.close();
		out.close();
	}
}
