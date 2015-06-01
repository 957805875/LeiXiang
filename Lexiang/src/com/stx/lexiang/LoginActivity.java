package com.stx.lexiang;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.PrivateCredentialPermission;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.stx.denglu.R;
import com.stx.denglu.RegistActivity;
import com.stx.denglu.WeiboActivity;
import com.stx.util.HttpUtil;

public class LoginActivity extends Activity {
	private EditText username;
	private EditText password;
	private Button denglu;
	private Button zhuce;
	private LoadTask loadTask;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_in);
		username = (EditText) findViewById(R.id.login_user_edit);
		password = (EditText) findViewById(R.id.login_passwd_edit);
		denglu = (Button) findViewById(R.id.login_login_btn);
		zhuce = (Button) findViewById(R.id.btn_yonghu_zhuce);
		denglu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				LoadTask loadTask = new LoadTask();
//				loadTask.execute("UserLogin");
				
				new AsyncTask<String, Void, Object>() {
					@Override
					protected Object doInBackground(String... params) {
						String url = "http://dddd345.oicp.net/MiniBlog/UserLogin";
						String result = "";
						try {
							//创建连接
							HttpClient httpClient = new DefaultHttpClient();
							HttpPost post = new HttpPost(url);
							//设置参数，仿html表单提交
							List<NameValuePair> paramList = new ArrayList<NameValuePair>();
							BasicNameValuePair param = new BasicNameValuePair("uusername", username.getText().toString());
							BasicNameValuePair param2 = new BasicNameValuePair("upassword", password.getText().toString());
							paramList.add(param);
							paramList.add(param2);

							post.setEntity(new UrlEncodedFormEntity(paramList, HTTP.UTF_8));
							//发送HttpPost请求，并返回HttpResponse对象
							HttpResponse httpResponse = httpClient.execute(post);
							// 判断请求响应状态码，状态码为200表示服务端成功响应了客户端的请求
							if (httpResponse.getStatusLine().getStatusCode() == 200) {
								//获取返回结果
								result = EntityUtils.toString(httpResponse.getEntity());
							}
						} catch (Exception e) {
							//无法连接http组件
						}
						return result;
					}

					protected void onPostExecute(Object result) {
						super.onPostExecute(result);
						//showMessageByToast(result+"");
						System.out.println(">"+result+"<");
						if (((String)result).startsWith("登陆成功")){
							System.out.println("ok");
							Intent intent = new Intent(LoginActivity.this,
									WeiboActivity.class);
							startActivity(intent);
						} else {
							System.out.println("no");
							Toast.makeText(LoginActivity.this, "密码或用户名输入错误",
									Toast.LENGTH_SHORT).show();
						}
					}
				}.execute();
			}
		});
		// private OnClickListener onButtonClickListener=new OnClickListener() {
		// public void onClick(View v) {
		// //启动异步通信
		// loadTask = new LoadTask();
		// //给AsyncTask传入一个参数
		// loadTask.execute("UserLogin");
		//
		// }
		// };
	}

	// public void onPostExecute(View v) {
	// loadTask = new LoadTask();
	// loadTask.execute("UserLogin");
	// }
	private class LoadTask extends AsyncTask<String, Void, String> {

		protected void onPreExecute() {
			super.onPreExecute();
		}
		protected String doInBackground(String... params) {
			String result = null;
			String path = params[0];
			// 设置参数，仿HTML提交
			List<NameValuePair> paramList = new ArrayList<NameValuePair>();
			BasicNameValuePair param1 = new BasicNameValuePair("uusername",
					username.getText().toString());
			BasicNameValuePair param2 = new BasicNameValuePair("upassword",
					password.getText().toString());
			paramList.add(param1);
			paramList.add(param2);
			result = doPost(path, paramList);
			Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();;
			System.out.println(result);
			return result;
		}
		// 判断用户名和密码是否正确，如果正确则跳转
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			System.out.println(result);
			if (result.equals("登陆成功")) {
				Intent intent = new Intent(LoginActivity.this,
						WeiboActivity.class);
				startActivity(intent);
			} else {
				Toast.makeText(LoginActivity.this, "密码或用户名输入错误",
						Toast.LENGTH_SHORT).show();
			}
		}
	}
	// 连接服务器，获取数据
	protected String doPost(String path, List<NameValuePair> paramList) {
		final String HOME = "http://localhost:8080/MiniBlog/";
		String result = null;
		try {
			// 创建连接
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost post = new HttpPost(HOME + path);
			post.setEntity(new UrlEncodedFormEntity(paramList, HTTP.UTF_8));
			// 发送HTTP请求，并返还Response
			HttpResponse httpResponse = httpClient.execute(post);
			// 判断响应状态码，状态码为200表示响应成功
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				// 获取返还结果
				result = EntityUtils.toString(httpResponse.getEntity());
			}
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		} catch (ParseException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return result;
	}
	// 点击注册，跳转到注册界面
	public void zhuce(View view) {
		Intent intent = new Intent(LoginActivity.this, RegistActivity.class);
		startActivity(intent);
		System.out.println("请输入");
	}
}