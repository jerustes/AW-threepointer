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
import webapp.Entities.Status;
import webapp.Entities.User;
import webapp.Entities.User.Role;
import webapp.Entities.Week;

@WebServlet("/MarketHomeServlet")
public class MarketHome extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8417478344457698175L;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		Configuration configuration = new Configuration();
		configuration.configure(this.getClass().getResource("/hibernate.cfg.xml"));
		configuration.addAnnotatedClass(Week.class);
		configuration.addAnnotatedClass(Status.class);
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session ses = sessionFactory.openSession();
		
		/*
		 * Market associated to user & league
		 * 	only access coming from a league once logged in
		 * */
		
		User user = (User) session.getAttribute("user");
		if (user == null) {
			out.println("Usuario o contrase�a incorrectas");
			response.sendRedirect("login.jsp");
		}
		League league = (League) session.getAttribute("league");
		
		
		
		// TODO: revisar h-querys
		String q1 = "from plantilla-deportista where id-lineup :id";
		Query query1 = ses.createQuery(q1);
		query1.setParameter("id",league.getId());
		//List<Team> weeksList = (List<Week>) query1.list();
		//session.setAttribute("weeksList", weeksList);

		String q2 = " ";
		Query query2 = ses.createQuery(q2);
		//List<Status> statusList = (List<Status>) query2.list();
		//Status status = statusList.get(0);
		//session.setAttribute("status", status);

		RequestDispatcher rd = request.getRequestDispatcher("AdminHome.jsp");
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
