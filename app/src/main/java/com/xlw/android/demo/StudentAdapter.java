package com.xlw.android.demo;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by wei on 2016/8/5.
 */
public class StudentAdapter<T> extends BaseAdapter {

    private Context mContext;
    private List<T> list;
    private int layoutId; //通用的，不知道布局
    private int variableId; //变量的id

    public StudentAdapter(Context mContext, List<T> list, int layoutId, int variableId) {
        this.mContext = mContext;
        this.list = list;
        this.layoutId = layoutId;
        this.variableId = variableId;
    }

    @Override
    public int getCount() {
        if(list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewDataBinding binding;

        if(convertView == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), layoutId, null, false);
        }else {
            binding =  DataBindingUtil.getBinding(convertView);
        }

        binding.setVariable(variableId, list.get(position));

        return binding.getRoot();
    }
}
