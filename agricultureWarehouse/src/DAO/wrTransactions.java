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

import MODELS.warehouseM;

public class wrTransactions extends Activity{
	
	ArrayList<warehouseM> model = new ArrayList<warehouseM>();
	Context c;
	warehouseDB dataBase;
	String dbName;
	
	public wrTransactions(Context c,String dbName) {
		this.c=c;
		this.dbName=dbName;
		dataBase= new warehouseDB(this.c);
	}
	
	public void clearData(){
		model.removeAll(model);
	}
	
	// reading data from db
	
	
	
	public ArrayList<warehouseM> read() {
		 
	
	    String sql = "SELECT * FROM "+dbName+" ORDER BY id ASC";
	 
	    SQLiteDatabase db = dataBase.getWritableDatabase();
	    Cursor cursor = db.rawQuery(sql, null);
	    
	    if (cursor.moveToFirst()) {
	        do {
	        	
	        	
	            int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
	            String name = cursor.getString(cursor.getColumnIndex("name"));
	            int amount=Integer.parseInt(cursor.getString(cursor.getColumnIndex("amount")));
	            
				warehouseM tempM = new warehouseM();
				tempM.setId(id);
				tempM.setName(name);
				tempM.setamount(amount);
	           
				
	            model.add(tempM);
	 
	        } while (cursor.moveToNext());
	    }
	 
	    cursor.close();
	    db.close();
	  
	    return model;
	}
	

	// end reading data from db
	
	// writing data from user

	public boolean add(warehouseM addModel) {
		 
	    ContentValues values = new ContentValues();
	 
	    values.put("name", addModel.getName());
	    values.put("amount", addModel.getamount());
	 
	    SQLiteDatabase db = dataBase.getWritableDatabase();
	 
	    boolean createSuccessful = db.insert(dbName, null, values) > 0;
	    db.close();
	   // Log.w("kaydedilen veriler",addModel.toString());
	    return createSuccessful;
	}

	// end writing data from user
	
	// updating data from user

	public boolean update(warehouseM updateM) {
		 
	    ContentValues values = new ContentValues();
	 
	    values.put("name",updateM.getName());
	    values.put("amount",updateM.getamount());
	 
	    String where = "id = ?";
	 
	    String[] whereArgs = { Integer.toString(updateM.getId()) };
	 
	    SQLiteDatabase db = dataBase.getWritableDatabase();
	 
	    boolean updateSuccessful = db.update(dbName, values, where, whereArgs) > 0;
	    db.close();
	 
	    return updateSuccessful;
	 
	}
	// end updating data from user
	
	// delete data from user
	
	public boolean delete(warehouseM deleteM) {
	    boolean deleteSuccessful = false;
	    
	    int id=deleteM.getId();
	    SQLiteDatabase db = dataBase.getWritableDatabase();
	    deleteSuccessful = db.delete(dbName, "id ='" + id + "'", null) > 0;
	    db.close();
	 
	    return deleteSuccessful;
	 
	}
	
	//end delete data from user
	
	
	
}
