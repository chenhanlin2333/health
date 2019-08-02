package com.chen.app_ec.login;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chen.app_core.app.BaseFragment;
import com.chen.app_core.net.Callback.IError;
import com.chen.app_core.net.Callback.ISuccess;
import com.chen.app_core.net.RestCilentBuilder;
import com.chen.app_core.ui.LatteLaoder;
import com.chen.app_ec.Ec_core_content.EcActivity;
import com.chen.app_ec.R;
import com.chen.app_ec.R2;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginFragment extends BaseFragment  {

    @BindView(R2.id.txlogin_phone)
    TextInputEditText te_phone;

    @BindView(R2.id.txlogin_password)
    TextInputEditText te_password;


    @BindView(R2.id.tv_gotoregist)
    TextView tv_gtregisit;

    @OnClick(R2.id.tv_gotoregist)
    void replacetoregist(){
        ((LoginActivity)getActivity()).replacetoregist();
    }


    @BindView(R2.id.bt_login)
    Button bt_login;

    @OnClick(R2.id.bt_login)
    void login(){
//        if (check()){
//            HashMap hashMap = new HashMap();
//            String phonenum = te_phone.getText().toString();
//            String password = te_password.getText().toString();
//            hashMap.put("phone", phonenum);
//            hashMap.put("password",password);
//            new RestCilentBuilder().url("http://39.108.224.57:8080/food/login")
//                    .params(hashMap)
//                    .error(new IError() {
//                        @Override
//                        public void onError(int id, String mesg) {
//                            Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
//                        }
//                    })
//                    .success(new ISuccess() {
//                        @Override
//                        public void onSuccess(String result) {
//                            Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
//                        }
//                    }).build().get();
//
            //TODO 放在成功的接口回调里，后端的锅先放外面
            EcActivity.start(getActivity());
//       }
    }


    private boolean check(){
        String pheone = te_phone.getText().toString();
        String password = te_password.getText().toString();
        boolean pass = true;
        if (!pheone.matches("^[1][3,4,5,7,8][0-9]{9}$")){
            te_phone.setError("手机号码格式错误");
            pass =false;
        }
        if (password.length() <6){
            te_password.setError("密码不能小于六位");
            pass =false;
        }
        return pass;
    }

    @Override
    public void bindview(Bundle savedInstance, View rootview) {



    }

    @Override
    public Object setlayout() {
        return R.layout.fragement_login;
    }


}
