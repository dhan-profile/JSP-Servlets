package webLayer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Validation")
public class Validation extends HttpServlet {
//	request Object contains request received from client, response Object contains response to be sent to client 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String clientuser = request.getParameter("user");
		String clientpass = request.getParameter("pass");
		
//		------JDBC Connectivity----
		try(PrintWriter out = response.getWriter()){
				Class.forName("com.mysql.jdbc.Driver");  
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","root");  
				Statement stmt=con.createStatement();
				
				ResultSet rs = stmt.executeQuery("select * from register;");
								
				if(clientuser.equals("admin") && clientpass.equals("admin123")) {
					out.println("\n WELCOME ADMIN!"); 
					while(rs.next()) {
		                out.println("\n" +rs.getString("username") + "\t" + rs.getString("phone") + "\t" + rs.getString("mail"));
					}
				} else {
				while(rs.next()) {
					String dbuser = rs.getString("username");
					String dbpass = rs.getString("password");
					String dbphone = rs.getString("phone");
	                String dbmail = rs.getString("mail");
					if(clientuser.equals(dbuser) && clientpass.equals(dbpass)) {
					out.println("\n WELCOME USER!"); 
	                out.println("\n" +dbuser + "\n" + dbphone + "\n" + dbmail);
	                break;
					}
				}
				}
		} 
		catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);		
		}
	}
}
