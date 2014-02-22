package com.example.itemfinder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	public static final int DATABASE_VERSION = 1;
	
	public DatabaseHelper(Context context) {
		super(context, "items.db", null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE items(_id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE," +
				   "name TEXT NOT NULL UNIQUE, " + 
				   "location TEXT NOT NULL, " +
				   "keywords TEXT NOT NULL" +
				   "image TEXT" +
				   ");");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
	
	public void clear(SQLiteDatabase db) {
		db.execSQL("DROP TABLE IF EXISTS items;");
		onCreate(db);
	}
}
