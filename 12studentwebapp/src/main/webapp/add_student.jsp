<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User List</title>
</head>
<body>
<form action="addstudent" method="post">

	<table>
		<tr> 
			<td>First Name</td>
			<td> <input name="firstName" type="text"/></td>
		</tr>
		
		<tr> 
			<td>Last Name</td>
			<td><input name="lastName"   type="text"/></td>
		</tr>
		
		<tr> 
			<td>Email Id</td>
			<td><input name="emailId"   type="text"/></td>
		</tr>
		<tr> 
			<td></td>
			<td><input type="submit" value="Save" /></td>
		</tr>
	</table>	
	</form>
	<a href="index.html">Back to list</a>

	
</body>
</html>