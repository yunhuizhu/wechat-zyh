package com.yuncia.wechat.pojo.form;

/**
 * Created with IntelliJ IDEA.
 * User: zhu
 * Date: 15-5-4
 * Time: 上午10:37
 * To change this template use File | Settings | File Templates.
 */
public class ExchangeGift {
    private String receiver;
    private String phone;
    private String address;
    private String provinceId;
    private String cityId;
    private String id;
    private String uid;
    private String isWechat;

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getWechat() {
        return isWechat;
    }

    public void setWechat(String wechat) {
        isWechat = wechat;
    }
}
