package com.hq.other.otherdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.hqjy.opensdk.opensdk.Statistics;
public class OtherLoginActivity extends Activity {
	TextView txt_back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.other_login_activity);
		Statistics.getInstance().getSP(OtherLoginActivity.this,"");
		txt_back = (TextView) findViewById(R.id.other_youe_back);
		findViewById(R.id.other_btn).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Statistics.getInstance().getSP(OtherLoginActivity.this,"you");
				Intent intent = new Intent(OtherLoginActivity.this,MainActivity.class);
				startActivity(intent);
				finish();
			}
		});

		findViewById(R.id.other_btn_youe).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Statistics.getInstance().init("appid=001",OtherLoginActivity.this);// 初始化SDK，判断是否安装了优e学堂，并跳转到不同的界面
			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Statistics.getInstance().getData(requestCode,resultCode,data,OtherLoginActivity.this);
		if(null!=Statistics.getInstance().getData(requestCode,resultCode,data,OtherLoginActivity.this)){
			if(Statistics.getInstance().getData(requestCode,resultCode,data,OtherLoginActivity.this).equals("安装未登录网络请求----->返回JSON数据")){
				Intent intent = new Intent(OtherLoginActivity.this,MainActivity.class);
				intent.putExtra("true", "手动授权成功，\n账号和优e学堂登录账号不同.\n安装未登录----->返回JSON数据");
				startActivity(intent);
				finish();
			}else
			if(Statistics.getInstance().getData(requestCode,resultCode,data,OtherLoginActivity.this).equals("安装已登录网络请求----->返回JSON数据")){
				Intent intent = new Intent(OtherLoginActivity.this,MainActivity.class);
				intent.putExtra("true", "自动授权成功，\n账号和优e学堂登录账号相同.\n安装已登录----->返回JSON数据");
				startActivity(intent);
				finish();
			}
			else{
				Toast.makeText(getApplicationContext(), "授权失败", Toast.LENGTH_SHORT).show();
			}
		}



	}
}
