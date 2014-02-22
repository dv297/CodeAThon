package com.example.itemfinder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class Tools {
	
	public static void deleteItemDialog(Context c, OnClickListener yes){
		//TODO: i18n string
		AlertDialog.Builder builder = new AlertDialog.Builder(c);
		builder.setMessage("Are you sure you wish to delete this item?");
		builder.setPositiveButton("Yes", yes);
		builder.setNegativeButton("No", null);
		builder.show();
	}
	
	//These functions modify both the database and the list.
	public static boolean addItem(Item item) {
		if(ContentHolder.getDS().createItem(item)) {
			return true;
		}
		MainActivity.activity.adapter.add(item);
		return false;
	}
	
	public static boolean updateItem(String name, Item item) {
		if(ContentHolder.getDS().updateItem(name, item)) {
			return true;
		}
		MainActivity.activity.adapter.update(name, item);
		return false;
	}
	
	public static void deleteItem(String name) {
		ContentHolder.getDS().deleteItem(name);
		MainActivity.activity.adapter.remove(name);
	}
	
	public static void deleteItem(Item item) {
		ContentHolder.getDS().deleteItem(item);
		MainActivity.activity.adapter.remove(item.getName());
	}
	
	public static void clearItems() {
		ContentHolder.getDS().clearItems();
		MainActivity.activity.adapter.clear();
	}
}
