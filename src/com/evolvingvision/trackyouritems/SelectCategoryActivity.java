package com.evolvingvision.trackyouritems;

import java.util.ArrayList;

import com.evolvingvision.trackyouritems.adapter.CategoryAdapter;
import com.evolvingvision.trackyouritems.dao.TrackYourItemsDao;
import com.evolvingvision.trackyouritems.entity.Category;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SelectCategoryActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_category);
		
		ArrayList<Category> categories = TrackYourItemsDao.getCategories(this);
		CategoryAdapter adapter = new CategoryAdapter(this, categories);
		setListAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.select_category, menu);
		return true;
	}
	
	@Override
	protected void onListItemClick(ListView listView, View view, int position, long rowID) {
		Intent data = new Intent();
		data.putExtra(Constants.CATEGORY_NAME, ((Category)listView.getItemAtPosition(position)).getCategoryName());
		data.putExtra(Constants.CATEGORY_ID, ((Category)listView.getItemAtPosition(position)).getCategoryID());
		setResult(202,data);
		finish();
	}
	
	public void addCategory(View view){
		Intent nextActivity = new Intent(this, AddCategoryActivity.class);
		startActivityForResult(nextActivity,204);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == 204){
			setResult(202,data);
			finish();
		}
	}
}
