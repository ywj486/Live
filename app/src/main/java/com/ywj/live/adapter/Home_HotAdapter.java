package com.ywj.live.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.ywj.live.R;
import com.ywj.live.adapter.base.BaseViewHolder;
import com.ywj.live.adapter.base.SimpleAdapter;
import com.ywj.live.entity.Living;

import java.util.List;

/**
 * Created by yj on 2017/3/9.
 */

public class Home_HotAdapter extends SimpleAdapter<Living.ResultBean.ListBean> {
    private Context mContext;


    public Home_HotAdapter(Context context, List<Living.ResultBean.ListBean> datas) {
        super(context, R.layout.item_live_hot, datas);
        this.mContext=context;
    }


    @Override
    protected void convert(BaseViewHolder holder, Living.ResultBean.ListBean item) {
        SimpleDraweeView view = (SimpleDraweeView) holder.getView(R.id.hot_header_img_sdv);


        view.setImageURI(item.getUser().getUser_data().getAvatar());

        holder.getTextView(R.id.hot_name_tv).setText(item.getUser().getUser_data().getUser_name());
        holder.getTextView(R.id.hot_location_tv).setText(item.getData().getLive_name());
        ImageView img = holder.getImageView(R.id.hot_face);
        holder.getTextView(R.id.hot_state).setText(((item.getData().getStatus()==0)?"直播中":"已结束"));

        //跳过内存缓存
        Glide.with( mContext ).load(item.getData().getPic()).skipMemoryCache(true).into(img);
    }
}
