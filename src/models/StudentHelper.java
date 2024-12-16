package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

public class StudentHelper {
	public DataSource datasource;

	public StudentHelper(DataSource datasource) {
		super();
		this.datasource = datasource;
	}
	public ArrayList<Students> getStudents() throws Exception{
		ArrayList<Students> al=new ArrayList<>();
		Connection myConn=null;
		Statement myStmt=null;
		ResultSet myrst=null;
		try {
			myConn=datasource.getConnection();
			String sql="SELECT * FROM students";
			myStmt=myConn.createStatement();
			myrst=myStmt.executeQuery(sql);
			
			while(myrst.next()) {
				int id=myrst.getInt("id");
				String firstname=myrst.getString("firstname");
				String lastname=myrst.getString("lastname");
				String email=myrst.getString("email");
				Students s=new Students(firstname,lastname,email,id);
				al.add(s);
			}
			return al;
			
		}
		finally {
			close(myConn,myrst,myStmt);
		}
	}
	private void close(Connection myConn, ResultSet myRs, Statement stmt) {
		
		try {
			if(myConn!=null) {
				myConn.close();
			}
			if(myRs!=null) {
				myRs.close();
			}
			if(stmt!=null) {
				stmt.close();
			}
		}
		catch(Exception e) {
			
		}
		
	}
	
	public void addStudent(Students new_stu) throws Exception {
		Connection myCon=null;
		PreparedStatement myStmt=null;
		try {
			myCon=datasource.getConnection();
			String sql="INSERT INTO students(id,firstname,lastname,email) VALUES(?,?,?,?)";
			myStmt=myCon.prepareStatement(sql);
			myStmt.setInt(1, new_stu.id);
			myStmt.setString(2, new_stu.getFirstname());
			myStmt.setString(3, new_stu.getLastname());
			myStmt.setString(4, new_stu.getEmail());
			
			myStmt.execute();
			
		}
		finally {
			close(myCon,null,myStmt);
		}
		
	}
	public void initLastId() throws Exception {
	    Connection myConn = null;
	    Statement myStmt = null;
	    ResultSet myRs = null;
	    try {
	        myConn = datasource.getConnection();
	        String sql = "SELECT MAX(id) AS max_id FROM students";
	        myStmt = myConn.createStatement();
	        myRs = myStmt.executeQuery(sql);
	        if (myRs.next()) {
	            Students.lastId = myRs.getInt("max_id");
	        }
	    } finally {
	        close(myConn, myRs, myStmt);
	    }
	}
	
	
	public int LastId() throws Exception {
	    Connection myConn = null;
	    Statement myStmt = null;
	    ResultSet myRs = null;
	    int result=-1;
	    try {
	        myConn = datasource.getConnection();
	        String sql = "SELECT MAX(id) AS max_id FROM logdata";
	        myStmt = myConn.createStatement();
	        myRs = myStmt.executeQuery(sql);
	        if (myRs.next()) {
	           result= myRs.getInt("max_id");
	        }
	    } finally {
	        close(myConn, myRs, myStmt);
	    }
	    return result;
	}
	
	
	public Students getData(String id) throws Exception {
		Students thestu=null;
		Connection myConn=null;
		PreparedStatement myStmt=null;
		ResultSet rs=null;
		try {
			myConn=datasource.getConnection();
			int stuId=Integer.parseInt(id);
			String sql="SELECT * FROM students WHERE id=?";
			myStmt=myConn.prepareStatement(sql);
			myStmt.setInt(1, stuId);
			rs=myStmt.executeQuery();
			if(rs.next()) {
				String firstname=rs.getString("firstname");
				String lastname=rs.getString("lastname");
				String email=rs.getString("email");
				int idd=rs.getInt("id");
				
				thestu=new Students(firstname,lastname,email,idd);
				
				
			}
			
		}
		finally {
			close(myConn,rs,myStmt);
		}
		return thestu;
	}
	public void updateStudent(Students stu) throws Exception {
		Connection myConn=null;
		PreparedStatement myStmt=null;
		try {
			myConn=datasource.getConnection();
			String sql="UPDATE students SET firstname=?,lastname=?,email=? WHERE id=?";
			myStmt=myConn.prepareStatement(sql);
			myStmt.setString(1, stu.getFirstname());
			myStmt.setString(2, stu.getLastname());
			myStmt.setString(3, stu.getEmail());
			myStmt.setInt(4, stu.getId());
			
			myStmt.execute();
		}
		finally {
			close(myConn,null,myStmt);
		}
		
	}
	public void deleteStudent(int id)throws Exception {
		Connection myConn=null;
		PreparedStatement myStmt=null;
		try {
			myConn=datasource.getConnection();
			String sql="DELETE FROM students WHERE id=?";
			myStmt=myConn.prepareStatement(sql);
			myStmt.setInt(1, id);
			myStmt.execute();
		}
		finally {
			close(myConn,null,myStmt);
		}
		
	}
	public boolean getLogin(String username, int password) throws Exception {
		
		Connection myConn=null;
		PreparedStatement myStmt=null;
		ResultSet rs=null;
		int result=-1;
		try {
			myConn=datasource.getConnection();
			String sql="SELECT password FROM logdata WHERE userid=?";
			myStmt=myConn.prepareStatement(sql);
			myStmt.setString(1, username);
			rs=myStmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt("password");
			}
			
		}
		finally {
			close(myConn,rs,myStmt);
		}
		if(result==password) {
			return true;
			
		}
		return false;
		
	}
	public String saveDetails(String fname, String lname, int password)throws Exception {
			int lastid=LastId()+1;
			String idd=Integer.toString(lastid);
			String usernme=fname+lname+idd;
			Connection myCon=null;
			PreparedStatement myStmt=null;
			try {
				myCon=datasource.getConnection();
				String sql="INSERT INTO logdata(id,firstname,lastname,userid,password) VALUES(?,?,?,?,?)";
				myStmt=myCon.prepareStatement(sql);
				myStmt.setInt(1, lastid);
				myStmt.setString(2, fname);
				myStmt.setString(3, lname);
				myStmt.setString(4, usernme);
				myStmt.setInt(5, password);
				
				
				myStmt.execute();
				
			}
			finally {
				close(myCon,null,myStmt);
			}
			
		return usernme;
	}


}
