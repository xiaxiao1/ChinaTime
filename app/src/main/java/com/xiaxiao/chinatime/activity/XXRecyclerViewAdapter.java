package com.xiaxiao.chinatime.activity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaxiao.chinatime.activity.ItemFragment.OnListFragmentInteractionListener;

import java.util.List;

public abstract class XXRecyclerViewAdapter<T,VH extends XXRecyclerViewAdapter.RCHolder> extends RecyclerView.Adapter {

    private final List<T> mValues;
    private final OnListFragmentInteractionListener mListener;

    public XXRecyclerViewAdapter(List<T> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(getItemId(), parent, false);
        return getHolder(view);
    }

    public abstract VH getHolder(View view);
    public abstract int getItemId();

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
       final  RCHolder rcHolder = (RCHolder) holder;
        rcHolder.itemValue = mValues.get(position);
        rcHolder.initData(position,rcHolder.itemValue);
        rcHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(rcHolder.itemValue);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


    /*E 好像没用 内部类啊*/
    public  abstract class RCHolder<E> extends RecyclerView.ViewHolder {
        E itemValue;
        View mView;
        public RCHolder(View itemView) {
            super(itemView);
            mView = itemView;
            initView(itemView);
        }

        public abstract void initView(View itemView);
        public abstract void initData(int position,E obj);

    }


}
