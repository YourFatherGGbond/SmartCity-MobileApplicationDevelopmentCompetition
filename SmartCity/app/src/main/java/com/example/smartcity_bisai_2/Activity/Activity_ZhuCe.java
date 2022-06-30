package com.example.smartcity_bisai_2.Activity;

import android.content.Intent;
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

import com.bumptech.glide.Glide;
import com.example.smartcity_bisai_2.Json.GetData;
import com.example.smartcity_bisai_2.Json.Img;
import com.example.smartcity_bisai_2.Json.JsonParse;
import com.example.smartcity_bisai_2.R;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Activity_ZhuCe extends AppCompatActivity implements View.OnClickListener {

    private ImageView zhuce_back;
    private EditText zhuce_user;
    private EditText zhuce_pwd1;
    private EditText zhuce_pwd2;
    private EditText zhuce_sex;
    private EditText zhuce_phone;
    private Button zhuce_btn;

    public Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            JsonParser jsonParser = new JsonParser();
            JsonObject object = jsonParser.parse((String) msg.obj).getAsJsonObject();
            super.handleMessage(msg);
            if (msg.what == 0) {
                if (object.get("code").getAsString().equals("200")){
                    onBackPressed();
                    Toast.makeText(Activity_ZhuCe.this, object.get("msg").getAsString(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Activity_ZhuCe.this, object.get("msg").getAsString(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__zhu_ce);
        initView();
    }

    private void initView() {
        zhuce_back = (ImageView) findViewById(R.id.zhuce_back);
        zhuce_user = (EditText) findViewById(R.id.zhuce_user);
        zhuce_pwd1 = (EditText) findViewById(R.id.zhuce_pwd1);
        zhuce_pwd2 = (EditText) findViewById(R.id.zhuce_pwd2);
        zhuce_sex = (EditText) findViewById(R.id.zhuce_sex);
        zhuce_phone = (EditText) findViewById(R.id.zhuce_phone);
        zhuce_btn = (Button) findViewById(R.id.zhuce_btn);

        zhuce_btn.setOnClickListener(this);
        zhuce_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zhuce_btn:
                submit();
                break;
            case R.id.zhuce_back:
                onBackPressed();
                break;
        }
    }

    private void submit() {
        String user = zhuce_user.getText().toString().trim();
        String pwd1 = zhuce_pwd1.getText().toString().trim();
        String pwd2 = zhuce_pwd2.getText().toString().trim();
        String sex = zhuce_sex.getText().toString().trim();
        String phone = zhuce_phone.getText().toString().trim();

        if (user.equals("") || pwd1.equals("") || pwd2.equals("") || sex.equals("") || phone.equals("")){
            Toast.makeText(this, "输入有空", Toast.LENGTH_SHORT).show();
        }else if (pwd1.length()<6 || pwd2.length()<6){
            Toast.makeText(this, "密码不能少于6位", Toast.LENGTH_SHORT).show();
        }else if (! pwd1.equals(pwd2)){
            Toast.makeText(this, "输入的两次密码不一致", Toast.LENGTH_SHORT).show();
        }else if (! (sex.equals("男") || sex.equals("女"))){
            Toast.makeText(this, "性别输入有误", Toast.LENGTH_SHORT).show();
        }else if (phone.length() != 11){
            Toast.makeText(this, "手机号不符合规范", Toast.LENGTH_SHORT).show();
        }else {
            if (sex.equals("男")){
                sex = "0";
            }else {
                sex = "1";
            }

            Map<String,Object> map = new HashMap<>();
            map.put("userName",user);
            map.put("password",pwd2);
            map.put("sex",sex);
            map.put("phonenumber",phone);

            GetData.post("/prod-api/api/register",map,GetData.JSON_json,0,mHandler);
        }

    }
}