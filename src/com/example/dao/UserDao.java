package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.business.Comment;
import com.example.business.Ingredient;
import com.example.business.Recipe;
import com.example.business.User;
import com.example.exceptions.DaoException;


public class UserDao extends Dao {

    public User findUserByUsernamePassword(String uname, String pword) throws DaoException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User u = null;
        try {
            con = this.getConnection();
            
            String query = "SELECT * FROM USER WHERE USERNAME = ? AND PASSWORD = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, uname);
            ps.setString(2, pword);
            
            rs = ps.executeQuery();
            if (rs.next()) {
            	int userId = rs.getInt("ID");
                String username = rs.getString("USERNAME");
                String password = rs.getString("PASSWORD");
                String lastname = rs.getString("LAST_NAME");
                String firstname = rs.getString("FIRST_NAME");
                u = new User(userId, firstname, lastname, username, password);
            }
        } catch (SQLException e) {
            throw new DaoException("findUserByUsernamePassword " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("findUserByUsernamePassword" + e.getMessage());
            }
        }
        return u;     // u may be null 
    }
   
    public List<User> getAllUsers() throws DaoException {
    	
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<User> allUsers = new ArrayList<User>();
        
        try {
            con = this.getConnection();
            
            String query = "SELECT ID, USERNAME, LAST_NAME, FIRST_NAME FROM USER ORDER BY USERNAME;";
            ps = con.prepareStatement(query);
            
            rs = ps.executeQuery();
            while (rs.next()) {
            	int userId = rs.getInt("ID");
                String username = rs.getString("USERNAME");
                String lastname = rs.getString("LAST_NAME");
                String firstname = rs.getString("FIRST_NAME");
                allUsers.add(new User(userId, firstname, lastname, username, "********"));
                System.out.println("adding "+firstname+" "+lastname);
            }
        } catch (SQLException e) {
            throw new DaoException("getAllUsers " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("getAllUsers" + e.getMessage());
            }
        }
        return allUsers;     // may be null      
        
    }
    
    public List<Recipe> getAllRecipes() throws DaoException {
    	
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Recipe> allRecipes = new ArrayList<Recipe>();
        
        try {
            con = this.getConnection();
           
            String query = 
            		"SELECT avgRating, recipe.recipeid, name, intro, method, imgurl  "
    				+"	FROM recipe  "
    				+"		LEFT OUTER JOIN  "
    				+"			(SELECT recipeid, AVG(rating) AS avgRating  "
    				+"			 FROM rating GROUP BY recipeid "
    				+"			) as Rating "
    				+"			ON recipe.recipeid = Rating.recipeid ";
            System.out.println(query);
            ps = con.prepareStatement(query);
            
            rs = ps.executeQuery();
            while (rs.next()) {
                allRecipes.add(new Recipe(
                		rs.getInt("recipeid"), 
                		rs.getString("name"), 
                		rs.getString("intro"), 
                		rs.getString("method"), 
                		rs.getString("imgurl"),
                		rs.getFloat("avgRating")));
            }
        } catch (SQLException e) {
            throw new DaoException("getAllRecipes " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("getAllRecipes" + e.getMessage());
            }
        }
        return allRecipes;     // may be null      
        
    }
    
    public Recipe getRecipe(int recipeID) throws DaoException {
    	
    	Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Recipe recipe = null;
        
        try {
            con = this.getConnection();
           
            String query = 
            		"SELECT avgRating, recipe.recipeid, name, intro, method, imgurl  "
    				+"	FROM recipe  "
    				+"		LEFT OUTER JOIN  "
    				+"			(SELECT recipeid, AVG(rating) AS avgRating  "
    				+"			 FROM rating GROUP BY recipeid "
    				+"			) as Rating "
    				+"			ON recipe.recipeid = Rating.recipeid "
    				+"WHERE recipe.recipeid=?; ";
            ps = con.prepareStatement(query);
            ps.setInt(1, recipeID);
            
            rs = ps.executeQuery();
            if (rs.next()) {
            	recipe = new Recipe(
                		rs.getInt("recipeid"), 
                		rs.getString("name"), 
                		rs.getString("intro"), 
                		rs.getString("method"), 
                		rs.getString("imgurl"),
                		rs.getFloat("avgRating")
                		);
            }
            
            rs.close();
            ps.close();
            
            // get comments
            query = 
            		"SELECT  "
    				+"	Rating.avgRating, comment.recipeid, "
    				+"	comment.commentid, comment.comment, comment.datetime, "
    				+"	user.id, user.username "
    				+"FROM  "
    				+"	comment  "
    				+"		INNER JOIN "
    				+"			(SELECT recipeid, AVG(rating) as avgRating "
    				+"				FROM rating GROUP BY recipeid) as Rating "
    				+"			ON comment.recipeid = Rating.recipeid "
    				+"		INNER JOIN user  "
    				+"			ON user.id = comment.userid "
    				+"WHERE  "
    				+"	comment.recipeid = ? "
    				+"ORDER BY  "
    				+"	comment.datetime DESC; ";

            ps = con.prepareStatement(query);
            ps.setInt(1, recipeID);
            
            rs = ps.executeQuery();
            while (rs.next()) {
            	recipe.addComment(new Comment(
        			rs.getInt("commentid"), 
        			rs.getInt("recipeid"), 
        			rs.getString("comment"),
        			rs.getTimestamp("datetime"), 
        			rs.getInt("id"),
        			rs.getString("username")
        			));
            }
            
            rs.close();
            ps.close();
            
            // get ingredients
            query = 
            		"SELECT recipeid, ingredient, qty, uom "
            		+"FROM ingredient WHERE recipeid=? ORDER BY ingredient";

            ps = con.prepareStatement(query);
            ps.setInt(1, recipeID);
            
            rs = ps.executeQuery();
            while (rs.next()) {
            	recipe.addIngredient(new Ingredient(
        			rs.getInt("recipeid"), 
        			rs.getString("ingredient"),
        			rs.getFloat("qty"), 
        			rs.getString("uom")
        			));
            }

        } catch (SQLException e) {
            throw new DaoException("getRecipe " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("getRecipe" + e.getMessage());
            }
        }
        return recipe;     // may be null    
    	
    }
    
    public List<String> checkForOffensive(String phrase) throws DaoException {
    	
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<String> OffensiveWords = new ArrayList<String>();
        
        // convert phrase to arraylist
        List<String> phraseWords = new ArrayList<String> ();
        String[] phraseparts = phrase.split("\\s+|,\\s*|\\.\\s*"); //http://www.tutorialspoint.com/java/lang/string_split.htm
        for (String s : phraseparts) {
        	s = s.trim().toLowerCase();
        	if (s != "") phraseWords.add(s);
        }
        
        try {
            con = this.getConnection();
            
            String query = "SELECT OffensiveWord FROM OffensiveWords;";
            ps = con.prepareStatement(query);
            
            rs = ps.executeQuery();
            while (rs.next()) {
                String word = rs.getString("OffensiveWord").toLowerCase();
                //add to return list if found in phraseWords
                if (phraseWords.contains(word)) {
                	OffensiveWords.add(word);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("checkForOffensive " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("checkForOffensive" + e.getMessage());
            }
        }
        return OffensiveWords; 
        
    }
    
   public void addComment(int recipeID, int userid, String comment) throws DaoException {
    	
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = this.getConnection();
            
            String query = "insert into comment "
            +"(recipeid, userid, comment, datetime) values (?, ?, ?, NOW());";
    
            ps = con.prepareStatement(query);
            ps.setInt(1, recipeID);
            ps.setInt(2, userid);
            if (comment.length() > 1000) comment = comment.substring(0, 1000);
            ps.setString(3, comment);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            throw new DaoException("addComment " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("addComment" + e.getMessage());
            }
        }     
        
    }
    

}
