package com.hqjy.opensdk.opensdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by annuo on 2015/5/16.
 */
public class DianLiangBR extends BroadcastReceiver {
   @Override
   public void onReceive(Context context, Intent intent) {
      if (intent.getAction().equals("com.test")) {
         String name = intent.getStringExtra("name");//获取广播传递过来的名字
         if (null != name) {
            SharedPreferences sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
            String data = sp.getString("name", null);//获取SDK中保存的名字
            Log.e("info", "name : " + name + "---- data : " + data);
            if (name.equals(data) || TextUtils.isEmpty(data)) {//优e学堂和SDK中保存的名字相同
               Log.e("info", "第三方  ==  优e");
            } else {//优e学堂和SDK中保存的名字不同，弹出切换账号对话框
               Intent in = new Intent(context, DialogActivity.class);
               in.putExtra("name", name);
               in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               context.startActivity(in);
            }
         }

      }
   }

}
