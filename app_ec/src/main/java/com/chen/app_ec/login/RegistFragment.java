package com.chen.app_ec.login;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chen.app_core.app.BaseFragment;
import com.chen.app_core.net.Callback.IError;
import com.chen.app_core.net.Callback.ISuccess;
import com.chen.app_core.net.RestCilentBuilder;
import com.chen.app_ec.R;
import com.chen.app_ec.R2;

import java.util.HashMap;

import butterknife.BindView;

public class RegistFragment extends BaseFragment implements View.OnClickListener {


    @BindView(R2.id.txlogin_email)
    TextInputEditText te_email;

    @BindView(R2.id.txlogin_name)
    TextInputEditText te_name;

    @BindView(R2.id.txlogin_password)
    TextInputEditText te_password;

    @BindView(R2.id.txlogin_repassword)
    TextInputEditText te_repassword;

    @BindView(R2.id.txlogin_age)
    TextInputEditText te_age;

    @BindView(R2.id.txlogin_high)
    TextInputEditText te_high;

    @BindView(R2.id.txlogin_wight)
    TextInputEditText te_weight;

    @BindView(R2.id.rg_sex)
    RadioGroup mRadioGroup_sex;

    @BindView(R2.id.rb_man)
    RadioButton rb_man;

    @BindView(R2.id.rb_woman)
    RadioButton rb_woman;

    @BindView(R2.id.cb_1)
    CheckBox mCheckBox1;
    @BindView(R2.id.cb_2)
    CheckBox mCheckBox2;
    @BindView(R2.id.cb_3)
    CheckBox mCheckBox3;

    @BindView(R2.id.bt_regist)
    Button bt_regist;

    @BindView(R2.id.tv_gotoLogin)
    TextView tv_gotologin;



    void gotologinin(){
        ((LoginActivity)getActivity()).replacetoLoginin();
    }

    //TODO 联网注册
    void regist(){
        if (checkform()){
            int id = mRadioGroup_sex.getCheckedRadioButtonId();
            String sex = "man";
            if (id == R.id.rb_woman){
                sex = "woman";
            }
            StringBuilder sb = new StringBuilder();
            CheckBox[] checkBoxes = new CheckBox[3];
            checkBoxes[0] = mCheckBox1;
            checkBoxes[1] = mCheckBox2;
            checkBoxes[2] = mCheckBox3;
            for (int i = 0; i < checkBoxes.length; i++) {
                if (checkBoxes[i].isChecked()){
                    sb.append(mCheckBox1.getText().toString()+" ");
                }
            }
            String du = sb.toString().trim();


            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("phone",te_email.getText().toString());
            hashMap.put("password",te_password.getText().toString());
            hashMap.put("sex",sex);
            hashMap.put("weight",te_high.getText());
            hashMap.put("info",du);
            hashMap.put("age",te_age.getText().toString());
            new RestCilentBuilder()
                    .url("http://39.108.224.57:8080/Food/register")
                    .params(hashMap)
                    .error(new IError() {
                        @Override
                        public void onError(int id, String mesg) {
                            Toast.makeText(getActivity(), mesg +"chengong", Toast.LENGTH_SHORT).show();

                        }
                    })
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String result) {
                            Toast.makeText(getActivity(), result+"shibai", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .build()
                    .get();
        }
    }

    @Override
    public void bindview(Bundle savedInstance, View rootview) {
        bt_regist.setOnClickListener(this);
        tv_gotologin.setOnClickListener(this);
    }

    @Override
    public Object setlayout() {
        return R.layout.fragment_regist;
    }


    private boolean checkform(){
        String email = te_email.getText().toString();
        String name = te_name.getText().toString();
        String password = te_password.getText().toString().trim();
        String repassword = te_repassword.getText().toString().trim();
        String age = te_age.getText().toString();
        String high = te_high.getText().toString();
        String weight = te_weight.getText().toString();
        int id = mRadioGroup_sex.getCheckedRadioButtonId();
        String sex = "man";
        if (id == R.id.rb_woman){
            sex = "woman";
        }

        // 检测常规注册信息
        boolean pass = true;
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            pass = false;
            te_email.setError("请填写正确的邮箱");
        }
        if (password.length()<6){
            pass = false;
            te_password.setError("密码请填写六位以上");
        }
        if (!repassword.equals(password)){
            pass = false;
            te_repassword.setError("再次输入的验证密码与密码不同");
        }

        if (Integer.valueOf(age)<0 || Integer.valueOf(age)>120){
            pass = false;
            te_age.setError("请输入正确的年龄");
        }

        if (Integer.valueOf(high)<0 || Integer.valueOf(high)>300){
            pass = false;
            te_age.setError("请输入正确的身高");
        }

        if (Integer.valueOf(weight)<0 || Integer.valueOf(weight)>200){
            pass = false;
            te_age.setError("请输入正确的体重");
        }
        return pass;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_gotoLogin){
            gotologinin();
        }
        if (view.getId() == R.id.bt_regist){
            regist();
        }
    }
}
