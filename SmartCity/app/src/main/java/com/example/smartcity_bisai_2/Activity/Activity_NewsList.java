package com.example.smartcity_bisai_2.Activity;

import android.content.Intent;
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

import com.bumptech.glide.Glide;
import com.example.smartcity_bisai_2.Adapter.Adapter_news;
import com.example.smartcity_bisai_2.GoToNewsXQ;
import com.example.smartcity_bisai_2.Json.GetData;
import com.example.smartcity_bisai_2.Json.Img;
import com.example.smartcity_bisai_2.Json.JsonParse;
import com.example.smartcity_bisai_2.Json.News;
import com.example.smartcity_bisai_2.R;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.List;

public class Activity_NewsList extends AppCompatActivity {

    private ImageView newsList_back;
    private RecyclerView recyclerView;
    private Adapter_news adapter_news;

    private Bundle bundle;
    //为了跳转详情页面
    private List<News> newsList;

    public Handler mHandler = new Handler(Looper.myLooper()) {

        @Override
        public void handleMessage(@NonNull Message msg) {
            JsonParser jsonParser = new JsonParser();
            JsonObject object = jsonParser.parse((String) msg.obj).getAsJsonObject();
            super.handleMessage(msg);
            if (msg.what == 0) {
                String rows = String.valueOf(object.get("rows"));
                newsList = JsonParse.getNews(rows);

                if (newsList.size()==0){
                    Toast.makeText(Activity_NewsList.this, "没有搜索结果", Toast.LENGTH_SHORT).show();
                }

                adapter_news = new Adapter_news(Activity_NewsList.this, newsList, new Adapter_news.OnItemClickListener() {
                    @Override
                    public void onClick(int position) {
                        startActivity(GoToNewsXQ.go(Activity_NewsList.this,position, newsList));//跳转到新闻详情页面
                        Toast.makeText(Activity_NewsList.this, "点击了"+position+"号新闻", Toast.LENGTH_SHORT).show();
                    }
                });
                recyclerView.setLayoutManager(new GridLayoutManager(Activity_NewsList.this,1));
                recyclerView.setNestedScrollingEnabled(false);
                recyclerView.setAdapter(adapter_news);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__news_list);
        initView();
    }

    private void initView() {
        newsList_back = (ImageView) findViewById(R.id.newsList_back);
        recyclerView = (RecyclerView) findViewById(R.id.newsList_recyclerView);

        bundle = getIntent().getExtras();

        newsList_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        GetData.get("/prod-api/press/press/list?title="+bundle.getString("text"),0,mHandler);
    }
}