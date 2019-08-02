package com.chen.app_ec.data;

import java.util.ArrayList;

import retrofit2.http.GET;

public class DayFoodDiary {
    String date;
    ArrayList<FoodDiary> morningArrayList = new ArrayList<>();
    ArrayList<FoodDiary> noonArraylist = new ArrayList<>();
    ArrayList<FoodDiary> dinnerArraylist = new ArrayList<>();


    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public ArrayList<FoodDiary> getMorningArrayList() {
        return morningArrayList;
    }

    public ArrayList<FoodDiary> getNoonArraylist() {
        return noonArraylist;
    }

    public ArrayList<FoodDiary> getDinnerArraylist() {
        return dinnerArraylist;
    }

    public void insertData(FoodDiary foodDiary){
        if (foodDiary.getFoodtype() == Foodtype.MORNING){
            morningArrayList.add(foodDiary);
        }
        if (foodDiary.getFoodtype() == Foodtype.NOON){
            noonArraylist.add(foodDiary);
        }
        if (foodDiary.getFoodtype() == Foodtype.DINNER){
            dinnerArraylist.add(foodDiary);
        }
    }

    public void removieData(FoodDiary foodDiary){
        if (foodDiary.getFoodtype() == Foodtype.MORNING){
            morningArrayList.remove(foodDiary);
        }
        if (foodDiary.getFoodtype() == Foodtype.NOON){
            noonArraylist.remove(foodDiary);
        }
        if (foodDiary.getFoodtype() == Foodtype.DINNER){
            dinnerArraylist.remove(foodDiary);
        }
    }

    public int getMorningCalorie(){
        int num = 0;
        for (int i = 0; i < morningArrayList.size(); i++) {
            FoodDiary foodDiary = morningArrayList.get(i);
            FoodData foodData = morningArrayList.get(i).getFoodData();
            num += (foodDiary.getGram()/100)*foodData.getCalorie();
        }
        return num;
    }


    public int getNoonCalorie(){
        int num = 0;
        for (int i = 0; i < noonArraylist.size(); i++) {
            FoodDiary foodDiary = noonArraylist.get(i);
            FoodData foodData = noonArraylist.get(i).getFoodData();
            num += (foodDiary.getGram()/100)*foodData.getCalorie();
        }
        return num;
    }
    public int getDinnerCalorie(){
        int num = 0;
        for (int i = 0; i < dinnerArraylist.size(); i++) {
            FoodDiary foodDiary = dinnerArraylist.get(i);
            FoodData foodData = dinnerArraylist.get(i).getFoodData();
            num += (foodDiary.getGram()/100)*foodData.getCalorie();
        }
        return num;
    }

    public int getSumCalore(){
        return getMorningCalorie()+getNoonCalorie()+getDinnerCalorie();
    }

}
