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
import webapp.Entities.Status;
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
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session ses = sessionFactory.openSession();
		int id = Integer.parseInt(request.getParameter("id"));
		User user = (User) session.getAttribute("user");
		League league = (League) session.getAttribute("league");
		
		String q1 = "from plantilla where liga = :id order by puntos desc";
		Query query1 = ses.createQuery(q1);
		query1.setParameter("id",league.getId());
		List<Lineup> lineupsLeague = (List<Lineup>) query1.list();
		session.setAttribute("lineupsLeague", lineupsLeague);
		
		String q2 = "from estado";
		Query query2 = ses.createQuery(q2);
		Status status = (Status) query2.list().get(0);
		session.setAttribute("status", status);
		
		String q3 = "from plantilla where usuario = :user and liga = :league";
		Query query3 = ses.createQuery(q3);
		query3.setParameter("user",user.getId());
		query3.setParameter("league",league.getId());
		Lineup lineup = (Lineup) query3.list().get(0);
		session.setAttribute("lineupUser", lineup);
		
//		if (user == null) {
//			out.println("Usuario o contraseña incorrectas");
//			response.sendRedirect("login.jsp");
//		} else if (league == null) {
//			out.println("No existe liga con dicho id");
//			response.sendRedirect("UserHomeServlet?id="+user.getId());
//		} else 
		if (id != league.getId()) {
			out.println("Usuario no inscrito en dicha liga.");
			response.sendRedirect("LeagueHomeServlet?id="+league.getId());
		} else {
			out.println("Mostrar liga con id "+id);
			RequestDispatcher rd = request.getRequestDispatcher("LeagueHome.jsp");
			rd.forward(request, response);
		}	
	}
}
