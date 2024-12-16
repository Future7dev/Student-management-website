package models;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class test
 */
@WebServlet("/test")
public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/student_data_tracker")
	private DataSource dataSource;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/plain");
		
		Connection myconn=null;
		Statement myStmt=null;
		ResultSet myRs=null;
		
		try {
			myconn=dataSource.getConnection();
			String sql="SELECT * FROM students";
			myStmt=myconn.createStatement();
			myRs=myStmt.executeQuery(sql);
			
			while(myRs.next()) {
				String nme=myRs.getString("firstname");
				out.println(nme);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
