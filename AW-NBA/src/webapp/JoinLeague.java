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
import webapp.Entities.User;

@WebServlet("/JoinLeague")
public class JoinLeague extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 939335133858407229L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		Configuration configuration = new Configuration();
		configuration.configure(this.getClass().getResource("/hibernate.cfg.xml"));
		configuration.addAnnotatedClass(League.class);
		configuration.addAnnotatedClass(Lineup.class);
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session ses = sessionFactory.openSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			out.println("Usuario o contraseña incorrectas");
			response.sendRedirect("login.jsp");
		}
		int id = Integer.parseInt(request.getParameter("id"));
		String hql = "from liga where id = :id";
		Query query = ses.createQuery(hql);
		query.setParameter("id",id);
		List<League> leagues = (List<League>) query.list();
		League league = leagues.get(0);
		int usuarios = league.getNMax();
		
		// NULL POINTER EXCEPTION AQUI
		String q = "from plantilla where liga = :liga";
		Query consulta = ses.createQuery(q);
		consulta.setParameter("liga",id);
		List<Lineup> plantillas = (List<Lineup>) consulta.list();
		
		String p = "from plantilla";
		Query plant = ses.createQuery(p);
		List<Lineup> todas_plantillas = (List<Lineup>) plant.list();
		
		if (plantillas.size() < usuarios) {
			out.println("Se puede añadir dicho usuario a la liga");
			// Añadir usuario a BD, transacción.
			Transaction tx = ses.beginTransaction();
			Lineup plantilla = new Lineup();
			plantilla.setId(todas_plantillas.size()+1);
			plantilla.setLeague(league.getId());
			plantilla.setSalary(league.getSaldo());
			plantilla.setUser(user.getId());
			plantilla.setTeamLineup(null);
			plantilla.setPoints(0);
			ses.save(plantilla);
			tx.commit();
			session.setParameter("liga",league);
			response.sendRedirect("LeagueHomeServlet?id="+league.getId());
		} else {
			out.println("Ya se ha alcanzado el número máximo de usuarios");
			out.println("No se puede añadir dicho usuario");
			response.sendRedirect("UserHomeServlet?id="+user.getId());
		}		
	}
}
