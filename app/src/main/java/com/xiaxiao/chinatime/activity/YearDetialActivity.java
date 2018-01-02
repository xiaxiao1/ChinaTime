package com.xiaxiao.chinatime.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xiaxiao.chinatime.R;
import com.xiaxiao.chinatime.bean.Year;
import com.xiaxiao.chinatime.bean.YearEvent;
import com.xiaxiao.chinatime.customview.BaseActivity;
import com.xiaxiao.chinatime.thirdframework.bmob.BmobServer;
import com.xiaxiao.chinatime.thirdframework.bmob.Page;
import com.xiaxiao.chinatime.thirdframework.bmob.SuccessListener;
import com.xiaxiao.chinatime.util.XiaoUtil;

import java.util.ArrayList;
import java.util.List;

public class YearDetialActivity extends BaseActivity implements ItemFragment.OnListFragmentInteractionListener<YearEvent>{

    BmobServer bmobServer;
    ItemFragment itemFragment;
    YearEventListAdapter yeareventListAdapter;
    Page page;
    List<YearEvent> yearList;
    int year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year_detial);
        bmobServer = getLocalBmobServer();
        itemFragment = (ItemFragment) getItemFragment(R.id.fragment);
        page = new Page();
        yearList = new ArrayList<>();
        bmobServer.setPage(page);
        year = getIntent().getIntExtra("year", 1990);
        setBarTitle(year<0?"公元前"+(0-year):"公元"+year);
    }

    public void getYearEvent(View view) {
        bmobServer.getYearEvents(year,new SuccessListener<List<YearEvent>>() {
            @Override
            public void onSuccess(List<YearEvent> object) {
                if (yeareventListAdapter == null) {
                    yearList = object;
                    yeareventListAdapter = new YearEventListAdapter(yearList, YearDetialActivity.this);
                    itemFragment.setAdapter(yeareventListAdapter);
                } else {
                    yearList.addAll(object);
                    itemFragment.getAdapter().notifyDataSetChanged();

                }
            }
        });
    }

    @Override
    public void onRefreshing() {
        stopRefresh();
    }

    @Override
    public void onListFragmentInteraction(YearEvent item) {
        XiaoUtil.toast(item.getYear()+"");
    }


    class YearEventListAdapter extends XXRecyclerViewAdapter<YearEvent,YearEventListAdapter.YearHolder>{

        public YearEventListAdapter(List<YearEvent> items, ItemFragment.OnListFragmentInteractionListener listener) {
            super(items, listener);
        }

        @Override
        public YearHolder getHolder(View view) {
            return new YearHolder(view);
        }

        @Override
        public int getItemId() {
            return R.layout.year_event_item;
        }

        class YearHolder extends XXRecyclerViewAdapter.RCHolder{
            TextView shortTv;
            TextView detialTv;
            public YearHolder(View itemView) {
                super(itemView);
            }

            @Override
            public void initData(int position, Object obj) {
                YearEvent ye = (YearEvent) obj;

                shortTv.setText(ye.getShortName());
                detialTv.setText(ye.getDetial());
            }

            @Override
            public void initView(View itemView) {
                shortTv = (TextView) itemView.findViewById(R.id.short_tv);
                detialTv = (TextView) itemView.findViewById(R.id.detial_tv);
            }
        }
    }
}
