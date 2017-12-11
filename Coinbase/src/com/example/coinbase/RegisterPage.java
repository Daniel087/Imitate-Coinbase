package com.example.coinbase;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterPage extends Activity{
	
	private EditText email;
	private EditText surname;
	private EditText name;
	private EditText password1;
	private EditText password2;
	private Button register;
	
	/**
	 * ע��ҳ��
	 * �û���д�û���Ϣ�ύ������������
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		email=(EditText) findViewById(R.id.email);
		surname=(EditText) findViewById(R.id.surname);
		name=(EditText) findViewById(R.id.name);
		password1=(EditText) findViewById(R.id.password1);
		password2=(EditText) findViewById(R.id.password2);
		register=(Button) findViewById(R.id.button2);
		register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String url="http://ip��ַ:8080/demo/data";
				if(password1.getText().toString().equals(password2.getText().toString()))
				{
//					new HttpThread1(url, email.getText().toString(), surname.getText().toString(), name.getText().toString(), password1.getText().toString()).start();
					new HttpClientThread(url, email.getText().toString(), surname.getText().toString(), name.getText().toString(), password1.getText().toString()).start();
					Toast.makeText(RegisterPage.this, "ע��ɹ�", Toast.LENGTH_LONG).show();
				}
				else {
					Toast.makeText(RegisterPage.this, "�������벻һ��", Toast.LENGTH_LONG).show();
				}
			}
		});
	}

}
