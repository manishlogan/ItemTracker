package com.evolvingvision.trackyouritems.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.evolvingvision.trackyouritems.Constants;
import com.evolvingvision.trackyouritems.dao.table.ItemTable;
import com.evolvingvision.trackyouritems.dao.table.ItemTransactionTable;

public class TrackYourItemsDao {
	
	public static long addItem(Context context,String itemName){
		DBHelper helper = new DBHelper(context);
		SQLiteDatabase database = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(Constants.ITEM_NAME, itemName);
		long id = database.insert(ItemTable.TABLE_NAME, null, values);
		database.close();
		return id;
	}

	public static long addTransaction(Context context, long itemID, long categoryID,
			int statusID, long personID) {
		DBHelper helper = new DBHelper(context);
		SQLiteDatabase database = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(ItemTransactionTable.ITEM_ID, itemID);
		values.put(ItemTransactionTable.CATEGORY_ID, categoryID);
		values.put(ItemTransactionTable.PERSON_ID, personID);
		values.put(ItemTransactionTable.STATUS_ID, statusID);
		
		long id = database.insert(ItemTransactionTable.TABLE_NAME, null, values);
		database.close();
		return id;
	}
}
