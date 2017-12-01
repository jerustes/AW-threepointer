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
import webapp.Entities.User;

@WebServlet("/LeagueHomeServlet")
public class LeagueHome extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		Configuration configuration = new Configuration();
		configuration.configure(this.getClass().getResource("/hibernate.cfg.xml"));
		configuration.addAnnotatedClass(Week.class);
		configuration.addAnnotatedClass(Status.class);
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session ses = sessionFactory.openSession();
    int id = Integer.parseInt(request.getParameter("id"));
		User user = (User) session.getAttribute("user");
    League league = (League) session.getAttribute("liga");
		if (user == null) {
			out.println("Usuario o contraseña incorrectas");
			response.sendRedirect("login.jsp");
		} else if (league == null) {
      out.println("No existe liga con dicho id");
      response.sendRedirect("UserHomeServlet?id="+user.getId());
		} else if (id != league.getId()) {
      out.println("Usuario no inscrito en dicha liga.");
      response.sendRedirect("LeagueHomeServlet?id="+plantilla.getLeague());
    } else {
      out.println("Mostrar liga con id "+id);
      RequestDispatcher rd = request.getRequestDispatcher("LeagueHome.jsp");
		  rd.forward(request, response);
    }	
	}
}
