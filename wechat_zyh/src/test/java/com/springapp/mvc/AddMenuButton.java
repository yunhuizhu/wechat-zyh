package com.springapp.mvc;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhu
 * Date: 15-4-12
 * Time: 上午12:39
 * To change this template use File | Settings | File Templates.
 */
public class AddMenuButton {
    private List<AddMenuButton> sub_button;
    private String type;
    private String key;
    private String url;
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<AddMenuButton> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<AddMenuButton> sub_button) {
        this.sub_button = sub_button;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
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
}
