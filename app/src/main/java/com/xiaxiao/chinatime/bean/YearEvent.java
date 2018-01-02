package com.xiaxiao.chinatime.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/12/28.
 */

public class YearEvent extends BmobObject{
    int year;
    String shortName;
    String detial;

    public YearEvent( int year, String shortName,String detial) {
        this.detial = detial;
        this.shortName = shortName;
        this.year = year;
    }

    public String getDetial() {
        return detial;
    }

    public void setDetial(String detial) {
        this.detial = detial;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
