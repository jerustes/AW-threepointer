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
import webapp.Entities.League.State;
import webapp.Entities.Status;
import webapp.Entities.User;
import webapp.Entities.User.Role;
import webapp.Entities.Week;

@WebServlet("/AdvanceLeagueStatus")
public class AdvanceStatusLeague extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6341589376387126732L;

	@SuppressWarnings({ "unchecked", "rawtypes" })
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
		League league = (League) session.getAttribute("league");
		
		if (user.getRole() != Role.jugador) {
			out.println("Usuario o contraseï¿½a incorrectas");
			response.sendRedirect("login.jsp");
		} else if (user.getId() != league.getCreator()) {
			out.println("Error, redireccionando a página de error.");
			response.sendRedirect("error.jsp");
		}
		
		
		Transaction tx = ses.beginTransaction();
		if (league.getState() == State.Inscripcion) {
			league.setState(State.Preparada);
		} else if (league.getState() == State.Activa) {
			league.setState(State.Finalizada);
		}
		ses.saveOrUpdate(league);
		tx.commit();

		RequestDispatcher rd = request.getRequestDispatcher("LeagueHome.jsp");
		rd.forward(request, response);
		
		ses.close();
		out.close();
	}
}
