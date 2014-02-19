package com.evolvingvision.trackyouritems;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SelectPersonActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_person);
		
		String values[] = {"LOGAN","Manish","Jain"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,values);
		setListAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.select_person, menu);
		return true;
	}
	
	@Override
	protected void onListItemClick(ListView listView, View view, int position, long rowID) {
		Intent data = new Intent().putExtra(Constants.PERSON_NAME, (String)listView.getItemAtPosition(position));
		setResult(201,data);
		finish();
	}
	
	public void addPerson(View view){
		Intent nextActivity = new Intent(this, AddPersonActivity.class);
		startActivityForResult(nextActivity,203);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == 203){
			setResult(201,data);
			finish();
		}
	}
}
