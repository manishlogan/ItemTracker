package com.evolvingvision.trackyouritems;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class AddBorrowedItemActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_borrowed_item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_borrowed_item, menu);
		return true;
	} 
	
	public void selectPerson(View view){
		Intent nextActivity = new Intent(this, SelectPersonActivity.class);
		startActivityForResult(nextActivity, 201);
	}
	
	public void selectCategory(View view){
		Intent nextActivity = new Intent(this, SelectCategoryActivity.class);
		startActivityForResult(nextActivity, 202);	
	}
	
	public void save(View view){
		//TODO : Write logic to add data to DB here
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == 201 && resultCode == 201){
			EditText personName = (EditText)findViewById(R.id.personName);
			String name = data.getStringExtra(Constants.PERSON_NAME);
			personName.setText(name);
		}else if(requestCode == 202 && resultCode == 202){
			EditText categoryName = (EditText)findViewById(R.id.categoryName);
			String category = data.getStringExtra(Constants.CATEGORY_NAME);
			categoryName.setText(category);
		}
	}
}
