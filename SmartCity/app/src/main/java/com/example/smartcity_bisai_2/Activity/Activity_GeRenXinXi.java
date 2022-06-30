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
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.Map;

public class Activity_GeRenXinXi extends AppCompatActivity implements View.OnClickListener {

    private ImageView geren_back;
    private EditText geren_name;
    private EditText geren_sex;
    private EditText geren_phone;
    private Button geren_btn;

    public Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            JsonParser jsonParser = new JsonParser();
            JsonObject object = jsonParser.parse((String) msg.obj).getAsJsonObject();
            super.handleMessage(msg);
            if (msg.what == 0) {
                if (object.get("code").getAsString().equals("200")){

                    GetData.get("/prod-api/api/common/user/getInfo",SaveData.getToken(Activity_GeRenXinXi.this),1,mHandler);

                    Toast.makeText(Activity_GeRenXinXi.this, object.get("msg").getAsString(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Activity_GeRenXinXi.this, object.get("msg").getAsString(), Toast.LENGTH_SHORT).show();
                }
            }

            if (msg.what==1){
                JsonObject object1 = jsonParser.parse(String.valueOf(object.get("user"))).getAsJsonObject();

                if (object1.get("nickName") == JsonNull.INSTANCE){
                    geren_name.setText("昵称未设置");
                }else {
                    geren_name.setText(object1.get("nickName").getAsString());
                }

                if (object1.get("sex").getAsString().equals("1")){
                    geren_sex.setText("男");
                }else {
                    geren_sex.setText("女");
                }
                geren_phone.setText(object1.get("phonenumber").getAsString());

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__ge_ren_xin_xi);
        initView();
    }

    private void initView() {
        geren_back = (ImageView) findViewById(R.id.geren_back);
        geren_name = (EditText) findViewById(R.id.geren_name);
        geren_sex = (EditText) findViewById(R.id.geren_sex);
        geren_phone = (EditText) findViewById(R.id.geren_phone);
        geren_btn = (Button) findViewById(R.id.geren_btn);

        GetData.get("/prod-api/api/common/user/getInfo",SaveData.getToken(Activity_GeRenXinXi.this),1,mHandler);

        geren_btn.setOnClickListener(this);
        geren_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.geren_btn:
                submit();
                break;
            case R.id.geren_back:
                onBackPressed();
                break;
        }
    }

    private void submit() {
        String name = geren_name.getText().toString().trim();
        String sex = geren_sex.getText().toString().trim();
        String phone = geren_phone.getText().toString().trim();

        if (name.equals("") || sex.equals("") || phone.equals("")){
            Toast.makeText(this, "输入有空", Toast.LENGTH_SHORT).show();
        }else if (! (sex.equals("男") || sex.equals("女"))){
            Toast.makeText(this, "性别输入有误", Toast.LENGTH_SHORT).show();
        }else if (phone.length()!=11){
            Toast.makeText(this, "手机号码不合规范", Toast.LENGTH_SHORT).show();
        }else {
            if (sex.equals("男")){
                sex = "1";
            }else {
                sex = "0";
            }

            Map<String,Object> map = new HashMap<>();
            map.put("nickName",name);
            map.put("sex",sex);
            map.put("phonenumber",phone);

            GetData.put("/prod-api/api/common/user",map, SaveData.getToken(Activity_GeRenXinXi.this),GetData.JSON_json,0,mHandler);
        }


    }
}