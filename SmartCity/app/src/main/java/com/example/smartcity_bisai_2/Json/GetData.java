package com.example.smartcity_bisai_2.Json;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GetData {
    public static final MediaType JSON_json = MediaType.parse("application/json; charset=utf-8");
    public static final String IP = "http://124.93.196.45:10001";
    private static final OkHttpClient client = new OkHttpClient();


    public static void get(String url, final int position, final Handler mHandler){
        Request request = new Request.Builder()
                .url(IP+url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println("网络连接错误");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String data = response.body().string();

                Message message = new Message();
                message.what=position;
                message.obj=data;
                mHandler.sendMessage(message);
            }
        });
    }

    public static void get(String url,String Token,final int position, final Handler mHandler){
        Request request = new Request.Builder()
                .url(IP+url)
                .addHeader("Authorization",Token)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println("网络连接错误");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String data = response.body().string();

                Message message = new Message();
                message.what=position;
                message.obj=data;
                mHandler.sendMessage(message);
            }
        });
    }


    public static void post(String url, Map<String,Object> map,MediaType JSON,final int position, final Handler mHandler){
        Gson gson = new Gson();
        String json = gson.toJson(map);
        RequestBody body = RequestBody.create(json,JSON);

        Request request = new Request.Builder()
                .url(IP+url)
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println("网络连接错误");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String data = response.body().string();

                Message message = new Message();
                message.what=position;
                message.obj=data;
                mHandler.sendMessage(message);
            }
        });
    }

    public static void post(String url, Map<String,Object> map,String Token,MediaType JSON,final int position, final Handler mHandler){
        Gson gson = new Gson();
        String json = gson.toJson(map);
        RequestBody body = RequestBody.create(json,JSON);

        Request request = new Request.Builder()
                .url(IP+url)
                .post(body)
                .addHeader("Authorization",Token)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println("网络连接错误");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String data = response.body().string();

                Message message = new Message();
                message.what=position;
                message.obj=data;
                mHandler.sendMessage(message);
            }
        });
    }

    public static void put(String url, Map<String,Object> map,String Token,MediaType JSON,final int position, final Handler mHandler){
        Gson gson = new Gson();
        String json = gson.toJson(map);
        RequestBody body = RequestBody.create(json,JSON);

        Request request = new Request.Builder()
                .url(IP+url)
                .put(body)
                .addHeader("Authorization",Token)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println("网络连接错误");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String data = response.body().string();

                Message message = new Message();
                message.what=position;
                message.obj=data;
                mHandler.sendMessage(message);
            }
        });
    }

}
