package com.example.smartcity_bisai_2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.smartcity_bisai_2.Activity.Car.Activity_CarXQ;
import com.example.smartcity_bisai_2.Activity.WaiMai.Activity_DianXQ;
import com.example.smartcity_bisai_2.Json.Car;
import com.example.smartcity_bisai_2.Json.DianJia;

import java.util.List;

public class GoToDianXq {
    public static Intent goDian(Context context, int position, List<DianJia> carList){
        Intent intent = new Intent(context, Activity_DianXQ.class);
        Bundle bundle = new Bundle();
        bundle.putInt("id",carList.get(position).getId());
        intent.putExtras(bundle);
        return intent;
    }
}
