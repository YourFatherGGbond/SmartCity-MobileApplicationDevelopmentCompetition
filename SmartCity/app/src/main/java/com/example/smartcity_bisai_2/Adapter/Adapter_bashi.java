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
import com.example.smartcity_bisai_2.Json.BaShi;
import com.example.smartcity_bisai_2.Json.GetData;
import com.example.smartcity_bisai_2.Json.News;
import com.example.smartcity_bisai_2.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter_bashi extends RecyclerView.Adapter<Adapter_bashi.MyViewHolder> {
    private Context context;
    private List<BaShi> baShiList = new ArrayList<>();
    private OnItemClickListener listener;

    public Adapter_bashi(Context context, List<BaShi> iconList, OnItemClickListener listener) {
        this.context = context;
        this.baShiList = iconList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_bashi,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.name.setText(baShiList.get(position).getName());
        holder.money.setText("价格:"+baShiList.get(position).getPrice());
        holder.start.setText(baShiList.get(position).getFirst()+"-------"+baShiList.get(position).getStartTime());
        holder.end.setText(baShiList.get(position).getEnd()+"-------"+baShiList.get(position).getEndTime());
        holder.licheng.setText("里程:"+baShiList.get(position).getMileage());
        holder.Rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return baShiList.size();
    }

    public interface OnItemClickListener{
        void onClick(int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout Rl;
        TextView name,money,start,end,licheng;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Rl = itemView.findViewById(R.id.bashi_Rl);
            name = itemView.findViewById(R.id.bashi_name);
            money = itemView.findViewById(R.id.bashi_money);
            start = itemView.findViewById(R.id.bashi_start);
            end = itemView.findViewById(R.id.bashi_end);
            licheng = itemView.findViewById(R.id.bashi_licheng);
        }
    }

}
