package com.example.asus.news;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.asus.news.util.DaoMaster;
import com.example.asus.news.util.DaoSession;

/**
 * Created by ASUS on 2016/10/30.
 */

public class BaseActivity extends AppCompatActivity {
    public DaoSession daoSession;
    private Handler baseHandler= new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==100){
                isFirst=true;
            }
        }
    };
    public boolean isFirst=true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "sang.db", null);
        DaoMaster daoMaster = new DaoMaster(openHelper.getWritableDb());
        daoSession = daoMaster.newSession();
    }
    @Override
    public void onBackPressed() {
        ActivityManager activityManager=(ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

        String runningActivity=activityManager.getRunningTasks(1).get(0).topActivity.getClassName();
        runningActivity=  runningActivity.substring(runningActivity.lastIndexOf(".")+1,+runningActivity.length());
        Log.d("zan", "onBackPressed: "+runningActivity);

        if (!runningActivity.equals("MainActivity")){
            //  sendBroadcast(new Intent(PlayUtil.CLOSE_PLAYACTIVITY));
            BaseActivity.this.finish();
        }else{
            if (isFirst){
                isFirst=false;
                Toast.makeText(BaseActivity.this, "再按一次退出新闻", Toast.LENGTH_SHORT).show();
                baseHandler.sendEmptyMessageDelayed(100,2500);
            }else{
                BaseActivity.this.finish();
            }

        }
    }
}
