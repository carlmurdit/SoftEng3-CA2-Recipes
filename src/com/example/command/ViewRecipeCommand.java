package com.example.command;

import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.example.business.Recipe;
import com.example.service.Service;

public class ViewRecipeCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse repsonse){
					
		Service userService = new Service();
		String forwardToJsp = "";	
				
		int recipeID = Integer.parseInt(request.getParameter("recipeID"));
		System.out.println("Recipe No "+recipeID+" requested.");
	
		//Make call to the 'Model' using the UserService class 
		Recipe recipe = userService.getRecipe(recipeID);
				
		//If successful, store the session id for this client...
		HttpSession session = request.getSession();
		String clientSessionId = session.getId();
		session.setAttribute("loggedSessionId", clientSessionId);
		
		// clear errors set when offensive comment submitted
		session.setAttribute("offense", "");		

		String datestring = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(recipe.getComments().get(0).getDatetime());
		System.out.println(datestring);
		
		session.setAttribute("recipe", recipe);

		forwardToJsp = "/ViewRecipe.jsp";

		return forwardToJsp;
	}

}
