package com.example.coinbase;




import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.PrivateCredentialPermission;

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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request.Method;
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import android.R.color;
import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;





public class MainActivity extends Activity {
	
	private Context mContext;
	private Button sell;
	private Button sell2;
	private Button buy;
	private Button buy2;
	private Button btc;
	private Button eth;
	private Button ltc;
	private Button first;
	private static String url="https://www.okcoin.cn/api/v1/trades.do?symbol=btc_cny";
	private ArrayList<String> list=new ArrayList<String>();
	
	private TextView last;
	private TextView cnup;
	private TextView cnup1;
	private TextView btc1;
	private TextView cna;
	private TextView cnb; 
	private TextView cnc;
	private TextView name;
	private TextView email; 
	
	private Handler handler=new Handler();
	
	private int a;
	private String emaildb;
	private String okcn;
	private String okbtc;
	private String okname;
	private String okemail;
	private String oksur;
	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext=this;
		SQLiteDatabase db = openOrCreateDatabase("user.db", MODE_PRIVATE, null);
		Cursor c = db.rawQuery("select _id,email from usertb ", null);
		c.moveToFirst();
		a=c.getInt(c.getColumnIndex("_id"));
		emaildb=c.getString(c.getColumnIndex("email"));
		 
		btc1=(TextView) findViewById(R.id.textViewbtc1);
		cna=(TextView) findViewById(R.id.textViewcna);
		cnb=(TextView) findViewById(R.id.textViewcnb);
		cnc=(TextView) findViewById(R.id.textViewcnc);
		name=(TextView) findViewById(R.id.textViewname);
		email=(TextView) findViewById(R.id.textViewemail);
		
		
		last = (TextView) findViewById(R.id.textViewlast);
		cnup = (TextView) findViewById(R.id.textViewup);
		cnup1 = (TextView) findViewById(R.id.textViewup1);
		btc1 = (TextView) findViewById(R.id.textViewbtc1);
		cna = (TextView) findViewById(R.id.textViewcna);
		cnb = (TextView) findViewById(R.id.textViewcnb);
		cnc = (TextView) findViewById(R.id.textViewcnc);
		name = (TextView) findViewById(R.id.textViewname);
		email = (TextView) findViewById(R.id.textViewemail);
		
		 sell = (Button) findViewById(R.id.sellpage);
		 sell2 = (Button) findViewById(R.id.sellpage1);
		 buy = (Button) findViewById(R.id.buypage);
		 buy2 = (Button) findViewById(R.id.buypage1);
		 btc = (Button) findViewById(R.id.btcpage);
		 eth = (Button) findViewById(R.id.ethpage);
		 ltc = (Button) findViewById(R.id.ltcpage);
		 first = (Button) findViewById(R.id.firstpage);
		MyAsyncTask task = new MyAsyncTask();
		task.execute(url);
		
		 sell.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext, SellPage.class);
				startActivity(intent);
			}
		});
		 
		 sell2.setOnClickListener(new OnClickListener() {
				
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext, SellPage.class);
				startActivity(intent);
			}
		});
		 
		 buy.setOnClickListener(new OnClickListener() {
				
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext, BuyPage.class);
				startActivity(intent);
			}
		});
		 
		 buy2.setOnClickListener(new OnClickListener() {
				
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext, BuyPage.class);
				startActivity(intent);
			}
		});
		 
		 btc.setOnClickListener(new OnClickListener() {
				
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext, BtcPage.class);
				startActivity(intent);
			}
		});
		 
		 eth.setOnClickListener(new OnClickListener() {
				
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext, EthPage.class);
				startActivity(intent);
			}
		});
		 
		 ltc.setOnClickListener(new OnClickListener() {
				
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext, LtcPage.class);
				startActivity(intent);
			}
		});
		 
		 first.setOnClickListener(new OnClickListener() {
				
			@Override
			public void onClick(View v) {
				SQLiteDatabase db = openOrCreateDatabase("user.db", MODE_PRIVATE, null);
				db.execSQL("delete from usertb");
				Intent intent = new Intent(mContext, FirstPage.class);
				startActivity(intent);
			}
		});
		



	}
	/**
	 * ��ҳ���˻���Ϣ����
	 */
	@Override
	protected void onResume(){
		new Thread(){
			public void run(){
				String url="http://ip��ַ:8080/demo/login";
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
						 okcn=jsonObject.getString("cn");
						 okbtc=jsonObject.getString("btc");
						 oksur=jsonObject.getString("surname");
						 okname=jsonObject.getString("name");
						 okemail=jsonObject.getString("email");
						
						handler.post(new Runnable() {
							
							@Override
							public void run() {
								btc1.setText(okbtc);
								cna.setText(okcn);	
								cnb.setText(okcn);
								cnc.setText(okcn);
								email.setText(okemail);
								name.setText(oksur+okname);
								
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

	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 
	 * ͼ����ʽ����
	 * 
	 * 
	 */
	
	 // ����chart��ʾ����ʽ
    private void setChartStyle(LineChart mLineChart, LineData lineData,
                               int color) {
        // �Ƿ�������ͼ����ӱ߿�
        mLineChart.setDrawBorders(false);

        mLineChart.setDescription("���ر�����ͼ");// ��������

        // ���û�����ݵ�ʱ�򣬻���ʾ���������listview��emtpyview
        mLineChart.setNoDataTextDescription("�������MPAndroidChart������Ϊ�գ���ô�㽫����������֡�");

        // �Ƿ���Ʊ�����ɫ��
        // ���mLineChart.setDrawGridBackground(false)��
        // ��ômLineChart.setGridBackgroundColor(Color.CYAN)��ʧЧ;
        mLineChart.setDrawGridBackground(false);
        mLineChart.setGridBackgroundColor(Color.CYAN);

        // ����
        mLineChart.setTouchEnabled(true);

        // ��ק
        mLineChart.setDragEnabled(true);

        // ����
        mLineChart.setScaleEnabled(true);

        mLineChart.setPinchZoom(false);
        // �����ұ� ��������
//        mLineChart.getAxisRight().setEnabled(false);
        // ��x��������
        mLineChart.getXAxis().setPosition(XAxisPosition.BOTTOM);

        // // ��������������������
        // mLineChart.getAxisLeft().setDrawGridLines(false);
        // // �����ұ��������������
        // mLineChart.getAxisRight().setDrawGridLines(false);
        // // ����X����������
         mLineChart.getXAxis().setDrawGridLines(false);
        mLineChart.getAxisRight().setEnabled(false); // �����ұ� ��������(true������)
        
        mLineChart.getXAxis().setPosition(XAxisPosition.BOTTOM); // ��x��������
        // ���ñ���
//        mLineChart.setBackgroundColor(color);

        // ����x,y�������
        mLineChart.setData(lineData);
        
        XAxis mXAxis = mLineChart.getXAxis();  //��ȡX��
        mXAxis.setAxisLineColor(Color.WHITE);
        mXAxis.setAxisLineWidth(1f);
        mXAxis.setTextColor(Color.WHITE);
        
        YAxis mLeftYAxis =mLineChart.getAxisLeft();//��ȡY��
        mLeftYAxis.setAxisLineColor(Color.WHITE);
        mLeftYAxis.setAxisLineWidth(1f);
        mLeftYAxis.setTextColor(Color.WHITE);
        mLeftYAxis.setGridColor(Color.WHITE);
//        mLeftYAxis.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
                                          
        

        // ���ñ���ͼ��ʾ�������Ǹ�һ��y��value��
        Legend mLegend = mLineChart.getLegend();

        mLegend.setPosition(LegendPosition.BELOW_CHART_CENTER);
        mLegend.setForm(LegendForm.CIRCLE);// ��ʽ
        mLegend.setFormSize(0.0f);// ����
        mLegend.setTextColor(Color.WHITE);// ��ɫ

        // ��x�ᶯ����ʱ��2000���롣
        mLineChart.animateX(2000);
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     *  ���ݵ������
     * 	ͼ����������
     */
    //��������int count
    private LineData makeLineData() {
        ArrayList<String> x = new ArrayList<String>();
        
        for (int i = 0; i < list.size(); i++) {
            // x����ʾ������
            x.add("x:" + i);
        }

        // y�������
        ArrayList<Entry> y = new ArrayList<Entry>();
        for (int i = 0; i < list.size(); i++) {
//            float val = (float) (Math.random() * 100000);
        	float val = Float.parseFloat(list.get(i));
            Entry entry = new Entry(val, i);
            y.add(entry);
        }

        // y�����ݼ�
        LineDataSet mLineDataSet = new LineDataSet(y, "");

        // ��y��ļ��������ò���
        // �߿�
        mLineDataSet.setLineWidth(2.0f);

        // ��ʾ��Բ�δ�С
        mLineDataSet.setCircleSize(0.0f);

        // ���ߵ���ɫ
        mLineDataSet.setColor(Color.WHITE);

        // Բ�����ɫ
        mLineDataSet.setCircleColor(Color.GREEN);

        // ����mLineDataSet.setDrawHighlightIndicators(false)��
        // Highlight��ʮ�ֽ�����ݺ��߽�������ʾ��
        // ͬʱ��mLineDataSet.setHighLightColor(Color.CYAN)ʧЧ��
        
        mLineDataSet.setDrawHighlightIndicators(true);
        

        // ������ʮ�ֽ����ߵ���ɫ
        mLineDataSet.setHighLightColor(Color.CYAN);

        // ������������ʾ�����ݵ�������С��
        mLineDataSet.setValueTextSize(10.0f);
        mLineDataSet.setValueTextColor(Color.WHITE);
        
        
        

        // mLineDataSet.setDrawCircleHole(true);

        // �ı�������ʽ�������ߡ�
        // mLineDataSet.setDrawCubic(true);
        // Ĭ����ֱ��
        // ���ߵ�ƽ���ȣ�ֵԽ��Խƽ����
        // mLineDataSet.setCubicIntensity(0.2f);

        // ��������·������򣬺�ɫ����͸����
        // mLineDataSet.setDrawFilled(true);
        // mLineDataSet.setFillAlpha(128);
        // mLineDataSet.setFillColor(Color.RED);
        

        // ������������ݵ㡢Բ��������������Ŀհ״�����ɫ��
        mLineDataSet.setCircleColorHole(Color.YELLOW);
        
        

        // ������������ʾ���ݵĸ�ʽ����������ã���Ĭ����ʾfloat���ݸ�ʽ��
        mLineDataSet.setValueFormatter(new ValueFormatter() {
        	


            @Override
            public String getFormattedValue(float value, Entry entry,
                                            int dataSetIndex, ViewPortHandler viewPortHandler) {
                // TODO Auto-generated method stub
                int n = (int) value;
//                String s = "\u00A5" + n;
                String s ="";
                return s;
            }
        });

        ArrayList<LineDataSet> mLineDataSets = new ArrayList<LineDataSet>();
        mLineDataSets.add(mLineDataSet);

        LineData mLineData = new LineData(x, mLineDataSets);

        return mLineData;
    }

    
    
    
    
    
    
    /**
     * �������¼۸����ʷ��۸���UI
     * ������ʷ�������ݸ���ͼ��
     *
     */
    
    
	
	
	 class MyAsyncTask extends AsyncTask<String, Void, Void >{
		 private String abcString;
		  
		 
		
		@Override
		protected Void doInBackground(String... params) {
			String url2="https://www.okcoin.cn/api/v1/ticker.do?symbol=btc_cny";
			String url = params[0];
			HttpGet httpGet2  = new HttpGet(url2);
			HttpGet httpGet  = new HttpGet(url);
			HttpClient client = new DefaultHttpClient();
			HttpClient client2 = new DefaultHttpClient();
			HttpResponse response;
			HttpResponse response2;
			try {
				response=client.execute(httpGet);
				response2=client2.execute(httpGet2);
				if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
//					
					String content = EntityUtils.toString(response.getEntity());
					String content2= EntityUtils.toString(response2.getEntity());
//					//System.out.println("content------->"+content);
					Log.d("asd", content);
					Log.d("asd", content2);
//					Toast.makeText(MainActivity.this,"heloo", Toast.LENGTH_LONG).show();
					JSONObject jsonObject;
					JSONObject jsonObject2;
					JSONObject jsonObject3;
					try {
						jsonObject2=new JSONObject(content2);
						jsonObject3=jsonObject2.getJSONObject("ticker");
						 abcString=jsonObject3.getString("last");
						
						JSONArray jsonArray=new JSONArray(content);
						Log.d("qwe", ""+jsonArray.length());
						for (int i = 0; i < jsonArray.length(); i++) {
							jsonObject=jsonArray.getJSONObject(i);
							list.add(jsonObject.getString("price")) ;
							Log.d("add", ""+list.get(i));
//							Float.parseFloat()
						}
//						String jiage=jsonObject2.getString("last");
						Log.d("add1", ""+abcString);
					} catch (JSONException e) {						
						e.printStackTrace();
					}
				}
			} catch (ClientProtocolException e) {				
				e.printStackTrace();
			} catch (IOException e) {				
				e.printStackTrace();
			}
							
			
			return null;
		}
		
		
		
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			Log.d("mnb", "HelloWord");
	        // ����7�����ݵ㣨��x�����ᣩ
			LineChart chart = (LineChart) findViewById(R.id.chart);
	        LineData mLineData = makeLineData();
	        setChartStyle(chart, mLineData, Color.WHITE);
	        last.setText(abcString);
//	        float a=Float.parseFloat(list.get(29))-Float.parseFloat(list.get(0));
//	        cnup.setText(a);
//	        Log.d("rfv", ""+a);
	        BigDecimal big =new BigDecimal(list.get(0));
	        BigDecimal big1 =new BigDecimal(list.get(59));
	        big.subtract(big1).toString();
	        Log.d("rfv", big.subtract(big1).toString());
	        if(Float.parseFloat(list.get(0))>Float.parseFloat(list.get(59)))
	        		{
	        			cnup.setText(big.subtract(big1).toString());
	        			cnup1.setText("�½�");
	        			cnup1.setTextColor(Color.RED);
	        		}
	        else if(Float.parseFloat(list.get(0))<Float.parseFloat(list.get(59))) 
	        		{
	        			cnup.setText(big1.subtract(big).toString());
	        			cnup1.setText("����");
	        			cnup1.setTextColor(Color.GREEN);
				
	        		}
	        else {
	        	cnup.setText(big1.subtract(big).toString());
    			cnup1.setText("��ƽ");
			}
	        
	        
	        
		}
		
	}
	
	
	


}
