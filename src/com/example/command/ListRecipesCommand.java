package com.example.command;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.business.Recipe;
import com.example.service.Service;

public class ListRecipesCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse repsonse){
					
		Service userService = new Service();
		String forwardToJsp = "";		
	
		//Make call to the 'Model' using the UserServive class to login...
		List<Recipe> allRecipes = userService.getAllRecipes();
		
		System.out.println("allRecipes.size = "+allRecipes.size());
		for (Recipe u : allRecipes) {
			System.out.println("ID = "+u.getRecipeID()+" "+"Name = "+u.getName()+" "+u.getImgurl());
		}
		
		//If successful, store the session id for this client...
		HttpSession session = request.getSession();
		String clientSessionId = session.getId();
		session.setAttribute("loggedSessionId", clientSessionId);
		
		session.setAttribute("allRecipes", allRecipes);

		forwardToJsp = "/listRecipes.jsp";

		return forwardToJsp;
	}

}
