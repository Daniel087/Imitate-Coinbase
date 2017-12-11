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

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class HttpClientThread1 extends Thread {
	
	private String url;
	private String email;

	private int a;
	private HttpResponse response;
	private String content;
	private String passwordString;
	public   HttpClientThread1(String url,String email,String passwordString) {
		
		this.url=url;
		this.email=email;
		this.passwordString=passwordString;

	}
	private void doHttpClientPost() throws UnsupportedEncodingException, JSONException{
		
		HttpPost post=new HttpPost(url);
		HttpClient client =new DefaultHttpClient();
//		HttpResponse response;
		ArrayList<NameValuePair> list=new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("email", email));
		post.setEntity(new UrlEncodedFormEntity(list,HTTP.UTF_8));
		
		try {
			 response=client.execute(post);
			if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				
				 content = EntityUtils.toString(response.getEntity());
				Log.d("jkl4", content);
			}
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
	}
	
	@Override
	public void run() {
		try {
			doHttpClientPost();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}
}
