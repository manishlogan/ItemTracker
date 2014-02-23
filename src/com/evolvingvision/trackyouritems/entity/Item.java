package com.evolvingvision.trackyouritems.entity;

public class Item {
	private long itemID;
	
	private String itemName;
	
	public Item(long id,String name) {
		itemID = id;
		itemName = name;
	}

	public long getItemID() {
		return itemID;
	}

	public void setItemID(long itemID) {
		this.itemID = itemID;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
}
