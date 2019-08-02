package com.chen.app_ec.Ec_core_content.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.chen.app_ec.Ec_core_content.MeFragment;
import com.chen.app_ec.Ec_core_content.MyFoodFragment;
import com.chen.app_ec.Ec_core_content.SearchFoodFragment;

import java.util.ArrayList;

public class MyViewPageAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<String> names = new ArrayList<>();

    public MyViewPageAdapter(FragmentManager fm) {
        super(fm);
        mFragments.add(new MyFoodFragment());
        mFragments.add(new SearchFoodFragment());
        mFragments.add(new MeFragment());
        names.add("食物管理");
        names.add("食物搜索");
        names.add("我的资料");
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return names.get(position);
    }
}
