package com.example.smartcity_bisai_2.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity_bisai_2.Adapter.Adapter_news;
import com.example.smartcity_bisai_2.GoToNewsXQ;
import com.example.smartcity_bisai_2.Json.News;
import com.example.smartcity_bisai_2.R;

import java.util.ArrayList;
import java.util.List;

public class Fragment_News extends Fragment {
    private Context context;
    private List<News> newsList = new ArrayList<>();

    private RecyclerView recyclerView;
    private Adapter_news adapter_news;

    public Fragment_News(Context context, List<News> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__news, container, false);

        recyclerView = view.findViewById(R.id.news_recyclerView);

        adapter_news = new Adapter_news(context, newsList, new Adapter_news.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                startActivity(GoToNewsXQ.go(context,position,newsList));//跳转到新闻详情页面
                Toast.makeText(context, "点击了"+position+"号新闻", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(context,1));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter_news);
        return view;
    }
}