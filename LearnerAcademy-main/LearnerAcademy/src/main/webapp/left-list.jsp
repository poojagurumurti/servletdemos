<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class=sidenav>
	<h3 id="logo">
		LearnerAcademy Administrative Portal
	</h3>
	
	<c:url var="classesLink" value="AdminControllerServlet">
		<c:param name="command" value="CLASSES"></c:param>
	</c:url>
	
	<c:url var="subjectsLink" value="AdminControllerServlet">
		<c:param name="command" value="SUBJECTS"></c:param>
	</c:url>
	
	<c:url var="teachersLink" value="AdminControllerServlet">
		<c:param name="command" value="TEACHERS"></c:param>
	</c:url>
	
	<c:url var="studentsLink" value="AdminControllerServlet">
		<c:param name="command" value="STUDENTS"></c:param>
	</c:url>
	
	<c:url var="assignLink" value="AdminControllerServlet">
		<c:param name="command" value="ASSIGN"></c:param>
	</c:url>
	
	
	
	<a class="bar-item" href="${classesLink}">Classes</a>
	
	<a class="bar-item" href="${subjectsLink}">Subjects</a>
	
	<a class="bar-item" href="${teachersLink}">Teachers</a>
	
	<a class="bar-item" href="${studentsLink}">Students</a>
	
	<a class="bar-item" href="assign-class-form.jsp">Assign Class</a>
	
	<a class="bar-item" href="login.jsp">Logout</a>
	
</div>


