package webapp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.sql.*;

public class RegisterUser extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		response.setContentType("text/html; charset = UTF-8");
		PrintWriter out = response.getWriter();
		
		//CHANGE TO SESSION
		String name = request.getParameter("email");
		String email = request.getParameter("password");
		
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
