package com.yuncia.wechat.pojo.server;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhu
 * Date: 15-4-2
 * Time: 下午12:03
 * To change this template use File | Settings | File Templates.
 */
public class MenuButton {
    private String type;
    private String name;
    private String key;
    private String url;
    private List<MenuButton> sub_button;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<MenuButton> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<MenuButton> sub_button) {
        this.sub_button = sub_button;
    }
}
