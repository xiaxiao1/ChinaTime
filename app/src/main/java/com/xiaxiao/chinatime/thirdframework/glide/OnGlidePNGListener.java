package com.xiaxiao.chinatime.thirdframework.glide;

import android.graphics.Bitmap;

import com.xiaxiao.chinatime.Listener.MyListener;


/**
 * Created by xiaxiao on 2017/1/12.
 */

public abstract class OnGlidePNGListener implements MyListener {
//    public abstract  void success(byte[] resource);
    public abstract  void success(Bitmap bitmap);
    public abstract  void failed();
}
