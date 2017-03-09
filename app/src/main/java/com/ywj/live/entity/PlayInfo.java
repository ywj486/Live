package com.ywj.live.entity;


/**
 * Created by Administrator on 2017/3/9 0009.
 */

public class PlayInfo extends BaseBean{
    //名字  地址  状态 头像  展示
    private String name;
    private String place;
    private String status;
    private String headIcon;
    private String informationImage;

    public String getHeadIcon() {
        return headIcon;
    }

    public void setHeadIcon(String headIcon) {
        this.headIcon = headIcon;
    }

    public String getInformationImage() {
        return informationImage;
    }

    public void setInformationImage(String informationImage) {
        this.informationImage = informationImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
