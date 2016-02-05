package com.example.agriculturalwarehouse;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


 public void clkSilos(View v){

		final Intent intent = new Intent("com.example.agriculturalwarehouse.TRACT");
		final Bundle bundle = new Bundle();
		bundle.putString("dbName", "SILOS");
		intent.putExtras(bundle);
		startActivity(intent);
 }
 
 public void clkTank(View v){

		final Intent intent = new Intent("com.example.agriculturalwarehouse.TRACT");
		final Bundle bundle = new Bundle();
		bundle.putString("dbName", "LIQUID_TANKS");
		intent.putExtras(bundle);
		startActivity(intent);
 }
 
 public void clkAll(View v){

	 final Intent intent = new Intent("com.example.agriculturalwarehouse.TABLE");
	 startActivity(intent);
 }
 
 @Override
public void onBackPressed() {
	super.onBackPressed();
	finish();
}
    
}
