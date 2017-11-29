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
import webapp.Entities.Player;
import webapp.Entities.Status;
import webapp.Entities.User;
import webapp.Entities.Week;
import webapp.Entities.User.Role;

@WebServlet("/AdvanceStatus")
public class AdminHome extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8417478344457698175L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
        
        if (session.getAttribute("user")==null) {
    		out.println("Usuario o contraseña incorrectas");
    		response.sendRedirect("login.jsp");
        }
        
        try {
        	String q = "from jornada";
	        Query querie = ses.createQuery(q);
	        List<Week> lista = querie.list();
        	String hql = "from estado";
	        Query query = ses.createQuery(hql);
	        List<Status> listb = query.list();
	        Status status = listb.get(0);
	        int phase = status.getPhase();
	        int week = status.getRound();
	        if (phase == 1) phase=2;
	        else if (phase == 2) phase=3;
	        else if (phase == 3) week=week+1;
	        RequestDispatcher rd = request.getRequestDispatcher("AdminHome.jsp");
	        rd.forward(request, response);
	        
        } catch (NullPointerException e) {
        	e.printStackTrace();
        }
	}
}
