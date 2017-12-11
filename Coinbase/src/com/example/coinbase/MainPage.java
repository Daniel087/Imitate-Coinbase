package com.example.coinbase;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainPage extends Activity{
	private Context mContext;
	/**
	 * Ӧ������ҳ��
	 * ���ݿͻ��˷��������û���Ϣ
	 * ���ж���ת����ҳ�����ע���¼ҳ��
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);		
		
		SQLiteDatabase db = openOrCreateDatabase("user.db", MODE_PRIVATE, null);
		db.execSQL("create table if not exists usertb(_id integer primary key autoincrement,email text not null)");
		Cursor c =db.rawQuery("select * from usertb", null);
		if(c.getCount()==0)
		{
			Intent intent = new Intent(this, FirstPage.class);
			startActivity(intent);
			
		}
		else {
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
		}
	}
	
	

}
