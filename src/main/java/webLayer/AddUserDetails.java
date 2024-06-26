package webLayer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddUserDetails")
public class AddUserDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("regname");
		String password = request.getParameter("regpass");
		String cpassword = request.getParameter("regcpass");
		String phone = request.getParameter("regphone");
		String email = request.getParameter("regmail");
		
// 		PrintWriter sends text to client (browser window)
//		object.println() will print result in browser window
//		-----------------------------------------------------
		try(PrintWriter object = response.getWriter();){
			object.println("Username: "+username);
			object.println("Password: "+password);
			object.println("Confirm Password: "+cpassword);
			object.println("Phone: "+phone);
			object.println("Email: "+email);
			
//			==================JDBC Connectivity=================
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","root");  
			Statement stmt=con.createStatement();
			
//			-----------------------------------------------------
//			executeUpdate() method has return type int, so assign it to an int variable 
//			If it return 0, it means no rows are affected (or) The statement executed was a DDL statement.(Create table)
//			If it returns 1 then it means 1 row is affected
//			It executes SQL queries, updates the table
			int value = stmt.executeUpdate("insert into register (username, password, cpassword, phone, mail) values ('"+username+"', '"+password+"', '"+cpassword+"', '"+phone+"', '"+email+"');");
			
			if(value==1) {
				object.println("Record inserted!");
			}	
			
//			System.out.println("Username: "+username);
//			System.out.println("Password: "+password);
//			System.out.println("Confirm Password: "+cpassword);
//			System.out.println("Phone: "+phone);
//			System.out.println("Email: "+email);
			
		} catch (ClassNotFoundException e) {
//		To handle if jar file is not added to project and classes are not found for it
			System.out.println(e);
		} catch (SQLException e) {
//		To indicate if read or write permission is denied and exception is caught
			System.out.println(e);
		}
	}
}
