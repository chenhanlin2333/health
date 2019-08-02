package com.chen.app_ec.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Objects;

public class FoodDiary implements Serializable {

    private double gram;
    private Foodtype mFoodtype;
    private FoodData mFoodData;
    int tag;

    public FoodDiary() {

    }

    public FoodDiary(double gram, Foodtype foodtype, FoodData foodData,int tag) {
        this.gram = gram;
        mFoodtype = foodtype;
        mFoodData = foodData;
        this.tag = tag;
    }


    protected FoodDiary(Parcel in) {
        gram = in.readDouble();
        tag = in.readInt();
    }



    public void setTag(int tag) {
        this.tag = tag;
    }

    public double getGram() {
        return gram;
    }

    public void setGram(double gram) {
        this.gram = gram;
    }

    public Foodtype getFoodtype() {
        return mFoodtype;
    }

    public void setFoodtype(Foodtype foodtype) {
        mFoodtype = foodtype;
    }

    public FoodData getFoodData() {
        return mFoodData;
    }

    public void setFoodData(FoodData foodData) {
        mFoodData = foodData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodDiary foodDiary = (FoodDiary) o;
        return Double.compare(foodDiary.gram, gram) == 0 &&
                mFoodtype == foodDiary.mFoodtype && tag == foodDiary.tag &&
                Objects.equals(mFoodData, foodDiary.mFoodData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gram, mFoodtype, mFoodData);
    }

}
