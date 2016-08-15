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

   private BRInteraction brInteraction;

   @Override
   public void onReceive(Context context, Intent intent) {

      if (intent.getAction().equals("com.test")) {

         String name = intent.getStringExtra("name");
         int number = intent.getIntExtra("number", 0);
         if (null != name) {
            SharedPreferences sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
            String data = sp.getString("name", null);
            Log.e("info", "name : " + name + "---- data : " + data);
            if (name.equals(data) || TextUtils.isEmpty(data)) {
               Log.e("info", "第三方  ==  优e");
            } else {
               Intent in = new Intent(context, DialogActivity.class);
               in.putExtra("name", name);
               in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// 广播中启动Activity需要设置此flag，因为Activity需要一个栈
               context.startActivity(in);
            }
         }

      }
   }

   public interface BRInteraction {
      public void setText(String content);
   }

   public void setBRInteractionListener(BRInteraction brInteraction) {
      this.brInteraction = brInteraction;
   }
}
