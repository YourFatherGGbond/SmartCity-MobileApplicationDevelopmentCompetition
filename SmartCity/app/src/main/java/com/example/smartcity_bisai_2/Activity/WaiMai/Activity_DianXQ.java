package com.example.smartcity_bisai_2.Activity.WaiMai;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.smartcity_bisai_2.Activity.Car.Activity_CarXQ;
import com.example.smartcity_bisai_2.Json.GetData;
import com.example.smartcity_bisai_2.R;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Activity_DianXQ extends AppCompatActivity {

    private ImageView dianXq_back;
    private TextView dianXqXq_title;
    private ImageView dianXqXq_img;
    private TextView dianXqXq_address;
    private TextView dianXqXq_juli;
    private TextView dianXqXq_time;
    private TextView dianXqXq_xiaoliang;
    private TextView dianXqXq_fen;

    private Bundle bundle;

    public Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            JsonParser jsonParser = new JsonParser();
            JsonObject object = jsonParser.parse((String) msg.obj).getAsJsonObject();
            super.handleMessage(msg);
            if (msg.what == 0) {
                String data = String.valueOf(object.get("data"));
                JsonObject object1 = jsonParser.parse(data).getAsJsonObject();

                dianXqXq_title.setText(object1.get("name").getAsString());
                Glide.with(Activity_DianXQ.this).load(GetData.IP+object1.get("imgUrl").getAsString()).into(dianXqXq_img);
                dianXqXq_address.setText("地址:"+object1.get("address").getAsString());
                dianXqXq_juli.setText("距离:"+object1.get("distance").getAsString());
                dianXqXq_time.setText("时间:"+object1.get("deliveryTime").getAsString());
                dianXqXq_xiaoliang.setText("3小时销量:"+object1.get("saleNum3hour").getAsString()+" /h");
                dianXqXq_fen.setText("评分:"+object1.get("score").getAsString());

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__dian_x_q);
        initView();
    }

    private void initView() {
        dianXq_back = (ImageView) findViewById(R.id.dianXq_back);
        dianXqXq_title = (TextView) findViewById(R.id.dianXqXq_title);
        dianXqXq_img = (ImageView) findViewById(R.id.dianXqXq_img);
        dianXqXq_address = (TextView) findViewById(R.id.dianXqXq_address);
        dianXqXq_juli = (TextView) findViewById(R.id.dianXqXq_juli);
        dianXqXq_time = (TextView) findViewById(R.id.dianXqXq_time);
        dianXqXq_xiaoliang = (TextView) findViewById(R.id.dianXqXq_xiaoliang);
        dianXqXq_fen = (TextView) findViewById(R.id.dianXqXq_fen);

        bundle = getIntent().getExtras();

        dianXq_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        GetData.get("/prod-api/api/takeout/seller/"+bundle.getInt("id"),0,mHandler);
    }
}