package com.example.smartcity_bisai_2.Fragment.Me;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.smartcity_bisai_2.Activity.Activity_ZhuCe;
import com.example.smartcity_bisai_2.Json.GetData;
import com.example.smartcity_bisai_2.Json.SaveData;
import com.example.smartcity_bisai_2.R;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.Map;

public class Fragment_log extends Fragment {

    private EditText log_user;
    private EditText log_pwd;
    private Button log_zhuce;
    private Button log_log;

    private Fragment_Me fragment_me;

    public Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            JsonParser jsonParser = new JsonParser();
            JsonObject object = jsonParser.parse((String) msg.obj).getAsJsonObject();
            super.handleMessage(msg);
            if (msg.what == 0) {
                if (object.get("code").getAsString().equals("200")){
                    SaveData.setLog(getActivity(),true);
                    SaveData.setToken(getActivity(),object.get("token").getAsString());

                    fragment_me = new Fragment_Me();
                    getFragmentManager().beginTransaction().replace(R.id.main_frameLayout, fragment_me).commitAllowingStateLoss();
                    Toast.makeText(getActivity(), object.get("msg").getAsString(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(), object.get("msg").getAsString(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_log, container, false);

        log_user = view.findViewById(R.id.log_user);
        log_pwd = view.findViewById(R.id.log_pwd);
        log_zhuce = view.findViewById(R.id.log_zhuce);
        log_log = view.findViewById(R.id.log_log);

        init();
        return view;
    }

    private void init(){
        log_zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Activity_ZhuCe.class);
                startActivity(intent);
            }
        });

        log_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = log_user.getText().toString();
                String pwd = log_pwd.getText().toString();

                if (user.equals("") || pwd.equals("")){
                    Toast.makeText(getActivity(), "输入有空", Toast.LENGTH_SHORT).show();
                }else {
                    Map<String, Object> map = new HashMap<>();
                    map.put("username",user);
                    map.put("password",pwd);

                    GetData.post("/prod-api/api/login",map,GetData.JSON_json,0,mHandler);
                }
            }
        });
    }
}