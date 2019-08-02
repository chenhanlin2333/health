package com.chen.healthapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.chen.app_core.app.BaseActivity;
import com.chen.app_core.net.Callback.IError;
import com.chen.app_core.net.Callback.ISuccess;
import com.chen.app_core.net.RestCilentBuilder;
import com.chen.app_core.stroge.AppSharedpreference;
import com.chen.app_ec.Ec_core_content.EcActivity;
import com.chen.app_ec.login.LoginActivity;
import com.chen.app_ec.login.NavigationActivity;
import com.chen.app_ec.search.SearchActivity;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends BaseActivity implements TimeListener {

    private int mCount = 5 ;

    TextView tv_timedown;
    private Timer mTimer = null;

    @OnClick(R2.id.time_down)
    void gotoEc(){
        if (mTimer != null){
            mTimer.cancel();
            mTimer = null;
            wherego();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



//        new RestCilentBuilder().url("http://39.108.224.57:8080/food/login")
//                .params(hashMap1)
//                .error(new IError() {
//                    @Override
//                    public void onError(int id, String mesg) {
//                        Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .success(new ISuccess() {
//                    @Override
//                    public void onSuccess(String result) {
//                        Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
//                    }
//                }).build().get();





//        new RestCilentBuilder()
//                .url("https://www.baidu.com/")
//                .success(new ISuccess() {
//                    @Override
//                    public void onSuccess(String result) {
//                        Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .error(new IError() {
//                    @Override
//                    public void onError(int id, String mesg) {
//
//                    }
//                }).build().get();
//
    }

    @Override
    public int getLayoutid() {
        return R.layout.activity_main;
    }

    @Override
    public void havebind() {
        initTimer();
    }

    private void wherego(){
        if (AppSharedpreference.isFirstOpenApp()) {
            startActivity(new Intent(this, NavigationActivity.class));
        }else {
            LoginActivity.start(this);
        }
        finish();
    }

    private void initTimer(){
        mTimer = new Timer();
        BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task,0,1000);
    }

    @Override
    public void onTimer() {
       runOnUiThread(new Runnable() {
           @Override
           public void run() {
               if (tv_timedown !=null){
                   tv_timedown.setText(MessageFormat.format("跳过\n{0}s",mCount));
                   mCount--;
                   if (mCount<0){
                       if (mTimer != null){
                           mTimer.cancel();
                           mTimer = null;
                           wherego();
                           finish();
                       }
                   }
               }
           }
       });
    }
}
