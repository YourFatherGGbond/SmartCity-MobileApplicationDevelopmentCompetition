package com.example.smartcity_bisai_2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.smartcity_bisai_2.Activity.Activity_newsXq;
import com.example.smartcity_bisai_2.Json.GetData;
import com.example.smartcity_bisai_2.Json.News;

import java.util.List;

public class GoToNewsXQ {
    public static Intent go(Context context,int position,List<News> newsList){
        Intent intent = new Intent(context, Activity_newsXq.class);
        Bundle bundle = new Bundle();
        bundle.putString("title_new", newsList.get(position).getTitle());
        bundle.putString("img_new", GetData.IP+newsList.get(position).getCover());
        bundle.putString("text", newsList.get(position).getContent());
        bundle.putString("pinglun", newsList.get(position).getCommentNum());
        bundle.putString("time", newsList.get(position).getPublishDate());
        intent.putExtras(bundle);
        return intent;
    }

}
