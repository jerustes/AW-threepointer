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

import webapp.Entities.Lineup;
import webapp.Entities.Player;
import webapp.Entities.Team;

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
		Lineup lineup = (Lineup) session.getAttribute("lineupUser");
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

		RequestDispatcher rd = request.getRequestDispatcher("MarketHome.jsp");
		rd.forward(request, response);
	}

}

/*
 * Vista de compra-venta de deportistas de una liga (para jugadores con sesión
 * iniciada e inscritos en la liga): muestra el saldo y plantilla actual del
 * jugador, así como la lista de todos los deportistas libres, todos ellos,
 * tanto los deportistas de su plantilla como los libres, con su valor inicial y
 * su valor actual. Si se encuentra en la primera fase de la jornada activa,
 * permite al usuario vender a deportistas de su plantilla. Si además el usuario
 * no tiene su plantilla completa, se le permite comprar deportistas libres del
 * mercado.
 */
