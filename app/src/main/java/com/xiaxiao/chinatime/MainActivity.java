package com.xiaxiao.chinatime;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xiaxiao.chinatime.activity.AddChaoDaiActivity;
import com.xiaxiao.chinatime.activity.AddYearEventActivity;
import com.xiaxiao.chinatime.activity.ChaoDaiListActivity;
import com.xiaxiao.chinatime.activity.YearListActivity;
import com.xiaxiao.chinatime.bean.ChaoDai;
import com.xiaxiao.chinatime.customview.BaseActivity;
import com.xiaxiao.chinatime.customview.bannerview.XXBannerAdapter;
import com.xiaxiao.chinatime.customview.bannerview.XXBannerView;
import com.xiaxiao.chinatime.roundcorner.RoundCornerImageView;
import com.xiaxiao.chinatime.thirdframework.bmob.BmobIniter;
import com.xiaxiao.chinatime.thirdframework.bmob.BmobServer;
import com.xiaxiao.chinatime.util.XiaoUtil;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class MainActivity extends BaseActivity {

    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    TextView tv5;
    TextView tv6;
    XXBannerView bannerView;
    XXBannerAdapter<String> myBannerAdapter;
    List<String> datas;
    RoundCornerImageView roundCornerImageView;
    BmobServer bmobServer;
    int order=30;
    List<ChaoDai> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BmobIniter.init(this);
        bmobServer = new BmobServer.Builder(this).build();
    }

    public void goChaodai(View view) {
        startActivity(new Intent(this,AddChaoDaiActivity.class));
    }
    public void insertEvent(View view) {
        startActivity(new Intent(this,AddYearEventActivity.class));
    }
    public void getChaodai(View view) {
        startActivity(new Intent(this,ChaoDaiListActivity.class));
        /*bmobServer.getChaodais(new SuccessListener() {
            @Override
            public void onSuccess(Object object) {
                list= (List<ChaoDai>) object;
                XiaoUtil.toast("dedaole");

            }
        });*/
    }
    public void getYear(View view) {
        startActivity(new Intent(this,YearListActivity.class));
        /*bmobServer.getChaodais(new SuccessListener() {
            @Override
            public void onSuccess(Object object) {
                list= (List<ChaoDai>) object;
                XiaoUtil.toast("dedaole");

            }
        });*/
    }
    public void update(View view) {
        for (int i=0;i<list.size();i++) {
            ChaoDai cccc=list.get(i);

        cccc.setObjectId(cccc.getObjectId());
        ChaoDai test = new ChaoDai(cccc.getName(),cccc.getStart(),cccc.getEnd());
            test.setOrder(order);
            order=order+30;
        test.setObjectId(cccc.getObjectId());
            test.update(new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        XiaoUtil.l("update ");
                    } else {
                        XiaoUtil.l(e.toString());
                    }
                }
            });
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int a=0;
    }
/*

    Handler yearHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
            List<BmobObject> list2 = (List<BmobObject>) msg.obj;
           */
/* for (Year t:list2) {
                XiaoUtil.l(t.getYear()+"");
            }*//*

            XiaoUtil.l("哈哈哈哈哈哈");
            new BmobBatch().insertBatch(list2).doBatch(new QueryListListener<BatchResult>() {
                @Override
                public void done(List<BatchResult> list, BmobException e) {
                    if (e == null) {
                        XiaoUtil.l("=====================================OK");
                    } else {
                        XiaoUtil.l("=====================================F A I L E D");

                    }
                }
            });

        }
    };
    Message message = new Message();
    List<BmobObject> ys = new ArrayList<>();
    int batch=0;
    public void addYear(View view) {
        Thread t=new Thread(new Runnable(){
            @Override
            public void run() {
                int a=-6000;
                for (int i=a;i<2020;i++) {
                    if (batch== 50) {
                        message = new Message();
                        message.obj = ys;
                        yearHandler.sendMessage(message);
                        try {
                            Thread.sleep(400);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        ys = new ArrayList<>();
                        batch=0;
                    }
                    ys.add(new Year(i));
                    batch++;
                }

            }
        });
        t.start();
    }

*/




















    @Override
    public void onRefreshing() {
        stopRefresh();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
