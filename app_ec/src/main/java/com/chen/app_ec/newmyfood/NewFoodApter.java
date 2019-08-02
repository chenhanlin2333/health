package com.chen.app_ec.newmyfood;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chen.app_ec.R;
import com.chen.app_ec.data.FoodData;
import com.chen.app_ec.data.FoodDiary;

import java.util.ArrayList;

public class NewFoodApter extends RecyclerView.Adapter<NewFoodApter.SimpleViewHolder> {

    Context mContext;
    ArrayList<FoodDiary> mFoodDiaries = new ArrayList<>();

    public NewFoodApter(Context context, ArrayList<FoodDiary> foodDiaries) {
        mContext = context;
        mFoodDiaries = foodDiaries;
    }


    @NonNull
    @Override
    public SimpleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.viewholder_simple_food,viewGroup,false);
        return new SimpleViewHolder(view);
    }


    //TODO 数据绑定

    @Override
    public void onBindViewHolder(@NonNull SimpleViewHolder simpleViewHolder, int i) {
        FoodDiary foodDiary = mFoodDiaries.get(i);
        FoodData foodData = foodDiary.getFoodData();
//        simpleViewHolder.tv_name.setText(foodData.getFoodname());
//        simpleViewHolder.tv_gram.setText(foodDiary.getGram()+"");
//        simpleViewHolder.tv_calore.setText(((foodDiary.getGram()/100)*foodData.getCalorie()+""));
    }



    @Override
    public int getItemCount() {
        return 0;
    }

    class SimpleViewHolder extends RecyclerView.ViewHolder{
        TextView tv_name;
        TextView tv_gram;
        TextView tv_calore;


        public SimpleViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_gram = itemView.findViewById(R.id.tv_gram);
            tv_calore = itemView.findViewById(R.id.tv_calorie);

        }
    }
}
