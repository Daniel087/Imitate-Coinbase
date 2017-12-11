package com.example.coinbase;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FirstPage extends Activity {
	
	private Context mContext;
	private Button button;
	private Button button1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first_main);
		mContext=this;
		button = (Button) findViewById(R.id.first_button1);
		button1 = (Button) findViewById(R.id.first_button2);
		
		button.setOnClickListener(new OnClickListener() {
				
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext, RegisterPage.class);
				startActivity(intent);
			}
		});
		
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext, LoginPage.class);
				startActivity(intent);
			}
		});
	}

}
