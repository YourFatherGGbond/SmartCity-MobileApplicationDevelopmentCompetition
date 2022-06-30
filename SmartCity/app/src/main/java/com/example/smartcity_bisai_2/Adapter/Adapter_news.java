package com.example.smartcity_bisai_2.Adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartcity_bisai_2.Json.GetData;
import com.example.smartcity_bisai_2.Json.Icon;
import com.example.smartcity_bisai_2.Json.News;
import com.example.smartcity_bisai_2.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter_news extends RecyclerView.Adapter<Adapter_news.MyViewHolder> {
    private Context context;
    private List<News> newsList = new ArrayList<>();
    private OnItemClickListener listener;

    public Adapter_news(Context context, List<News> iconList, OnItemClickListener listener) {
        this.context = context;
        this.newsList = iconList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_news,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.title.setText(newsList.get(position).getTitle());
        Glide.with(context).load(GetData.IP+newsList.get(position).getCover()).into(holder.iv);
        holder.text.setText(Html.fromHtml(newsList.get(position).getContent()));
        holder.pinglun.setText("评论:"+newsList.get(position).getCommentNum());
        holder.time.setText(newsList.get(position).getPublishDate());
        holder.Rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public interface OnItemClickListener{
        void onClick(int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout Rl;
        ImageView iv;
        TextView title,text,pinglun,time;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Rl = itemView.findViewById(R.id.news_Rl);
            iv = itemView.findViewById(R.id.news_img);
            title = itemView.findViewById(R.id.news_title);
            text = itemView.findViewById(R.id.news_text);
            pinglun = itemView.findViewById(R.id.news_pinglun);
            time = itemView.findViewById(R.id.news_time);
        }
    }

}
