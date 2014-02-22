package com.example.itemfinder;

public class Item {

	private String item_name;
	private String item_location;
	private String keywords;
	private long id;
	
	public Item(){
		
	}
	
	public Item(Item a){
		item_name = a.item_name;
		item_location = a.item_location;
		keywords = a.keywords;
	}
	
	public Item(String _name, String _location){
		item_name = _name;
		item_location = _location;
		keywords = "";
	}
	
	public Item(String _name, String _location, String _keywords){
		item_name = _name;
		item_location = _location;
		keywords = _keywords;
	}
	
	public String getName(){
		return item_name;
	}
	
	public void setName(String _name){
		item_name = _name;
	}
	
	public String getLocation(){
		return item_location;
	}
	
	public void setLocation(String _item_location){
		item_location = _item_location;
	}
	
	public String getKeywords(){
		return keywords;
	}
	
	public void setKeywords(String _keywords){
		keywords = _keywords;
	}
	
	public long getId(){
		return id;
	}
	
	public void setId(long _id){
		id = _id;
	}
	
	public boolean equals(Item a){
		boolean name = a.item_name.equals(this.item_name);
		boolean location = a.item_location.equals(this.item_location);
		boolean keywords = a.keywords.equals(this.keywords);
		return name && location && keywords;
	}
}
