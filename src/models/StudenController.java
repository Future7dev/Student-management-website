package models;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;



/**
 * Servlet implementation class StudenController
 */
@WebServlet("/StudenController")
public class StudenController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/student_data_tracker")
	private DataSource dataSource;
	
	StudentHelper sh;
	@Override
	public void init() throws ServletException {
		
		super.init();
		try {
			sh=new StudentHelper(dataSource);
			sh.initLastId();
		}
		catch(Exception es) {
			es.printStackTrace();
		}
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudenController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String theCommand=request.getParameter("command");
			switch(theCommand) {
			case null:listStudents(request,response);
						break;
			case "ADD":	sh.initLastId();
						addStudents(request,response);
						break;
			case "LOAD":loadStudent(request,response);
						break;
			case "UPDATE":updateStudent(request,response);
						break;
			case "DELETE":deleteStudent(request,response);
						break;
			case "LOGIN":loginTeacher(request,response);
						break;
			case "SIGNUP":signupTeacher(request,response);
						break;
			default:
				break;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void signupTeacher(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		int password=Integer.parseInt(request.getParameter("passw"));
		String user=sh.saveDetails(fname,lname,password);
		request.setAttribute("usernme", user);
		RequestDispatcher dispatcher =request.getRequestDispatcher("/show.jsp");
		dispatcher.forward(request, response);
		
	}

	private void loginTeacher(HttpServletRequest request, HttpServletResponse response)throws Exception {
		String username=request.getParameter("username");
		int password=Integer.parseInt(request.getParameter("password"));
		if(sh.getLogin(username,password)) {
			listStudents(request,response);
		}
		else {
			RequestDispatcher dispatcher=request.getRequestDispatcher("/wrong.html");
			dispatcher.forward(request, response);
		}
		
		
	}

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response)throws Exception {
		int id=Integer.parseInt(request.getParameter("studentId"));
		
		sh.deleteStudent(id);
		listStudents(request,response);
	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String firstname=request.getParameter("firstname");
		String lastname=request.getParameter("lastname");
		String email=request.getParameter("email");
		int id=Integer.parseInt(request.getParameter("studentId"));
		
		Students stu=new Students(firstname,lastname,email,id);
		
		sh.updateStudent(stu);
		
		listStudents(request,response);
		
		
	}

	private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id=request.getParameter("studentId");
		Students stu=sh.getData(id);
		request.setAttribute("studentData",stu);
		RequestDispatcher dispatcher=request.getRequestDispatcher("/update_student.jsp");
		dispatcher.forward(request, response);
		
	}

	private void addStudents(HttpServletRequest request, HttpServletResponse response) {
		String firstname=request.getParameter("firstname");
		String lastname=request.getParameter("lastname");
		String email=request.getParameter("email");
		Students new_stu=new Students(firstname,lastname,email);
		try {
		sh.addStudent(new_stu);
		listStudents(request,response);
	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void listStudents(HttpServletRequest request, HttpServletResponse response)throws Exception {
		ArrayList<Students> al =sh.getStudents();
		request.setAttribute("Data", al);
		RequestDispatcher dispatcher =request.getRequestDispatcher("/student_view.jsp");
		dispatcher.forward(request, response);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
