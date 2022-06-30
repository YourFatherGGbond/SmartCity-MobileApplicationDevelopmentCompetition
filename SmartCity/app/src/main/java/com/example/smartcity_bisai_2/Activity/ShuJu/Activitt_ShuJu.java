package com.example.smartcity_bisai_2.Activity.ShuJu;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity_bisai_2.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;

public class Activitt_ShuJu extends AppCompatActivity {

    private LineChart lineChart;
    private LineDataSet lineset1;
    private LineDataSet lineset2;

    private BarChart barChart;
    private BarDataSet barset1;
    private BarDataSet barset2;
    private float width = 0.2f;

    private PieChart pieChart;
    private PieDataSet pieset1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitt__shu_ju);
        initView();

    }

    private void initView() {

        lineChart = (LineChart) findViewById(R.id.lineChart);
        barChart = (BarChart) findViewById(R.id.barChart);
        pieChart = (PieChart) findViewById(R.id.pieChart);

        setLine();
        setBar();
        setPie();
    }


    private void setLine() {
        ArrayList<Entry> values = new ArrayList<>();
        values.add(new Entry(1, 2));
        values.add(new Entry(2, 2));
        values.add(new Entry(3, 3));
        values.add(new Entry(4, 4));

        ArrayList<Entry> values2 = new ArrayList<>();
        values2.add(new Entry(1, 2));
        values2.add(new Entry(2, 4));
        values2.add(new Entry(3, 3));
        values2.add(new Entry(4, 5));

        lineset1 = new LineDataSet(values, "line1");
        lineset1.setColor(Color.BLACK);
        lineset1.setLabel("男");

        lineset2 = new LineDataSet(values2, "line2");
        lineset2.setColor(Color.RED);
        lineset2.setLabel("女");

        LineData data = new LineData();
        data.addDataSet(lineset1);
        data.addDataSet(lineset2);
        lineChart.setData(data);
    }

    private void setBar() {
        ArrayList<BarEntry> values = new ArrayList<>();
        values.add(new BarEntry(1, 2));
        values.add(new BarEntry(2, 3));
        values.add(new BarEntry(3, 3));
        values.add(new BarEntry(4, 4));

        ArrayList<BarEntry> values2 = new ArrayList<>();
        values2.add(new BarEntry(1 + width, 3));
        values2.add(new BarEntry(2 + width, 4));
        values2.add(new BarEntry(3 + width, 3));
        values2.add(new BarEntry(4 + width, 8));

        barset1 = new BarDataSet(values, "bar1");
        barset1.setColor(Color.BLACK);
        barset1.setLabel("男");
        barset2 = new BarDataSet(values2, "bar2");
        barset2.setColor(Color.RED);
        barset2.setLabel("女");

        BarData data = new BarData();
        data.addDataSet(barset1);
        data.addDataSet(barset2);
        data.setBarWidth(0.2f);
        barChart.setData(data);
    }

    private void setPie() {
        ArrayList<PieEntry> values = new ArrayList<>();
        values.add(new PieEntry(1, "周一"));
        values.add(new PieEntry(2, "周二"));
        values.add(new PieEntry(3, "周三"));
        values.add(new PieEntry(4, "周四"));

        pieset1 = new PieDataSet(values, "pie1");
        pieset1.setColors(new int[]{Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN});

        PieData data = new PieData();
        data.addDataSet(pieset1);
        data.setValueTextSize(18);

        pieChart.setUsePercentValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));


        pieChart.setDrawHoleEnabled(false);
        pieChart.setData(data);
    }
}