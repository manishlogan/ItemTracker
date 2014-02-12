package com.evolvingvision.trackyouritems;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class AddLentItemActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_lent_item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_lent_item, menu);
		return true;
	}

}
