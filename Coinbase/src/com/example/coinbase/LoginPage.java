package com.example.coinbase;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.coinbase.MainActivity.MyAsyncTask;

import android.R.integer;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPage extends Activity{
	
	private EditText email;
	private EditText password;
	private Button login;
	private Context mContext=this;
	private int a;
	private String url="http://ipµÿ÷∑:8080/demo/login";
	
	/**
	 * ”√ªßµ«¬º
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		email=(EditText) findViewById(R.id.email2);
		password=(EditText) findViewById(R.id.password3); 
		login=(Button) findViewById(R.id.button_login);
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
										
				new Thread(){
					public void run(){
						String url="http://ipµÿ÷∑:8080/demo/login";
						HttpPost post=new HttpPost(url);
						HttpClient client =new DefaultHttpClient();
						HttpResponse response;
						ArrayList<NameValuePair> list=new ArrayList<NameValuePair>();
						list.add(new BasicNameValuePair("email", email.getText().toString()));
						try {
							String content;
							post.setEntity(new UrlEncodedFormEntity(list,HTTP.UTF_8));
							response=client.execute(post);
							 content = EntityUtils.toString(response.getEntity());
							if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
																 
								Log.d("jkl4", content);								
						} 
							JSONObject jsonObject=new JSONObject(content);
							String word=jsonObject.getString("password");
							
							
							
							
							if(response.getStatusLine().getStatusCode()!=HttpStatus.SC_OK)
								{
								Log.d("cvb1", "” œ‰≤ª¥Ê‘⁄");
								}
							
								else if(!password.getText().toString().equals(word)) {
									Log.d("cvb2", "√‹¬Î¥ÌŒÛ");
									Looper.prepare();
									Toast.makeText(LoginPage.this,"√‹¬Î¥ÌŒÛ", Toast.LENGTH_LONG).show();
									Looper.loop();
									
									
								}
								else {
									Log.d("cvb3", "µ«¬º≥…π¶");
									SQLiteDatabase db = openOrCreateDatabase("user.db", MODE_PRIVATE, null);									
									ContentValues values = new ContentValues();  
					                values.put("_id", 1);  
					                values.put("email", email.getText().toString());  
									db.insert("usertb", null, values);
									Intent intent = new Intent(mContext, MainActivity.class);
									startActivity(intent);
									
									Looper.prepare();
									Toast.makeText(LoginPage.this,"µ«¬º≥…π¶", Toast.LENGTH_LONG).show();
									Looper.loop();
																		
								}														
						}catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						} catch (ClientProtocolException e) {							
							e.printStackTrace();
						} catch (IOException e) {							
							e.printStackTrace();
						} catch (JSONException e) {							
							e.printStackTrace();
							Log.d("cvb1", "” œ‰≤ª¥Ê‘⁄");
							Looper.prepare();
							Toast.makeText(LoginPage.this,"” œ‰≤ª¥Ê‘⁄", Toast.LENGTH_LONG).show();
							Looper.loop();
						}
					};
				}.start();
				
								
			}
		});
		
		
		
		
	}
			
}
