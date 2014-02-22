package com.example.itemfinder;

import android.content.Context;

public class ContentHolder {

	private static DataSource DS;
	
	public ContentHolder(Context context){
		DS = new DataSource(context);
		DS.open();

	}
	
	public static DataSource getDS(){
		return DS;
	}
}
