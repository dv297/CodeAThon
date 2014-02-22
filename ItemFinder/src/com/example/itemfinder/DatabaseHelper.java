package com.example.itemfinder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper{

	public static final int DATABASE_VERSION = 1;
	
	public static final String DATABASE_NAME = "records.db";
	
	public static final String COLUMN_ID = "_id"; // Used in all tables


	// Contains material for TABLE_STUDENTS
	public static final String TABLE_ITEMS = "items";
		public static final String COLUMN_ITEM_NAMES = "names";
		public static final String COLUMN_ITEM_LOCATIONS = "locations";
		public static final String COLUMN_ITEM_KEYWORDS = "keywords";

	public static final String[] ITEM_ALLCOLUMNS = new String[]{COLUMN_ID, COLUMN_ITEM_NAMES, COLUMN_ITEM_LOCATIONS, COLUMN_ITEM_KEYWORDS};
	
	private static final String ITEMS_TABLE_CREATE = "create table " 
		+ TABLE_ITEMS + "(" 
		+ COLUMN_ID	+ " integer primary key autoincrement, " 
		+ COLUMN_ITEM_NAMES + " text not null"
		+ COLUMN_ITEM_LOCATIONS + " text not null"
		+ COLUMN_ITEM_KEYWORDS + "text not null);"; 
	
	public DatabaseHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(ITEMS_TABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(DatabaseHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
				+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
		onCreate(db);
	}
}
