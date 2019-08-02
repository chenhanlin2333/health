package com.chen.app_ec.data;

import java.util.Date;

public class DayFood {
    private String img_url;
    private String foodname;
    //营养成分
    private String nutritional;
    //热量
    private int calorie;
    //日期
    private Date mDate;

    //作为是否为实际数标记
    private boolean isT;

    private DAYTYPE TYPE;


    public DayFood(String img_url, String foodname, String nutritional, int calorie, Date date, boolean isT,DAYTYPE TYPE) {
        this.img_url = img_url;
        this.foodname = foodname;
        this.nutritional = nutritional;
        this.calorie = calorie;
        mDate = date;
        this.isT = isT;
        this.TYPE = TYPE;
    }

    public DAYTYPE getTYPE() {
        return TYPE;
    }

    public void setTYPE(DAYTYPE TYPE) {
        this.TYPE = TYPE;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getNutritional() {
        return nutritional;
    }

    public void setNutritional(String nutritional) {
        this.nutritional = nutritional;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isT() {
        return isT;
    }

    public void setT(boolean t) {
        isT = t;
    }

    enum DAYTYPE{
        Morning("早餐"),
        Noon("午餐"),
        Diner("晚餐");
        private String name;
        DAYTYPE(String name) {
            this.name = name;
        }
    }
}
