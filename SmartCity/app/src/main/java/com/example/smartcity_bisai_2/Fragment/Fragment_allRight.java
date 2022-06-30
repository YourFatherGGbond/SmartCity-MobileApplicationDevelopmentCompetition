package com.example.smartcity_bisai_2.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity_bisai_2.Activity.BaShi.Activity_BaShi;
import com.example.smartcity_bisai_2.Activity.Car.Activity_Car;
import com.example.smartcity_bisai_2.Activity.DianYing.Activity_dy;
import com.example.smartcity_bisai_2.Activity.ShuJu.Activitt_ShuJu;
import com.example.smartcity_bisai_2.Activity.WaiMai.Activity_wm_main;
import com.example.smartcity_bisai_2.Adapter.Adapter_allRight;
import com.example.smartcity_bisai_2.Json.Icon;
import com.example.smartcity_bisai_2.R;

import java.util.ArrayList;
import java.util.List;

public class Fragment_allRight extends Fragment {
    private Context context;
    private List<Icon> iconList = new ArrayList<>();

    private RecyclerView recyclerView;
    private Adapter_allRight adapter_allRight;

    public Fragment_allRight(Context context, List<Icon> iconList,String  key) {
        List<Icon> res=new ArrayList<>();
        this.context = context;
        if(key!=null && key.length()>0){
            for (Icon  icon:iconList) {
                if (icon.getServiceName().contains(key))
                    res.add(icon);
            }
            this.iconList = res;
        }
        else
            this.iconList=iconList;


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__right, container, false);

        recyclerView = view.findViewById(R.id.allRight_recyclerView);

        adapter_allRight = new Adapter_allRight(context, iconList, new Adapter_allRight.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                if (iconList.get(position).getServiceName().equals("停哪儿")){
                    Intent intent = new Intent(context, Activity_BaShi.class);
                    startActivity(intent);
                }else if (iconList.get(position).getServiceName().equals("智慧巴士")){
                    Intent intent3 = new Intent(context, Activity_Car.class);
                    startActivity(intent3);
                }else if (iconList.get(position).getServiceName().equals("外卖订餐")){
                    Intent intent4 = new Intent(context, Activity_wm_main.class);
                    startActivity(intent4);
                }else if (iconList.get(position).getServiceName().equals("看电影")){
                    Intent intent5 = new Intent(context, Activity_dy.class);
                    startActivity(intent5);
                }
                else if (iconList.get(position).getServiceName().equals("数据分析")){
                    Intent intent6 = new Intent(context, Activitt_ShuJu.class);
                    startActivity(intent6);
                }

                Toast.makeText(context, "点击了"+position+"号服务", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(context,5));
        recyclerView.setAdapter(adapter_allRight);
        return view;
    }
}