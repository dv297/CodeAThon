package com.example.itemfinder;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;
import java.util.*;

public class ItemInfoActivity extends Activity {

	private static String item_name;
	private static TextView itemTextView,
					 locationTextView,
					 keywordsTextView;
					 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_info);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.item_info, menu);
		return true;
	}
	
	public static void stateItemSearchedFor(String _item){
		item_name = _item;
	}
	
	public static void setValues(String _name, String _location, String _keywords){
		itemTextView.setText(_name);
		locationTextView.setText(_location);
		keywordsTextView.setText(_keywords);
	}

}
