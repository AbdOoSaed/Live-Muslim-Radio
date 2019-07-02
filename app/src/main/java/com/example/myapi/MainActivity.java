package com.example.myapi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapi.Adapter.AdapterLanguage;
import com.example.myapi.Adapter.AdapterRadio;
import com.example.myapi.Api.ApiManger;
import com.example.myapi.Model.LanguageItem;
import com.example.myapi.Model.LanguageResponse;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        final AdapterLanguage adapterLanguage = new AdapterLanguage(null, MainActivity.this);
        recy.setLayoutManager(new LinearLayoutManager(this));
        recy.setAdapter(adapterLanguage);
        adapterLanguage.setListenerLanguage(new AdapterRadio.OnItemClickListener() {
            @Override
            public void onItemClick(Object item, int position) {
                Intent intent = new Intent(getApplicationContext(), LiveRedioActivity.class);
                intent.putExtra("language", (Serializable) item);
                startActivity(intent);
            }
        });


        ApiManger.getApi().getAllLanguageChanal("radios/get_radios.php").enqueue(new Callback<LanguageResponse>() {
            @Override
            public void onResponse(Call<LanguageResponse> call, Response<LanguageResponse> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    List<LanguageItem> languageItems = response.body().getLanguage();
                    adapterLanguage.changeData(languageItems);

                }
            }

            @Override
            public void onFailure(Call<LanguageResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage() + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        recy = findViewById(R.id.recy_main);
    }
}
