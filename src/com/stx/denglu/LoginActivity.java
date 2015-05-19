package com.stx.denglu;


import com.stx.lexiang.AppStart;
import com.stx.lexiang.LoadActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	private EditText mUser;// 用户名编辑框
	private EditText mPssword;// 密码编辑框

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		mUser = (EditText) findViewById(R.id.login_user_edit);
		mPssword = (EditText) findViewById(R.id.login_passwd_edit);
	}

	public void Login_lexiang(View v) {
		if ("xhb".equals(mUser.getText().toString())
				&& "123456".equals(mPssword.getText().toString())) // 判断 帐号和密码
		{
			Intent intent = new Intent();
			intent.setClass(LoginActivity.this, LoadActivity.class);
			startActivity(intent);
			Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT)
					.show();
		} else if ("".equals(mUser.getText().toString())
				|| "".equals(mPssword.getText().toString())) // 判断 帐号和密码
		{
			new AlertDialog.Builder(LoginActivity.this)
			.setIcon(
					getResources().getDrawable(
							R.drawable.login_error_icon))
			.setTitle("登录错误").setMessage("微博帐号或者密码不能为空，\n请输入后再登录！")
			.create().show();
		} else {

			new AlertDialog.Builder(LoginActivity.this)
					.setIcon(
							getResources().getDrawable(
									R.drawable.login_error_icon))
					.setTitle("登录失败").setMessage("微博帐号或者密码不正确，\n请检查后重新输入！")
					.create().show();
		}
	}
	public void forgetpassword(View v) { // 忘记密码按钮
		Uri uri = Uri.parse("http://lstxl.sinaapp.com/");
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(intent);
	}
	public void problem(View v) { // 登录遇到问题按钮
		Uri uri = Uri.parse("http://www.baidu.com/");
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(intent);
	}
	// 跳转到注册界面
	public void register(View view) {
		Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
		startActivity(intent);
	}
}
