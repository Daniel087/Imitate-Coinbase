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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class BtcPage extends Activity{
	private TextView cn;
	private TextView btc;
	private Button send;
	private Button income;
	private EditText email;
	private EditText number;
	private RadioGroup rGroup;
	private RadioButton a1,a2;
	private Handler handler = new Handler();
	
	private String emaildb;
	private int a;
	
	private String okcn1;
	private String okbtc1;
	
	/**
	 * BTC页面
	 * 用户输入目标邮箱进行收发钱币
	 * 并且更新当前的价格显示
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.btc_page);
		cn=(TextView) findViewById(R.id.textView3);
		btc=(TextView) findViewById(R.id.textView5);
		income=(Button) findViewById(R.id.button1);
		send=(Button) findViewById(R.id.button2);
		email=(EditText) findViewById(R.id.editText1);
		number=(EditText) findViewById(R.id.editText2);
		rGroup=(RadioGroup) findViewById(R.id.radioGroup1);
		a1 = (RadioButton) findViewById(R.id.radio0);
		a2 = (RadioButton) findViewById(R.id.radio1);
		
		SQLiteDatabase db = openOrCreateDatabase("user.db", MODE_PRIVATE, null);
		Cursor c = db.rawQuery("select _id,email from usertb ", null);
		c.moveToFirst();
		a=c.getInt(c.getColumnIndex("_id"));
		emaildb=c.getString(c.getColumnIndex("email"));
		
		
		send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int selectedId = rGroup.getCheckedRadioButtonId();
				if(selectedId == a1.getId()){
					new Thread(){
						public void run(){
							String url1="http://ip地址:8080/demo/sendbtc";
							String url2="http://ip地址:8080/demo/login";
							
							HttpPost post=new HttpPost(url1);
							HttpPost post1=new HttpPost(url2);
							
							HttpClient client =new DefaultHttpClient();
							HttpClient client1 =new DefaultHttpClient();
							
							HttpResponse response;
							HttpResponse response1;
							

							ArrayList<NameValuePair> list=new ArrayList<NameValuePair>();
							list.add(new BasicNameValuePair("email1", emaildb));
							list.add(new BasicNameValuePair("email2", email.getText().toString()));
							list.add(new BasicNameValuePair("btc", number.getText().toString()));
							
							ArrayList<NameValuePair> list1=new ArrayList<NameValuePair>();
							list1.add(new BasicNameValuePair("email", emaildb));
							
							try {
								post.setEntity(new UrlEncodedFormEntity(list,HTTP.UTF_8));
								post1.setEntity(new UrlEncodedFormEntity(list1,HTTP.UTF_8));
								response=client.execute(post);
								response1=client1.execute(post1);
								
								if(response1.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
									
									String content = EntityUtils.toString(response1.getEntity());
									JSONObject jsonObject=new JSONObject(content);
									 okcn1=jsonObject.getString("cn");
									 okbtc1=jsonObject.getString("btc");
									
									handler.post(new Runnable() {
										
										@Override
										public void run() {
											btc.setText(okbtc1);
											cn.setText(okcn1);									
											
										}
									});

								}
								if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
									 
									Looper.prepare();
									Toast.makeText(BtcPage.this,"发送BTC成功", Toast.LENGTH_LONG).show();
									Looper.loop();
							} 
								
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ClientProtocolException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							
							
							
						};
					}.start();
					Log.d("cn",emaildb+"a");
					
				}else {
					new Thread(){
						public void run(){
							String url1="http://ip地址:8080/demo/sendcn";
							String url2="http://ip地址:8080/demo/login";
							
							HttpPost post=new HttpPost(url1);
							HttpPost post1=new HttpPost(url2);
							
							HttpClient client =new DefaultHttpClient();
							HttpClient client1 =new DefaultHttpClient();
							
							HttpResponse response;
							HttpResponse response1;
							

							ArrayList<NameValuePair> list=new ArrayList<NameValuePair>();
							list.add(new BasicNameValuePair("email1", emaildb));
							list.add(new BasicNameValuePair("email2", email.getText().toString()));
							list.add(new BasicNameValuePair("cn", number.getText().toString()));
							
							ArrayList<NameValuePair> list1=new ArrayList<NameValuePair>();
							list1.add(new BasicNameValuePair("email", emaildb));
							
							try {
								post.setEntity(new UrlEncodedFormEntity(list,HTTP.UTF_8));
								post1.setEntity(new UrlEncodedFormEntity(list1,HTTP.UTF_8));
								response=client.execute(post);
								response1=client1.execute(post1);
								
								if(response1.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
									
									String content = EntityUtils.toString(response1.getEntity());
									JSONObject jsonObject=new JSONObject(content);
									 okcn1=jsonObject.getString("cn");
									 okbtc1=jsonObject.getString("btc");
									
									handler.post(new Runnable() {
										
										@Override
										public void run() {
											btc.setText(okbtc1);
											cn.setText(okcn1);									
											
										}
									});

								}
								if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
									 
									Looper.prepare();
									Toast.makeText(BtcPage.this,"发送CN成功", Toast.LENGTH_LONG).show();
									Looper.loop();
							} 
								
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ClientProtocolException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						};
					}.start();
					Log.d("cn",emaildb+"b");
				}
				
				
			}
		});
		
		income.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int selectedId = rGroup.getCheckedRadioButtonId();
				if(selectedId == a1.getId()){
					new Thread(){
						public void run(){
							String url1="http://ip地址:8080/demo/incomebtc";
							String url2="http://ip地址:8080/demo/login";
							
							HttpPost post=new HttpPost(url1);
							HttpPost post1=new HttpPost(url2);
							
							HttpClient client =new DefaultHttpClient();
							HttpClient client1 =new DefaultHttpClient();
							
							HttpResponse response;
							HttpResponse response1;
							

							ArrayList<NameValuePair> list=new ArrayList<NameValuePair>();
							list.add(new BasicNameValuePair("email1", emaildb));
							list.add(new BasicNameValuePair("email2", email.getText().toString()));
							list.add(new BasicNameValuePair("btc", number.getText().toString()));
							
							ArrayList<NameValuePair> list1=new ArrayList<NameValuePair>();
							list1.add(new BasicNameValuePair("email", emaildb));
							
							try {
								post.setEntity(new UrlEncodedFormEntity(list,HTTP.UTF_8));
								post1.setEntity(new UrlEncodedFormEntity(list1,HTTP.UTF_8));
								response=client.execute(post);
								response1=client1.execute(post1);
								
								if(response1.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
									
									String content = EntityUtils.toString(response1.getEntity());
									JSONObject jsonObject=new JSONObject(content);
									 okcn1=jsonObject.getString("cn");
									 okbtc1=jsonObject.getString("btc");
									
									handler.post(new Runnable() {
										
										@Override
										public void run() {
											btc.setText(okbtc1);
											cn.setText(okcn1);									
											
										}
									});

								}
								if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
									 
									Looper.prepare();
									Toast.makeText(BtcPage.this,"接收BTC成功", Toast.LENGTH_LONG).show();
									Looper.loop();
							} 
								
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ClientProtocolException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						};
					}.start();
					Log.d("cn",emaildb+"c");
				}else {
					new Thread(){
						public void run(){
							String url1="http://ip地址:8080/demo/incomecn";
							String url2="http://ip地址:8080/demo/login";
							
							HttpPost post=new HttpPost(url1);
							HttpPost post1=new HttpPost(url2);
							
							HttpClient client =new DefaultHttpClient();
							HttpClient client1 =new DefaultHttpClient();
							
							HttpResponse response;
							HttpResponse response1;
							

							ArrayList<NameValuePair> list=new ArrayList<NameValuePair>();
							list.add(new BasicNameValuePair("email1", emaildb));
							list.add(new BasicNameValuePair("email2", email.getText().toString()));
							list.add(new BasicNameValuePair("cn", number.getText().toString()));
							
							ArrayList<NameValuePair> list1=new ArrayList<NameValuePair>();
							list1.add(new BasicNameValuePair("email", emaildb));
							
							try {
								post.setEntity(new UrlEncodedFormEntity(list,HTTP.UTF_8));
								post1.setEntity(new UrlEncodedFormEntity(list1,HTTP.UTF_8));
								response=client.execute(post);
								response1=client1.execute(post1);
								
								if(response1.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
									
									String content = EntityUtils.toString(response1.getEntity());
									JSONObject jsonObject=new JSONObject(content);
									 okcn1=jsonObject.getString("cn");
									 okbtc1=jsonObject.getString("btc");
									
									handler.post(new Runnable() {
										
										@Override
										public void run() {
											btc.setText(okbtc1);
											cn.setText(okcn1);									
											
										}
									});

								}
								if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
									 
									Looper.prepare();
									Toast.makeText(BtcPage.this,"接收CN成功", Toast.LENGTH_LONG).show();
									Looper.loop();
							} 
								
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ClientProtocolException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						};
					}.start();
					Log.d("cn",emaildb+"d");
				}
				
			}
		});
	}

	@Override
	protected void onResume(){
		new Thread(){
			public void run(){
				String url="http://ip地址:8080/demo/login";
				HttpPost post=new HttpPost(url);
				HttpClient client =new DefaultHttpClient();
				HttpResponse response;
				ArrayList<NameValuePair> list=new ArrayList<NameValuePair>();
				list.add(new BasicNameValuePair("email", emaildb));
				try {
					post.setEntity(new UrlEncodedFormEntity(list,HTTP.UTF_8));
					response=client.execute(post);
					if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
						
						String content = EntityUtils.toString(response.getEntity());
						JSONObject jsonObject=new JSONObject(content);
						 okcn1=jsonObject.getString("cn");
						 okbtc1=jsonObject.getString("btc");
						
						handler.post(new Runnable() {
							
							@Override
							public void run() {
								btc.setText(okbtc1);
								cn.setText(okcn1);									
								
							}
						});

					}
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};
		}.start();
		super.onResume();
	}
}
