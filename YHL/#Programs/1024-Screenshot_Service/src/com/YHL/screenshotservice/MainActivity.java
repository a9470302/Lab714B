package com.YHL.screenshotservice;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	EditText ET1;
	Button B1,B2,B3,B4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ET1 = (EditText)findViewById(R.id.ET1);
		B1 = (Button)findViewById(R.id.B1);
		B1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent I = new Intent(MainActivity.this,Service_SendView.class);
				Bundle bundle = new Bundle();
				bundle.putString("IP",ET1.getText().toString());
				I.putExtras(bundle);
				startService(I);
			}
		});
		B2 = (Button)findViewById(R.id.B2);
		B2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				stopService(new Intent(MainActivity.this,Service_SendView.class));
			}
		});
		B3 = (Button)findViewById(R.id.B3);
		B3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent I2 = new Intent(MainActivity.this,Service_SendView2.class);
				Bundle bundle = new Bundle();
				bundle.putString("IP",ET1.getText().toString());
				I2.putExtras(bundle);
				startService(I2);
			}
		});
		B4 = (Button)findViewById(R.id.B4);
		B4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				stopService(new Intent(MainActivity.this,Service_SendView2.class));
			}
		});
	}
}
