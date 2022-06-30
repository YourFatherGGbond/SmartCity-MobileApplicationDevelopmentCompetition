package com.example.smartcity_bisai_2.Json;

public class Car {

    /**
     * id : 1
     * parkName : 国际大厦停车场
     * vacancy : 30
     * priceCaps : 30
     * imgUrl : /dev-api/profile/upload/image/2021/04/11/ac978c51-d7 5a-4797-9845-86a73527c55d.jpg
     * rates : 5
     * address : 大连市国际大厦 F1 楼
     * distance : 20
     * allPark : 90
     * open : Y
     */

    private int id;
    private String parkName;
    private String vacancy;
    private String priceCaps;
    private String imgUrl;
    private String rates;
    private String address;
    private String distance;
    private String allPark;
    private String open;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public String getVacancy() {
        return vacancy;
    }

    public void setVacancy(String vacancy) {
        this.vacancy = vacancy;
    }

    public String getPriceCaps() {
        return priceCaps;
    }

    public void setPriceCaps(String priceCaps) {
        this.priceCaps = priceCaps;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getRates() {
        return rates;
    }

    public void setRates(String rates) {
        this.rates = rates;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getAllPark() {
        return allPark;
    }

    public void setAllPark(String allPark) {
        this.allPark = allPark;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }
}
