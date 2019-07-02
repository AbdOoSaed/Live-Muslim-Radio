package com.example.myapi.Model;



import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class RadiosItem implements Serializable {

    @SerializedName("name")
    private String name;

    @SerializedName("radio_url")
    private String radioUrl;

    private boolean stetusCheck;

    public RadiosItem(boolean stetusCheck) {
        this.stetusCheck = stetusCheck;
    }

    public boolean isStetusCheck() {
        return stetusCheck;
    }

    public void setStetusCheck(boolean stetusCheck) {
        this.stetusCheck = stetusCheck;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setRadioUrl(String radioUrl) {
        this.radioUrl = radioUrl;
    }

    public String getRadioUrl() {
        return radioUrl;
    }

    @Override
    public String toString() {
        return
                "RadiosItem{" +
                        "name = '" + name + '\'' +
                        ",radio_url = '" + radioUrl + '\'' +
                        "}";
    }
}