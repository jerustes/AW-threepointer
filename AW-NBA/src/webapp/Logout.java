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

import webapp.Entities.User;

@WebServlet("/LogoutServlet")
public class Logout extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2492682936461161525L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
//		Configuration configuration = new Configuration();
//		configuration.configure(this.getClass().getResource("/hibernate.cfg.xml"));
//		configuration.addAnnotatedClass(User.class);
//		SessionFactory sessionFactory = configuration.buildSessionFactory();
//		Session ses = sessionFactory.openSession();
		
		User user = (User) session.getAttribute("user");
		
		//in case of error
		if (user == null) {
			System.out.println("No se detecta usuario, regÃ­strese por favor");
			response.sendRedirect("login.jsp");
		}
		
		session.invalidate();
		System.out.println("Usuario " + user.getName() + " saliendo de la aplicación.");

		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	}
}

