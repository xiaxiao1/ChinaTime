package com.xiaxiao.chinatime.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xiaxiao.chinatime.R;
import com.xiaxiao.chinatime.bean.Year;
import com.xiaxiao.chinatime.customview.BaseActivity;
import com.xiaxiao.chinatime.thirdframework.bmob.BmobServer;
import com.xiaxiao.chinatime.thirdframework.bmob.Page;
import com.xiaxiao.chinatime.thirdframework.bmob.SuccessListener;
import com.xiaxiao.chinatime.util.ActivityUtil;
import com.xiaxiao.chinatime.util.XiaoUtil;

import java.util.ArrayList;
import java.util.List;

public class YearListActivity extends BaseActivity implements ItemFragment.OnListFragmentInteractionListener<Year>{

    BmobServer bmobServer;
    ItemFragment itemFragment;
    YearListAdapter yearListAdapter;
    Page page;
    List<Year> yearList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year_list);
        bmobServer = getLocalBmobServer();
        itemFragment = (ItemFragment) getItemFragment(R.id.fragment);
        page = new Page();
        yearList = new ArrayList<>();
        bmobServer.setPage(page);
    }

    public void getYear(View view) {
        bmobServer.getYears(new SuccessListener<List<Year>>() {
            @Override
            public void onSuccess(List<Year> object) {
                if (yearListAdapter == null) {
                    yearList = object;
                    yearListAdapter = new YearListAdapter(yearList, YearListActivity.this);
                    itemFragment.setAdapter(yearListAdapter);
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
    public void onListFragmentInteraction(Year item) {
        XiaoUtil.toast(item.getYear()+"");
        ActivityUtil.goYearDetial(this,item.getYear());
    }


    class YearListAdapter extends XXRecyclerViewAdapter<Year,YearListAdapter.YearHolder>{

        public YearListAdapter(List<Year> items, ItemFragment.OnListFragmentInteractionListener listener) {
            super(items, listener);
        }

        @Override
        public YearHolder getHolder(View view) {
            return new YearHolder(view);
        }

        @Override
        public int getItemId() {
            return R.layout.year_item;
        }

        class YearHolder extends XXRecyclerViewAdapter.RCHolder{
            TextView year;
            public YearHolder(View itemView) {
                super(itemView);
            }

            @Override
            public void initData(int position, Object obj) {
                Year y = (Year) obj;
                StringBuffer stringBuffer = new StringBuffer();
                if (y.getYear()<0) {
                    stringBuffer.append("公元前");
                    stringBuffer.append((0-y.getYear()));
                    stringBuffer.append("年");
                } else if (y.getYear() == 0) {
                    stringBuffer.append("公元元年");
                } else {
                    stringBuffer.append("公元");
                    stringBuffer.append(y.getYear());
                    stringBuffer.append("年");
                }
                year.setText(stringBuffer);
            }

            @Override
            public void initView(View itemView) {
                year = (TextView) itemView.findViewById(R.id.year);
            }
        }
    }
}
