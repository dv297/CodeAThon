package com.example.itemfinder;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {

	private ItemAdapter adapter; // Used to search for class list.
	private ListView itemListView;
	@SuppressWarnings("unused")
	private ContentHolder content_holder; // This is necessary, DO NOT DELETE

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		content_holder = new ContentHolder(getBaseContext());

        initList();
        
        //Setup search
        ListView list = (ListView)findViewById(R.id.itemListView);
		registerForContextMenu(list);
        EditText search = (EditText)findViewById(R.id.searchEditText);
        search.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable e) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				MainActivity.this.adapter.getFilter().filter(s);
			}
        	
        });
    }

    @Override
	protected void onPause() {
		super.onPause();
		
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		 initList();
	}


	@Override
	protected void onResume() {
		super.onResume();
		 initList();
	}

	//Context menu for holding a list item
	@Override
	public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
		if(info.position > 0) {
			menu.setHeaderTitle(adapter.getItem(info.position).getName());
			super.onCreateContextMenu(menu, view, menuInfo);
		    getMenuInflater().inflate(R.menu.context_menu, menu);
		}
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem menuItem) {
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuItem.getMenuInfo();
		switch(menuItem.getItemId()) {
		case R.id.context_edit:
			Intent intent = new Intent(info.targetView.getContext(), ItemInfoActivity.class);
			Item item = ContentHolder.getDS().getItemByName(adapter.getItem(info.position).getName());
			intent.putExtra("item", item);
	        startActivity(intent);
			break;
			
		case R.id.context_delete:
			System.out.println("delete");
			break;
		}
		return true;
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.action_about:
			AlertDialog.Builder dlg  = new AlertDialog.Builder(this);
			dlg.setTitle("Item Finder");
		    dlg.setMessage("This application was created for the first Boeing Code-a-Thon competition by:\n\n" +
		    			   "Mathew Velasquez\n" +
		    			   "Daniel Vu\n" +
		    			   "Tyler Wagner"
		    			   );
		    dlg.setPositiveButton("OK", null);
		    dlg.setCancelable(true);
		    dlg.create().show();
			break;
		}
		return true;
	}
    
	public void initList() {
		AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				if(position == 0) {
					Intent intent = new Intent(view.getContext(), AddItemActivity.class);
					intent.putExtra("name", ((EditText)findViewById(R.id.searchEditText)).getText());
					startActivity(intent);
				} else {
					Intent intent = new Intent(view.getContext(), ItemInfoActivity.class);
					Item item = ContentHolder.getDS().getItemByName((String)parent.getItemAtPosition(position));
					intent.putExtra("item", item);
			        startActivity(intent);
				}
			}
		};
		
		ContentHolder.getDS().createItem(new Item("toilet", "bathroom"));
		ContentHolder.getDS().createItem(new Item("sofa", "living room"));
		ContentHolder.getDS().createItem(new Item("zebra", "barn"));
		
		ArrayList<Item> items_list = ContentHolder.getDS().getAllItems();
		itemListView = (ListView) findViewById(R.id.itemListView);
		adapter = new ItemAdapter(this, items_list);
		itemListView.setAdapter(adapter);
		itemListView.setOnItemClickListener(clickListener);
	}
	
	public void addButtonClick(View view) {
		Intent intent = new Intent(view.getContext(), AddItemActivity.class);
		startActivity(intent);

	}
	
	/**
	 * The results of the AddItemActivity
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if(resultCode == RESULT_OK) {
	    	// This means that the user did everything to add an item.
	    	// Item adding is done in AddItemActivity
	    	// We just have to refresh the list.
	 		initList();
	    } else if (resultCode == RESULT_CANCELED) {    
	       //Write your code if there's no result
	    }
	}
}
