package com.example.smartcity_bisai_2.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity_bisai_2.Activity.Activity_NewsList;
import com.example.smartcity_bisai_2.Adapter.Adapter_allBtn;
import com.example.smartcity_bisai_2.Json.GetData;
import com.example.smartcity_bisai_2.Json.Icon;
import com.example.smartcity_bisai_2.Json.JsonParse;
import com.example.smartcity_bisai_2.R;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class Fragment_all extends Fragment implements View.OnClickListener, TextView.OnEditorActionListener {
    private List<Icon> iconList = new ArrayList<>();
    private List<Icon> list1 = new ArrayList<>();
    private List<Icon> list2 = new ArrayList<>();
    private List<Icon> list3 = new ArrayList<>();

    private RecyclerView allBtn_recyclerView;
    private Adapter_allBtn adapter_allBtn;
    private Fragment_allRight fragment_allRight;

    public Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            JsonParser jsonParser = new JsonParser();
            JsonObject object = jsonParser.parse((String) msg.obj).getAsJsonObject();
            super.handleMessage(msg);
            if (msg.what == 0) {
                String rows = String.valueOf(object.get("rows"));
                iconList = JsonParse.getIcon(rows);

                list1 = new ArrayList<>();
                list2 = new ArrayList<>();
                list3 = new ArrayList<>();

                for (int i = 0; i < iconList.size(); i++) {
                    switch (iconList.get(i).getServiceType()) {
                        case "车主服务":
                            list1.add(iconList.get(i));
                            break;
                        case "生活服务":
                            list2.add(iconList.get(i));
                            break;
                        case "便民服务":
                            list3.add(iconList.get(i));
                            break;
                    }
                }

                fragment_allRight = new Fragment_allRight(getActivity(), list1, "");
                getFragmentManager().beginTransaction().replace(R.id.allRight_frameLayout, fragment_allRight).commitAllowingStateLoss();

                adapter_allBtn = new Adapter_allBtn(getActivity(), new Adapter_allBtn.OnItemClickListener() {
                    @Override
                    public void onClick(int position) {
                        switch (position) {
                            case 0:
                                fragment_allRight = new Fragment_allRight(getActivity(), list1, "");
                                getFragmentManager().beginTransaction().replace(R.id.allRight_frameLayout, fragment_allRight).commitAllowingStateLoss();
                                break;
                            case 1:
                                fragment_allRight = new Fragment_allRight(getActivity(), list2, "");
                                getFragmentManager().beginTransaction().replace(R.id.allRight_frameLayout, fragment_allRight).commitAllowingStateLoss();
                                break;
                            case 2:
                                fragment_allRight = new Fragment_allRight(getActivity(), list3, "");
                                getFragmentManager().beginTransaction().replace(R.id.allRight_frameLayout, fragment_allRight).commitAllowingStateLoss();
                                break;
                            case 3:
                                fragment_allRight = new Fragment_allRight(getActivity(), iconList, "");
                                getFragmentManager().beginTransaction().replace(R.id.allRight_frameLayout, fragment_allRight).commitAllowingStateLoss();
                                break;
                        }
                        adapter_allBtn.setAllBtn_Color(position);
                    }
                });
                allBtn_recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
                allBtn_recyclerView.setAdapter(adapter_allBtn);
            }
        }
    };
    private EditText all_edit;
    private Button all_edit_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all, container, false);

        allBtn_recyclerView = view.findViewById(R.id.allBtn_recyclerView);
        all_edit = view.findViewById(R.id.all_edit);
        all_edit_btn = view.findViewById(R.id.all_edit_btn);

        GetData.get("/prod-api/api/service/list", 0, mHandler);

        all_edit.setOnEditorActionListener(this);
        all_edit_btn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        iconList = new ArrayList<>();
        GetData.get("/prod-api/api/service/list", 0, mHandler);
    }

    @Override
    public void onClick(View view) {
        String text = all_edit.getText().toString();
        if (text.equals("")){
            Toast.makeText(getActivity(), "请输入搜索内容...", Toast.LENGTH_SHORT).show();
        }else {
            adapter_allBtn.setAllBtn_Color(3);
            adapter_allBtn.notifyDataSetChanged();
            fragment_allRight = new Fragment_allRight(getActivity(), iconList, text);
            getFragmentManager().beginTransaction().replace(R.id.allRight_frameLayout, fragment_allRight).commitAllowingStateLoss();
            Toast.makeText(getActivity(), "正在搜索\""+text+"\"中...", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        String text = all_edit.getText().toString();
        if (text.equals("")){
            Toast.makeText(getActivity(), "请输入搜索内容...", Toast.LENGTH_SHORT).show();
        }else {
            adapter_allBtn.setAllBtn_Color(3);
            adapter_allBtn.notifyDataSetChanged();
            fragment_allRight = new Fragment_allRight(getActivity(), iconList, text);
            getFragmentManager().beginTransaction().replace(R.id.allRight_frameLayout, fragment_allRight).commitAllowingStateLoss();
            Toast.makeText(getActivity(), "正在搜索\""+text+"\"中...", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}