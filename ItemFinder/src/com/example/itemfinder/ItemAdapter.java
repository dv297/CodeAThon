package com.example.itemfinder;

import java.util.ArrayList;
import java.util.Collections;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter implements Filterable {
	private ArrayList<Item> objects;
	private ArrayList<Item> objectsOriginal;
	
	private LayoutInflater inflater;
	
	private String szConstraint = null;
	
	Filter filter = new Filter() {
		@Override
        protected FilterResults performFiltering(CharSequence constraint) {
	        FilterResults resultFilter = new FilterResults();
	        ArrayList<Item> resultList = new ArrayList<Item>(); 
	        
	        if(constraint == null || constraint.length() == 0) {
	        	szConstraint = null;
	        	resultFilter.count = objectsOriginal.size();
	        	resultFilter.values = objectsOriginal;
	        } else {
	        	szConstraint = constraint.toString().toLowerCase();
	        	for(Item i : objectsOriginal) {
        			if(doesFilter(i)) {
	        			resultList.add(i);
	        		}
	        	}
	        	resultFilter.count = resultList.size();
				resultFilter.values = resultList;
	        }
	        return resultFilter;
        }

		@Override
		protected void publishResults(CharSequence contraint, FilterResults results) {
			objects = (ArrayList<Item>)results.values;
			if(results.count > 0) {
				notifyDataSetChanged();
			} else {
				notifyDataSetInvalidated();
			}
		}
	};
	
	private boolean doesFilter(Item item) {
		if(szConstraint == null)
			return true;
		return item.getName().toLowerCase().contains(szConstraint) || item.getKeywords().toLowerCase().contains(szConstraint);
	}
	
	public ItemAdapter(Context context, ArrayList<Item> objects) {
		super();
		this.objectsOriginal = objects;
		Collections.sort(this.objectsOriginal);
		this.objects = objectsOriginal;
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public void add(Item item) {
		objectsOriginal.add(item);
		Collections.sort(objectsOriginal);
		if(objects != objectsOriginal) {
			if(doesFilter(item)) {
				objects.add(item);
				Collections.sort(objects);
			}
		}
		notifyDataSetChanged();
	}
	
	public void update(String name, Item item) {
		int i = 0;
		for(; i < objectsOriginal.size(); i++) {
			if(name.equals(objectsOriginal.get(i).getName())) {
				objectsOriginal.set(i, new Item(item));
				Collections.sort(objectsOriginal);
				break;
			}
		}
		if(objects != objectsOriginal && i < objectsOriginal.size()) {
			for(i = 0; i < objects.size(); i++) {
				if(name.equals(objects.get(i).getName())) {
					objects.set(i, new Item(item));
					Collections.sort(objects);
					break;
				}
			}
		}
		notifyDataSetChanged();
	}
	
	public void remove(String name) {
		int i = 0;
		for(; i < objectsOriginal.size(); i++) {
			if(name.equals(objectsOriginal.get(i).getName())) {
				objectsOriginal.remove(i);
				break;
			}
		}
		if(objects != objectsOriginal && i < objectsOriginal.size()) {
			for(i = 0; i < objects.size(); i++) {
				if(name.equals(objects.get(i).getName())) {
					objects.remove(i);
					break;
				}
			}
		}
		notifyDataSetChanged();
	}
	
	public Item getItem(int position) {
		if(position == 0) {
			return null;
		}
		return objects.get(position - 1);
	}
	
	@Override
	public int getCount() {
		return objects.size() + 1;
	}
	
	@Override
	public Filter getFilter() {
		return filter;
	}
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		if (convertView == null) {
		    view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
		} else {
		    view = convertView;
		}
		
		if(view instanceof TextView) {
			TextView text = (TextView)view;
			if(position == 0) {
				text.setText("Add new item...");
			} else {
				text.setText(objects.get(position - 1).getName());
			}
		}
		return view;
    }

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}
