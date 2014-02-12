package com.evolvingvision.trackyouritems.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.evolvingvision.trackyouritems.dao.table.CategoryTable;
import com.evolvingvision.trackyouritems.dao.table.ItemTable;
import com.evolvingvision.trackyouritems.dao.table.ItemTransactionTable;
import com.evolvingvision.trackyouritems.dao.table.PersonTable;
import com.evolvingvision.trackyouritems.dao.table.StatusTable;

public class DBHelper extends SQLiteOpenHelper{

	private final static String DATABASE_NAME = "TrackYourItems.db";
	
	private final static int DATABASE_VERSION = 1;
	
	private final static String CREATE_CATEGORY_TABLE = "create table "+
	CategoryTable.TABLE_NAME + "("+CategoryTable.ID + " Integer Primary Key,"+
	CategoryTable.COL_CATEGORY_NAME + " text)";
	
	private final static String CREATE_ITEM_TABLE = "create table "+
			ItemTable.TABLE_NAME + "("+ItemTable.ID + " Integer Primary Key,"+
			ItemTable.ITEM_NAME+ " text)";
	
	private final static String CREATE_PERSON_TABLE = "create table "+
			PersonTable.TABLE_NAME + "("+PersonTable.ID + " Integer Primary Key,"+
			PersonTable.PERSON_NAME + " text)";
	
	private final static String CREATE_STATUS_TABLE = "create table "+
			StatusTable.TABLE_NAME + "("+StatusTable.ID + " Integer Primary Key,"+
			StatusTable.STATUS_NAME + " text)";
	
	private final static String CREATE_ITEM_TRANSACTION_TABLE = "create table "+
			ItemTransactionTable.TABLE_NAME + "("+ItemTransactionTable.ID + " Integer Primary Key,"+
			ItemTransactionTable.ITEM_ID + " integer,"+ItemTransactionTable.CATEGORY_ID+" integer,"+
			ItemTransactionTable.CATEGORY_ID+" integer,"+ItemTransactionTable.PERSON_ID+" integer)";
	
	public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_CATEGORY_TABLE);
		db.execSQL(CREATE_ITEM_TABLE);
		db.execSQL(CREATE_PERSON_TABLE);
		db.execSQL(CREATE_STATUS_TABLE);
		db.execSQL(CREATE_ITEM_TRANSACTION_TABLE);
		initializeCategories(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
	}
	
	private void initializeCategories(SQLiteDatabase db) {
		ContentValues book = new ContentValues();
		book.put(CategoryTable.COL_CATEGORY_NAME, "Book");
		
		ContentValues laptopAccessory = new ContentValues();
		laptopAccessory.put(CategoryTable.COL_CATEGORY_NAME, "Laptop Accessory");
		
		db.insert(CategoryTable.TABLE_NAME, null, book);
		db.insert(CategoryTable.TABLE_NAME, null, laptopAccessory);
	}
}
