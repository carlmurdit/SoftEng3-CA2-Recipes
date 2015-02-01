<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="java.util.List"%>
<%@ page import="com.example.business.Recipe"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Recipe Listing</title>
<style type="text/css">
	body {background-color: #FFFFFA;}
</style>

</head>

<body>
	Servlet forwarded to me... ListRecipes.jsp

	<br />
	<br />

	<c:set var="user" value="${sessionScope.user}" />
	<b>Hello <c:out value="${user.firstName}" />.
	</b>
	<b>Here are the recipes:</b>

	<br />
	<br />

<%--
	<% 
		List<Recipe> recipes;
		recipes = (List)(request.getSession().getAttribute("allRecipes"));
	%>
--%>

	<c:forEach items="${allRecipes}" var="current">
		<h2><c:out value="${current.getName()}" /></h2>
		<br />
		Rating:&nbsp;<c:out value="${current.getAvgRating()}" />/5
		<br /><br />
		<form action="FrontController" method="post">
			<input type="hidden" name="action" value="ViewRecipe" />
			<input type="hidden" name="recipeID" value="${current.getRecipeID()}" />
			<input type="image" src="${current.getImgurl()}" border="0">
		</form>
		<br /><br />
		<c:out value="${current.getIntro()}" />
		<br /><br />
		<hr>
	</c:forEach>

	<a href="loginSuccess.jsp">Main Menu</a>

</body>

</html>