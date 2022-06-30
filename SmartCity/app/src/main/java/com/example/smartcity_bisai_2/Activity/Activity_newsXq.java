package com.example.smartcity_bisai_2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.smartcity_bisai_2.R;


public class Activity_newsXq extends AppCompatActivity {
    private ImageView back,imageView;
    private TextView title,text,pinglun,time;

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_xq);

        bundle = getIntent().getExtras();

        init();
    }

    private void init(){
        back = findViewById(R.id.newsXq_back);
        imageView = findViewById(R.id.newsXq_img);
        title = findViewById(R.id.newsXq_title);
        text = findViewById(R.id.newsXq_text);
        pinglun = findViewById(R.id.newsXq_pinglun);
        time = findViewById(R.id.newsXq_time);

        setBtn();
        setData();
    }

    private void setData(){
        title.setText(bundle.getString("title"));
        Glide.with(Activity_newsXq.this).load(bundle.getString("img_new")).into(imageView);
        text.setText(Html.fromHtml(bundle.getString("text")));
        pinglun.setText("评论："+bundle.getString("pinglun"));
        time.setText(bundle.getString("time"));
    }

    private void setBtn(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}