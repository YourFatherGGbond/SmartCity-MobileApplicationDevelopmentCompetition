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
import com.example.smartcity_bisai_2.Json.Wm_icon;
import com.example.smartcity_bisai_2.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter_wm_icon extends RecyclerView.Adapter<Adapter_wm_icon.MyViewHolder> {
    private Context context;
    private List<Wm_icon> iconList = new ArrayList<>();
    private OnItemClickListener listener;

    public Adapter_wm_icon(Context context, List<Wm_icon> iconList, OnItemClickListener listener) {
        this.context = context;
        this.iconList = iconList;
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
        holder.textView.setText(iconList.get(position).getThemeName());
        Glide.with(context).load(GetData.IP+iconList.get(position).getImgUrl()).into(holder.iv);
        holder.Rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return iconList.size();
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
}
