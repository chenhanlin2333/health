package com.chen.app_ec.login.ui;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.chen.app_ec.R;

import java.util.ArrayList;

public class TestLunchHolder implements Holder<Integer> {

    ImageView mImageView;
    TextView mTextView;

    @Override
    public View createView(Context context) {
         View view = View.inflate(context,R.layout.viewholder_navigation,null);
         mImageView = view.findViewById(R.id.img_nav_bc);
         mTextView = view.findViewById(R.id.tv_nav);
         return view;
    }

    @Override
    public void UpdateUI(Context context, int position, Integer data) {
        Glide.with(context).load(data).into(mImageView);
        if (position ==0){
            mTextView.setText("欢\n迎\n来\n到\n健\n康\n饮\n食");
        }
        if (position == 1){
            mTextView.setText("提\n供\n你\n健\n康\n的\n饮\n食\n方\n式" );
        }
        if (position == 2){
            mTextView.setText("为\n你\n饮\n食\n健\n康\n保\n驾\n护\n航");
        }
        if (position == 3){
            mTextView.setText("就\n让\n我\n们\n开\n启\n吧");
        }
    }
}
