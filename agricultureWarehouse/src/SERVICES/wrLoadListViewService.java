package SERVICES;

import java.util.ArrayList;

import COSTUMADAPTERS.wrStatusCostumAdapter;
import DAO.wrTransactions;
import MODELS.warehouseM;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class wrLoadListViewService {
	
	Context c;
	ListView wrList;
	wrStatusCostumAdapter adapter;
	public ListView loadListView(Context c,ArrayList<warehouseM> model,ListView wrList){
		this.wrList = wrList;
		adapter = new wrStatusCostumAdapter(c, model);
		this.wrList.setAdapter(adapter);
		this.wrList.setItemsCanFocus(true);
		return this.wrList;
	}
	
	public warehouseM[] getModel(){
		
		return adapter.getModel2();
	}
	
}
