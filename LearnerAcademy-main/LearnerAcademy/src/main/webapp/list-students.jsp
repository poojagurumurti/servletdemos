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
				<h3>List of Students </h3>
			</div>
		</div>
		
		<div id="container">
			<table>
				<tr>
					
					<th>First Name</th>
					<th>LastName</th>
					<th>Age</th>
					
				
				</tr>
				
				<c:forEach var="tempStudent" items="${STUDENT_LIST}">
				
				<tr>
					<td>${tempStudent.fname}</td>
					<td>${tempStudent.lname}</td>
					<td>${tempStudent.age}</td>
				</tr>
				
				</c:forEach>
			</table>
		</div>
		
	</div>
</body>
</html>