package com.example.smartcity_bisai_2.Activity.Car;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity_bisai_2.Activity.Activity_ZhuCe;
import com.example.smartcity_bisai_2.Adapter.Adapter_car;
import com.example.smartcity_bisai_2.GoToCarXq;
import com.example.smartcity_bisai_2.Json.Car;
import com.example.smartcity_bisai_2.Json.GetData;
import com.example.smartcity_bisai_2.Json.JsonParse;
import com.example.smartcity_bisai_2.R;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.List;

public class Activity_Car extends AppCompatActivity {

    private ImageView car_back;
    private RecyclerView car_recyclerView;
    private Adapter_car adapter_car;

    public Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            JsonParser jsonParser = new JsonParser();
            JsonObject object = jsonParser.parse((String) msg.obj).getAsJsonObject();
            super.handleMessage(msg);
            if (msg.what == 0) {
                String rows = String.valueOf(object.get("rows"));
                final List<Car> carList = JsonParse.getCar(rows);

                adapter_car = new Adapter_car(Activity_Car.this, carList, new Adapter_car.OnItemClickListener() {
                    @Override
                    public void onClick(int position) {
                        startActivity(GoToCarXq.goCar(Activity_Car.this,position,carList));
                        Toast.makeText(Activity_Car.this, "点击了"+position+"号停车场", Toast.LENGTH_SHORT).show();
                    }
                });
                car_recyclerView.setLayoutManager(new GridLayoutManager(Activity_Car.this,1));
                car_recyclerView.setAdapter(adapter_car);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__car);
        initView();
    }

    private void initView() {
        car_back = (ImageView) findViewById(R.id.car_back);
        car_recyclerView = (RecyclerView) findViewById(R.id.car_recyclerView);

        car_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        GetData.get("/prod-api/api/park/lot/list",0,mHandler);
    }
}