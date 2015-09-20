package com.yuncia.wechat.pojo.wap;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhu
 * Date: 15-4-29
 * Time: 下午3:02
 * To change this template use File | Settings | File Templates.
 */
public class PaginationJson<T> {
    private boolean isEnd;
    private List<T> dataList;

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}
