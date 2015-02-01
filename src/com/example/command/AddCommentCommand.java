package com.example.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.business.Recipe;
import com.example.business.User;
import com.example.service.Service;

public class AddCommentCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse repsonse){
		
		// In the original design model, the Service layer provided only
		// one function, addComment(), which encapsulated checkForOffense().
		// 
		// But as AddCommentCommand needed the result of checkForOffense(), 
		// the service layer now exposes both.
					
		Service userService = new Service();
		String forwardToJsp = "";	
		
		HttpSession session = request.getSession();
		
		// get the data to pass to the service functions
		int recipeID;
		int userid;
		String comment;
		recipeID = Integer.parseInt(request.getParameter("recipeID"));
		userid = ((User) session.getAttribute("user")).getId();
		comment = request.getParameter("comment");
		
		System.out.println(String.format("Adding comment. Recipe= %s, User= %s, Comment= %s", recipeID, userid, comment));
		
		// look for offensive words in the comment
		String offenseMessage = userService.checkForOffensive(comment);
		
		Recipe recipe;
		if (offenseMessage == "") { // add comment only if no offensive words
			recipe = userService.addComment(recipeID, userid, comment);
			session.setAttribute("offense", "");
		} else {
			recipe = userService.getRecipe(recipeID);
			session.setAttribute("offense", offenseMessage);
		}
							
		//If successful, store the session id for this client...

		String clientSessionId = session.getId();
		session.setAttribute("loggedSessionId", clientSessionId);
		// give the next page the recipe (including the new comment)
		session.setAttribute("recipe", recipe);

		forwardToJsp = "/ViewRecipe.jsp";

		return forwardToJsp;
	}

}
