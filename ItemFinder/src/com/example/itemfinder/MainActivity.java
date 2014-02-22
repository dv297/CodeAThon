package com.example.itemfinder;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	private ArrayAdapter<String> adapter; // Used to search for class list.
	private ListView itemListView;

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
	public void initList(){
		List<Item> items_list = ContentHolder.getDS().getAllItems();
		ArrayList<String> items_string = new ArrayList<String>(100);
		itemListView = (ListView) findViewById(R.id.itemListView);
		//if(items_list.size() == 0){ // changed
			for(int x = 0; x<100; x++){ // changed
				//items_string.add(items_list.get(x).getName());
				// Testing purposes
				items_string.add("TEST");
			}
			adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, items_string);
			
			itemListView.setAdapter(adapter);
			
			itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View arg1, int position,
						long arg3) {
					Intent i = new Intent(arg1.getContext(), ItemInfoActivity.class);
			        final String item_name = (String) parent.getItemAtPosition(position);
			        Item item = new Item();
			        List<Item> item_list = ContentHolder.getDS().getAllItems();
			        for(int x = 0; x<item_list.size(); x++){
			        	if(item_name.equals(item_list.get(x).getName())){
			        		item = item_list.get(x);
			        	}
			        }
			        
			        ItemInfoActivity.setValues(item.getName(), item.getLocation(), item.getKeywords());
//					//GradeListActivity.setTitle(item);
//					//GradeListActivity.setSubject(new Subject(ContentHolder.getStudent().getName(), item, "ABSOLUTE")); // FIX THIS TEMPORARY CODE.
//					//startActivity(i);
//					
				}
				
			});
		}
//	}
	
	public void addButtonClick(View view){
		
	}
}
