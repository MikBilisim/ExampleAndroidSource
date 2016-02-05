package DAO;

import java.util.ArrayList;

import db.warehouseDB;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import MODELS.allTransactionsM;
import MODELS.allTransactionsM;

public class allTransactions extends Activity{
	
	ArrayList<allTransactionsM> model = new ArrayList<allTransactionsM>();
	Context c;
	warehouseDB dataBase;
	String dbName;
	
	public allTransactions(Context c,String dbName) {
		this.c=c;
		this.dbName=dbName;
		dataBase= new warehouseDB(this.c);
	}
	
	public void clearData(){
		model.removeAll(model);
	}
	
	// reading data from db
	
	
	
	public ArrayList<allTransactionsM> read() {
		 
	
	    String sql = "SELECT * FROM "+dbName+" ORDER BY id ASC";
	 
	    SQLiteDatabase db = dataBase.getWritableDatabase();
	    Cursor cursor = db.rawQuery(sql, null);
	 
	    if (cursor.moveToFirst()) {
	        do {
				int id = Integer.parseInt(cursor.getString(cursor
						.getColumnIndex("id")));
				String productGroup = cursor.getString(cursor
						.getColumnIndex("productGroup"));
				String productName = cursor.getString(cursor
						.getColumnIndex("productName"));
				String transaction = cursor.getString(cursor
						.getColumnIndex("transaciton"));
				String date = cursor.getString(cursor.getColumnIndex("date"));
	        			
	            model.add(new allTransactionsM(id, productGroup, productName, transaction, date));
	 
	        } while (cursor.moveToNext());
	    }
	 
	    cursor.close();
	    db.close();
	  
	    return model;
	}
	

	// end reading data from db
	
	// reading data from db only productgroup
	
	
	
		public ArrayList<allTransactionsM> read(String productName2) {
			 
		
			
			String sql = "SELECT * FROM "+dbName+" WHERE productName = '"+productName2+"'";
		 
		    SQLiteDatabase db = dataBase.getWritableDatabase();
		    Cursor cursor = db.rawQuery(sql, null);
		 
		    if (cursor.moveToFirst()) {
		        do {
					int id = Integer.parseInt(cursor.getString(cursor
							.getColumnIndex("id")));
					String productGroup = cursor.getString(cursor
							.getColumnIndex("productGroup"));
					String productName = cursor.getString(cursor
							.getColumnIndex("productName"));
					String transaction = cursor.getString(cursor
							.getColumnIndex("transaciton"));
					String date = cursor.getString(cursor.getColumnIndex("date"));
		        			
		            model.add(new allTransactionsM(id, productGroup, productName, transaction, date));
		 
		        } while (cursor.moveToNext());
		    }
		 
		    cursor.close();
		    db.close();
		  
		    return model;
		}
		

		// end reading data from db
	
	// writing data from user

	public boolean add(allTransactionsM addModel) {
		 
	    ContentValues values = new ContentValues();
	    
	   
	    
	    values.put("productGroup", addModel.getProductGroup());
	    values.put("productName", addModel.getProductName());
	    values.put("transaciton", addModel.getTransaction());
	    values.put("date", addModel.getDate());

	    SQLiteDatabase db = dataBase.getWritableDatabase();
	 
	    boolean createSuccessful = db.insert(dbName, null, values) > 0;
	    db.close();
	    Log.w("kaydedilen veriler all",addModel.toString());
	    return createSuccessful;
	}

	// end writing data from user
	
	// updating data from user

	public boolean update(allTransactionsM updateM) {
		 
	    ContentValues values = new ContentValues();
	 
	    values.put("productGroup", updateM.getProductGroup());
	    values.put("productName", updateM.getProductName());
	    values.put("transaciton", updateM.getTransaction());
	    values.put("date", updateM.getDate());
	 
	 
	    String where = "id = ?";
	 
	    String[] whereArgs = { Integer.toString(updateM.getId()) };
	 
	    SQLiteDatabase db = dataBase.getWritableDatabase();
	 
	    boolean updateSuccessful = db.update(dbName, values, where, whereArgs) > 0;
	    db.close();
	 
	    return updateSuccessful;
	 
	}
	// end updating data from user
	
	// delete data from user
	
	public boolean delete(allTransactionsM deleteM) {
	    boolean deleteSuccessful = false;
	    int id=deleteM.getId();
	    SQLiteDatabase db = dataBase.getWritableDatabase();
	    deleteSuccessful = db.delete(dbName, "id ='" + id + "'", null) > 0;
	    db.close();
	 
	    return deleteSuccessful;
	 
	}
	
	//end delete data from user
	
	
	
}
