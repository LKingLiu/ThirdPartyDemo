package com.hqjy.opensdk.opensdk;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class DialogActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.opensdk_dialog);

		if(null!=getIntent().getStringExtra("name")){

			AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle("提示：").setMessage("是否切换账号").setCancelable(false)
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Statistics.getInstance().deleteSP(DialogActivity.this);
							Intent intent = new Intent();
							intent.setClassName("com.hq.other.otherdemo", "com.hq.other.otherdemo.OtherLoginActivity");
							startActivity(intent);
							finish();
						}
					})
					.setNegativeButton("取消",new DialogInterface.OnClickListener() {//添加返回按钮
						@Override
						public void onClick(DialogInterface dialog, int which) {//响应事件
							finish();
						}
					})
					.create();
			alertDialog.show();
		}


	}
}
