<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="java.util.List"%>
<%@ page import="com.example.business.User"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<body>
	Servlet forwarded to me... ListUsers.jsp
	
	<br/><br/>
	
	<c:set var="user" value="${sessionScope.user}"/>
	<b>Hello <c:out value="${user.firstName}"/>.</b>
	<b>Here are the users:</b>
	
	<br/><br/>
	

	<% 
// 		List<User> users;
// 		users = (List)(request.getSession().getAttribute("allUsers"));
	%>

 
	<table>
		<c:forEach items="${allUsers}" var="current">
			<tr>
				<td><c:out value="${current.getFirstName()}" /></td>
				<td><c:out value="${current.getLastName()}" /></td>
			</tr>
		</c:forEach>
	</table>


	<a href="loginSuccess.jsp">Return</a>	
	 
		

				</body>

</html>