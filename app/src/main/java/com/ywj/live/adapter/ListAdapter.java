package com.ywj.live.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.ywj.live.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/13 0013.
 */

public class ListAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mData;

    public ListAdapter(Context context, List<String> list) {
        mData  = new ArrayList<>();
        this.mContext = context;
        this.mData = list;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.fragment_live_list_item, null);
            viewHolder.tv = (TextView) convertView.findViewById(R.id.pure_page_live_list_item_tv);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv.setText(mData.get(position));
        return convertView;
    }

    class ViewHolder{
        TextView tv;
    }
}
