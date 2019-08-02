package com.chen.app_ec.search;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.chen.app_ec.data.FoodData;
import com.chen.app_ec.R;

import java.util.ArrayList;


public class SearchFoodAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEWHOLDER0 =0;
    private static final int VIEWHOLDER1 =1;

    private ArrayList<FoodData> data ;
    private Context mContext;

    private boolean isSearch;

    private ViewHolderDataOnclick<FoodData> mViewHolderOnclick;
    private ViewholderDataOnclick1<FoodData> mViewholderDataOnclick1;

    public void setFoodDataViewholderDataOnclick1(ViewholderDataOnclick1<FoodData> foodDataViewholderDataOnclick1) {
        mViewholderDataOnclick1 = foodDataViewholderDataOnclick1;
    }

    public void setViewHolderOnclick(ViewHolderDataOnclick<FoodData> viewHolderOnclick) {
        mViewHolderOnclick = viewHolderOnclick;
    }

    public SearchFoodAdaper(Context context, ArrayList arrayList, boolean isSearch){
        data = arrayList;
        mContext = context;
        this.isSearch = isSearch;
    }

    @Override
    public int getItemViewType(int position) {
        if (isSearch){
            return VIEWHOLDER0;
        }else {
            return VIEWHOLDER1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if (i == VIEWHOLDER0){
            View view = LayoutInflater.from(mContext).inflate(R.layout.viewholder_searchfood,viewGroup, false);
            return new FoodViewHolder(view);
        }
        else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.viewholder_vs_searchfood,viewGroup, false);
            return new VsFoodViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int i) {

        if (isSearch){
            //TODO 绑定数据
            ((FoodViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int positon = holder.getAdapterPosition();
//                     FoodData Food = data.get(positon);
                    if (mViewholderDataOnclick1!=null){
                        mViewholderDataOnclick1.onItemClick1(null);
                    }

                }
            });
        }else {
            ((VsFoodViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int positon = holder.getAdapterPosition();
//                    FoodData Food = data.get(positon);
                    FoodData food = null;
                    if (mViewHolderOnclick != null){
                        mViewHolderOnclick.onItemClick(food);
                    }
                }
            });

        }
    }



    //TODO 后面需要修改
    @Override
    public int getItemCount() {
        return 10;
    }



    class FoodViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageView ;
        TextView foodname;
        TextView tv_num;
        TextView tv_danwei;


        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.img_foodpic);
            foodname = itemView.findViewById(R.id.tv_food_name);
            tv_num  =itemView.findViewById(R.id.tv_num_of_nutritin);
            tv_danwei =itemView.findViewById(R.id.tv_danwei_of_nutritin);
        }
    }

    class VsFoodViewHolder extends RecyclerView.ViewHolder{

        public VsFoodViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


}
