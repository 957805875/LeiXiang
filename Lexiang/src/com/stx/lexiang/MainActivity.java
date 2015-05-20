package com.stx.lexiang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.security.PublicKey;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Templates;

import org.apache.http.HttpClientConnection;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.DefaultClientConnection;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.stx.denglu.R;
import com.stx.denglu.RegistActivity;
import com.stx.denglu.WeiboActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.DialogInterface.OnClickListener;
import android.content.Entity;
import android.content.Intent;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText username;
	private EditText password;
	private Handler handler;
	private Button button;
	private Button button2;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_in);
		username = (EditText) findViewById(R.id.login_user_edit);
		password = (EditText) findViewById(R.id.login_passwd_edit);
		button = (Button) findViewById(R.id.login_login_btn);
		button2 = (Button) findViewById(R.id.btn_yonghu_zhuce);
		new AsyncTask<String, Void, Object>() {
			@Override
			protected Object doInBackground(String... params) {
				String url = "http://localhost/MiniBlog/UserLogin?username='kk'&password=1111";
				String result = null;
				try {
					// 创建连接
					HttpClient httpClient = new DefaultHttpClient();
					HttpPost post = new HttpPost(url);
					// 设置参数，仿HTML提交
					List<NameValuePair> paramList = new ArrayList<NameValuePair>();
					BasicNameValuePair param1 = new BasicNameValuePair(
							"uusername", username.getText().toString());
					BasicNameValuePair param2 = new BasicNameValuePair(
							"upassword", password.getText().toString());
					paramList.add(param1);
					paramList.add(param2);
					post.setEntity(new UrlEncodedFormEntity(paramList,
							HTTP.UTF_8));
					// 发送HTTP请求，并返还Response
					HttpResponse httpResponse = httpClient.execute(post);
					// 判断响应状态码，状态码为200表示响应成功
					if (httpResponse.getStatusLine().getStatusCode() == 200) {
						// 获取返还结果
						result = EntityUtils.toString(httpResponse.getEntity());
						// Toast显示结果
						Toast.makeText(MainActivity.this, result,
								Toast.LENGTH_SHORT).show();
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				return result;
			}

			// 判断用户名，密码是否正确，如果正确执行跳转
			protected void onPostExecute(Object result) {
				super.onPostExecute(result);
				if ((Boolean) (result = true)) {
					Intent intent = new Intent(MainActivity.this,
							WeiboActivity.class);
					startActivity(intent);
				}
			}
		}.execute();
	}
	        // 点击注册，跳转到注册界面
	       public void zhuce(View view) {
		   Intent intent = new Intent();
		   intent.setClass(MainActivity.this, RegistActivity.class);
		   startActivity(intent);
	}
	       
	       public void onPostExecute(View v){
	    	   //在这里写登录按钮的代码，你这里的原因是，按钮设置了onclick事件，名字是onPostExecute
	    	   //但是程序执行时找不到方法public void onPostExecute(View v)
	    	   //你虽然之前哪里有个叫做onPostExecute的方法，但其是内部方法，且不为public，也无法接受上下文View
	    	   //即，找不到onPostExecute方法Exception
	    	   //然后就崩溃了啊
	    	   //以上
	       }
}
