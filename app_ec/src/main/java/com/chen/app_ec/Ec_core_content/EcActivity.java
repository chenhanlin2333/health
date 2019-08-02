package com.chen.app_ec.Ec_core_content;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chen.app_core.app.BaseActivity;
import com.chen.app_ec.Ec_core_content.adapter.MyViewPageAdapter;
import com.chen.app_ec.R;
import com.chen.app_ec.R2;

import java.util.ArrayList;

import butterknife.BindView;

public class EcActivity extends BaseActivity {

    @BindView(R2.id.vp_fragment_container)
    ViewPager mViewPager;

    @BindView(R2.id.tl_nav)
    TabLayout mTlnav;

    @Override
    public int getLayoutid() {
        return R.layout.activity_ec;
    }

    @Override
    public void havebind() {
        initTablayout();
    }


    ArrayList<String> mTitlelist = new ArrayList<>();
    ArrayList<Integer> mIndectorList = new ArrayList<>();


    private void initTablayout(){
        mTitlelist.add("膳食记录");
        mTitlelist.add("食物推荐");
        mTitlelist.add("我的资料");

        mIndectorList.add(R.drawable.rb_canzuo_select);
        mIndectorList.add(R.drawable.rb_food_select);
        mIndectorList.add(R.drawable.rb_person_select);

        mViewPager.setAdapter(new MyViewPageAdapter(getSupportFragmentManager()));
        mTlnav.setupWithViewPager(mViewPager);
        for (int i = 0; i < mTlnav.getTabCount(); i++) {
            TabLayout.Tab tab = mTlnav.getTabAt(i);
            if (tab != null){
                tab.setCustomView(getTabView(mTitlelist.get(i),mIndectorList.get(i)));
            }
        }

        mTlnav.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ImageView imageView = tab.getCustomView().findViewById(R.id.imageview);
                TextView textView = tab.getCustomView().findViewById(R.id.textview);
                textView.setTextColor(Color.parseColor("#ffffbb33"));
                imageView.setSelected(true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                ImageView imageView = tab.getCustomView().findViewById(R.id.imageview);
                imageView.setSelected(false);
                TextView textView = tab.getCustomView().findViewById(R.id.textview);
                textView.setTextColor(Color.parseColor("#a1a1a1"));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mViewPager.setCurrentItem(1);
        mViewPager.setOffscreenPageLimit(3);
    }

    public View getTabView(String title, int src) {
        View v = LayoutInflater.from(this).inflate(R.layout.tab_item_view, null);
        TextView textView =  v.findViewById(R.id.textview);
        textView.setText(title);
        ImageView imageView =  v.findViewById(R.id.imageview);
        imageView.setImageResource(src);
        return v;
    }

    public static void start(Activity activity){
        Intent intent = new Intent(activity, EcActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }
}
