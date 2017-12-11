package com.example.coinbase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class HttpThread1 extends Thread {
	
	String url;
	String email;
	String surname;
	String name;
	String password;
	
	public  HttpThread1(String url,String email,String surname,String name,String password) {
		
		this.url=url;
		this.email=email;
		this.surname=surname;
		this.name=name;
		this.password=password;
	}
	
	private void doPost(){
		try {
			URL httpUrl=new URL(url);
			try {
				
				HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
				conn.setRequestMethod("POST");
				conn.setReadTimeout(5000);
				conn.setDoOutput(true);
//				conn.setRequestProperty("Charset", "UTF-8");
				conn.setDoInput(true);
				String content ="?email="+URLEncoder.encode(email, "UTF-8")+"&surname="+URLEncoder.encode(surname, "UTF-8")+"&name="+URLEncoder.encode(name, "UTF-8")+"&password"+URLEncoder.encode(password, "UTF-8");
				
				conn.setRequestProperty("Content-Type",  
	                    "application/x-www-form-urlencoded");  				
				
				conn.setRequestProperty("Connection", "keep-alive");
				conn.setRequestProperty("Content-Length",  
	                    String.valueOf(content.getBytes().length));
				conn.setRequestProperty("User-Agent",  
                        "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:27.0) Gecko/20100101 Firefox/27.0");
				
				OutputStream out = conn.getOutputStream();
				out.write(content.getBytes());
				
				BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream())); 
				StringBuffer sb = new StringBuffer();
				String str;
				
				while((str=reader.readLine())!=null)
				{
					sb.append(str);
				}
				
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		doPost();
	}

}
