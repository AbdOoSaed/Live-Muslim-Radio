package com.example.myapi.Api;

import com.example.myapi.MainActivity;
import com.example.myapi.Model.LanguageItem;
import com.example.myapi.Model.LanguageResponse;
import com.example.myapi.Model.RadiosItem;
import com.example.myapi.Model.RadiosResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiCalls {
    String BASE_URL = "http://api.mp3quran.net/";

//    public void getUrl();

    @GET
    Call<LanguageResponse> getAllLanguageChanal(@Url String anEmptyString);

    //"radios/get_radios.php"
//    Call<LanguageResponse> getAllLanguageChanal();//@Query("id") String id


    @GET//("radios/radio_arabic.json")
    Call<RadiosResponse> getAllRedioChanal(@Url String anEmptyString);

}
