package webapp;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class RegisterUser extends HttpServlet {
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("email");
		String email = (String) session.getAttribute("password");
		
		try {
			//Database connection
			//prepared statement HQL
			
			//when checked todo ok
				//out.println("Register complete");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
