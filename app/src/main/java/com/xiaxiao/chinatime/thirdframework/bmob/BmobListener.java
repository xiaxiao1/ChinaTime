package com.xiaxiao.chinatime.thirdframework.bmob;

import cn.bmob.v3.exception.BmobException;

/**
 * Created by Administrator on 2016/11/4.
 */
public  interface  BmobListener<T> {
    public void onSuccess(T object);
    public void onError(BmobException e);


}
