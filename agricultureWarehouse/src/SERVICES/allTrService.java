package SERVICES;

import java.util.ArrayList;

import DAO.allTransactions;
import DAO.wrTransactions;
import MODELS.allTransactionsM;
import MODELS.warehouseM;
import android.content.Context;
import android.widget.Toast;

public class allTrService {
	
	String dbName="TRANSACTIONS";
	Context c;
	
	public void setC(Context c) {
		this.c = c;
	}
	
	//read all data
	public ArrayList<allTransactionsM> Loadmodel (Context c,ArrayList<allTransactionsM> model){
		this.c=c;
		model = new allTransactions(c, dbName).read();
		return model;
	}
	
	//read only productname
	public ArrayList<allTransactionsM> loadProductName (Context c,ArrayList<allTransactionsM> model,allTransactionsM prdName){
		this.c=c;
		this.dbName=dbName;
		model = new allTransactions(c, dbName).read(prdName.getProductName());
		return model;
	}
	
	public boolean addData(allTransactionsM addModel){
		
		if(new allTransactions(c, dbName).add(addModel)){
			Toast.makeText(c," successfully saved", Toast.LENGTH_SHORT).show();
			return true;
		}else{
			Toast.makeText(c,"unsuccessful saved", Toast.LENGTH_SHORT).show();
			return false;
		}
		
	}
	
	
	public boolean deleteData(allTransactionsM deleteM) {

		if (new allTransactions(c, dbName).delete(deleteM)) {
			Toast.makeText(c, " successfully deleted", Toast.LENGTH_SHORT).show();
			return true;
		} else {
			Toast.makeText(c, "unsuccessful deleted", Toast.LENGTH_SHORT).show();
			return false;
		}

	}

}
