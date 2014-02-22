package com.example.itemfinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddItemActivity extends Activity {

	private boolean ready_to_submit = false; // Determines whether or not all of the forms are completed with correct values.

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.add_item, menu);
		return true;
	}
	
	public void addItemSubmissionClick(View view){
		EditText addItemEditText = (EditText) findViewById(R.id.addItemEditText);
		EditText addLocationEditText = (EditText) findViewById(R.id.addLocationEditText);
		EditText addKeywordsEditText = (EditText) findViewById(R.id.addKeywordsEditText);
		
		
		String item_name = addItemEditText.getText().toString();
		String item_location = addLocationEditText.getText().toString();
		String keywords = addKeywordsEditText.getText().toString();
		
		if(item_name.length() == 0 || item_location.length() == 0){
			ready_to_submit = false;
		}
		else{
			ready_to_submit = true;
		}
		
		if(!ready_to_submit){
			CharSequence text = "Please enter values into the appropriate boxes.";
			Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
			toast.show();
		}
		else{
			ContentHolder.getDS().createItem(new Item(item_name, item_location, keywords));
			finish();
		}
	}
	
	public void cameraButtonClick(){
		
	}

}
