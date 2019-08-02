package com.chen.app_ec.Ec_core_content;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.chen.app_ec.newmyfood.DialogFoodfragment;

public class DialogfragmentSex extends DialogFoodfragment {

    private  DialogfragmentCallback<String> mStringDialogfragmentCallback;

    public void setStringDialogfragmentCallback(DialogfragmentCallback<String> stringDialogfragmentCallback) {
        mStringDialogfragmentCallback = stringDialogfragmentCallback;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final String[] sex = {"男","女"};
        builder.setTitle("用户性别");
        builder.setSingleChoiceItems(sex, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (mStringDialogfragmentCallback !=null){
                    mStringDialogfragmentCallback.dealwith(sex[i]);
                    dismiss();
                }
            }
        });
        return  builder.create();
    }


    interface DialogfragmentCallback<T>{
        void dealwith(T t);
    }
}
