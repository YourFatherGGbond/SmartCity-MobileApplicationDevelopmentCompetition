package com.example.smartcity_bisai_2.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity_bisai_2.Json.GetData;
import com.example.smartcity_bisai_2.Json.SaveData;
import com.example.smartcity_bisai_2.R;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.Map;

public class Activity_YiJianFanKui extends AppCompatActivity implements View.OnClickListener {

    private ImageView yijian_back;
    private EditText yijian_title;
    private EditText yijian_text;
    private Button yijian_btn;

    public Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            JsonParser jsonParser = new JsonParser();
            JsonObject object = jsonParser.parse((String) msg.obj).getAsJsonObject();
            super.handleMessage(msg);
            if (msg.what == 0) {
                if (object.get("code").getAsString().equals("200")){
                    onBackPressed();
                    Toast.makeText(Activity_YiJianFanKui.this, object.get("msg").getAsString(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Activity_YiJianFanKui.this, object.get("msg").getAsString(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__yi_jian_fan_kui);
        initView();
    }

    private void initView() {
        yijian_back = (ImageView) findViewById(R.id.yijian_back);
        yijian_title = (EditText) findViewById(R.id.yijian_title);
        yijian_text = (EditText) findViewById(R.id.yijian_text);
        yijian_btn = (Button) findViewById(R.id.yijian_btn);

        yijian_btn.setOnClickListener(this);
        yijian_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.yijian_btn:
                submit();
                break;
            case R.id.yijian_back:
                onBackPressed();
                break;
        }
    }

    private void submit() {
        String title = yijian_title.getText().toString().trim();
        String text = yijian_text.getText().toString().trim();

        if (title.equals("") || text.equals("")){
            Toast.makeText(this, "不能提交空内容", Toast.LENGTH_SHORT).show();
        }else {
            Map<String, Object> map = new HashMap<>();
            map.put("title",title);
            map.put("content",text);

            GetData.post("/prod-api/api/common/feedback",map, SaveData.getToken(Activity_YiJianFanKui.this),GetData.JSON_json,0,mHandler);
        }

    }
}