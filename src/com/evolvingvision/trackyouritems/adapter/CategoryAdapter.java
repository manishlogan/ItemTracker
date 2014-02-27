package com.evolvingvision.trackyouritems.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.evolvingvision.trackyouritems.R;
import com.evolvingvision.trackyouritems.entity.Category;

public class CategoryAdapter extends BaseAdapter{

	private Activity activity;
    private ArrayList<Category> data;
    private static LayoutInflater inflater=null;
    
	public CategoryAdapter(Activity a, ArrayList<Category> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
	
	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Category getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view=convertView;
        if(convertView==null)
            view = inflater.inflate(R.layout.category, null);
 
        TextView personName = (TextView)view.findViewById(R.id.categoryName);
 
        Category category = data.get(position);
        personName.setText(category.getCategoryName());
        return view;
	}
	
}
