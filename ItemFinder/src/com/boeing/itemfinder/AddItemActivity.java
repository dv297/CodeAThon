package com.boeing.itemfinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddItemActivity extends Activity {

	private boolean ready_to_submit = false; // Determines whether or not all of the forms are completed with correct values.
	private boolean beingEdited;
	private Item item;
	
	private EditText addItemEditText, addLocationEditText, addKeywordEditText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_item);
		
		Bundle data = getIntent().getExtras();
		item = (Item) data.getParcelable("item");
		
		addItemEditText = (EditText) findViewById(R.id.ItemEditText);
		addLocationEditText = (EditText) findViewById(R.id.LocationEditText);
		addKeywordEditText = (EditText) findViewById(R.id.KeywordsEditText);
		
		Button submitButton = (Button)findViewById(R.id.addItemButton);
		
		if(item!=null){
			addItemEditText.setText(item.getName());
			addLocationEditText.setText(item.getLocation());
			addKeywordEditText.setText(item.getKeywords());
			beingEdited = true;
			
			submitButton.setText("Update Item");
		} else {
			submitButton.setText("Add Item");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.add_item, menu);
		return true;
	}
	
	public void addItemSubmissionClick(View view){
		String item_name = addItemEditText.getText().toString();
		String item_location = addLocationEditText.getText().toString();
		String keywords = addKeywordEditText.getText().toString();
		
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
			if(beingEdited){
				// True if you can't add the item in
				if(Tools.updateItem(item.getName(), new Item(item_name, item_location, keywords))){
					// Need to add TOAST
				}
				else{
					Item updated_item = new Item(item_name, item_location, keywords);
					Tools.updateItem(item.getName(), updated_item);
					ItemInfoActivity.setItem(updated_item);
					setResult(RESULT_OK);
					
					finish();
				}
			}
			else{
				//TODO check return value
				Tools.addItem(new Item(item_name, item_location, keywords));
				setResult(RESULT_OK);
				finish();
			}
		}
	}
	
	public void returnClick(View view){
		finish();
	}

}
