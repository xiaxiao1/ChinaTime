package com.xiaxiao.chinatime.activity;

import android.view.View;
import android.widget.TextView;

import com.xiaxiao.chinatime.R;
import com.xiaxiao.chinatime.bean.ChaoDai;

import java.util.List;

/**
 * Created by Administrator on 2017/12/26.
 */

public class MyAdapter extends XXRecyclerViewAdapter<ChaoDai,MyAdapter.Holder>{


    public MyAdapter(List<ChaoDai> items, ItemFragment.OnListFragmentInteractionListener listener) {
        super(items, listener);
    }

    @Override
    public Holder getHolder(View view) {
        return new Holder(view);
    }

    @Override
    public int getItemId() {
        return R.layout.fragment_item;
    }

    class Holder extends XXRecyclerViewAdapter.RCHolder{
        TextView tv;
        TextView start_tv;
        TextView end_tv;

        public Holder(View itemView) {
            super(itemView);

        }

        @Override
        public void initView(View itemView) {
            tv=(TextView)itemView.findViewById(R.id.id);
            start_tv=(TextView)itemView.findViewById(R.id.start_tv);
            end_tv=(TextView)itemView.findViewById(R.id.end_tv);
        }

        @Override
        public void initData(int position, Object obj) {
            ChaoDai c = (ChaoDai) obj;
            tv.setText(c.getName());
            if (c.getStart() < 0) {
                start_tv.setText("公元前" + Math.abs(c.getStart()) + "年 ");
            } else {
                start_tv.setText("公元" + c.getStart() + "年 ");
            }
            if (c.getEnd() < 0) {
                end_tv.setText("- 公元前" + Math.abs(c.getEnd()) + "年");
            } else {
                end_tv.setText("- 公元" + c.getEnd() + "年");
            }
        }
    }

}
