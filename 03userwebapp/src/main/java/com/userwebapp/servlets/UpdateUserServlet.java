package com.userwebapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/updateUserServlet")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    
	private Connection connection;

	   public void init(ServletConfig config) {
	    	try {
	    		ServletContext context=config.getServletContext();
	    		String dburl = context.getInitParameter("dburl");
	    		String dbuser=context.getInitParameter("dbuser");
	    		String dbpassword = context.getInitParameter("dbpassword");
	    		Class.forName("com.mysql.jdbc.Driver");
				connection= DriverManager.getConnection(dburl,dbuser,dbpassword);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	    }

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			
			String emailId = request.getParameter("emailId");
			String password = request.getParameter("password");
			response.setContentType("text/html");
			
			try(Statement statement = connection.createStatement();){
				

			int result =statement.executeUpdate(" update user set password = '" +password + "' where email ='"+ emailId + "'");
			PrintWriter out =response.getWriter();
			if(result>0) {
				out.println("<h1>Password updated</h1>");
			}else {
				out.println("<h1>Error updating user password</h1>");
			}
			out.println("<a href=\"index.html\">Home</a>");
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		}
		public void destroy() {
			try {
				if(connection !=null) {
					connection.close();
				  }
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
