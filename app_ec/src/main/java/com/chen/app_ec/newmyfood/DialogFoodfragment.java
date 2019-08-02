package com.chen.app_ec.newmyfood;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.chen.app_ec.R;
import com.chen.app_ec.R2;
import com.chen.app_ec.data.FoodData;
import com.chen.app_ec.data.FoodDiary;
import com.chen.app_ec.data.Foodtype;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class DialogFoodfragment extends DialogFragment {

    @BindView(R2.id.tv_cancel)
    TextView tv_cancel;
    @BindView(R2.id.tv_makesure)
    TextView tv_makesure;

    @BindView(R2.id.civ_pic)
    CircleImageView mCircleImageView;

    @BindView(R2.id.name_text)
    TextView tv_name;

    @BindView(R2.id.tl_select)
    TabLayout mTabLayout_select;

    @BindView(R2.id.tv_keshu)
    TextView tv_gram;

    @OnClick(R2.id.bt_0)
    void  mButton0(){
        String s= tv_gram.getText().toString();
        if (!s.isEmpty() && Double.valueOf(s)>0){
            tv_gram.setText(s+"0");
        }
    }
    @OnClick(R2.id.bt_1)
    void mButton1(){
        String s= tv_gram.getText().toString();
        if (Integer.valueOf(s)==0){
            tv_gram.setText("1");
        }else {
            tv_gram.setText(s+"1");
        }
    }
    @OnClick(R2.id.bt_2)
    void mButton2(){
        String s= tv_gram.getText().toString();
        if (Integer.valueOf(s)==0){
            tv_gram.setText("2");
        }else {
            tv_gram.setText(s+"2");
        }
    }
    @OnClick(R2.id.bt_3)
    void mButton3(){
        String s= tv_gram.getText().toString();
        if (Integer.valueOf(s)==0){
            tv_gram.setText("3");
        }else {
            tv_gram.setText(s+"3");
        }
    }
    @OnClick(R2.id.bt_4)
    void mButton4(){
        String s= tv_gram.getText().toString();
        if (Integer.valueOf(s)==0){
            tv_gram.setText("4");
        }else {
            tv_gram.setText(s+"4");
        }
    }
    @OnClick(R2.id.bt_5)
    void mButton5(){
        String s= tv_gram.getText().toString();
        if (Integer.valueOf(s)==0){
            tv_gram.setText("5");
        }else {
            tv_gram.setText(s+"5");
        }
    }
    @OnClick(R2.id.bt_6)
    void mButton6(){
        String s= tv_gram.getText().toString();
        if (Integer.valueOf(s)==0){
            tv_gram.setText("6");
        }else {
            tv_gram.setText(s+"6");
        }
    }
    @OnClick(R2.id.bt_7)
    void mButton7(){
        String s= tv_gram.getText().toString();
        if (Integer.valueOf(s)==0){
            tv_gram.setText("7");
        }else {
            tv_gram.setText(s+"7");
        }
    }
    @OnClick(R2.id.bt_8)
    void mButton8(){
        String s= tv_gram.getText().toString();
        if (Integer.valueOf(s)==0){
            tv_gram.setText("8");
        }else {
            tv_gram.setText(s+"8");
        }
    }
    @OnClick(R2.id.bt_9)
    void mButton9(){
        String s= tv_gram.getText().toString();
        if (Integer.valueOf(s)==0){
            tv_gram.setText("9");
        }else {
            tv_gram.setText(s+"9");
        }
    }


    @OnClick(R2.id.ibt_delete)
    void ibt_delete(){
        String s= tv_gram.getText().toString();
        if (!s.isEmpty() && s.length()>1){
            s = s.substring(0,s.length()-1);
            tv_gram.setText(s);
        }
        if (!s.isEmpty() && s.length() == 1){
            tv_gram.setText("0");
        }
    }

    @OnClick(R2.id.bt_dian)
    void bt_dian(){
//        String s = tv_gram.getText().toString();
//        if (!s.isEmpty() && s.length() >1){
//            tv_gram.setText(s +".");
//        }
    }

    @OnClick(R2.id.tv_gui100)
    void back100(){
        tv_gram.setText("100");
    }

    TextView tv_gui100;
    Unbinder munbinder;

    FoodDiary mFoodDiary = new FoodDiary();

    //Dialogdissmiss回调接口
    DialogFragmentdismisslistener<FoodDiary> mFoodDiaryDialogFragmentdismisslistener;

    public void setFoodDiaryDialogFragmentdismisslistener(DialogFragmentdismisslistener<FoodDiary> foodDiaryDialogFragmentdismisslistener) {
        mFoodDiaryDialogFragmentdismisslistener = foodDiaryDialogFragmentdismisslistener;
    }

    private FoodData mFoodData;

    public void setFoodData(FoodData foodData) {
        mFoodData = foodData;
    }

    @OnClick(R2.id.tv_cancel)
    void clickTv_cancel(){
        dismiss();
    }

    @OnClick(R2.id.tv_makesure)
    void sure(){
        //TODO 回调
        String ke = tv_gram.getText().toString();
        mFoodDiary.setGram(Integer.valueOf(ke));
        if (mFoodDiaryDialogFragmentdismisslistener != null){
            mFoodDiaryDialogFragmentdismisslistener.setDialogResult(mFoodDiary);
        }
        dismiss();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.dialogfragment_newfood,container,false);
        munbinder = ButterKnife.bind(this,view);
        init();
        return view;
    }


    //设置界面
    private void init(){
        mFoodDiary.setFoodData(mFoodData);
        mTabLayout_select.addTab(mTabLayout_select.newTab().setText("早餐"));
        mTabLayout_select.addTab(mTabLayout_select.newTab().setText("午餐"));
        mTabLayout_select.addTab(mTabLayout_select.newTab().setText("晚餐"));
        mTabLayout_select.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
               String string =  tab.getText().toString();
               if (string == Foodtype.MORNING.name){
                   mFoodDiary.setFoodtype(Foodtype.MORNING);
               }else if (string == Foodtype.NOON.name){
                   mFoodDiary.setFoodtype(Foodtype.NOON);
               }else if (string == Foodtype.DINNER.name){
                   mFoodDiary.setFoodtype(Foodtype.DINNER);
               }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        if (munbinder !=null){
            munbinder.unbind();
        }
        super.onDestroy();
    }
}
