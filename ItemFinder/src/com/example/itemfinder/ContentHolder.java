package com.example.itemfinder;

import android.content.Context;

public class ContentHolder {

	private static Database DS;
	
	public ContentHolder(Context context){
		DS = new Database(context);
		DS.open();

	}
	
	public static Database getDS(){
		return DS;
	}
}
