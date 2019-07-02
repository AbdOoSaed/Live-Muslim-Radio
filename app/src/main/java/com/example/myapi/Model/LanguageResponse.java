package com.example.myapi.Model;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;


public class LanguageResponse {

	@SerializedName("language")
	private List<LanguageItem> language;

	public void setLanguage(List<LanguageItem> language){
		this.language = language;
	}

	public List<LanguageItem> getLanguage(){
		return language;
	}

	@Override
 	public String toString(){
		return 
			"LanguageResponse{" +
			"language = '" + language + '\'' + 
			"}";
		}
}