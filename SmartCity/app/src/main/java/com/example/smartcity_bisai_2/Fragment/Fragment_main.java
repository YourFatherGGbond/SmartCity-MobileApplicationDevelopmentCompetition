package com.example.smartcity_bisai_2.Fragment;

import android.content.Context;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.smartcity_bisai_2.Activity.Activity_NewsList;
import com.example.smartcity_bisai_2.Activity.BaShi.Activity_BaShi;
import com.example.smartcity_bisai_2.Activity.Car.Activity_Car;
import com.example.smartcity_bisai_2.Activity.DianYing.Activity_dy;
import com.example.smartcity_bisai_2.Activity.MainActivity;
import com.example.smartcity_bisai_2.Activity.ShuJu.Activitt_ShuJu;
import com.example.smartcity_bisai_2.Activity.WaiMai.Activity_wm_main;
import com.example.smartcity_bisai_2.Adapter.Adapter_hot;
import com.example.smartcity_bisai_2.Adapter.Adapter_icon;
import com.example.smartcity_bisai_2.GoToNewsXQ;
import com.example.smartcity_bisai_2.Json.GetData;
import com.example.smartcity_bisai_2.Json.Icon;
import com.example.smartcity_bisai_2.Json.Img;
import com.example.smartcity_bisai_2.Json.JsonParse;
import com.example.smartcity_bisai_2.Json.News;
import com.example.smartcity_bisai_2.Json.NewsType;
import com.example.smartcity_bisai_2.R;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fragment_main extends Fragment implements TextView.OnEditorActionListener, View.OnClickListener {
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private List<NewsType> typeList = new ArrayList<>();


    private EditText main_edit;
    private Button main_edit_btn;
    private Banner main_banner;
    private RecyclerView recyclerView_icon;
    private Adapter_icon adapter_icon;
    private RecyclerView recyclerView_hot;
    private Adapter_hot adapter_hot;
    private TabLayout main_tabLayout;
    private ViewPager main_viewPager;

    private Fragment_all fragment_all;

    public Handler mHandler = new Handler(Looper.myLooper()) {

        //为了跳转页面
        private List<News> newsList;

        @Override
        public void handleMessage(@NonNull Message msg) {
            JsonParser jsonParser = new JsonParser();
            JsonObject object = jsonParser.parse((String) msg.obj).getAsJsonObject();
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    String rows = String.valueOf(object.get("rows"));
                    List<Img> imgList = JsonParse.getImg(rows);
                    List<String> pathList = new ArrayList<>();

                    for (int i = 0; i < imgList.size(); i++) {
                        pathList.add(GetData.IP+imgList.get(i).getAdvImg());
                    }

                    main_banner.setImageLoader(new Adapter_banner())
                            .setImages(pathList)
                            .setOnBannerListener(new OnBannerListener() {
                                @Override
                                public void OnBannerClick(int i) {
                                    startActivity(GoToNewsXQ.go(getActivity(),i,newsList));//跳转到新闻详情页面
                                    Toast.makeText(getActivity(), "点击了"+i+"号轮播图", Toast.LENGTH_SHORT).show();
                                }
                            }).start();
                    break;
                case 1:
                    String rows1 = String.valueOf(object.get("rows"));
                    List<Icon> iconList = JsonParse.getIcon(rows1);

                    adapter_icon = new Adapter_icon(getActivity(), iconList, new Adapter_icon.OnItemClickListener() {
                        @Override
                        public void onClick(int position) {
                            switch (position){
                                case 0:
                                    Intent intent = new Intent(getActivity(), Activity_Car.class);
                                    startActivity(intent);
                                    break;
                                case 2:
                                    Intent intent2 = new Intent(getActivity(), Activity_BaShi.class);
                                    startActivity(intent2);
                                    break;
                                case 6:
                                    Intent intent6 = new Intent(getActivity(), Activity_wm_main.class);
                                    startActivity(intent6);
                                    break;
                                case 8:
                                    Intent intent8 = new Intent(getActivity(), Activity_dy.class);
                                    startActivity(intent8);
                                    break;
                                case 9:
                                    fragment_all = new Fragment_all();
                                    getFragmentManager().beginTransaction().replace(R.id.main_frameLayout, fragment_all).commitAllowingStateLoss();
                                    MainActivity.adapter_main_btn.setMainBtn_Color(1);
                                    MainActivity.adapter_main_btn.notifyDataSetChanged();
                                    break;
                            }
                            Toast.makeText(getActivity(), "点击了"+position+"号图标", Toast.LENGTH_SHORT).show();
                        }
                    });
                    recyclerView_icon.setLayoutManager(new GridLayoutManager(getActivity(),5));
                    recyclerView_icon.setNestedScrollingEnabled(false);
                    recyclerView_icon.setAdapter(adapter_icon);
                    break;
                case 2:
                    String rows2 = String.valueOf(object.get("rows"));
                    final List<News> hotList = JsonParse.getNews(rows2);

                    adapter_hot = new Adapter_hot(getActivity(), hotList, new Adapter_hot.OnItemClickListener() {
                        @Override
                        public void onClick(int position) {
                            startActivity(GoToNewsXQ.go(getActivity(),position,hotList));//跳转到新闻详情页面
                            Toast.makeText(getActivity(), "点击了"+position+"号热门", Toast.LENGTH_SHORT).show();
                        }
                    });
                    recyclerView_hot.setLayoutManager(new GridLayoutManager(getActivity(),2));
                    recyclerView_hot.setNestedScrollingEnabled(false);
                    recyclerView_hot.setAdapter(adapter_hot);
                    break;
                case 3:
                    String data = String.valueOf(object.get("data"));
                    typeList = JsonParse.getNewsType(data);

                    titles = new ArrayList<>();
                    for (int i = 0; i < typeList.size(); i++) {
                        titles.add(typeList.get(i).getName());
                    }
                    break;
                case 4:
                    String rows4 = String.valueOf(object.get("rows"));
                    newsList = JsonParse.getNews(rows4);

                    fragmentList = new ArrayList<>();
                    Map<Integer,ArrayList<News>> map = new HashMap<>();

                    for (int i = 0; i < newsList.size(); i++) {
                        int type = Integer.parseInt(newsList.get(i).getType());
                        if (! map.containsKey(type)){
                            map.put(type,new ArrayList<News>());
                        }
                        map.get(type).add(newsList.get(i));
                    }

                    for (int i = 0; i < titles.size(); i++) {
                        fragmentList.add(new Fragment_News(getActivity(), map.get(typeList.get(i).getId())));
                    }

                    main_viewPager.setOffscreenPageLimit(fragmentList.size());
                    main_viewPager.setAdapter(new Adapter_main_news(getChildFragmentManager()));
                    main_tabLayout.setupWithViewPager(main_viewPager);

                    //获取轮播图
                    GetData.get("/prod-api/api/rotation/list?type=2", 0, mHandler);
                    //获取图标
                    GetData.get("/prod-api/api/service/list",1,mHandler);
                    //获取热门
                    GetData.get("/prod-api/press/press/list?hot=Y",2,mHandler);

                    MainActivity.recyclerView.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        main_edit = view.findViewById(R.id.main_edit);
        main_edit_btn = view.findViewById(R.id.main_edit_btn);
        main_banner = view.findViewById(R.id.main_banner);
        recyclerView_icon = view.findViewById(R.id.recyclerView_icon);
        recyclerView_hot = view.findViewById(R.id.recyclerView_hot);
        main_tabLayout = view.findViewById(R.id.main_tabLayout);
        main_viewPager = view.findViewById(R.id.main_viewPager);

        MainActivity.recyclerView.setVisibility(View.GONE);

        init();
        return view;
    }

    private void init(){
        main_edit.setOnEditorActionListener(this);
        main_edit_btn.setOnClickListener(this);

        //获取新闻类别
        GetData.get("/prod-api/press/category/list",3,mHandler);
        //获取新闻
        GetData.get("/prod-api/press/press/list",4,mHandler);
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        String text = main_edit.getText().toString();
        if (text.equals("")){
            Toast.makeText(getActivity(), "请输入搜索内容...", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(getActivity(), Activity_NewsList.class);
            Bundle bundle = new Bundle();
            bundle.putString("text",text);
            intent.putExtras(bundle);
            startActivity(intent);
            Toast.makeText(getActivity(), "正在搜索\""+text+"\"中...", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
    @Override
    public void onClick(View view) {
        String text = main_edit.getText().toString();
        if (text.equals("")){
            Toast.makeText(getActivity(), "请输入搜索内容...", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(getActivity(), Activity_NewsList.class);
            Bundle bundle = new Bundle();
            bundle.putString("text",text);
            intent.putExtras(bundle);
            startActivity(intent);
            Toast.makeText(getActivity(), "正在搜索\""+text+"\"中...", Toast.LENGTH_SHORT).show();
        }
    }

    public static class Adapter_banner extends ImageLoader{
        @Override
        public void displayImage(Context context, Object o, ImageView imageView) {
            Glide.with(context).load(o.toString()).into(imageView);
        }
    }

    class Adapter_main_news extends FragmentPagerAdapter{

        public Adapter_main_news(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        main_edit.setText("");
    }
}