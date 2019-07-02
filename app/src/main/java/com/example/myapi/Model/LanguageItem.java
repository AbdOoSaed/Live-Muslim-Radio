package com.example.myapi.Model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class LanguageItem implements Serializable {

    @SerializedName("language")
    private String language;

    @SerializedName("id")
    private String id;

    @SerializedName("radio_url")
    private String radioUrl;

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
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
                "LanguageItem{" +
                        "language = '" + language + '\'' +
                        ",id = '" + id + '\'' +
                        ",radio_url = '" + radioUrl + '\'' +
                        "}";
    }
}