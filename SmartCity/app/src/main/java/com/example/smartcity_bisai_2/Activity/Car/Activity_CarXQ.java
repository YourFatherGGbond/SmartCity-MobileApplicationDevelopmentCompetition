package com.example.smartcity_bisai_2.Activity.Car;

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
import com.example.smartcity_bisai_2.Json.GetData;
import com.example.smartcity_bisai_2.R;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Activity_CarXQ extends AppCompatActivity {

    private ImageView car_back;
    private TextView carXq_title;
    private ImageView carXq_img;
    private TextView carXq_address;
    private TextView carXq_juli;
    private TextView carXq_open;
    private TextView carXq_moeny;
    private TextView carXq_kong;

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

                carXq_title.setText(object1.get("parkName").getAsString());
                Glide.with(Activity_CarXQ.this).load(GetData.IP + object1.get("imgUrl").getAsString()).into(carXq_img);
                carXq_address.setText("地址:" + object1.get("address").getAsString());
                carXq_juli.setText("距离:" + object1.get("distance").getAsString());
                carXq_open.setText("是否开放:" + object1.get("open").getAsString());
                carXq_moeny.setText("收费:" + object1.get("rates").getAsString() + " /h");
                carXq_kong.setText("空位:" + object1.get("vacancy").getAsString());

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__car_x_q);
        initView();
    }

    private void initView() {
        car_back = (ImageView) findViewById(R.id.car_back);
        carXq_title = (TextView) findViewById(R.id.carXq_title);
        carXq_img = (ImageView) findViewById(R.id.carXq_img);
        carXq_address = (TextView) findViewById(R.id.carXq_address);
        carXq_juli = (TextView) findViewById(R.id.carXq_juli);
        carXq_open = (TextView) findViewById(R.id.carXq_open);
        carXq_moeny = (TextView) findViewById(R.id.carXq_moeny);
        carXq_kong = (TextView) findViewById(R.id.carXq_kong);

        bundle = getIntent().getExtras();

        car_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        GetData.get("/prod-api/api/park/lot/" + bundle.getInt("id"), 0, mHandler);
    }
}