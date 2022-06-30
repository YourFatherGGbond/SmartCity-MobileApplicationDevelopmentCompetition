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
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.smartcity_bisai_2.Json.GetData;
import com.example.smartcity_bisai_2.Json.News;
import com.example.smartcity_bisai_2.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter_allBtn extends RecyclerView.Adapter<Adapter_allBtn.MyViewHolder> {
    private Context context;
    private String[] strings = {"车主服务","生活服务","便民服务","全部服务"};
    private OnItemClickListener listener;

    public Adapter_allBtn(Context context, OnItemClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_allbtn,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.textView.setText(strings[position]);
        holder.Rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(position);
                notifyDataSetChanged();
            }
        });

        if (position == getAllBtn_Color()){
            holder.textView.setTextColor(Color.parseColor("#000000"));
            holder.Rl.setBackgroundColor(Color.parseColor("#e7e7e7"));
        }else {
            holder.textView.setTextColor(Color.parseColor("#7e7e7e"));
            holder.Rl.setBackgroundColor(Color.parseColor("#ffffff"));
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
        TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Rl = itemView.findViewById(R.id.allbtn_Rl);
            textView = itemView.findViewById(R.id.allbtn_text);
        }
    }

    private int allBtn_Color;

    public int getAllBtn_Color() {
        return allBtn_Color;
    }

    public void setAllBtn_Color(int allBtn_Color) {
        this.allBtn_Color = allBtn_Color;
    }
}
