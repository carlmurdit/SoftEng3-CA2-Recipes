package com.example.business;

import java.io.Serializable;

public class Ingredient implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int recipeid;
	private String ingredient;
	private float qty;
	private String uom;
	
	public Ingredient(int recipeid, String ingredient, float qty, String uom) {
		this.recipeid = recipeid;
		this.ingredient = ingredient;
		this.qty = qty;
		this.uom = uom;
	}
	
	public int getRecipeid() {
		return recipeid;
	}
	public void setRecipeid(int recipeid) {
		this.recipeid = recipeid;
	}
	public String getIngredient() {
		return ingredient;
	}
	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}
	public float getQty() {
		return qty;
	}
	public void setQty(float qty) {
		this.qty = qty;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	
	public void toMetric() {
		//todo
	}

}
