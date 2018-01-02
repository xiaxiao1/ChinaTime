package com.xiaxiao.chinatime.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * Created by Administrator on 2017/12/25.
 */

public class Year extends BmobObject{
    int year;
    BmobRelation yearEvent;
    BmobPointer bmobPointer;


    public Year(int year) {
        this.year = year;
    }
    public BmobPointer getBmobPointer() {
        return bmobPointer;
    }

    public void setBmobPointer(BmobPointer bmobPointer) {
        this.bmobPointer = bmobPointer;
    }

    public BmobRelation getYearEvent() {
        return yearEvent;
    }

    public void setYearEvent(BmobRelation yearEvent) {
        this.yearEvent = yearEvent;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
