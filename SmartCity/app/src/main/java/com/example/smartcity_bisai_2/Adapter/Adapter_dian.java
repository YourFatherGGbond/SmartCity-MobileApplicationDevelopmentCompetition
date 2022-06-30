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
import com.example.smartcity_bisai_2.Json.DianJia;
import com.example.smartcity_bisai_2.Json.GetData;
import com.example.smartcity_bisai_2.Json.News;
import com.example.smartcity_bisai_2.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter_dian extends RecyclerView.Adapter<Adapter_dian.MyViewHolder> {
    private Context context;
    private List<DianJia> newsList = new ArrayList<>();
    private OnItemClickListener listener;

    public Adapter_dian(Context context, List<DianJia> iconList, OnItemClickListener listener) {
        this.context = context;
        this.newsList = iconList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_dianjia,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.name.setText(newsList.get(position).getName());
        Glide.with(context).load(GetData.IP+newsList.get(position).getImgUrl()).into(holder.iv);
        holder.xiaoliang.setText("三小时销量:"+newsList.get(position).getSaleNum3hour());
        holder.address.setText("地址:"+newsList.get(position).getAddress());
        holder.fen.setText("评分:"+newsList.get(position).getScore());
        holder.juli.setText("距离:"+newsList.get(position).getDistance());
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
        TextView name,xiaoliang,address,fen,juli;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Rl = itemView.findViewById(R.id.dianjia_Rl);
            iv = itemView.findViewById(R.id.dianjia_img);
            name = itemView.findViewById(R.id.dianjia_name);
            xiaoliang = itemView.findViewById(R.id.dianjia_xiaoliang);
            address = itemView.findViewById(R.id.dianjia_address);
            fen = itemView.findViewById(R.id.dianjia_fen);
            juli = itemView.findViewById(R.id.dianjia_juli);
        }
    }

}
