package webapp;

import java.sql.*;

public class Validate {
     public static boolean checkUser(String email,String pass) {
      boolean st =false;
      try{
    	  SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
    	  try{
    	  Session session=sessionFactory.openSession();
    	  Connection conn=session.connection();
    	  String sql="SELECT * FROM student WHERE roll_no=?";
    	  PreparedStatement statement=conn.prepareStatement(sql);
    	  statement.setInt(1, 3);
    	  ResultSet rs=statement.executeQuery();
    	  System.out.println("RollNo.\t Name\tCourse");
    	  while(rs.next()){
    	  System.out.print(rs.getString(1));
    	  System.out.print("\t"+rs.getString(2));
    	  System.out.print("\t"+rs.getString(3)); 
    	 Query ps = ((Session) session).createSQLQuery("SELECT * FROM User WHERE mail = ? AND contrasena = ?");
         ps.setString(1, email);
         ps.setString(2, pass);
         ps.list();
        
      }catch(Exception e)
      {
          e.printStackTrace();
      }
         return st;                 
  }   
}
