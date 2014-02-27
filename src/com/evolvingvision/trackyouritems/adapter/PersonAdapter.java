package com.evolvingvision.trackyouritems.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.evolvingvision.trackyouritems.R;
import com.evolvingvision.trackyouritems.dao.table.PersonTable;
import com.evolvingvision.trackyouritems.entity.Person;

public class PersonAdapter extends BaseAdapter{

	private Activity activity;
    private ArrayList<Person> data;
    private static LayoutInflater inflater=null;
    
	public PersonAdapter(Activity a, ArrayList<Person> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
	
	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Person getItem(int position) {
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
            view = inflater.inflate(R.layout.person, null);
 
        TextView personName = (TextView)view.findViewById(R.id.personName);
 
        Person person = data.get(position);
        personName.setText(person.getPersonName());
        return view;
	}
	
}
