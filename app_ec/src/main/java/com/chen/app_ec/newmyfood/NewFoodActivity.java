package com.chen.app_ec.newmyfood;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chen.app_core.app.BaseActivity;
import com.chen.app_ec.R;
import com.chen.app_ec.R2;
import com.chen.app_ec.data.DayFoodDiary;
import com.chen.app_ec.data.FoodData;
import com.chen.app_ec.data.FoodDiary;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.OnClick;

public class NewFoodActivity extends BaseActivity {

    @BindView(R2.id.tl_back)
    Toolbar mToolbar;


    @BindView(R2.id.img_lastday)
    ImageView img_lastday;
    @BindView(R2.id.img_nextday)
    ImageView img_nextday;
    @BindView(R2.id.tv_date)
    TextView tv_date;


    @BindView(R2.id.ll_gotosearch)
    LinearLayout ll_gotosearch;

    @BindView(R2.id.rv_morning)
    RecyclerView rv_morning;

    @BindView(R2.id.rv_noon)
    RecyclerView rv_noon;

    @BindView(R2.id.rv_dinner)
    RecyclerView rv_dinner;


    @BindView(R2.id.tv_sum_morning)
    TextView tv_sum_moring;

    @BindView(R2.id.tv_sum_noon)
    TextView tv_sum_noon;

    @BindView(R2.id.tv_sum_dinner)
    TextView tv_sum_dinner;


    @BindView(R2.id.tv_sum)
    TextView tv_sum;


    private Calendar mCalendar ;
    DatePickerDialog mDatePickerDialog;

    @OnClick(R2.id.tv_date)
    void opendateDatepickerDialog(){
        mDatePickerDialog.show();
    }



    @OnClick(R2.id.img_nextday)
    void onclicknextday(){
        mCalendar.add(Calendar.DAY_OF_MONTH,1);
        tv_date.setText(getFormateDate());
    }

    @OnClick(R2.id.img_lastday)
    void onClicklastday(){
        mCalendar.add(Calendar.DAY_OF_MONTH,-1);
        tv_date.setText(getFormateDate());
    }

    public final static int REQUESTCODE =1;
    public final static String FOODDATA = "fooddata";


    @OnClick(R2.id.ll_gotosearch)
    void gotoSearch(){
        NewFoodSearchActivity.start(this,REQUESTCODE);
    }

    @Override
    public int getLayoutid() {
        return R.layout.activity_new_food;
    }

    @Override
    public void havebind() {
        inittoolbar();
        initdate();
        initRecycleview();
    }



    private DayFoodDiary mDayFoodDiary = new DayFoodDiary();

    private NewFoodApter adapterMorning ;
    private NewFoodApter adapterNoon ;
    private NewFoodApter adapterDinner ;



    //初始化顶部栏
    private void inittoolbar(){
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //初始化日期
    private void initdate(){
       mCalendar = Calendar.getInstance();
       mCalendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
       int year = mCalendar.get(Calendar.YEAR);
       int month = mCalendar.get(Calendar.MONTH);
       int day = mCalendar.get(Calendar.DAY_OF_MONTH);
       mDatePickerDialog  =new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
           @Override
           public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
               String date = ""+i +"-"+(i1+1)+"-"+i2;
               tv_date.setText(date);
               mDayFoodDiary.setDate(date);
           }
       },year,month,day);
    }


    // 初始化列表
    private void initRecycleview(){
        adapterMorning = new NewFoodApter(this,mDayFoodDiary.getMorningArrayList());
        adapterNoon = new NewFoodApter(this,mDayFoodDiary.getNoonArraylist());
        adapterDinner = new NewFoodApter(this,mDayFoodDiary.getDinnerArraylist());
        rv_morning.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rv_noon.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rv_dinner.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rv_morning.setAdapter(adapterMorning);
        rv_noon.setAdapter(adapterNoon);
        rv_dinner.setAdapter(adapterDinner);
    }


    private String getFormateDate(){
        String year = String.valueOf(mCalendar.get(Calendar.YEAR));
        String month = String.valueOf(mCalendar.get(Calendar.MONTH)+1);
        String day = String.valueOf(mCalendar.get(Calendar.DAY_OF_MONTH));
        String show = year+"-"+month+"-"+day;
        return show;
    }


    //Activity入口
    public static void start(Context mContext){
        Intent intent = new Intent(mContext, NewFoodActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != RESULT_OK){
            return;
        }
        if (requestCode ==REQUESTCODE ){
            ArrayList<FoodDiary> foodDatas = (ArrayList<FoodDiary>) data.getSerializableExtra(FOODDATA);
            for(FoodDiary foodDiary : foodDatas){
                mDayFoodDiary.insertData(foodDiary);
            }
        }
        adapterDinner.notifyDataSetChanged();
        adapterNoon.notifyDataSetChanged();
        adapterMorning.notifyDataSetChanged();
        updateCalorie();
    }

    //跟新卡路里
    public void updateCalorie(){
        tv_sum_moring.setText(mDayFoodDiary.getMorningCalorie()+"千卡");
        tv_sum_noon.setText(mDayFoodDiary.getNoonCalorie()+"千卡");
        tv_sum_dinner.setText(mDayFoodDiary.getDinnerCalorie()+"千卡");
        tv_sum.setText("总摄入:"+mDayFoodDiary.getSumCalore()+"千卡");
    }

}
