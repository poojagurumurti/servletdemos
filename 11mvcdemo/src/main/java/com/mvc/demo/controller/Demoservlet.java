package com.mvc.demo.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.demo.utils.UserDataUtil;


@WebServlet("/mvcdemo")
public class Demoservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		String [] students= {"Pooja","Tota","Trigun","Kavya"};
		request.setAttribute("user_list", UserDataUtil.getUsers());
		
		RequestDispatcher dispatcher= request.getRequestDispatcher("/view_users.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
