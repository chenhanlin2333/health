package com.chen.app_ec.database;


import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.structure.BaseModel;

public class FoodMoudle extends BaseModel {

    @PrimaryKey(autoincrement = true)
    private int id;

    @Column
    String name;

    @Column
    String url;

    @Column
    int hot;

    @Column
    int daibaizhi;



}
