package com.xiaxiao.chinatime.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.xiaxiao.chinatime.R;
import com.xiaxiao.chinatime.bean.ChaoDai;
import com.xiaxiao.chinatime.customview.BaseActivity;
import com.xiaxiao.chinatime.thirdframework.bmob.BmobServer;
import com.xiaxiao.chinatime.thirdframework.bmob.SuccessListener;
import com.xiaxiao.chinatime.util.XiaoUtil;

public class AddChaoDaiActivity extends BaseActivity {

    EditText chaodai;
    EditText start;
    EditText end;

    String chaodaiStr;
    String startStr;
    String endStr;
    BmobServer bmobServer;
    SuccessListener successListener;
    ChaoDai show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chao_dai);
        setRefreshEnable(false);
        chaodai = (EditText) findViewById(R.id.cahodai_tv);
        start = (EditText) findViewById(R.id.start_tv);
        end = (EditText) findViewById(R.id.end_tv);
        bmobServer = new BmobServer.Builder(this).build();
        successListener=new SuccessListener() {
            @Override
            public void onSuccess(Object object) {
                show = (ChaoDai) object;
                XiaoUtil.toast(show.getName()+" 添加成功");
                chaodai.setText("");
                start.setText("");
                end.setText("");
            }
        };
    }

    public void add(View view) {
        chaodaiStr = chaodai.getText().toString().trim();
        startStr = start.getText().toString().trim();
        endStr = end.getText().toString().trim();
        ChaoDai chaoDai = new ChaoDai(chaodaiStr, Integer.parseInt(startStr), Integer.parseInt(endStr));
//        chaoDai.setOrder(1111);
        bmobServer.addChaodai(chaoDai,successListener);
    }

    @Override
    public void onRefreshing() {

    }
}
