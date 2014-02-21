package com.example.itemfinder;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import java.util.List;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends Activity {
	private ArrayAdapter<String> adapter; // Used to search for class list.
	private ListView itemListView;
	
	private static DataSource dataSource;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
//        btnObj = (Button) this.findViewById(R.id.btnTESTBUTTON);
        ListView lstView = (ListView) findViewById(R.id.itemListView);
        
        ArrayList<String> arrayList = new ArrayList<String>(100);
        for(int i = 0; i < 100; i++)
        {
        	arrayList.add(Integer.toString(i));
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        
        lstView.setAdapter(adapter);        
    }


    public void testMethod(View someView)
    {
    	adapter.add("added");
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


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
	public void initList(){
		List<Item> items_list = dataSource.getAllItems();
		ArrayList<String> items_string = new ArrayList<String>();
		itemListView = (ListView) findViewById(R.id.itemListView);
		if(items_list.size() != 0){
			for(int x = 0; x<items_list.size(); x++){
				items_string.add(items_list.get(x).getName());
			}
			adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, items_string);
			
			itemListView.setAdapter(adapter);
			
			itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View arg1, int position,
						long arg3) {
//					//Intent i = new Intent(arg1.getContext(), GradeListActivity.class);
//			        //final String item = (String) parent.getItemAtPosition(position);
//					//GradeListActivity.setTitle(item);
//					//GradeListActivity.setSubject(new Subject(ContentHolder.getStudent().getName(), item, "ABSOLUTE")); // FIX THIS TEMPORARY CODE.
//					//startActivity(i);
//					
				}
				
			});
		}
	}
	
	public void submitButtonClick(View view){
		
	}
	
	public void addButtonClick(View view){
		
	}
}
