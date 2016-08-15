package com.hq.other.otherdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.hqjy.opensdk.opensdk.MResource;
import com.hqjy.opensdk.opensdk.Statistics;

public class MainActivity extends Activity {
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = (TextView)findViewById(R.id.txt);
        if(null!=getIntent().getStringExtra("true")){
            txt.setText(getIntent().getStringExtra("true"));
        }else{
            txt.setText("第三方自己账号登录");
        }

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //在退出登录方法里添加清除数据方法
                Statistics.getInstance().getSP(MainActivity.this,"");
            }
        });
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        Statistics.getInstance().getSP(MainActivity.this,"");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) { //按下的如果是BACK，同时没有重复
            Statistics.getInstance().getSP(MainActivity.this,"");
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
