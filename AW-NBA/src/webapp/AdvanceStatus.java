package webapp;

import java.io.IOException;
import java.io.PrintWriter;

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

import webapp.Entities.Status;
import webapp.Entities.Week;

@WebServlet("/AdvanceStatus")
public class AdvanceStatus extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2642528257958171662L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
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

		if (session.getAttribute("user") == null) {
			out.println("Usuario o contraseña incorrectas");
			response.sendRedirect("login.jsp");
		}
		Status status = (Status) session.getAttribute("status");
		Transaction tx = ses.beginTransaction();
		int phase = status.getPhase();
		int week = status.getRound();
		if (phase == 1) {
			status.setPhase(2);
		} else if (phase == 2) {
			status.setPhase(3);
		} else if (phase == 3 && week < 26) {
			status.setRound(week + 1);
			status.setPhase(1);
		} else if (phase == 3 && week == 26) {
			out.println("La liga ha concluido.");
			status.setRound(0);
			status.setPhase(0);
		}
		ses.saveOrUpdate(status);
		tx.commit();
		RequestDispatcher rd = request.getRequestDispatcher("AdminHome.jsp");
		rd.forward(request, response);
	}
}
