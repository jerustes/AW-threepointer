package webapp;

import java.util.List;

import javax.servlet.http.HttpSession;
import org.hibernate.Query;
import org.hibernate.Session;

@SuppressWarnings("deprecation")
public class Validate {
	@SuppressWarnings("unused")
	public static boolean checkUser(String email,String pass, HttpSession session) {
      boolean st =false;
      try{
    	 Query<?> ps = ((Session) session).createQuery("SELECT mail, contrasena FROM User WHERE mail =:mail AND contrasena =:pwd");
         ps.setString("mail", email);
         ps.setString("pwd", pass);
         List<?> list = ps.list();
         st = true;
      }  catch(Exception e)  {
          e.printStackTrace();
      }
         return st;                 
  }   
}
