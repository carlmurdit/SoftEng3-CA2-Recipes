package com.example.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.business.User;
import com.example.business.Recipe;
import com.example.dao.UserDao;
import com.example.exceptions.DaoException;

public class Service {

	UserDao dao = new UserDao();
	
	public User login(String username, String password){
		
		User u = null;
		try {			
			u = dao.findUserByUsernamePassword(username, password);
		} 
		catch (DaoException e) {
			e.printStackTrace();
		}
		return u;
		
	}
	
	public List<User> getAllUsers() {
		
		List<User> allUsers = null;
		try {
			allUsers = dao.getAllUsers();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return allUsers;
		
	}
	
	public List<Recipe> getAllRecipes() {
		
		List<Recipe> allRecipes = null;
		try {
			allRecipes = dao.getAllRecipes();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return allRecipes;
		
	}
	
	public Recipe getRecipe(int recipeID) {
		
		Recipe recipe = null;
		try {
			recipe = dao.getRecipe(recipeID);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return recipe;
		
	}
	
	public String checkForOffensive(String comment) {
		
		String msg = "";

		List<String> OffensiveWords = new ArrayList<String>();
		try {
			OffensiveWords = dao.checkForOffensive(comment);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		if (OffensiveWords.size() > 0) {
			msg = "Your comment contains the following offensive words:\n";
			for (String s : OffensiveWords) {
				msg += s + "\n";
			}
			msg = msg.trim()+".";
		}
		return msg;
	}
	
	public Recipe addComment(int recipeID, int userid, String comment) {
			
		try {
			dao.addComment(recipeID, userid, comment);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		// refresh recipe to include new comment
		return getRecipe(recipeID);
		
	}
	
}


