package com.example.coinbase;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

import android.util.Log;

public class HttpClientThread extends Thread {
	
	private String url;
	private String email;
	private String surname;
	private String name;
	private String password;
	
	public  HttpClientThread(String url,String email,String surname,String name,String password) {
		
		this.url=url;
		this.email=email;
		this.surname=surname;
		this.name=name;
		this.password=password;
	}
	private void doHttpClientPost() throws UnsupportedEncodingException{
		
		HttpClient client =new DefaultHttpClient();
		HttpPost post=new HttpPost(url);

		ArrayList<NameValuePair> list=new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("email", email));
		list.add(new BasicNameValuePair("surname",surname));
		list.add(new BasicNameValuePair("name", name));
		list.add(new BasicNameValuePair("password", password));
		
		post.setEntity(new UrlEncodedFormEntity(list,HTTP.UTF_8));
		
		try {
			HttpResponse response=client.execute(post);
			if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				
				String content = EntityUtils.toString(response.getEntity());
				Log.d("jkl", content);
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			doHttpClientPost();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
