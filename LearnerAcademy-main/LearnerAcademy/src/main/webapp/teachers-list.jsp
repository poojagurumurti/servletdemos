<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link type="text/css" rel="stylesheet" href="css/style2.css">
<title>Students of Class | LearnerAcademy</title>
</head>
<body>
	<div id="page">
		<jsp:include page="left-list.jsp" />
		
		<div id="wrapper">
			<div id = "header">
				<h3>List of Teachers </h3>
			</div>
		</div>
		
		<div id="container">
			<table>
				<tr>
					
					<th>FirstName</th>
					<th>LastName</th>
					<th>Age</th>
					
				
				</tr>
				
				<c:forEach var="tempTeachers" items="${TEACHER_LIST}">
				
				<tr>
					<td>${tempTeachers.fname}</td>
					<td>${tempTeachers.lname}</td>
					<td>${tempTeachers.age}</td>
				</tr>
				
				</c:forEach>
			</table>
		</div>
		
	</div>
</body>
</html>