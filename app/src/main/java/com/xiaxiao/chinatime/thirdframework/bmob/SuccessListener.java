package com.xiaxiao.chinatime.thirdframework.bmob;



import com.xiaxiao.chinatime.util.XiaoUtil;

import cn.bmob.v3.exception.BmobException;

/**
 * Created by Administrator on 2016/11/4.
 */
public abstract class SuccessListener<T> implements BmobListener<T> {

        @Override
        public void onError(BmobException e) {
                if (e!=null) {
                        XiaoUtil.l(e.getMessage());
                }
        }
}
