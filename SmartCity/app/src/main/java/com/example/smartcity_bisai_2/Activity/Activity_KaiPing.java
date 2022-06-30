package com.example.smartcity_bisai_2.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.smartcity_bisai_2.Json.GetData;
import com.example.smartcity_bisai_2.Json.Img;
import com.example.smartcity_bisai_2.Json.JsonParse;
import com.example.smartcity_bisai_2.R;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.List;

public class Activity_KaiPing extends AppCompatActivity {
    private Handler handler_time = new Handler();
    private Runnable runnable;

    private Time time_in;
    private int time = 3000;

    private ImageView kaiping_img;
    private TextView kaiping_text;
    private RelativeLayout kaiping_Rl;


    public Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            JsonParser jsonParser = new JsonParser();
            JsonObject object = jsonParser.parse((String) msg.obj).getAsJsonObject();
            super.handleMessage(msg);
            if (msg.what == 0) {
                String rows = String.valueOf(object.get("rows"));
                List<Img> imgList = JsonParse.getImg(rows);

                Glide.with(Activity_KaiPing.this).load(GetData.IP + imgList.get(0).getAdvImg()).into(kaiping_img);
                setTime();
                kaiping_Rl.setVisibility(View.VISIBLE);

                runnable = new Runnable() {
                    @Override
                    public void run() {
                        handler_time.removeCallbacks(runnable);
                        Intent intent = new Intent(Activity_KaiPing.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                };
                handler_time.postDelayed(runnable,time);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__kai_ping);
        initView();
    }

    private void initView() {
        kaiping_img = (ImageView) findViewById(R.id.kaiping_img);
        kaiping_text = (TextView) findViewById(R.id.kaiping_text);

        GetData.get("/prod-api/api/rotation/list", 0, mHandler);
        kaiping_Rl = (RelativeLayout) findViewById(R.id.kaiping_Rl);

    }

    private void setTime(){
        time_in = new Time(time,1000);
        time_in.start();

        kaiping_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler_time.removeCallbacks(runnable);
                Intent intent = new Intent(Activity_KaiPing.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    class Time extends CountDownTimer {

        public Time(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            kaiping_text.setText(l/1000+"s 跳过");
        }

        @Override
        public void onFinish() {

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (time_in!=null){
            time_in.cancel();
        }
    }
}