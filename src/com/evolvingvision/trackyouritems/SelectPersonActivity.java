package com.evolvingvision.trackyouritems;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

import com.evolvingvision.trackyouritems.adapter.PersonAdapter;
import com.evolvingvision.trackyouritems.dao.TrackYourItemsDao;
import com.evolvingvision.trackyouritems.entity.Person;

public class SelectPersonActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_person);
		
		ArrayList<Person> persons = TrackYourItemsDao.getPersons(this);
		PersonAdapter adapter = new PersonAdapter(this, persons);
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
		Intent data = new Intent();
		data.putExtra(Constants.PERSON_NAME, ((Person)listView.getItemAtPosition(position)).getPersonName());
		data.putExtra(Constants.PERSON_ID, ((Person)listView.getItemAtPosition(position)).getPersonID());
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
