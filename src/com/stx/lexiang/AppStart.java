package com.stx.lexiang;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.stx.denglu.LoginActivity;
import com.stx.denglu.R;

public class AppStart extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		new Handler().postDelayed(new Runnable(){
			//跳转到登录界面
			@Override
			public void run(){
				Intent intent = new Intent (AppStart.this,LoginActivity.class);			
				startActivity(intent);			
				AppStart.this.finish();
			}
		}, 1000);//设定时间
	}

}
