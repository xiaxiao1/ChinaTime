package com.xiaxiao.chinatime.thirdframework.bmob;

/**
 * Created by xiaxiao on 2017/12/28.
 * 用于分页
 */

public class Page {
    int pageSize=50;
    int currentPage=0;

    boolean isLastPage=false;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void next() {
        currentPage++;
    }

}
