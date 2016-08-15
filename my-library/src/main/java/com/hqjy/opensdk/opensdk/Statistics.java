package com.hqjy.opensdk.opensdk;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;

public class Statistics<ac> {
	public static Statistics mStatistics;
	public static String appid;

	public static Statistics getInstance() {
		if (mStatistics == null) {
			mStatistics = new Statistics();
		}

		return mStatistics;
	}
	public void init(String appid,Activity ac) {
		this.appid = appid;//获取到了appid

		if(!checkInstallation(ac)){//没有安装优e学堂，跳转到下载界面
			Uri uri = Uri.parse("http://www.google.com");
			Intent it = new Intent(Intent.ACTION_VIEW,uri);
			ac.startActivity(it);
		}else{//安装了“优e学堂”，跳转到“优e学堂”登录授权界面
			Intent intent = new Intent();
			intent.setClassName("com.hengqian.education.excellentlearning.localwork", "com.hengqian.education.excellentlearning.ui.mine.MyFileActivity");
			intent.putExtra("APPID", appid);
			ac.startActivityForResult(intent, 2);
		}

	}

	public  String getData(int requestCode, int resultCode, Intent data,Activity ac) {
		String info = null;
		String name = null;
		switch (requestCode) {
			case 1:
				if (resultCode == Activity.RESULT_OK) {
					info = data.getStringExtra("first");
					name = data.getStringExtra("user");
				}
				break;
			case 2:
				if (resultCode == Activity.RESULT_OK) {
					if(null!=data.getStringExtra("loginstate")){
						info = data.getStringExtra("loginstate");
						name = data.getStringExtra("user");
						getSP(ac,name);
					}

				}
				break;
		}
		return info;

	}



	/**
	 * 查看是否安装了程序
	 */
	public  boolean checkInstallation(Activity ac) {
		try {
			ac.getPackageManager().getPackageInfo("com.hengqian.education.excellentlearning.localwork", PackageManager.GET_ACTIVITIES);
			return true;
		} catch (PackageManager.NameNotFoundException e) {
			return false;
		}
	}

	/**
	 *  获取SDK储存的用户名
	 */
	public static void getSP(Activity ac,String name)
	{
		SharedPreferences.Editor sharedata = ac.getSharedPreferences("data", 0).edit();
		sharedata.putString("name",name);
		sharedata.commit();
	}

	/**
	 *  清除SDK储存的用户名
	 */
	public static void setSP(Activity ac)
	{
		SharedPreferences.Editor sharedata = ac.getSharedPreferences("data", 0).edit();
		sharedata.putString("name","");
		sharedata.commit();
	}

}
