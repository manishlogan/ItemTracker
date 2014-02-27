package com.evolvingvision.trackyouritems;

import com.evolvingvision.trackyouritems.dao.DBHelper;
import com.evolvingvision.trackyouritems.dao.table.PersonTable;
import com.evolvingvision.trackyouritems.entity.Person;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class AddPersonActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_person);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_person, menu);
		return true;
	}
	
	public void save(View view){
		DBHelper helper = new DBHelper(this);
		SQLiteDatabase database = helper.getWritableDatabase();
		String name = ((EditText)findViewById(R.id.newPersonName)).getText().toString();
		
		ContentValues values = new ContentValues();
		values.put(PersonTable.PERSON_NAME, name);
		long id = database.insert(PersonTable.TABLE_NAME, null, values);
		Person person = new Person(id,name);
		
		Intent result = new Intent();
		result.putExtra(Constants.PERSON_NAME, name);
		result.putExtra(Constants.PERSON_ID, id);
		setResult(203, result);
		finish();
	}
}
