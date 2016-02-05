package com.example.agriculturalwarehouse;

import java.util.ArrayList;
import java.util.Random;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import CONTROLLER.wareHouseListController;
import DAO.wrTransactions;
import MODELS.warehouseM;
import android.net.http.SslCertificate.DName;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;

public class TrActivity extends Activity {
	String dbName;
	 ListView showList;
	 wareHouseListController cntrl;
	
	 BarChart mChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wr_status);
        Bundle receiveData=getIntent().getExtras();
        dbName = receiveData.getString("dbName");
       
        mChart = (BarChart) findViewById(R.id.chart1);
        showList = (ListView)findViewById(R.id.statusList);

       loadList();
       
       }


 public void addItem(View v){
	
	 wrTransactions deneme = new wrTransactions(this, dbName);
	 
	 EditText name = (EditText)findViewById(R.id.etProductName);
	 EditText amount = (EditText)findViewById(R.id.etAmountkg);
	 
	 if(!name.getText().toString().isEmpty() && ! amount.getText().toString().isEmpty()){
	 
	 int amnt = Integer.parseInt(amount.getText().toString());
	 String nameStr=name.getText().toString();
	 
	 cntrl.addDataInDb(new warehouseM(nameStr, amnt));
	 
	 name.setText(null);
	 amount.setText(null);
	 
	 }else{
		 Toast.makeText(this,"don't leave empty spaces", Toast.LENGTH_SHORT).show();
	 }
	 
 }
 
	public void loadList() {
		cntrl = new wareHouseListController(this, showList, dbName, mChart);

	}

	public void plusItem(View v) {
		final int index = showList.getPositionForView((View) v.getParent());

		cntrl.updateDb(index, "+");

	}

	public void minusItem(View v) {
		final int index = showList.getPositionForView((View) v.getParent());

		cntrl.updateDb(index, "-");
	}

	public void deleteItem(View v) {
		final int index = showList.getPositionForView((View) v.getParent());
		cntrl.deleteItem(index);
	}

	public void showDetails(View v) {
		final int index = showList.getPositionForView((View) v.getParent());

		final Intent intent = new Intent(
				"com.example.agriculturalwarehouse.TABLE");
		final Bundle bundle = new Bundle();
		bundle.putString("productName", cntrl.getLoadmodel().get(index)
				.getName());
		intent.putExtras(bundle);
		startActivity(intent);
	}
 
	
	 @Override
	 public void onBackPressed() {
	 	super.onBackPressed();
	 	finish();
	 }
 
    
}
