package com.example.smartcity_bisai_2.Adapter;

import android.content.Context;
import android.graphics.Color;
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
import com.example.smartcity_bisai_2.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter_main_btn extends RecyclerView.Adapter<Adapter_main_btn.MyViewHolder> {
    private Context context;
    private List<Icon> imageViewList = new ArrayList<>();
    private String[] strings = {"首页","全部服务","停车位","智慧巴士","个人中心"};
    private OnItemClickListener listener;

    public Adapter_main_btn(Context context, List<Icon> iconList, OnItemClickListener listener) {
        this.context = context;
        this.imageViewList = iconList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_icon,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.textView.setText(strings[position]);

        Glide.with(context).load(GetData.IP+imageViewList.get(position).getImgUrl()).into(holder.iv);
        holder.Rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(position);
                notifyDataSetChanged();
            }
        });

        if (position == getMainBtn_Color()){
            holder.Rl.setBackgroundColor(Color.parseColor("#e7e7e7"));
            holder.textView.setTextColor(Color.parseColor("#000000"));
        }else {
            holder.Rl.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.textView.setTextColor(Color.parseColor("#7e7e7e"));
        }

    }

    @Override
    public int getItemCount() {
        return strings.length;
    }

    public interface OnItemClickListener{
        void onClick(int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout Rl;
        ImageView iv;
        TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Rl = itemView.findViewById(R.id.icon_Rl);
            iv = itemView.findViewById(R.id.icon_img);
            textView = itemView.findViewById(R.id.icon_text);
        }
    }

    private int mainBtn_Color;

    public int getMainBtn_Color() {
        return mainBtn_Color;
    }

    public void setMainBtn_Color(int mainBtn_Color) {
        this.mainBtn_Color = mainBtn_Color;
    }
}
