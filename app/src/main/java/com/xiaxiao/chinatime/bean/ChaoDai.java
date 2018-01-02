package com.xiaxiao.chinatime.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/12/25.
 */

public class ChaoDai extends BmobObject {
    String name;
    int start;
    int end;
    //表明事件顺序的
    int order;

    public ChaoDai(String name, int start, int end ) {
        this.end = end;
        this.name = name;
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }
}
