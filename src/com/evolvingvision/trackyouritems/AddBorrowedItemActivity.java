package com.evolvingvision.trackyouritems;

import com.evolvingvision.trackyouritems.dao.TrackYourItemsDao;
import com.evolvingvision.trackyouritems.entity.Category;
import com.evolvingvision.trackyouritems.entity.Person;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddBorrowedItemActivity extends Activity {

	private Person person;
	
	private Category category;
	
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
		EditText itemNameTxt = (EditText)findViewById(R.id.itemName);
		String itemName = itemNameTxt.getText().toString();
		long itemID = TrackYourItemsDao.addItem(this, itemName);
		long transactionID = TrackYourItemsDao.addTransaction(this,itemID,category.getCategoryID(),Constants.BORROWED_STATUS_ID,person.getPersonID());
		if(transactionID != -1){
			Toast.makeText(this, "Borrowed Item Added", Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(this, "Failed to add Borrowed Item", Toast.LENGTH_SHORT).show();
		}
		
		Intent nextActivity = new Intent(this, MainActivity.class);
		startActivity(nextActivity);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == 201 && resultCode == 201){
			EditText personName = (EditText)findViewById(R.id.personName);
			String name = data.getStringExtra(Constants.PERSON_NAME);
			long id = data.getLongExtra(Constants.PERSON_ID,0);
			person = new Person(id, name);
			personName.setText(name);
		}else if(requestCode == 202 && resultCode == 202){
			EditText categoryNameText = (EditText)findViewById(R.id.categoryName);
			String categoryName = data.getStringExtra(Constants.CATEGORY_NAME);
			long id = data.getLongExtra(Constants.CATEGORY_ID, 0); 
			category = new Category(id, categoryName);
			categoryNameText.setText(categoryName);
		}
	}
}
