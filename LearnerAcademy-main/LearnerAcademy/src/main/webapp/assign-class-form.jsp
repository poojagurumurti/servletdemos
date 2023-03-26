<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link type="text/css" rel="stylesheet" href="css/style2.css">
<link type="text/css" rel="stylesheet" href="css/login.css"> 
<title>Assign Class | LearnerAcademy</title>
</head>
<body>
	<div >
		<jsp:include page="left-list.jsp" />

		<div  id="page">
			<h2 id="logo">LearnerAcademy</h2>
		</div>
		

		<div id="maindiv">
		<div>
			<h4 id="logo">Assign Class</h4>
		</div>
			<form action="AssignClassServlet" method="POST">
				<div class="container">
			 <label>SectionId: </label> <br />
			  <input type="text" placeholder="Enter SectionId" name="sectionId" required> <br />
			 <label>TeacherId :</label> <br /> 
			 <input type="text" placeholder="Enter TeacherId" name="teacherId" required> <br />
			 <label>SubjectId :</label> <br /> 
			 <input type="text" placeholder="Enter SubjectId" name="subjectId" required> <br />
			 <label>Time :</label> <br /> 
			 <input type="text" placeholder="Enter Time" name="time"  required> <br >
			  <button type="submit" >Assign</button><br /> 

  				</div>
			</form>

		</div>
		<br><br>
		<a href="classes-list.jsp">Back to list</a>

	</div>
</body>
</html>