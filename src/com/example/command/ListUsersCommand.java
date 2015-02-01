package com.example.command;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.business.User;
import com.example.service.Service;

public class ListUsersCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse repsonse){
					
		Service userService = new Service();
		String forwardToJsp = "";		
	
		//Make call to the 'Model' using the UserServive class to login...
		List<User> allUsers = userService.getAllUsers();
		
		System.out.println("allUsers.size = "+allUsers.size());
		for (User u : allUsers) {
			System.out.println("Name = "+u.getFirstName()+" "+u.getLastName());
		}
		
		//If successful, store the session id for this client...
		HttpSession session = request.getSession();
		String clientSessionId = session.getId();
		session.setAttribute("loggedSessionId", clientSessionId);
		
		session.setAttribute("allUsers", allUsers);

		forwardToJsp = "/listUsers.jsp";

		return forwardToJsp;
	}

}
