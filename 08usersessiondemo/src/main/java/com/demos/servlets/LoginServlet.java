package com.demos.servlets;

import java.io.IOException;




import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.net.HttpCookie;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;




@WebServlet("/loginservlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Connection connection;
	//Compile once ,execute many times
	private PreparedStatement preparedStatement;
	
   
    public void init(ServletConfig config) {
    	try {
    		ServletContext context=config.getServletContext();
    		String dburl = context.getInitParameter("dburl");
    		String dbuser=context.getInitParameter("dbuser");
    		String dbpassword = context.getInitParameter("dbpassword");
    		Class.forName("com.mysql.jdbc.Driver");
			connection= DriverManager.getConnection(dburl,dbuser,dbpassword);
			preparedStatement=connection.prepareStatement("select * from user where email=? and password=?");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
	
		
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		
		if(!isValidInput(username,false) || !isValidInput(password,false)  ) {
			out.println("<h1>Pls. Enter the valid input</h1>");
			 return;
		}
		
		try {
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet resultSet = null;
			
			boolean result=preparedStatement.execute();
			if(result)
				resultSet=preparedStatement.getResultSet();
			if(resultSet.next()) {
				//now navigate to the home page
				System.out.println("User successfully logged in. Navigating to home page");
				HttpSession session = request.getSession();
//				session.setMaxInactiveInterval(60);
				session.setAttribute("username",username);
				response.addCookie( new Cookie("favouritecolor","Orange"));
				response.addCookie( new Cookie("token","!124%RF2992"));
				RequestDispatcher rd =request.getRequestDispatcher("home");
//				rd.include(request, response);
				rd.forward(request, response);
			}else {
				//navigate to login.html
				out.println("<p>User not found</p>");
				RequestDispatcher rd =request.getRequestDispatcher("Login.html");
				rd.include(request, response);
				
			}
			
		}catch (SQLException e) {
			out.println("Product Not created.Error occured.Error message ="+e.getMessage());
			e.printStackTrace();
		}
	  }
	
	
	
	private boolean isValidInput(String inputvalue,boolean isNumber)
	{
		if(inputvalue==null||inputvalue.isBlank()||inputvalue.isEmpty()) {
			return false;
		}else if(isNumber){
			try {
				Integer.parseInt(inputvalue);
				return true;
				}catch(NumberFormatException e) {
					return false;}
			}else {
				return true; 
				}
		}
	 

	public void destroy() {
		try {
			if(connection !=null) {
				connection.close();
			  }
			 if(preparedStatement!=null) {
				 preparedStatement.close();
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

