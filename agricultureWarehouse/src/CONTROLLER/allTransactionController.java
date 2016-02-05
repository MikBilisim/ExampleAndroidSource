package CONTROLLER;

import java.util.ArrayList;

import com.example.agriculturalwarehouse.R;


import CONTROLLER.wareHouseListController.Task;
import MODELS.allTransactionsM;
import MODELS.warehouseM;
import SERVICES.allTrService;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;

public class allTransactionController {
	
	Context c;
	ArrayList<allTransactionsM> model;
	allTransactionsM prdGroup;
	TableLayout table_layout;
	int cols=6;
	int rows=0;
	String[] headers = {"id","productGroup","productName","transaciton","date",""};
	allTrService dbService = new allTrService();
	int weightScroll=0;
	
	boolean status = true;
	Handler handler;
	
	
	
	public allTransactionController(Context c,TableLayout table_layout) {
		
		this.c=c;
		handler = new Handler();
		this.table_layout=table_layout;
	}



	public void loadDataFromUser(allTransactionsM prdGroup){
		this.prdGroup=prdGroup;

		new Thread(new fromUser()).start();
	
	}
	
	
	public void loadDataFromdb() {

		new Thread(new allData()).start();
	}
	
	public void deleteDataFromDb(allTransactionsM deleteM){
		table_layout.removeAllViews();
		dbService.deleteData(deleteM);
	}
	

	public void BuildTable() {
		
		//header options
		TableRow row2 = new TableRow(c);
		row2.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));

					for (int j = 0; j < cols; j++) {

						TextView tv = new TextView(c);
						tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
								LayoutParams.WRAP_CONTENT));

						tv.setPadding(5, 5, 5, 5);
					
							tv.setText(headers[j]);
					
					
						
						row2.addView(tv);

					}
					table_layout.addView(row2);
					
		//end header options

		// outer for loop
		for (int i = 0; i < rows; i++) {

			TableRow row = new TableRow(c);
			row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.WRAP_CONTENT));
			
			
			
			// inner for loop
			for (int j = 0; j < cols; j++) {

				TextView tv = new TextView(c);
				tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT));
				
				Button deneme = new Button(c);
				deneme.setLayoutParams(new LayoutParams(50,50));
				
				
				
				deneme.setId(i);
				deneme.setBackgroundResource(R.drawable.delete_btn);
				deneme.setPadding(5, 5, 5, 5);
				
				deneme.setOnClickListener(new View.OnClickListener() 
				{                   
				    @Override
				    public void onClick(View v)
				    {
				    	  new deleteDialogProductName().showDialog(c,allTransactionController.this,v.getId());
				    }
				});
				
				
				tv.setPadding(5, 5, 5, 5);
				tv.setBackgroundResource(R.drawable.cell_shape);
				
					
					switch (j) {
					case 0:
						tv.setText(""+model.get(i).getId());
						break;
					case 1:
						tv.setText(model.get(i).getProductGroup());
						break;
					case 2:
						tv.setText(model.get(i).getProductName());
						break;
					case 3:
						tv.setText(model.get(i).getTransaction());
						break;
					case 4:
						tv.setText(model.get(i).getDate());
						break;

					default:
						break;
					}
					
				
		
				if(j==5){
					row.addView(deneme);
					weightScroll+=50;
				}else{
				row.addView(tv);
				weightScroll+=tv.getWidth();
				}
				
				

			}

			table_layout.addView(row);

		}
	}
	
	
	class fromUser implements Runnable {
		@Override
		public void run() {
			while (status) {
				
				handler.post(new Runnable() {
					@Override
					public void run() {

						if (model == null) {

							model=dbService.loadProductName(c, model,prdGroup);
							rows=model.size();	
							
						} else {

							BuildTable();
							status = false;
						}

					}
				});

				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
	class allData implements Runnable {
		@Override
		public void run() {
			while (status) {
				
				handler.post(new Runnable() {
					@Override
					public void run() {

						if (model == null) {

							model=dbService.Loadmodel(c, model);
							rows=model.size();	
							
						} else {

							BuildTable();
							status = false;
						}

					}
				});

				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public ArrayList<allTransactionsM> getModel() {
		return model;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	public void setModel(ArrayList<allTransactionsM> model) {
		this.model = model;
		}
	public void trdRun(){
		try {
			prdGroup.toString().isEmpty();
			 new Thread(new fromUser()).start();
		} catch (NullPointerException e) {
			// TODO: handle exception
			new Thread(new allData()).start();
		}
	
	}
	

}
