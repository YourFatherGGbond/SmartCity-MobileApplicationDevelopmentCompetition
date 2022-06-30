package com.example.smartcity_bisai_2.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity_bisai_2.R;

public class Activity_DingDanList extends AppCompatActivity {

    private ImageView dingdan_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__ding_dan_list);
        initView();

    }

    private void initView() {
        dingdan_back = (ImageView) findViewById(R.id.dingdan_back);

        dingdan_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}