package com.chen.app_ec.Ec_core_content.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.chen.app_ec.data.DayFood;
import com.chen.app_ec.R;
import com.chen.app_ec.data.DayFoodDiary;

import java.util.ArrayList;


public class DayOffoodAdapter extends RecyclerView.Adapter {

    private ArrayList<DayFoodDiary> datas ;
    private Context mContext;


    public DayOffoodAdapter(Context context, ArrayList arrayList){
        datas = arrayList;
        mContext = context;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        if (i == 0){
            DayFoodHolder holder;
            View view = LayoutInflater.from(mContext).inflate(R.layout.viewholder_dayfood,viewGroup, false);
            holder = new DayFoodHolder(view);
            return holder;
//        }else {
//            View view = LayoutInflater.from(mContext).inflate(R.layout.viewholder_day, viewGroup, false);
//            return new DayHolder(view);
//        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
//        DayFoodDiary dayFood = datas.get(i);
//        if (dayFood.isT()){
//            DayHolder dayHolder = (DayHolder) viewHolder;
//            //TODO 进行绑定
//        }else {
            DayFoodHolder dayFoodHolder = (DayFoodHolder) viewHolder;
//        }
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    class DayFoodHolder extends RecyclerView.ViewHolder{
        TextView tv_mornig,tv_noon,tv_dinner;
        public DayFoodHolder(@NonNull View itemView) {
            super(itemView);
            tv_mornig = itemView.findViewById(R.id.tv_food_morning);
            tv_noon  = itemView.findViewById(R.id.tv_food_noon);
            tv_dinner = itemView.findViewById(R.id.tv_food_dinner);
        }
    }

    class DayHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView month;
        ImageView im_down_up;

        public DayHolder(@NonNull View itemView) {
            super(itemView);
            month = itemView.findViewById(R.id.tv_food_month);
            im_down_up = itemView.findViewById(R.id.img_up_down);
            im_down_up.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
