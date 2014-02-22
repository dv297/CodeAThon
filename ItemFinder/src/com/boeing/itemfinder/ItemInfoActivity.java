package com.boeing.itemfinder;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;


public class ItemInfoActivity extends Activity {

	private static Item item;
	private TextView itemTextView, locationTextView, keywordsTextView, keywordLabelTextView;
					 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_info);
		Bundle data = getIntent().getExtras();
		item = (Item) data.getParcelable("item");
		initValues();


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.item_info, menu);
		return true;
	}
	
	public void initValues(){
		itemTextView = (TextView) findViewById(R.id.ItemTextView);
		locationTextView = (TextView) findViewById(R.id.LocationTextView);
		keywordsTextView = (TextView) findViewById(R.id.KeywordsTextView);
		keywordLabelTextView = (TextView) findViewById(R.id.keywordLabelTextView);
		
		String name = item.getName();
		String location = item.getLocation();
		String keywords = item.getKeywords();
		
		itemTextView.setText(name);
		locationTextView.setText(location);
		keywordsTextView.setText(keywords);
		
		if(keywords.length()==0){
			keywordLabelTextView.setText("");
		} else {
			keywordLabelTextView.setText("  Keywords");
		}
	}
	
	public void editClick(View view){
		Intent intent = new Intent(view.getContext(), AddItemActivity.class);
		intent.putExtra("item", item);
		startActivity(intent);
	}
	
	public void finishClick(View view){
		finish();		
	}
	
	public void deleteClick(View view){
		Tools.deleteItemDialog(ItemInfoActivity.this, new OnClickListener() {
			// Uses the Tools class, runs when the AlertDialog clicks on the positive button.
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Tools.deleteItem(item);
				finish();
			}
			
		});
	}
	
	/**
	 * The results of the editing
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if(resultCode == RESULT_OK) {
	 		initValues();
	    } else if (resultCode == RESULT_CANCELED) {
	       //Write your code if there's no result
	    }
	}
	
	public static void setItem(Item i){
		item = i;
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		 initValues();
	}

}
