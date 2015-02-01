package com.example.business;

import java.io.Serializable;
import java.util.ArrayList;

public class Recipe implements Serializable {
	
		private static final long serialVersionUID = 1L;
		private int recipeid;
		private String name;
		private String intro;
		private String method;
		private String imgurl;
		private float avgRating = -1;
		private ArrayList<Comment> comments;
		private ArrayList<Ingredient> ingredients;

		public Recipe(int recipeid, String name, String intro, 
				String method, String imgurl, float avgRating) {
			this.recipeid = recipeid;
			this.name = name;
			this.intro = intro;
			this.method = method;
			this.imgurl = imgurl;
			this.avgRating = avgRating;
			comments = new ArrayList<Comment>();
			ingredients = new ArrayList<Ingredient>();
		}
		
		public void addComment(Comment comment) {
			comments.add(comment);
		}
		
		public void removeComment(Comment comment) {
			comments.remove(comment);
		}
		
		public ArrayList<Comment> getComments(){
			return this.comments;
		}
		
		public void addIngredient(Ingredient ingredient) {
			ingredients.add(ingredient);
		}
		
		public void removeIngredient(Ingredient ingredient) {
			ingredients.remove(ingredient);
		}
		
		public ArrayList<Ingredient> getIngredients(){
			return this.ingredients;
		}

		public int getRecipeID() {
			return recipeid;
		}

		public void setRecipeID(int recipeid) {
			this.recipeid = recipeid;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getIntro() {
			return intro;
		}

		public void setIntro(String intro) {
			this.intro = intro;
		}

		public String getMethod() {
			return method;
		}

		public void setMethod(String method) {
			this.method = method;
		}

		public String getImgurl() {
			return imgurl;
		}

		public void setImgurl(String imgurl) {
			this.imgurl = imgurl;
		}

		public float getAvgRating() {
			return avgRating;
		}

		public void setAvgRating(float avgRating) {
			this.avgRating = avgRating;
		}
		
		public void toMetric() {
			//todo
		}

}
