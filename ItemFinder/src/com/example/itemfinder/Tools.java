package com.example.itemfinder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class Tools {
	
	public static void deleteItemDialog(final Item i, Context c, OnClickListener yes){
		//TODO: i18n string
		AlertDialog.Builder builder = new AlertDialog.Builder(c);
		builder.setMessage("Are you sure you wish to delete this item?");
		builder.setPositiveButton("Yes", yes);
		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int id) {}
		});
		builder.show();
	}
}
