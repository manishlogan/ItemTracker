package com.evolvingvision.trackyouritems.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.evolvingvision.trackyouritems.dao.table.CategoryTable;
import com.evolvingvision.trackyouritems.dao.table.ItemTable;
import com.evolvingvision.trackyouritems.dao.table.ItemTransactionTable;
import com.evolvingvision.trackyouritems.dao.table.PersonTable;
import com.evolvingvision.trackyouritems.entity.Category;
import com.evolvingvision.trackyouritems.entity.Person;

public class TrackYourItemsDao {
	
	public static long addItem(Context context,String itemName){
		DBHelper helper = new DBHelper(context);
		SQLiteDatabase database = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(ItemTable.ITEM_NAME, itemName);
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

	public static ArrayList<Person> getPersons(
			Context context) {
		ArrayList<Person> personList = new ArrayList<Person>();
		DBHelper helper = new DBHelper(context);
		SQLiteDatabase database = helper.getWritableDatabase();
		String cols[] = {PersonTable.ID,PersonTable.PERSON_NAME};
		Cursor cursor = database.query(PersonTable.TABLE_NAME, cols, null, null, null, null, PersonTable.PERSON_NAME); 
		
		if(cursor.moveToFirst()){
			do{
				Person person = new Person(cursor.getLong(0), cursor.getString(1));
				personList.add(person);
			}while(cursor.moveToNext());
		}
		return personList;
	}

	public static ArrayList<Category> getCategories(
			Context context) {
		ArrayList<Category> categoryList = new ArrayList<Category>();
		DBHelper helper = new DBHelper(context);
		SQLiteDatabase database = helper.getWritableDatabase();
		String cols[] = {CategoryTable.ID,CategoryTable.COL_CATEGORY_NAME};
		Cursor cursor = database.query(CategoryTable.TABLE_NAME, cols, null, null, null, null, CategoryTable.COL_CATEGORY_NAME); 
		
		if(cursor.moveToFirst()){
			do{
				Category category = new Category(cursor.getLong(0), cursor.getString(1));
				categoryList.add(category);
			}while(cursor.moveToNext());
		}
		return categoryList;
	}
}
