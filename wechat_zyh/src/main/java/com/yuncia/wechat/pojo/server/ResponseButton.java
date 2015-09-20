package com.yuncia.wechat.pojo.server;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhu
 * Date: 15-4-2
 * Time: 下午12:18
 * To change this template use File | Settings | File Templates.
 */
public class ResponseButton {
    private List<MenuButton> button;

    public List<MenuButton> getButton() {
        return button;
    }

    public void setButton(List<MenuButton> button) {
        this.button = button;
    }
}
