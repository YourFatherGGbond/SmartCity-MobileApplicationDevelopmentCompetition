package com.example.smartcity_bisai_2.Json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class JsonParse {
    public static List<Img> getImg(String json){
        Gson gson = new Gson();
        Type type = new TypeToken<List<Img>>(){}.getType();

        List<Img> list = gson.fromJson(json,type);
        return list;
    }

    public static List<Icon> getIcon(String json){
        Gson gson = new Gson();
        Type type = new TypeToken<List<Icon>>(){}.getType();

        List<Icon> list = gson.fromJson(json,type);
        return list;
    }

    public static List<News> getNews(String json){
        Gson gson = new Gson();
        Type type = new TypeToken<List<News>>(){}.getType();

        List<News> list = gson.fromJson(json,type);
        return list;
    }

    public static List<NewsType> getNewsType(String json){
        Gson gson = new Gson();
        Type type = new TypeToken<List<NewsType>>(){}.getType();

        List<NewsType> list = gson.fromJson(json,type);
        return list;
    }

    public static List<BaShi> getBaShi(String json){
        Gson gson = new Gson();
        Type type = new TypeToken<List<BaShi>>(){}.getType();

        List<BaShi> list = gson.fromJson(json,type);
        return list;
    }

    public static List<Car> getCar(String json){
        Gson gson = new Gson();
        Type type = new TypeToken<List<Car>>(){}.getType();

        List<Car> list = gson.fromJson(json,type);
        return list;
    }

    public static List<Wm_icon> getwm(String json){
        Gson gson = new Gson();
        Type type = new TypeToken<List<Wm_icon>>(){}.getType();

        List<Wm_icon> list = gson.fromJson(json,type);
        return list;
    }

    public static List<Wm_Img> getwm_img(String json){
        Gson gson = new Gson();
        Type type = new TypeToken<List<Wm_Img>>(){}.getType();

        List<Wm_Img> list = gson.fromJson(json,type);
        return list;
    }

    public static List<DianJia> getDianJia(String json){
        Gson gson = new Gson();
        Type type = new TypeToken<List<DianJia>>(){}.getType();

        List<DianJia> list = gson.fromJson(json,type);
        return list;
    }

    public static List<Dy_GG> getDy_GG(String json){
        Gson gson = new Gson();
        Type type = new TypeToken<List<Dy_GG>>(){}.getType();

        List<Dy_GG> list = gson.fromJson(json,type);
        return list;
    }
}
