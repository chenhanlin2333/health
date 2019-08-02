package com.chen.app_ec.search;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chen.app_ec.R;

import java.util.ArrayList;

public class TextViewAdapter extends RecyclerView.Adapter<TextViewAdapter.TextViewHolder> {

    Context mContext;
    ArrayList<String> data;
    ViewHolderOnclick mViewHolderOnclick;

    public void setViewHolderOnclick(ViewHolderOnclick viewHolderOnclick) {
        mViewHolderOnclick = viewHolderOnclick;
    }

    public TextViewAdapter(Context context, ArrayList<String> data) {
        mContext = context;
        this.data = data;
    }

    @NonNull
    @Override
    public TextViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.viewholder_textview,viewGroup,false);
        return new TextViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TextViewHolder textViewHolder, int i) {
         textViewHolder.tv_text.setText(data.get(i));
         textViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if (mViewHolderOnclick != null){
                     mViewHolderOnclick.onClick(view);
                 }
             }
         });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class TextViewHolder extends RecyclerView.ViewHolder {
        TextView tv_text;

        public TextViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_text = itemView.findViewById(R.id.tv_text);
        }
    }

}
