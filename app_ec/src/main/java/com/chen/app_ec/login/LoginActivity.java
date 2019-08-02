package com.chen.app_ec.login;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.widget.FrameLayout;
import com.chen.app_core.app.BaseActivity;
import com.chen.app_ec.R;
import com.chen.app_ec.R2;

import butterknife.BindView;

public class LoginActivity extends BaseActivity {

    @BindView(R2.id.fragment_container)
    FrameLayout container;

    FragmentManager mFragmentManager =null;
    LoginFragment mLoginFragment = new LoginFragment();
    RegistFragment mRegistFragment = new RegistFragment();

    private boolean isLogin = true;


    @Override
    public int getLayoutid() {
        return R.layout.activity_login;
    }

    @Override
    public void havebind() {
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction()
                .add(R.id.fragment_container,mRegistFragment)
                .add(R.id.fragment_container,mLoginFragment)
                .hide(mRegistFragment)
                .commit();
    }

    public void replacetoregist(){
        mFragmentManager
                .beginTransaction()
                .hide(mLoginFragment)
                .show(mRegistFragment)
                .addToBackStack(null)
                .commit();
        isLogin = false;
    }

    public void replacetoLoginin(){
        mFragmentManager
                .beginTransaction()
                .hide(mRegistFragment)
                .show(mLoginFragment)
                .commit();
        isLogin = true;
    }

    @Override
    public void onBackPressed() {
        if (!isLogin) {
            isLogin = true;
        }
        super.onBackPressed();
    }

    public static void start(Context context){
        Intent intent =  new Intent(context,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
