package com.evolvingvision.trackyouritems;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.evolvingvision.trackyouritems.dao.DBHelper;
import com.evolvingvision.trackyouritems.dao.table.CategoryTable;

public class AddCategoryActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_category);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_category, menu);
		return true;
	}
	
	public void save(View view){
		DBHelper helper = new DBHelper(this);
		SQLiteDatabase database = helper.getWritableDatabase();
		String name = ((EditText)findViewById(R.id.newCategoryName)).getText().toString();
		
		ContentValues values = new ContentValues();
		values.put(CategoryTable.COL_CATEGORY_NAME, name);
		long id = database.insert(CategoryTable.TABLE_NAME, null, values);
		
		Intent result = new Intent();
		result.putExtra(Constants.CATEGORY_NAME, name);
		result.putExtra(Constants.CATEGORY_ID, id);
		setResult(204, result);
		finish();
	}
}
