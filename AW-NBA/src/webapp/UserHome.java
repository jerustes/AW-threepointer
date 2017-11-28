package webapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.query.Query;
import webapp.User.Role;

@WebServlet("/HomeServlet")
public class UserHome extends HttpServlet {
 
	/**
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        
        PrintWriter out = response.getWriter();
        List<League> leaguesUser = (List<League>) session.getAttribute("leagues_user"/*ligas en las que está inscrita el user*/);
		if(leaguesUser == null){
			//create and add Leagues (in which the user is signed up) to session
			leaguesUser = new ArrayList<League>();
			response.setAttribute("leagues_user", leaguesUser);
		}
        //...
        }
    }
}
