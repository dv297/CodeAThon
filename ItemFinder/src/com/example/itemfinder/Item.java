package com.example.itemfinder;

public class Item {

	private String item_name;
	private String item_location;
	private long id;
	
	public Item(){
		
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
	
	public long getId(){
		return id;
	}
	
	public void setId(long _id){
		id = _id;
	}
}
