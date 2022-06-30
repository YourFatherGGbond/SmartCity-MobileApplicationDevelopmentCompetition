package com.example.smartcity_bisai_2.Activity.WaiMai;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity_bisai_2.Adapter.Adapter_wmbtn_icon;
import com.example.smartcity_bisai_2.Fragment.Me.Fragment_Me;
import com.example.smartcity_bisai_2.Fragment.WaiMai.Fragment_wm;
import com.example.smartcity_bisai_2.Json.GetData;
import com.example.smartcity_bisai_2.Json.JsonParse;
import com.example.smartcity_bisai_2.Json.Wm_icon;
import com.example.smartcity_bisai_2.R;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.List;

public class Activity_wm_main extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView wm_btn_recyclerView;
    private Adapter_wmbtn_icon adapter_wmbtn_icon;

    private Fragment_wm fragment_wm;
    private Fragment_Me fragment_me;

    public Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            JsonParser jsonParser = new JsonParser();
            JsonObject object = jsonParser.parse((String) msg.obj).getAsJsonObject();
            super.handleMessage(msg);
            if (msg.what == 0) {
                String rows = String.valueOf(object.get("data"));
                List<Wm_icon> list = JsonParse.getwm(rows);

                adapter_wmbtn_icon = new Adapter_wmbtn_icon(Activity_wm_main.this, list, new Adapter_wmbtn_icon.OnItemClickListener() {
                    @Override
                    public void onClick(int position) {
                        switch (position) {
                            case 0:
                                if (fragment_wm==null){
                                    fragment_wm = new Fragment_wm();
                                }
                                getSupportFragmentManager().beginTransaction().replace(R.id.main_frameLayout, fragment_wm).commitAllowingStateLoss();
                                break;
                            case 1:

                                break;
                            case 2:
                                if (fragment_me==null){
                                    fragment_me = new Fragment_Me();
                                }
                                getSupportFragmentManager().beginTransaction().replace(R.id.main_frameLayout, fragment_me).commitAllowingStateLoss();
                                break;
                        }
                        adapter_wmbtn_icon.setWm_Color(position);
                    }
                });
                wm_btn_recyclerView.setLayoutManager(new GridLayoutManager(Activity_wm_main.this, 3));
                wm_btn_recyclerView.setAdapter(adapter_wmbtn_icon);
            }
        }
    };
    private ImageView wm_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wm_main);
        initView();
    }

    private void initView() {
        wm_btn_recyclerView = (RecyclerView) findViewById(R.id.wm_btn_recyclerView);

        fragment_wm = new Fragment_wm();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frameLayout, fragment_wm).commitAllowingStateLoss();

        GetData.get("/prod-api/api/takeout/theme/list", 0, mHandler);
        wm_back = (ImageView) findViewById(R.id.wm_back);
        wm_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onBackPressed();
    }
}