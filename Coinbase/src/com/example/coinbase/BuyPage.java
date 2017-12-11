package com.example.coinbase;




import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
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

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class BuyPage extends Activity{
	
	private TextView textbtc;
	private TextView btccn;
	private TextView ltccn;
	private TextView ethcn;
	private EditText EditText;
	private Button Button;
	private RadioGroup rGroup;
	private RadioButton a1,a2,a3,a4;
	private Handler handler = new Handler();
	
	private String email;
	private int a;
	
	private String cnn;
	private String btcString;
		
	private String lastbtc;
	
	private String okcn;
	private String okbtc;
	private String overcn;
	private String okcn1;
	private String okbtc1;


	/**
	 * 购买页面
	 * 用户充值钱包根据当前最新价格兑换BTC
	 * 并且更新当前的价格显示
	 */
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.buy_page);
		textbtc=(TextView) findViewById(R.id.textView17);
		btccn=(TextView) findViewById(R.id.textView19);
		ltccn=(TextView) findViewById(R.id.textView24);
		ethcn=(TextView) findViewById(R.id.textView29);
		EditText=(android.widget.EditText) findViewById(R.id.editText1);
		Button=(android.widget.Button) findViewById(R.id.button1);
		rGroup=(RadioGroup) findViewById(R.id.radioGroup1);
		
		a1 = (RadioButton) findViewById(R.id.radio0);
		a2 = (RadioButton) findViewById(R.id.radio1);
		a3 = (RadioButton) findViewById(R.id.radio2);
		a4 = (RadioButton) findViewById(R.id.radio3);
		
		SQLiteDatabase db = openOrCreateDatabase("user.db", MODE_PRIVATE, null);
		Cursor c = db.rawQuery("select _id,email from usertb ", null);
		c.moveToFirst();
		a=c.getInt(c.getColumnIndex("_id"));
		email=c.getString(c.getColumnIndex("email"));
		
		
		Button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
		    		
			int selectedId = rGroup.getCheckedRadioButtonId();			
			if(selectedId == a1.getId()){

				new Thread(){
					public void run(){
						String url1="http://ip地址:8080/demo/updatecn";
						String url2="http://ip地址:8080/demo/login";
					    String url3="https://www.okcoin.cn/api/v1/ticker.do?symbol=btc_cny";
					    
						HttpPost post=new HttpPost(url1);
						HttpPost post1=new HttpPost(url2);
						
						HttpClient client =new DefaultHttpClient();
						HttpClient client1 =new DefaultHttpClient();
						
						HttpResponse response;
						HttpResponse response1;
						
//						Float cnn =Float.parseFloat(EditText.getText().toString());
						ArrayList<NameValuePair> list=new ArrayList<NameValuePair>();
						list.add(new BasicNameValuePair("email", email));
						list.add(new BasicNameValuePair("cn", EditText.getText().toString()));
						
						ArrayList<NameValuePair> list1=new ArrayList<NameValuePair>();
						list1.add(new BasicNameValuePair("email", email));
						
						
						try {							
							post.setEntity(new UrlEncodedFormEntity(list,HTTP.UTF_8));
							post1.setEntity(new UrlEncodedFormEntity(list1,HTTP.UTF_8));
							
							response=client.execute(post);
							response1=client1.execute(post1);
							
							if(response1.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
								
								String content = EntityUtils.toString(response1.getEntity());
								JSONObject jsonObject=new JSONObject(content);
								 cnn=jsonObject.getString("cn");
//								 btcString=jsonObject.getString("btc");
								
								handler.post(new Runnable() {
									
									@Override
									public void run() {
//										textbtc.setText(btcString);
										btccn.setText(cnn);									
										ltccn.setText(cnn);
										ethcn.setText(cnn);
									}
								});

							}
							if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
								 
								Looper.prepare();
								Toast.makeText(BuyPage.this,"购买成功", Toast.LENGTH_LONG).show();
								Looper.loop();
						} 
							
						} catch (UnsupportedEncodingException e) {
							
							e.printStackTrace();
						} catch (ClientProtocolException e) {
							
							e.printStackTrace();
						} catch (IOException e) {
							
							e.printStackTrace();
						} catch (JSONException e) {
							
							e.printStackTrace();
						}
						
						
						
					};
				}.start();

				Log.d("cn",""+a+email);
			}
			else {
				new Thread(){
					public void run(){
						String url1="http://ip地址:8080/demo/updatebtc";
						String url2="http://ip地址:8080/demo/login";
					    String url3="https://www.okcoin.cn/api/v1/ticker.do?symbol=btc_cny";
					    HttpGet httpGet  = new HttpGet(url3);
					    HttpClient client = new DefaultHttpClient();
					    HttpResponse response;
					    try {
							response=client.execute(httpGet);
							if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
								String content = EntityUtils.toString(response.getEntity());
								Log.d("asd", content);
								
								JSONObject jsonObject2=new JSONObject(content);
								JSONObject jsonObject3=jsonObject2.getJSONObject("ticker");
								 lastbtc=jsonObject3.getString("last");
								 Log.d("asd", lastbtc);
								 BigDecimal input =new BigDecimal(EditText.getText().toString());
							     BigDecimal last =new BigDecimal(lastbtc);
							     BigDecimal xianzai =new BigDecimal(btccn.getText().toString());
							     BigDecimal jianshu =new BigDecimal(input.multiply(last).toString());
								 overcn=xianzai.subtract(jianshu).toString();
								 Log.d("asd", overcn);
								 if(Float.parseFloat(overcn)>=0){
									 
									 HttpPost posta=new HttpPost(url1);
										HttpPost postb=new HttpPost(url2);
										
										HttpClient clienta =new DefaultHttpClient();
										HttpClient clientb =new DefaultHttpClient();
										
										HttpResponse responsea;
										HttpResponse responseb;
										
//										Float cnn =Float.parseFloat(EditText.getText().toString());
										ArrayList<NameValuePair> lista=new ArrayList<NameValuePair>();
										lista.add(new BasicNameValuePair("email", email));
										lista.add(new BasicNameValuePair("cn", overcn));
										lista.add(new BasicNameValuePair("btc", EditText.getText().toString()));
										
										ArrayList<NameValuePair> listb=new ArrayList<NameValuePair>();
										listb.add(new BasicNameValuePair("email", email));
										
										posta.setEntity(new UrlEncodedFormEntity(lista,HTTP.UTF_8));
										postb.setEntity(new UrlEncodedFormEntity(listb,HTTP.UTF_8));
										
										responsea=clienta.execute(posta);
										responseb=clientb.execute(postb);
										if(responsea.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
											
											String contentb = EntityUtils.toString(responseb.getEntity());
											JSONObject jsonObjectb=new JSONObject(contentb);
											 okcn=jsonObjectb.getString("cn");
											 okbtc=jsonObjectb.getString("btc");
											
											handler.post(new Runnable() {
												
												@Override
												public void run() {
													textbtc.setText(okbtc);
													btccn.setText(okcn);									
													ltccn.setText(okcn);
													ethcn.setText(okcn);
												}
											});
											Looper.prepare();
											Toast.makeText(BuyPage.this,"购买成功", Toast.LENGTH_LONG).show();
											Looper.loop();

										}
									 
								 }
								 else {
									 Looper.prepare();
										Toast.makeText(BuyPage.this,"余额不足", Toast.LENGTH_LONG).show();
										Looper.loop();
								}
								
								
							}
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
				Log.d("btc", "btc");
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
				list.add(new BasicNameValuePair("email", email));
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
								textbtc.setText(okbtc1);
								btccn.setText(okcn1);									
								ltccn.setText(okcn1);
								ethcn.setText(okcn1);								
								
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
