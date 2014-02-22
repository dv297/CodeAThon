package com.example.itemfinder;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {

	private String item_name;
	private String item_location;
	private String keywords;
	
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
	
	public boolean equals(Item a){
		boolean name = a.item_name.equals(this.item_name);
		boolean location = a.item_location.equals(this.item_location);
		boolean keywords = a.keywords.equals(this.keywords);
		return name && location && keywords;
	}

	//Parcelable methods
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(item_name);
		dest.writeString(item_location);
		dest.writeString(keywords);
	}
	
	public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
    
    private Item(Parcel in) {
    	item_name = in.readString();
    	item_location = in.readString();
    	keywords = in.readString();
    }
    
    public String toString(){
    	return item_name + " " + item_location + " " + keywords;
    }
}
