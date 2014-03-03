package com.evolvingvision.trackyouritems.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.evolvingvision.trackyouritems.dao.table.CategoryTable;
import com.evolvingvision.trackyouritems.dao.table.ItemTable;
import com.evolvingvision.trackyouritems.dao.table.ItemTransactionTable;
import com.evolvingvision.trackyouritems.dao.table.PersonTable;
import com.evolvingvision.trackyouritems.entity.Category;
import com.evolvingvision.trackyouritems.entity.Item;
import com.evolvingvision.trackyouritems.entity.Person;
import com.evolvingvision.trackyouritems.entity.Status;
import com.evolvingvision.trackyouritems.entity.Transaction;

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

	public static ArrayList<Transaction> getTransactionItems(Context context, int statusID) {
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		DBHelper helper = new DBHelper(context);
		SQLiteDatabase database = helper.getWritableDatabase();
		String cols[] = {ItemTransactionTable.ID,ItemTransactionTable.ITEM_ID,ItemTransactionTable.PERSON_ID,ItemTransactionTable.CATEGORY_ID};
		String args[] = {""+statusID};
		Cursor cursor = database.query(ItemTransactionTable.TABLE_NAME, cols, ItemTransactionTable.STATUS_ID+"=?", args, null, null, ItemTransactionTable.ID);
		if(cursor.moveToFirst()){
			do{
				int transactionID = cursor.getInt(0);
				int itemID = cursor.getInt(1);
				int personID = cursor.getInt(2);
				int categoryID = cursor.getInt(3);
				Item item = getItem(database,itemID);
				Person person = getPerson(database,personID);
				Category category = getCategory(database,categoryID);
				Status status = new Status();
				status.setStatusID(statusID);
				Transaction transaction = new Transaction();
				transaction.setCategory(category);
				transaction.setItem(item);
				transaction.setPerson(person);
				transaction.setStatus(status);
				transaction.setTransactionID(transactionID);
				transactions.add(transaction);
			}while(cursor.moveToNext());
		}else{
			System.out.println("No records found");
		}
		database.close();
		return transactions;
	}

	private static Category getCategory(SQLiteDatabase database, int categoryID) {
		String cols[] = {CategoryTable.ID,CategoryTable.COL_CATEGORY_NAME};
		String args[] = {""+categoryID};
		Cursor cursor = database.query(CategoryTable.TABLE_NAME, cols, CategoryTable.ID+"=?", args, null, null, CategoryTable.ID);
		Category category = null;
		if(cursor.moveToFirst()){
			String categoryName = cursor.getString(1);
			
			category = new Category(categoryID, categoryName);
		}else{
			System.out.println("No records found");
		}
		return category;
	}

	private static Person getPerson(SQLiteDatabase database, int personID) {
		String cols[] = {PersonTable.ID,PersonTable.PERSON_NAME};
		String args[] = {""+personID};
		Cursor cursor = database.query(PersonTable.TABLE_NAME, cols, PersonTable.ID+"=?", args, null, null, PersonTable.ID);
		Person person = null;
		if(cursor.moveToFirst()){
			String personName = cursor.getString(1);
			
			person = new Person(personID, personName);
		}else{
			System.out.println("No records found");
		}
		return person;
	}

	private static Item getItem(SQLiteDatabase database, int itemID) {
		String cols[] = {ItemTable.ID,ItemTable.ITEM_NAME};
		String args[] = {""+itemID};
		Cursor cursor = database.query(ItemTable.TABLE_NAME, cols, ItemTable.ID+"=?", args, null, null, ItemTable.ID);
		Item item = null;
		if(cursor.moveToFirst()){
			String itemName = cursor.getString(1);
			item = new Item(itemID, itemName);
		}else{
			System.out.println("No records found");
		}
		return item;
	}
}
