package com.ywj.live.adapter.base;


import android.content.Context;


import com.ywj.live.adapter.base.BaseAdapter;
import com.ywj.live.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/2/22 0022.
 */

public abstract  class SimpleAdapter<T> extends BaseAdapter<T,BaseViewHolder> {

    public SimpleAdapter(Context context, int layoutId , List<T> datas){
        super(datas,context,layoutId);
    }
}