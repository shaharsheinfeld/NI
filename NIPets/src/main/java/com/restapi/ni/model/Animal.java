package com.restapi.ni.model;

public class Animal {
	
	private String type;
	private String name;
	private String breed;
	private int age;
	private String ownerName;
	private String photoLink;
	
	public Animal() {
	}
	
	public Animal(String type, String name, String breed, int age, String OwnerName, String photoLink ) {
		this.type = type;
		this.name = name;
		this.breed = breed;
		this.age = age;
		this.ownerName = OwnerName;
		this.photoLink = photoLink;
	}
	
	public String getType() { return this.type; }
    public void setType(String type) { this.type = type; }
    
	public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }
	
    public String getBreed() { return this.breed; }
    public void setBreed(String breed) { this.breed = breed; }
    
    public int getAge() { return this.age; }
    public void setAge(int age) { this.age = age; }
    
    public String getOwnerName() { return this.ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
    
    public String getPhotoLink() { return this.photoLink; }
    public void setPhotoLink(String photoLink) { this.photoLink = photoLink; }


}
