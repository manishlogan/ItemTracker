package com.evolvingvision.trackyouritems.entity;

public class Person {
	private long personID;
	
	private String personName;

	public Person(long id,String name) {
		personID = id;
		personName = name;
	}
	
	public long getPersonID() {
		return personID;
	}

	public void setPersonID(long personID) {
		this.personID = personID;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}
}
