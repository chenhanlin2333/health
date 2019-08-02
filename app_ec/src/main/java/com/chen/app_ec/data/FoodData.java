package com.chen.app_ec.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class FoodData implements Serializable {
    private String img_url;
    private String foodname;
    //营养成分
    private String nutritional;
    //热量
    private int calorie;

    private ArrayList<Nutrition> mNutritionArrayLislt = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodData foodData = (FoodData) o;
        return calorie == foodData.calorie &&
                Objects.equals(img_url, foodData.img_url) &&
                Objects.equals(foodname, foodData.foodname) &&
                Objects.equals(nutritional, foodData.nutritional);
    }

    @Override
    public int hashCode() {
        return Objects.hash(img_url, foodname, nutritional, calorie);
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
}
