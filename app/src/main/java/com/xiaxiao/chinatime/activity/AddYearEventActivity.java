package com.xiaxiao.chinatime.activity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.xiaxiao.chinatime.R;
import com.xiaxiao.chinatime.bean.ChaoDai;
import com.xiaxiao.chinatime.bean.Year;
import com.xiaxiao.chinatime.bean.YearEvent;
import com.xiaxiao.chinatime.customview.BaseActivity;
import com.xiaxiao.chinatime.thirdframework.bmob.BmobListener;
import com.xiaxiao.chinatime.thirdframework.bmob.BmobServer;
import com.xiaxiao.chinatime.thirdframework.bmob.SuccessListener;
import com.xiaxiao.chinatime.util.XiaoUtil;

import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.exception.BmobException;

public class AddYearEventActivity extends BaseActivity {

    EditText year_tv;
    EditText shortStr_tv;
    EditText detial_tv;

    String yearStr;
    String shortStr;
    String detialStr;
    BmobServer bmobServer;
    BmobListener<YearEvent> successListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chao_dai);
        setRefreshEnable(false);
        year_tv = (EditText) findViewById(R.id.cahodai_tv);
        shortStr_tv = (EditText) findViewById(R.id.start_tv);
        detial_tv = (EditText) findViewById(R.id.end_tv);
        year_tv.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        shortStr_tv.setInputType(InputType.TYPE_CLASS_TEXT);
        detial_tv.setInputType(InputType.TYPE_CLASS_TEXT);
        year_tv.setHint("年份");
        shortStr_tv.setHint("短描述");
        detial_tv.setHint("详细");
        bmobServer = new BmobServer.Builder(this).build();
        successListener=new BmobListener<YearEvent>() {
            @Override
            public void onSuccess(final YearEvent object) {
                XiaoUtil.toast(" 添加成功");
                bmobServer.getYear(Integer.parseInt(yearStr), new SuccessListener<Year>() {
                    @Override
                    public void onSuccess(Year y) {
                        Year year = new Year(1);
                        year.setYear(y.getYear());
                        year.setObjectId(y.getObjectId());
                        BmobRelation bmobRelation = new BmobRelation();
                        bmobRelation.add(object);
                        year.setYearEvent(bmobRelation);
                        XiaoUtil.l("添加成功事件");
                        bmobServer.updateYear(year, new SuccessListener<Year>() {
                            @Override
                            public void onSuccess(Year object) {
                                XiaoUtil.l("时间点事件添加成功");
                                year_tv.setText("");
                                shortStr_tv.setText("");
                                detial_tv.setText("");
                            }
                            @Override
                            public void onError(BmobException e) {
                                XiaoUtil.l("插入year事件时出错："+e.toString());
                            }
                        });
                    }

                    @Override
                    public void onError(BmobException e) {
                        XiaoUtil.l("获取year 时出错："+e.toString());
                    }
                });
            }

            @Override
            public void onError(BmobException e) {
                XiaoUtil.l("lalalalallala"+e.toString());
            }
        };
    }

    public void add(View view) {
        yearStr = year_tv.getText().toString().trim();
        shortStr = shortStr_tv.getText().toString().trim();
        detialStr = detial_tv.getText().toString().trim();
        YearEvent yearEvent = new YearEvent(Integer.parseInt(yearStr),shortStr,detialStr);
//        chaoDai.setOrder(1111);
        bmobServer.addYearEvent(yearEvent,successListener);
    }

    @Override
    public void onRefreshing() {
        stopRefresh();
    }
}
