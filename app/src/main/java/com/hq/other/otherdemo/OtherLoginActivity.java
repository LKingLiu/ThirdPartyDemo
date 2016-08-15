package com.hq.other.otherdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
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
		Statistics.getInstance().getSP(OtherLoginActivity.this, "");
		txt_back = (TextView) findViewById(R.id.other_youe_back);
		findViewById(R.id.other_btn).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Statistics.getInstance().getSP(OtherLoginActivity.this, "you");
				Intent intent = new Intent(OtherLoginActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		});

		findViewById(R.id.other_btn_youe).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Statistics.getInstance().init("appid=001", OtherLoginActivity.this);// 初始化SDK，判断是否安装了优e学堂，并跳转到不同的界面
			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Statistics.getInstance().getData(requestCode, resultCode, data, OtherLoginActivity.this);
		switch (requestCode) {
			case 1:
				if (resultCode == Activity.RESULT_OK) {
					Intent intent = new Intent(OtherLoginActivity.this, MainActivity.class);
					intent.putExtra("true", "手动授权成功，\n第三方安装未登录----->返回数据:" + data.getStringExtra("user"));
					startActivity(intent);
					finish();
				}
				break;
			case 2:
				if (resultCode == Activity.RESULT_OK) {
					Intent intent = new Intent(OtherLoginActivity.this, MainActivity.class);
					intent.putExtra("true", "自动授权成功，\n第三方安装已登录----->返回数据:" + data.getStringExtra("user"));
					startActivity(intent);
					finish();

				}
				break;
			default:
				Toast.makeText(getApplicationContext(), "授权失败", Toast.LENGTH_SHORT).show();
				break;
		}
	}


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) { //按下的如果是BACK，同时没有重复
			//退出应用
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}