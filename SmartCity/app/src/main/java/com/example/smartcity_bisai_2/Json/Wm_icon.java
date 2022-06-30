package com.example.smartcity_bisai_2.Json;

public class Wm_icon {

    /**
     * id : 1
     * themeName : 美食
     * themeDesc : 美食来找我
     * imgUrl : http://118.190.154.52:7777/profile/upload/image/2021 /05/08/f8c06dfc-9da2-41cc-9784-8cf234c999f2.png
     * sort : 1
     */

    private int id;
    private String themeName;
    private String themeDesc;
    private String imgUrl;
    private int sort;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public String getThemeDesc() {
        return themeDesc;
    }

    public void setThemeDesc(String themeDesc) {
        this.themeDesc = themeDesc;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
