package com.chen.app_ec.Ec_core_content.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chen.app_ec.R;

import java.util.ArrayList;

import static com.chen.app_core.util.ChangeUtil.drawable2Bitmap;

public class Foodadapter extends RecyclerView.Adapter<Foodadapter.FoodViewHolder> {


    private ArrayList<String> data ;
    private Context mContext;


    public Foodadapter(Context context, ArrayList arrayList){
        data = arrayList;
        mContext = context;
    }


    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.viewholder_food,viewGroup,
                false);
        return new FoodViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull final FoodViewHolder foodViewHolder, int i) {
        Drawable drawable = foodViewHolder.mImageView.getDrawable();
        Palette.from(drawable2Bitmap(drawable)).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(@Nullable Palette palette) {
                int rgb = palette.getLightMutedColor(Color.BLUE);
                foodViewHolder.ll_container.setBackgroundColor(rgb);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    class FoodViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageView ;
        LinearLayout ll_container;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.iv_food);
            ll_container =itemView.findViewById(R.id.ll_container);
        }
    }



}
