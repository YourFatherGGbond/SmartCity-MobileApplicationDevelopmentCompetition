package com.example.smartcity_bisai_2.Json;

public class Icon {

    /**
     * id : 17
     * serviceName : 停车场
     * serviceDesc : 查询停车场
     * serviceType : 车主服务
     * imgUrl : http://localhost:7777/profile/upload/image/2021/05/1 0/814fc6c4-de48-48a1-95f8-de3e749e348d.png
     * link : park/index
     * sort : 1
     * isRecommend : N
     */

    private int id;
    private String serviceName;
    private String serviceDesc;
    private String serviceType;
    private String imgUrl;
    private String link;
    private int sort;
    private String isRecommend;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDesc() {
        return serviceDesc;
    }

    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(String isRecommend) {
        this.isRecommend = isRecommend;
    }
}
