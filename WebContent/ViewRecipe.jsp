<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="com.example.business.Recipe"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>View Recipe</title>
	<style type="text/css">
	body {
		background-color: #FFFFFA;
	}
	#offensemessage {
		color: red;
 	} 
	</style>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script>
	$(document).ready(function(){
		$("#commentsform").hide();
		$("#btnshowcommentform").click(function(){
			$("#btnshowcommentform").hide();
			$("#commentsform").show();
		});
		$("#btncancel").click(function(e){
			$("#btnshowcommentform").show();
			$("#commentsform").hide();	
			e.preventDefault(e);
		});
	});
	</script>
</head>

<body>
	<div id="recipearea">
	Servlet forwarded to me... ViewRecipe.jsp
	<br><br>
	<c:set var="user" value="${sessionScope.user}" />
	<b>Hello <c:out value="${user.firstName}" />.</b>
	<div id="offensemessage"><c:out value="${offense}" /><br></div>
	Here is the recipe:
	<br />	<br />
	<h2>
		<c:out value="${recipe.getName()}" />
	</h2>
	<br />	<br />
	
	<c:out value="${recipe.getIntro()}" />	
	Rating:&nbsp; <c:out value="${recipe.getAvgRating()}" /> /5
	
	<br />	<br />
	<img src="<c:out value="${recipe.getImgurl()}"/>" />
	<br />
	<br />	<c:out value="${recipe.getMethod()}" />

	<br />	<br />
	<h3>Ingredients:</h3>
	<br />
	<c:forEach items="${recipe.getIngredients()}" var="current">
		<c:out value="${current.getQty()}" />&nbsp;
		<c:out value="${current.getUom()}" />&nbsp;
		<c:out value="${current.getIngredient()}" />
		<br />
	</c:forEach>
	<br />	<br />

	<br />	<br />
	
	</div>
	
	<hr>
	
	<div id="commentslist">
		<h3>Comments:</h3>
	
	<div id="commentarea">
		<button type="button" id="btnshowcommentform">Add Comment</button>
		<form id="commentsform" action="FrontController" method="post">
			Your comment:
			<br>
			<textarea rows="4" cols="50" name="comment" maxlength="1000"></textarea>
			<br>
			<input type="hidden" name="action" value="AddComment" /> 
			<input type="hidden" name="recipeID" value="${recipe.getRecipeID()}" />
			<a id="btncancel" href="#commentarea" >Cancel</a>
			<input type="submit" value="Submit" />
		</form>
	</div>
	
	<br />

	<c:forEach items="${recipe.getComments()}" var="current">
		<em><c:out value="${current.getUsername()}" />&nbsp;said
			at:&nbsp;<c:out value="${current.getDatetimeString()}" /></em>
		<br />
		<c:out value="${current.getComment()}" />
		<br><br>
	</c:forEach>
	<br />	<br />
	</div>
	
	<hr>
	<a href="loginSuccess.jsp">Main Menu</a>

</body>
</html>