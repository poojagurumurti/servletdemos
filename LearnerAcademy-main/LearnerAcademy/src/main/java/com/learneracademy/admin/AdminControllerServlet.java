package com.learneracademy.admin;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.learneracademy.models.Student;
import com.learneracademy.models.Subject;
import com.learneracademy.models.Teacher;
import com.learneracademy.models.Class;

@WebServlet("/AdminControllerServlet")
public class AdminControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DatabaseUtil dbUtil;

	@Resource(name = "jdbc_database")
	private DataSource dataSource;

	public void init() throws ServletException {
		super.init();

		try {
			dbUtil = new DatabaseUtil(dataSource);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AdminControllerServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String command = request.getParameter("command");

			if (command == null) {
				command = "CLASSES";
			}

			if (!getCookies(request, response) && (!command.equals("LOGIN"))) {
				response.sendRedirect("/login.jsp");
			} else {
				switch (command) {
				case "STUDENTS":
					studentsList(request, response);
					break;
				case "TEACHERS":
					teachersList(request, response);
					break;
				case "SUBJECTS":
					subjectsList(request, response);
					break;
				case "CLASSES":
					classesList(request, response);
					break;
				case "ST_LIST":
					classStudentsList(request, response);
					break;
				case "LOGIN":
					login(request, response);
					break;
				
				default:
					classesList(request, response);

				}
			}

		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	

	private void studentsList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Student> students = dbUtil.getStudents();

		request.setAttribute("STUDENT_LIST", students);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
		dispatcher.forward(request, response);
	}

	private void teachersList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Teacher> teachers = dbUtil.getTeachers();

		request.setAttribute("TEACHER_LIST", teachers);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/teachers-list.jsp");
		dispatcher.forward(request, response);
	}

	private void subjectsList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Subject> subjects = dbUtil.getSubjects();

		request.setAttribute("SUBJECTS_LIST", subjects);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/subjects-list.jsp");
		dispatcher.forward(request, response);
	}

	private void classesList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Class> classes = dbUtil.getClasses();

		request.setAttribute("CLASSES_LIST", classes);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/classes-list.jsp");
		dispatcher.forward(request, response);
	}

	private void classStudentsList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int classId = Integer.parseInt(request.getParameter("classId"));
		String section = request.getParameter("section");
		String subject = request.getParameter("subject");
		
		List<Student> students = dbUtil.loadClassStudents(classId);
		
		request.setAttribute("STUDENTS_LIST", students);
		request.setAttribute("SECTION", section);
		request.setAttribute("SUBJECT", subject);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/class-students.jsp");
		dispatcher.forward(request, response);
	}
	
	

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(username.toLowerCase().equals("admin") && password.toLowerCase().equals("admin")) {
			Cookie cookie = new Cookie(username,password);
			cookie.setMaxAge(86400);
			
			response.addCookie(cookie);
			classesList(request,response);
		}
		else
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}
	}

	private boolean getCookies(HttpServletRequest request, HttpServletResponse response) {
		boolean check = false;
		Cookie[] cookies  =request.getCookies();
		
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals("admin") && cookie.getValue().equals("admin")) {
				check = true;
				break;
			}
		}
		return check;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
