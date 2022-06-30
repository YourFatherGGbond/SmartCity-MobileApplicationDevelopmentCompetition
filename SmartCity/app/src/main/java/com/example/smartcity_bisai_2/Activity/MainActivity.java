package com.example.smartcity_bisai_2.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartcity_bisai_2.Activity.BaShi.Activity_BaShi;
import com.example.smartcity_bisai_2.Activity.Car.Activity_Car;
import com.example.smartcity_bisai_2.Adapter.Adapter_main_btn;
import com.example.smartcity_bisai_2.Fragment.Fragment_all;
import com.example.smartcity_bisai_2.Fragment.Fragment_main;
import com.example.smartcity_bisai_2.Fragment.Me.Fragment_Me;
import com.example.smartcity_bisai_2.Fragment.Me.Fragment_log;
import com.example.smartcity_bisai_2.Json.GetData;
import com.example.smartcity_bisai_2.Json.Icon;
import com.example.smartcity_bisai_2.Json.Img;
import com.example.smartcity_bisai_2.Json.JsonParse;
import com.example.smartcity_bisai_2.Json.SaveData;
import com.example.smartcity_bisai_2.R;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    //本地图片列表
//    private int[] a = {R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};

    public static RecyclerView recyclerView;
    public static Adapter_main_btn adapter_main_btn;

    private Fragment_main fragment_main;
    private Fragment_all fragment_all;
    private Fragment_log fragment_log;
    private Fragment_Me fragment_me;

    public Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            JsonParser jsonParser = new JsonParser();
            JsonObject object = jsonParser.parse((String) msg.obj).getAsJsonObject();
            super.handleMessage(msg);
            if (msg.what == 0) {
                String rows = String.valueOf(object.get("rows"));
                List<Icon> iconList = JsonParse.getIcon(rows);

                adapter_main_btn = new Adapter_main_btn(MainActivity.this, iconList, new Adapter_main_btn.OnItemClickListener() {
                    @Override
                    public void onClick(int position) {
                        switch (position){
                            case 0:
                                if (fragment_main == null){
                                    fragment_main = new Fragment_main();
                                }
                                getSupportFragmentManager().beginTransaction().replace(R.id.main_frameLayout, fragment_main).commitAllowingStateLoss();
                                break;
                            case 1:
                                if (fragment_all == null){
                                    fragment_all = new Fragment_all();
                                }
                                getSupportFragmentManager().beginTransaction().replace(R.id.main_frameLayout, fragment_all).commitAllowingStateLoss();
                                break;
                            case 2:
                                Intent intent = new Intent(MainActivity.this, Activity_Car.class);
                                startActivity(intent);
                                break;
                            case 3:
                                Intent intent3 = new Intent(MainActivity.this, Activity_BaShi.class);
                                startActivity(intent3);
                                break;
                            case 4:
                                if (SaveData.getLog(MainActivity.this)){
                                    if (fragment_me == null){
                                        fragment_me = new Fragment_Me();
                                    }
                                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frameLayout, fragment_me).commitAllowingStateLoss();
                                }else {
                                    if (fragment_log == null){
                                        fragment_log = new Fragment_log();
                                    }
                                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frameLayout, fragment_log).commitAllowingStateLoss();
                                }
                                break;
                        }
                        adapter_main_btn.setMainBtn_Color(position);
                    }
                });
                recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,5));
                recyclerView.setNestedScrollingEnabled(false);
                recyclerView.setAdapter(adapter_main_btn);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.main_recyclerView);

        fragment_main = new Fragment_main();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frameLayout, fragment_main).commitAllowingStateLoss();

        GetData.get("/prod-api/api/service/list",0,mHandler);
    }
}