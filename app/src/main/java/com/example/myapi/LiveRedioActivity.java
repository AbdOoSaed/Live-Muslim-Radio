package com.example.myapi;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapi.Adapter.AdapterRadio;
import com.example.myapi.Api.ApiManger;
import com.example.myapi.Model.LanguageItem;
import com.example.myapi.Model.RadiosItem;
import com.example.myapi.Model.RadiosResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveRedioActivity extends AppCompatActivity {

    private RecyclerView recyLive;
    private String[] arrOfStr;
    private AdapterRadio adapterRadio;
    private List<RadiosItem> radiosItems = new ArrayList<>();
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_redio);
        Intent i = getIntent();
        LanguageItem language = (LanguageItem) i.getSerializableExtra("language");
        arrOfStr = language.getRadioUrl().split("http://api.mp3quran.net/");
        initView();
        adapterRadio = new AdapterRadio(null, LiveRedioActivity.this);
        recyLive.setLayoutManager(new LinearLayoutManager(this));
        recyLive.setAdapter(adapterRadio);
        threadGetRadioLive getRadioLive = new threadGetRadioLive();
        getRadioLive.start();
        adapterRadio.setListener(new AdapterRadio.OnItemClickListener() {
            @Override
            public void onItemClick(Object item, int position) {
                if (position >= 0) {
                    RadiosItem radiosItem = radiosItems.get(position);
                    //play
                    stopMediaPlayer();
                    try {
//                        Toast.makeText(LiveRedioActivity.this,  radiosItem.getRadioUrl(), Toast.LENGTH_SHORT).show();
                        mediaPlayer = new MediaPlayer();
                        mediaPlayer.setDataSource(radiosItem.getRadioUrl());
                        mediaPlayer.prepareAsync();
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();

                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    //stop
                    stopMediaPlayer();
                }

            }
        });
    }


    private void initView() {
        recyLive = findViewById(R.id.recy_live);
    }

    private void stopMediaPlayer() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
//            mediaPlayer.release();
        }
    }

    class threadGetRadioLive extends Thread {
        @Override
        public void run() {
            boolean b = true;

            while (b) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ApiManger.getApi().getAllRedioChanal(arrOfStr[1]).enqueue(new Callback<RadiosResponse>() {
                            @Override
                            public void onResponse(Call<RadiosResponse> call, Response<RadiosResponse> response) {

                                if (response.isSuccessful()) {
                                    assert response.body() != null;
                                    radiosItems = response.body().getRadios();
                                    adapterRadio.changeData(radiosItems);

                                }
                            }

                            @Override
                            public void onFailure(Call<RadiosResponse> call, Throwable t) {
                                Toast.makeText(LiveRedioActivity.this, t.getMessage() + "", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });


                try {
                    if (!radiosItems.isEmpty()) {
                        b = false;
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
