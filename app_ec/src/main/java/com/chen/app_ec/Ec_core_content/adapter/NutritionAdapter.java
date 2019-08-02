package com.chen.app_ec.Ec_core_content.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chen.app_ec.data.Nutrition;
import com.chen.app_ec.R;

import java.util.ArrayList;

public class NutritionAdapter extends RecyclerView.Adapter<NutritionAdapter.NutritionViewHolder> {


    ArrayList<Nutrition> data;
    Context mContext;

    public NutritionAdapter( Context context,ArrayList<Nutrition> data) {
        this.data = data;
        mContext = context;
    }

    @NonNull
    @Override
    public NutritionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.viewholder_nutrition,viewGroup,false);
        return new NutritionViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull NutritionViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 8;
    }

    class NutritionViewHolder extends RecyclerView.ViewHolder{
        TextView tv_name;
        TextView tv_liang;
        TextView tv_beizhu;


        public NutritionViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_nutrition_name);
            tv_liang =itemView.findViewById(R.id.tv_nutrition_liang);
            tv_beizhu = itemView.findViewById(R.id.tv_nutrition_beizhu);
        }
    }
}
