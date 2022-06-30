package com.example.smartcity_bisai_2.Activity.BaShi;

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

import com.example.smartcity_bisai_2.Adapter.Adapter_bashi;
import com.example.smartcity_bisai_2.Json.BaShi;
import com.example.smartcity_bisai_2.Json.GetData;
import com.example.smartcity_bisai_2.Json.JsonParse;
import com.example.smartcity_bisai_2.R;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.List;

public class Activity_BaShi extends AppCompatActivity {

    private ImageView bashi_back;
    private RecyclerView recyclerView;
    private Adapter_bashi adapter_bashi;

    public Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            JsonParser jsonParser = new JsonParser();
            JsonObject object = jsonParser.parse((String) msg.obj).getAsJsonObject();
            super.handleMessage(msg);
            if (msg.what == 0) {
                String rows = String.valueOf(object.get("rows"));
                final List<BaShi> baShiList = JsonParse.getBaShi(rows);

                adapter_bashi = new Adapter_bashi(Activity_BaShi.this, baShiList, new Adapter_bashi.OnItemClickListener() {
                    @Override
                    public void onClick(int position) {
                        Toast.makeText(Activity_BaShi.this, "点击了"+position+"号巴士", Toast.LENGTH_SHORT).show();
                    }
                });
                recyclerView.setLayoutManager(new GridLayoutManager(Activity_BaShi.this,1));
                recyclerView.setAdapter(adapter_bashi);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__ba_shi);
        initView();
    }

    private void initView() {
        bashi_back = (ImageView) findViewById(R.id.bashi_back);
        recyclerView = (RecyclerView) findViewById(R.id.bashi_recyclerView);

        bashi_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        GetData.get("/prod-api/api/bus/line/list",0,mHandler);
    }
}