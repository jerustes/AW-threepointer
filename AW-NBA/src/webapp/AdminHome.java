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

import webapp.Entities.Status;
import webapp.Entities.User;
import webapp.Entities.User.Role;
import webapp.Entities.Week;

@WebServlet("/AdminHomeServlet")
public class AdminHome extends HttpServlet {
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
		User user = (User) session.getAttribute("user");
		if (user == null) {
			out.println("Usuario o contrase�a incorrectas");
			response.sendRedirect("login.jsp");
		} else if (user.getRole() == Role.jugador) {
			out.println("Rol de jugador, redireccionando...");
			response.sendRedirect("UserHomeServlet?id="+user.getId());
		}
		String q1 = "from jornada";
		Query query1 = ses.createQuery(q1);
		List<Week> weeksList = (List<Week>) query1.list();
		session.setAttribute("weeksList", weeksList);

		String q2 = "from estado";
		Query query2 = ses.createQuery(q2);
		List<Status> statusList = (List<Status>) query2.list();
		Status status = statusList.get(0);
		session.setAttribute("status", status);

		RequestDispatcher rd = request.getRequestDispatcher("AdminHome.jsp");
		rd.forward(request, response);
		
		ses.close();
		out.close();
	}
}
