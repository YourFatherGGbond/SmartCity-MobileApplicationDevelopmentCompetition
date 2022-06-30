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

public class Activity_XiuGaiPwd extends AppCompatActivity implements View.OnClickListener {

    private ImageView xiugai_back;
    private EditText xiugai_old_pwd;
    private EditText xiugai_new_pwd1;
    private EditText xiugai_new_pwd2;
    private Button xiugai_btn;

    public Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            JsonParser jsonParser = new JsonParser();
            JsonObject object = jsonParser.parse((String) msg.obj).getAsJsonObject();
            super.handleMessage(msg);
            if (msg.what == 0) {
                if (object.get("code").getAsString().equals("200")){
                    SaveData.setLog(Activity_XiuGaiPwd.this,false);
                    SaveData.setToken(Activity_XiuGaiPwd.this,"");

                    onBackPressed();
                    Toast.makeText(Activity_XiuGaiPwd.this, object.get("msg").getAsString(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Activity_XiuGaiPwd.this, object.get("msg").getAsString(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__xiu_gai_pwd);
        initView();
    }

    private void initView() {
        xiugai_back = (ImageView) findViewById(R.id.xiugai_back);
        xiugai_old_pwd = (EditText) findViewById(R.id.xiugai_old_pwd);
        xiugai_new_pwd1 = (EditText) findViewById(R.id.xiugai_new_pwd1);
        xiugai_new_pwd2 = (EditText) findViewById(R.id.xiugai_new_pwd2);
        xiugai_btn = (Button) findViewById(R.id.xiugai_btn);

        xiugai_btn.setOnClickListener(this);
        xiugai_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.xiugai_btn:
                submit();
                break;
            case R.id.xiugai_back:
                onBackPressed();
                break;
        }
    }

    private void submit() {
        String pwd = xiugai_old_pwd.getText().toString().trim();
        String pwd1 = xiugai_new_pwd1.getText().toString().trim();
        String pwd2 = xiugai_new_pwd2.getText().toString().trim();

        if (pwd.equals("") || pwd1.equals("") || pwd2.equals("")){
            Toast.makeText(this, "输入有空", Toast.LENGTH_SHORT).show();
        }else if (pwd1.length()<6 || pwd2.length()<6){
            Toast.makeText(this, "新密码不能少于6位", Toast.LENGTH_SHORT).show();
        }else if (! pwd1.equals(pwd2)){
            Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show();
        }else {
            Map<String,Object> map = new HashMap<>();
            map.put("oldPassword",pwd);
            map.put("newPassword",pwd2);

            GetData.put("/prod-api/api/common/user/resetPwd",map, SaveData.getToken(Activity_XiuGaiPwd.this),GetData.JSON_json,0,mHandler);
        }


    }
}