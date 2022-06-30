package com.example.smartcity_bisai_2.Json;

public class BaShi {

    /**
     * id : 1
     * name : 一号线
     * first : 光谷金融街
     * end : 南湖大厦
     * startTime : 6:30
     * endTime : 19:45
     * price : 8
     * mileage : 20
     */

    private int id;
    private String name;
    private String first;
    private String end;
    private String startTime;
    private String endTime;
    private int price;
    private String mileage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }
}
