package com.evolvingvision.trackyouritems.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.evolvingvision.trackyouritems.Constants;
import com.evolvingvision.trackyouritems.dao.table.CategoryTable;
import com.evolvingvision.trackyouritems.dao.table.ItemTable;
import com.evolvingvision.trackyouritems.dao.table.ItemTransactionTable;
import com.evolvingvision.trackyouritems.dao.table.PersonTable;
import com.evolvingvision.trackyouritems.dao.table.StatusTable;

public class DBHelper extends SQLiteOpenHelper{

	private final static String DATABASE_NAME = "TrackYourItems.db";
	
	private final static int DATABASE_VERSION = 2;
	
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
			ItemTransactionTable.PERSON_ID+" integer,"+ItemTransactionTable.STATUS_ID+" integer,"+
			ItemTransactionTable.DUE_DATE+" text)";
	
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
//		initializeCategories(db);
		initializeStatus(db);
	}

	private void initializeStatus(SQLiteDatabase db) {
		ContentValues lentStatus = new ContentValues();
		lentStatus.put(StatusTable.ID, Constants.LENT_STATUS);
		lentStatus.put(StatusTable.STATUS_NAME, "Lent");
		
		ContentValues borrowedStatus = new ContentValues();
		borrowedStatus.put(StatusTable.ID, Constants.BORROWED_STATUS);
		borrowedStatus.put(StatusTable.STATUS_NAME, "Borrowed");
		
		ContentValues returnedStatus = new ContentValues();
		returnedStatus.put(StatusTable.ID, Constants.RETURNED_STATUS);
		returnedStatus.put(StatusTable.STATUS_NAME, "Returned");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		db.execSQL("drop table "+ItemTransactionTable.TABLE_NAME);
		db.execSQL("drop table "+CategoryTable.TABLE_NAME);
		db.execSQL("drop table "+StatusTable.TABLE_NAME);
		db.execSQL("drop table "+PersonTable.TABLE_NAME);
		db.execSQL("drop table "+ItemTable.TABLE_NAME);
		onCreate(db);
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
