package com.chen.app_ec.login;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.chen.app_core.app.BaseFragment;
import com.chen.app_core.stroge.AppSharedpreference;
import com.chen.app_ec.R;
import com.chen.app_ec.login.ui.LauncherHolderCreator;

import java.util.ArrayList;

public class NavigationFragment extends BaseFragment implements OnItemClickListener, ViewPager.OnPageChangeListener {

    private ConvenientBanner<Integer> mConvenientBanner = null;
    private static final ArrayList<Integer> INSTANCE = new ArrayList<>();


    @Override
    public Object setlayout() {
        mConvenientBanner = new ConvenientBanner<>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void bindview(Bundle savedInstance, View rootview) {
        INSTANCE.add(R.drawable.nav_bc_0);
        INSTANCE.add(R.drawable.nav_bc_1);
        INSTANCE.add(R.drawable.nav_bc_2);
        INSTANCE.add(R.drawable.nav_bc_3);
        mConvenientBanner
                .setPages(new LauncherHolderCreator(),INSTANCE)
                .setPageIndicator(new int[]{R.drawable.dot_normal,R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setOnPageChangeListener(this)
                .setCanLoop(false)
        ;
    }

    @Override
    public void onItemClick(int position) {
//        NavigationActivity navigationActivity = (NavigationActivity) getActivity();
//        if (position == INSTANCE.size()-1){
//            AppSharedpreference.setISFirstopen(false);
//            navigationActivity.showBt();
//        }else {
//            navigationActivity.hideBt();
//        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {
        NavigationActivity navigationActivity = (NavigationActivity) getActivity();
        if (i == INSTANCE.size()-1){
            AppSharedpreference.setISFirstopen(false);
            navigationActivity.showBt();
        }else {
            navigationActivity.hideBt();
        }
    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
