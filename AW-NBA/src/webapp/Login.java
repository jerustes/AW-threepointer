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

import webapp.Entities.*;
import webapp.Entities.User.Role;

@WebServlet("/LoginServlet")
public class Login extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7653006066357612724L;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Configuration configuration = new Configuration();
		configuration.configure(this.getClass().getResource("/hibernate.cfg.xml"));
		configuration.addAnnotatedClass(User.class);
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session ses = sessionFactory.openSession();
		String email = (String) request.getParameter("inputEmail");
		String pass = (String) request.getParameter("inputPassword");
		String q1 = "from usuario where email = :mail and password = :pwd ";
		Query query = ses.createQuery(q1);
		query.setParameter("mail", email);
		query.setParameter("pwd", pass);
		List<User> listUsers = (List<User>) query.list();
		User user = listUsers.get(0);
		Role rol = user.getRole();
		if (rol == Role.jugador) {
			session.setAttribute("user", user);
			System.out.println("Usuario con rol de jugador entrando en la vista principal.");
			response.sendRedirect("UserHomeServlet?id="+user.getId());
		} else if (rol == Role.admin) {
			session.setAttribute("user", user);
			System.out.println("Usuario con rol de admin entrando en la vista de administrador.");
			response.sendRedirect("AdminHomeServlet");
		} else {
			System.out.println("Error de la Base de Datos");
			response.sendRedirect("error.jsp");
		}
		if (session.getAttribute("user") == null) {
			System.out.println("Usuario o contrase�a incorrectas");
			response.sendRedirect("login.jsp");
		}
		
		ses.close();
	}
}
