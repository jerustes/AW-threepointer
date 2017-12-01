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
		response.setContentType("text/html;charset=UTF-8");
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
		String hql = "from liga";
		Query query = ses.createQuery(hql);
		List<League> leagues = (List<League>) query.list();
		String name = request.getParameter("name");
		int nmax = Integer.parseInt(request.getParameter("max_usuarios"));
		int saldo = Integer.parseInt(request.getParameter("saldo_inicial"));
		if (saldo > 200000 || saldo < 50000 || name == null || nmax > 20 || nmax < 2) {
			out.println("Parámetros incorrectos, inténtelo de nuevo.");
			response.sendRedirect("UserHomeServlet?id="+user.getId());
		} else {
			League league = new League();
			league.setId(leagues.size()+1);
			league.setCreator(user.getId());
			league.setName(name);
			league.setNMax(nmax);
			league.setSaldo(saldo);
			league.setState(State.Inscripcion);
			leagues.add(league);
			ses.saveOrUpdate(league);
			tx.commit();
			session.setParameter("liga",league);
			response.sendRedirect("LeagueHomeServlet?id="+league.getId());
		}
	}
}
