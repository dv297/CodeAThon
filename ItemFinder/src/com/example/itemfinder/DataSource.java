package com.example.itemfinder;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

/**
 * With code taken from Lars Vogel
 * URL: http://www.vogella.com/articles/AndroidSQLite/article.html
 *
 */

public class DataSource {

	private SQLiteDatabase database;
	private DatabaseHelper database_helper;

	public DataSource(Context context){
		database_helper = new DatabaseHelper(context);
	}

	public void open() throws SQLiteException{
		database = database_helper.getWritableDatabase();
	}

	public void close(){
		database_helper.close();
	}

	public Item createItem(Item Item){
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.COLUMN_ITEM_NAMES, Item.getName());
		long insertId = database.insert(DatabaseHelper.TABLE_ITEMS, null, values);
		Cursor cursor = database.query(DatabaseHelper.TABLE_ITEMS, DatabaseHelper.ITEM_ALLCOLUMNS, DatabaseHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
		cursor.moveToFirst();
		Item newItem = cursorToItem(cursor);
		cursor.close();
		return newItem;
	}
	
	public void deleteItem(Item Item){
		long id = Item.getId();
		System.out.println("Item deleted with id: " + id);
		database.delete(DatabaseHelper.TABLE_ITEMS, DatabaseHelper.COLUMN_ID + " = " + id, null);
	}
	
	public List<Item> getAllItems(){
		List<Item> Items = new ArrayList<Item>();
		
		Cursor cursor = database.query(DatabaseHelper.TABLE_ITEMS, DatabaseHelper.ITEM_ALLCOLUMNS, null, null, null, null, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			Item Item = cursorToItem(cursor);
			Items.add(Item);
			cursor.moveToNext();
		}
		cursor.close();
		return Items;
	}
	
	public Item getItemByName(String name){
		Item item = new Item();
		item.setName(name);
		Cursor cursor = database.query(DatabaseHelper.TABLE_ITEMS, DatabaseHelper.ITEM_ALLCOLUMNS, null, null, null, null, null);
		while(!cursor.isAfterLast()){
			Item compare_item = cursorToItem(cursor);
			if(item.getName().equals(compare_item.getName())){
				item = new Item(compare_item);
				break;
			}
			cursor.moveToNext();
		}
		return item;
	}
	
	private Item cursorToItem(Cursor cursor){
		Item item = new Item();
		item.setId(cursor.getLong(0));
		item.setName(cursor.getString(0));
		item.setLocation(cursor.getString(1));
		return item;
	}
	


} 

