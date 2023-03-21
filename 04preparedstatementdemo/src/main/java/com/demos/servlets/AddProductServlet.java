package com.demos.servlets;

import java.io.IOException;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;




@WebServlet("/addproduct")
public class AddProductServlet extends HttpServlet {
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
			preparedStatement=connection.prepareStatement("insert into product values(?,?,?,?)");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String price = request.getParameter("price");
		
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		
		if(!isValidInput(id,true) || !isValidInput(name,false) || !isValidInput(description,false) ||  !isValidInput(price,true) ) {
			out.println("<h1>Pls. Enter the valid input</h1>");
			 return;
		}
		
		try {
			preparedStatement.setInt(1, Integer.parseInt(id));
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, description);
			preparedStatement.setInt(4, Integer.parseInt(price));
			int result=preparedStatement.executeUpdate();
			out.println("Product created. Result="+result);
			
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

