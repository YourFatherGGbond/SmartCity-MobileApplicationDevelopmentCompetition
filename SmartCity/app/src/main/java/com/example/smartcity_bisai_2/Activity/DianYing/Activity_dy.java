package com.example.smartcity_bisai_2.Activity.DianYing;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity_bisai_2.Fragment.Fragment_main;
import com.example.smartcity_bisai_2.Json.Dy_GG;
import com.example.smartcity_bisai_2.Json.GetData;
import com.example.smartcity_bisai_2.Json.Img;
import com.example.smartcity_bisai_2.Json.JsonParse;
import com.example.smartcity_bisai_2.R;
import com.github.mikephil.charting.charts.LineChart;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

public class Activity_dy extends AppCompatActivity implements TextView.OnEditorActionListener, View.OnClickListener {

    private ImageView dy_back;
    private EditText dy_edit;
    private Button dy_edit_btn;
    private Banner dy_banner;
    private RecyclerView recyclerView_icon;
    private RecyclerView recyclerView_yingyuan;

    public Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            JsonParser jsonParser = new JsonParser();
            JsonObject object = jsonParser.parse((String) msg.obj).getAsJsonObject();
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    String rows = String.valueOf(object.get("rows"));
                    List<Dy_GG> imgList = JsonParse.getDy_GG(rows);
                    List<String> pathList = new ArrayList<>();

                    for (int i = 0; i < imgList.size(); i++) {
                        pathList.add(GetData.IP + imgList.get(i).getAdvImg());
                    }

                    dy_banner.setImageLoader(new Fragment_main.Adapter_banner())
                            .setImages(pathList)
                            .setOnBannerListener(new OnBannerListener() {
                                @Override
                                public void OnBannerClick(int i) {
                                    Toast.makeText(Activity_dy.this, "点击了" + i + "号轮播图", Toast.LENGTH_SHORT).show();
                                }
                            }).start();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dy);
        initView();
    }

    private void initView() {
        dy_back = (ImageView) findViewById(R.id.dy_back);
        dy_edit = (EditText) findViewById(R.id.dy_edit);
        dy_edit_btn = (Button) findViewById(R.id.dy_edit_btn);
        dy_banner = (Banner) findViewById(R.id.dy_banner);
        recyclerView_icon = (RecyclerView) findViewById(R.id.recyclerView_icon);
        recyclerView_yingyuan = (RecyclerView) findViewById(R.id.recyclerView_yingyuan);

        dy_edit.setOnEditorActionListener(this);
        dy_edit_btn.setOnClickListener(this);

        //设置返回按钮
        dy_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        GetData.get("/prod-api/api/movie/rotation/list",0,mHandler);
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        String text = dy_edit.getText().toString();
        if (text.equals("")) {
            Toast.makeText(Activity_dy.this, "请输入搜索内容...", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(Activity_dy.this, "正在搜索\"" + text + "\"中...", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        String text = dy_edit.getText().toString();
        if (text.equals("")) {
            Toast.makeText(Activity_dy.this, "请输入搜索内容...", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(Activity_dy.this, "正在搜索\"" + text + "\"中...", Toast.LENGTH_SHORT).show();
        }
    }



}