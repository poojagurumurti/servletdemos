package com.learneracademy.admin;

import java.io.IOException;


import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet("/AssignClassServlet")
public class AssignClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseUtil dbUtil;

	@Resource(name = "jdbc_database")
	private DataSource dataSource;

    
    public AssignClassServlet() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		
		super.init();

		try {
			dbUtil = new DatabaseUtil(dataSource);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sectionId = request.getParameter("sectionId");
		String subjectId = request.getParameter("subjectId");
		String teacherId = request.getParameter("teacherId");
		
		int section = Integer.parseInt(sectionId);
		int subject = Integer.parseInt(subjectId);
		int teacher = Integer.parseInt(teacherId);
		String time = request.getParameter("time");
		
		dbUtil.assignNewClass(section, teacher, subject, time);
		request.setAttribute("CLASSES_LIST", dbUtil.getClasses());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/classes-list.jsp");
		dispatcher.forward(request, response);
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
