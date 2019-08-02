package com.chen.app_ec.data;

import java.io.Serializable;
import java.util.ArrayList;

public class Nutrition implements Serializable {

    public String Nname;
    public String Ncontent;

    public Nutrition(String nname, String ncontent) {
        Nname = nname;
        Ncontent = ncontent;
    }

    public String getNname() {
        return Nname;
    }

    public void setNname(String nname) {
        Nname = nname;
    }

    public String getNcontent() {
        return Ncontent;
    }

    public void setNcontent(String ncontent) {
        Ncontent = ncontent;
    }
}
