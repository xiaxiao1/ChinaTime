package com.xiaxiao.chinatime.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.xiaxiao.chinatime.R;
import com.xiaxiao.chinatime.bean.ChaoDai;
import com.xiaxiao.chinatime.thirdframework.bmob.BmobServer;
import com.xiaxiao.chinatime.thirdframework.bmob.SuccessListener;
import com.xiaxiao.chinatime.util.XiaoUtil;

import java.util.List;

public class ChaoDaiListActivity extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener<ChaoDai>{

    ItemFragment fragment;
    BmobServer bmobServer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chao_dai_list);
        fragment = (ItemFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        bmobServer = new BmobServer.Builder(this).build();
    }

    public void add(View view) {
        bmobServer.getChaodais(new SuccessListener() {
            @Override
            public void onSuccess(Object object) {
                List<ChaoDai> list= (List<ChaoDai>) object;
                XiaoUtil.toast("dedaole");
                MyAdapter myAdapter = new MyAdapter(list, ChaoDaiListActivity.this);
                fragment.setAdapter(myAdapter);
            }
        });
    }

    @Override
    public void onListFragmentInteraction(ChaoDai item) {
        XiaoUtil.toast("我在activity, "+item.getName());
    }
}
