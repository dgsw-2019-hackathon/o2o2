package com.example.user.hack;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Api api;

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            api.a().enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Log.e("a", response.body());
                    String result = response.body();

                    if (result.equals("1")){
                        Notification.Builder builder = new Notification.Builder(MainActivity.this, "channel_id")
                                .setContentTitle("알림")
                                .setContentText("누군가가 나가려고 하고 있습니다.")
                                .setSmallIcon(R.mipmap.ic_launcher_round)
                                .setAutoCancel(true);
                        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        notificationManager.notify(0, builder.build());


                    }

                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.e("a", t.getMessage());
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = findViewById(R.id.webview);
        String url = "http://192.168.137.227:8080/?action=stream";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel notificationChannel = new NotificationChannel("channel_id", "channel_name", NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.GREEN);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 100, 200});
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            notificationManager.createNotificationChannel(notificationChannel);
        }


        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.137.209:8420")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

         api = mRetrofit.create(Api.class);

         Timer timer = new Timer();
         timer.schedule(task, 0, 1000);
//        CDT.start(); //CountDownTimer

        webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

        });

        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        webView.loadUrl(url);

        findViewById(R.id.off_button).setOnClickListener(view -> {
            api.b(new Request(1)).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful())
                        Log.e("aaaa", "Aaaaa");
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.e("aaaa", t.getMessage());
                }
            });
        });


    }

}


