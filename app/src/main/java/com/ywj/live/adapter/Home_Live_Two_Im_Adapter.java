package com.ywj.live.adapter;

import android.content.Context;

import com.ywj.live.R;
import com.ywj.live.adapter.base.BaseViewHolder;
import com.ywj.live.adapter.base.SimpleAdapter;
import com.ywj.live.entity.TempBean;

import java.util.List;


/**
 * Created by yj on 2017/3/9.
 */

public class Home_Live_Two_Im_Adapter extends SimpleAdapter<TempBean> {
    private Context mContext;


    public Home_Live_Two_Im_Adapter(Context context, List<TempBean> datas) {
        super(context,R.layout.home_live_two_rl_im_item, datas);
        this.mContext=context;
    }


    @Override
    protected void convert(BaseViewHolder holder, TempBean item) {
        holder.getTextView(R.id.home_tow_im_mes).setText(item.getMessage());
    }
}
