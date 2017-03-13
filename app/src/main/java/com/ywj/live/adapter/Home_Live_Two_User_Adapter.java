package com.ywj.live.adapter;

import android.content.Context;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ywj.live.R;
import com.ywj.live.adapter.base.BaseViewHolder;
import com.ywj.live.adapter.base.SimpleAdapter;
import com.ywj.live.entity.TempBean;

import java.util.List;

/**
 * Created by yj on 2017/3/9.
 */

public class Home_Live_Two_User_Adapter extends SimpleAdapter<TempBean> {
    private Context mContext;


    public Home_Live_Two_User_Adapter(Context context, List<TempBean> datas) {
        super(context, R.layout.home_live_two_rl_user_item, datas);
        this.mContext=context;
    }


    @Override
    protected void convert(BaseViewHolder holder, TempBean item) {
        SimpleDraweeView head = (SimpleDraweeView) holder.getView(R.id.homw_live_two_rl_uhead);
        head.setImageURI(item.getUrl());
    }
}
