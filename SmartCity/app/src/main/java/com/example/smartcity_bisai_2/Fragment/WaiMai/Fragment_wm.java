package com.example.smartcity_bisai_2.Fragment.WaiMai;

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

import com.example.smartcity_bisai_2.Activity.WaiMai.Activity_wm_main;
import com.example.smartcity_bisai_2.Adapter.Adapter_dian;
import com.example.smartcity_bisai_2.Adapter.Adapter_wm_icon;
import com.example.smartcity_bisai_2.Adapter.Adapter_wmbtn_icon;
import com.example.smartcity_bisai_2.Fragment.Fragment_main;
import com.example.smartcity_bisai_2.GoToDianXq;
import com.example.smartcity_bisai_2.Json.DianJia;
import com.example.smartcity_bisai_2.Json.GetData;
import com.example.smartcity_bisai_2.Json.Img;
import com.example.smartcity_bisai_2.Json.JsonParse;
import com.example.smartcity_bisai_2.Json.Wm_Img;
import com.example.smartcity_bisai_2.Json.Wm_icon;
import com.example.smartcity_bisai_2.R;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

public class Fragment_wm extends Fragment implements View.OnClickListener, TextView.OnEditorActionListener {

    private EditText wm_edit;
    private Button wm_edit_btn;
    private Banner wm_banner;
    private RecyclerView recyclerView_wm_icon;
    private Adapter_wm_icon adapter_wm_icon;
    private RecyclerView recyclerView_wm;
    private Adapter_dian adapter_dian;

    public Handler mHandler = new Handler(Looper.myLooper()) {

        //用于轮播图跳转
        private List<DianJia> dianJiaList;

        @Override
        public void handleMessage(@NonNull Message msg) {
            JsonParser jsonParser = new JsonParser();
            JsonObject object = jsonParser.parse((String) msg.obj).getAsJsonObject();
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    String rows = String.valueOf(object.get("rows"));
                    List<Wm_Img> imgList = JsonParse.getwm_img(rows);
                    List<String> pathList = new ArrayList<>();

                    for (int i = 0; i < imgList.size(); i++) {
                        pathList.add(GetData.IP + imgList.get(i).getAdvImg());
                    }

                    wm_banner.setImageLoader(new Fragment_main.Adapter_banner())
                            .setImages(pathList)
                            .setOnBannerListener(new OnBannerListener() {
                                @Override
                                public void OnBannerClick(int i) {
                                    startActivity(GoToDianXq.goDian(getActivity(),i, dianJiaList));
                                    Toast.makeText(getActivity(), "点击了" + i + "号轮播图", Toast.LENGTH_SHORT).show();
                                }
                            }).start();
                    break;
                case 1:
                    String data = String.valueOf(object.get("data"));
                    List<Wm_icon> list = JsonParse.getwm(data);

                    adapter_wm_icon = new Adapter_wm_icon(getActivity(), list, new Adapter_wm_icon.OnItemClickListener() {
                        @Override
                        public void onClick(int position) {
                            Toast.makeText(getActivity(), "点击了" + position + "号图标", Toast.LENGTH_SHORT).show();
                        }
                    });
                    recyclerView_wm_icon.setLayoutManager(new GridLayoutManager(getActivity(), 5));
                    recyclerView_wm_icon.setNestedScrollingEnabled(false);
                    recyclerView_wm_icon.setAdapter(adapter_wm_icon);
                    break;
                case 2:
                    String rows2 = String.valueOf(object.get("rows"));
                    dianJiaList = JsonParse.getDianJia(rows2);

                    adapter_dian = new Adapter_dian(getActivity(), dianJiaList, new Adapter_dian.OnItemClickListener() {
                        @Override
                        public void onClick(int position) {
                            startActivity(GoToDianXq.goDian(getActivity(),position, dianJiaList));
                            Toast.makeText(getActivity(), "点击了" + position + "号店家", Toast.LENGTH_SHORT).show();
                        }
                    });
                    recyclerView_wm.setLayoutManager(new GridLayoutManager(getActivity(),1));
                    recyclerView_wm.setNestedScrollingEnabled(false);
                    recyclerView_wm.setAdapter(adapter_dian);
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wm, container, false);

        wm_edit = view.findViewById(R.id.wm_edit);
        wm_edit_btn = view.findViewById(R.id.wm_edit_btn);
        wm_banner = view.findViewById(R.id.wm_banner);
        recyclerView_wm_icon = view.findViewById(R.id.recyclerView_wm_icon);
        recyclerView_wm = view.findViewById(R.id.recyclerView_wm);

        init();
        return view;
    }

    private void init(){
        wm_edit.setOnEditorActionListener(this);
        wm_edit_btn.setOnClickListener(this);

        GetData.get("/prod-api/api/takeout/rotation/list",0,mHandler);
        GetData.get("/prod-api/api/takeout/theme/list", 1, mHandler);
        GetData.get("/prod-api/api/takeout/seller/list", 2, mHandler);
    }

    @Override
    public void onClick(View view) {
        String text = wm_edit.getText().toString();
        if (text.equals("")){
            Toast.makeText(getActivity(), "请输入搜索内容...", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getActivity(), "正在搜索\""+text+"\"中...", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        String text = wm_edit.getText().toString();
        if (text.equals("")){
            Toast.makeText(getActivity(), "请输入搜索内容...", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getActivity(), "正在搜索\""+text+"\"中...", Toast.LENGTH_SHORT).show();
        }
        return true;
    }




}