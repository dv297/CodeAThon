package com.boeing.itemfinder;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

/**
 * Based on code from tutorial
 * URL: http://www.vogella.com/articles/AndroidSQLite/article.html
 *
 */

public class Database {

	private SQLiteDatabase database;
	private DatabaseHelper database_helper;

	public Database(Context context) {
		database_helper = new DatabaseHelper(context);
	}

	public void open() throws SQLiteException {
		database = database_helper.getWritableDatabase();
	}

	public void close() {
		database_helper.close();
	}

	public boolean createItem(Item item) {
		ContentValues values = new ContentValues();
		values.put("name", item.getName());
		values.put("location", item.getLocation());
		values.put("keywords", item.getKeywords());
		return database.insert("items", null, values) == -1;
	}
	
	public boolean updateItem(String name, Item item) {
		ContentValues values = new ContentValues();
		values.put("name", item.getName());
		values.put("location", item.getLocation());
		values.put("keywords", item.getKeywords());
		return database.update("items", values, "name = ?", new String[]{name}) == -1;
	}

	public void deleteItem(String item) {
		database.delete("items", "name = ?", new String[]{item});
	}

	public void deleteItem(Item item) {
		deleteItem(item.getName());
	}
	
	public void clearItems() {
		database_helper.clear(database);
	}
	
	public ArrayList<Item> getAllItems() {
		ArrayList<Item> items = new ArrayList<Item>();
		
		Cursor cursor = database.rawQuery("SELECT * FROM items", null);
		if(cursor.moveToFirst()) {
			while(!cursor.isAfterLast()) {
				items.add(cursorToItem(cursor));
				cursor.moveToNext();
			}
			cursor.close();
		}
		
		return items;
	}
	
	public Item getItemByName(String name) {
		Item item = new Item();
		item.setName(name);
		Cursor cursor = database.rawQuery("SELECT * FROM items WHERE name = ?", new String[]{name});
		if(cursor.moveToFirst()) {
			return cursorToItem(cursor);
		}
		return null;
	}
	
	private Item cursorToItem(Cursor cursor) {
		Item item = new Item();
		item.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
		item.setLocation(cursor.getString(cursor.getColumnIndexOrThrow("location")));
		item.setKeywords(cursor.getString(cursor.getColumnIndexOrThrow("keywords")));
		return item;
	}
} 

