package com.evolvingvision.trackyouritems.entity;

public class Category {
	private long categoryID;
	
	private String categoryName;
	
	public Category(long id,String name) {
		categoryID = id;
		categoryName = name;
	}

	public long getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(long categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
