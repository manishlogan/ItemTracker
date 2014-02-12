package com.evolvingvision.trackyouritems;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void showBorrowedItems(View view){
		Intent nextActivity = new Intent(this, BorrowedItemsActivity.class);
		startActivity(nextActivity);
	}
	
	public void showLentItems(View view){
		Intent nextActivity = new Intent(this, LentItemsActivity.class);
		startActivity(nextActivity);
	}
	
	public void addBorrowedItems(View view){
		Intent nextActivity = new Intent(this, AddBorrowedItemActivity.class);
		startActivity(nextActivity);
	}
	
	public void addLentItems(View view){
		Intent nextActivity = new Intent(this, AddLentItemActivity.class);
		startActivity(nextActivity);
	}
	
	public void showActivity(View view){
		Intent nextActivity = new Intent(this, ViewActivity.class);
		startActivity(nextActivity);
	}
}
