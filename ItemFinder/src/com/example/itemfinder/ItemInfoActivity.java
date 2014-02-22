package com.example.itemfinder;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class ItemInfoActivity extends Activity {

	private Item item;
					 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_info);
		Bundle data = getIntent().getExtras();
		item = (Item) data.getParcelable("item");
		
		TextView itemTextView = (TextView) findViewById(R.id.ItemTextView);
		TextView locationTextView = (TextView) findViewById(R.id.LocationTextView);
		TextView keywordsTextView = (TextView) findViewById(R.id.KeywordsTextView);
		
		String name = item.getName();
		String location = item.getLocation();
		String keywords = item.getKeywords();
		
		itemTextView.setText(name);
		locationTextView.setText(location);
		keywordsTextView.setText(keywords);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.item_info, menu);
		return true;
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
		Tools.deleteItemDialog(item, ItemInfoActivity.this, new OnClickListener() {
			// Uses the Tools class, runs when the AlertDialog clicks on the positive button.
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				ContentHolder.getDS().deleteItem(item);
				finish();
			}
			
		});
	}

}
