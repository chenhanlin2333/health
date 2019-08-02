package com.chen.app_ec.login;


public class User {

    private String phone;
    private String password;
    private int age;
    private int weight;
    private int high;
    private String info;

    public static volatile User INSATNCE = null;

    private User(String phone, String password, int age , int weight, int high, String info){
        this.phone = phone;
        this.password = password;
        this.age = age;
        this.weight = weight;
        this.high = high;
        this.info = info;
    }

    public static User getInstanceFirst(String phone, String password, int age , int weight, int high, String info){
       if (INSATNCE == null){
           synchronized (User.class){
               if (INSATNCE == null){
                   INSATNCE = new User(phone,password,age,weight,high,info);
               }
           }
       }
       return INSATNCE;
    }

    public static User getINSATNCE() {
        return INSATNCE;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    public int getWeight() {
        return weight;
    }

    public int getHigh() {
        return high;
    }

    public String getInfo() {
        return info;
    }
}
