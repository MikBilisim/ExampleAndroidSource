package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class warehouseDB extends SQLiteOpenHelper{
	static final String DataBase="WAREHOUSE";
	static final int version=1;
	public warehouseDB(Context context) {
		super(context, DataBase, null, version);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE TRANSACTIONS (id INTEGER PRIMARY KEY AUTOINCREMENT , productGroup string, productName string, transaciton string,date string);");
		db.execSQL("CREATE TABLE LIQUID_TANKS (id INTEGER PRIMARY KEY AUTOINCREMENT , name TEXT, amount INTEGER);");
		db.execSQL("CREATE TABLE SILOS (id INTEGER PRIMARY KEY AUTOINCREMENT , name TEXT, amount INTEGER);");
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXIST Person");
		onCreate(db);
	}
	
	

}
