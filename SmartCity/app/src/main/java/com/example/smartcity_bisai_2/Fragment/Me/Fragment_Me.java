package com.example.smartcity_bisai_2.Fragment.Me;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity_bisai_2.Activity.Activity_DingDanList;
import com.example.smartcity_bisai_2.Activity.Activity_GeRenXinXi;
import com.example.smartcity_bisai_2.Activity.Activity_XiuGaiPwd;
import com.example.smartcity_bisai_2.Activity.Activity_YiJianFanKui;
import com.example.smartcity_bisai_2.Activity.MainActivity;
import com.example.smartcity_bisai_2.Adapter.Adapter_me;
import com.example.smartcity_bisai_2.Json.SaveData;
import com.example.smartcity_bisai_2.R;

public class Fragment_Me extends Fragment {

    private RecyclerView me_recyclerView;
    private Adapter_me adapter_me;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__me, container, false);

        me_recyclerView = view.findViewById(R.id.me_recyclerView);

        adapter_me = new Adapter_me(getActivity(), new Adapter_me.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                switch (position){
                    case 0:
                        Intent intent = new Intent(getActivity(), Activity_GeRenXinXi.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(getActivity(), Activity_DingDanList.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(getActivity(), Activity_XiuGaiPwd.class);
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(getActivity(), Activity_YiJianFanKui.class);
                        startActivity(intent3);
                        break;
                    case 4:
                        SaveData.setToken(getActivity(),"");
                        SaveData.setLog(getActivity(), false);

                        Intent intent4 = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent4);
                        getActivity().finish();
                        Toast.makeText(getActivity(), "已退出登录", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        me_recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        me_recyclerView.setNestedScrollingEnabled(false);
        me_recyclerView.setAdapter(adapter_me);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (! SaveData.getLog(getActivity())){
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
    }
}