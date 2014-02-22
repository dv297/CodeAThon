package com.example.itemfinder;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.app.AlertDialog;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

public class MainActivity extends Activity {

	private ArrayAdapter<String> adapter; // Used to search for class list.
	private ListView itemListView;

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.itemListView);
        
//        ArrayList<String> arrayList = new ArrayList<String>(100);
//        for(int i = 0; i < 100; i++) {
//        	arrayList.add(Integer.toString(i));
//        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
    }

    @Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	//Context menu for holding a list item
	@Override
	public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
		if(info.position > 0) {
			menu.setHeaderTitle(adapter.getItem(info.position));
			super.onCreateContextMenu(menu, view, menuInfo);
		    getMenuInflater().inflate(R.menu.context_menu, menu);
		}
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
		switch(item.getItemId()) {
		case R.id.context_edit:
			System.out.println("edit");
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
			public void onItemClick(AdapterView<?> parent, View arg1, int position,	long arg3) {
				Intent i = new Intent(arg1.getContext(), ItemInfoActivity.class);
		        final String item_name = (String) parent.getItemAtPosition(position);
				startActivity(i);				
			}
		};
		
		List<Item> items_list = ContentHolder.getDS().getAllItems();
		ArrayList<String> items_string = new ArrayList<String>(100);
		itemListView = (ListView) findViewById(R.id.itemListView);
		if(items_list.size() != 0){
			for(int x = 0; x<items_list.size(); x++){ // changed
				items_string.add(items_list.get(x).getName());
			}
			adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, items_string);
			itemListView.setAdapter(adapter);
			itemListView.setOnItemClickListener(clickListener);
		}
	}
	
	public void addButtonClick(View view) {
		
	}
}
