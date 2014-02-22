package com.example.itemfinder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	public static final int DATABASE_VERSION = 1;
	
	public static final String DATABASE_NAME = "items.db";
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table items(_id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE," +
				   "name TEXT NOT NULL UNIQUE, " + 
				   "location TEXT NOT NULL, " +
				   "keywords TEXT NOT NULL" +
				   ");");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(DatabaseHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
				+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS items;");
		onCreate(db);
	}
}
