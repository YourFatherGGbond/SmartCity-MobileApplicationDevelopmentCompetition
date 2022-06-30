package com.example.smartcity_bisai_2.Json;

public class Img {

    /**
     * id : 14
     * sort : 1
     * advTitle : 测试首页轮播
     * advImg : http://152.136.210.130:7777/profile/upload/image/202 1/04/26/183e63c6-a59d-4551-a5b4-7055ff7a9575.jpg
     * servModule : 新闻
     * targetId : 1
     * type : 2
     */

    private int id;
    private int sort;
    private String advTitle;
    private String advImg;
    private String servModule;
    private int targetId;
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getAdvTitle() {
        return advTitle;
    }

    public void setAdvTitle(String advTitle) {
        this.advTitle = advTitle;
    }

    public String getAdvImg() {
        return advImg;
    }

    public void setAdvImg(String advImg) {
        this.advImg = advImg;
    }

    public String getServModule() {
        return servModule;
    }

    public void setServModule(String servModule) {
        this.servModule = servModule;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
