package com.example.smartcity_bisai_2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity_bisai_2.Json.BaShi;
import com.example.smartcity_bisai_2.Json.Car;
import com.example.smartcity_bisai_2.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter_car extends RecyclerView.Adapter<Adapter_car.MyViewHolder> {
    private Context context;
    private List<Car> carList = new ArrayList<>();
    private OnItemClickListener listener;

    public Adapter_car(Context context, List<Car> iconList, OnItemClickListener listener) {
        this.context = context;
        this.carList = iconList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_car,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.name.setText(carList.get(position).getParkName());
        holder.money.setText("价格:"+carList.get(position).getRates());
        holder.kong.setText("空位"+carList.get(position).getVacancy());
        holder.address.setText("收费"+carList.get(position).getAddress());
        holder.juli.setText("距离:"+carList.get(position).getDistance());
        holder.Rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public interface OnItemClickListener{
        void onClick(int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout Rl;
        TextView name,kong,money,address,juli;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Rl = itemView.findViewById(R.id.car_Rl);
            name = itemView.findViewById(R.id.car_name);
            money = itemView.findViewById(R.id.car_money);
            kong = itemView.findViewById(R.id.car_kong);
            address = itemView.findViewById(R.id.car_address);
            juli = itemView.findViewById(R.id.car_juli);
        }
    }

}
