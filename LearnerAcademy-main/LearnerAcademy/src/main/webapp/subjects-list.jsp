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
				<h3>Subjects</h3>
			</div>
		</div>
		
		<div id="container">
			<table>
				<tr>
					
					<th>Subject </th>
					<th>Subject Code</th>					
				
				</tr>
				
				<c:forEach var="tempSubjects" items="${SUBJECTS_LIST}">
				
				<tr>
					<td>${tempSubjects.name}</td>
					<td>${tempSubjects.shortcut}</td>
				</tr>
				
				</c:forEach>
			</table>
		</div>
		
	</div>
</body>
</html>