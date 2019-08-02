package com.chen.app_ec.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.chen.app_core.app.BaseActivity;
import com.chen.app_ec.R;
import com.chen.app_ec.R2;

import butterknife.BindView;
import butterknife.OnClick;

public class NavigationActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R2.id.fragment_container)
    FrameLayout container;

    FragmentManager mFragmentManager =null;
    NavigationFragment mNavigationFragment = new NavigationFragment();
    Button mButton;


    @Override
    public int getLayoutid() {
        return R.layout.activity_navigation;
    }

    void openApp(){
        LoginActivity.start(this);
    }



    @Override
    public void havebind() {
        mButton = findViewById(R.id.bt_gotologin);
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction()
                .add(R.id.fragment_container,mNavigationFragment)
                .commit();

        mButton.setOnClickListener(this);
    }

    public void showBt(){
        mButton.setZ(20);
        mButton.setVisibility(View.VISIBLE);
    }

    public void hideBt(){
        if (mButton.getVisibility() == View.VISIBLE) {
            mButton.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.bt_gotologin) {
            openApp();
        }
    }
}
