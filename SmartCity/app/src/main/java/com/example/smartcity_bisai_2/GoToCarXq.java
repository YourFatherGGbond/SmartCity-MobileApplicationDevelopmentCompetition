package com.example.smartcity_bisai_2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.smartcity_bisai_2.Activity.Car.Activity_CarXQ;
import com.example.smartcity_bisai_2.Json.Car;

import java.util.List;

public class GoToCarXq {
    public static Intent goCar(Context context, int position, List<Car> carList){
        Intent intent = new Intent(context, Activity_CarXQ.class);
        Bundle bundle = new Bundle();
        bundle.putInt("id",carList.get(position).getId());
        intent.putExtras(bundle);
        return intent;
    }
}
