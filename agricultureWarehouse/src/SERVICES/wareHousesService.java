package SERVICES;

import java.util.ArrayList;

import db.warehouseDB;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import COSTUMADAPTERS.wrStatusCostumAdapter;
import DAO.wrTransactions;
import MODELS.warehouseM;

public class wareHousesService {
	
	Context c;
	String dbName;
	
	public ArrayList<warehouseM> Loadmodel (Context c,String dbName,ArrayList<warehouseM> model){
		this.c=c;
		this.dbName=dbName;
		model = new wrTransactions(c, dbName).read();
		
		return model;
	}
	
	public boolean addData(warehouseM addModel){
		
		if(new wrTransactions(c, dbName).add(addModel)){
			Toast.makeText(c," successfully saved", Toast.LENGTH_SHORT).show();
			return true;
		}else{
			Toast.makeText(c,"unsuccessful saved", Toast.LENGTH_SHORT).show();
			return false;
		}
		
	}
	
	public boolean updateData(warehouseM updateM) {

		if (new wrTransactions(c, dbName).update(updateM)) {
			Toast.makeText(c, " successfully updated", Toast.LENGTH_SHORT)
					.show();
			return true;
		} else {
			Toast.makeText(c, "unsuccessful updated", Toast.LENGTH_SHORT)
					.show();
			return false;
		}

	}

	public boolean deleteData(warehouseM deleteM) {

		if (new wrTransactions(c, dbName).delete(deleteM)) {
			Toast.makeText(c, " successfully deleted", Toast.LENGTH_SHORT)
					.show();
			return true;
		} else {
			Toast.makeText(c, "unsuccessful deleted", Toast.LENGTH_SHORT)
					.show();
			return false;
		}

	}


	

}
