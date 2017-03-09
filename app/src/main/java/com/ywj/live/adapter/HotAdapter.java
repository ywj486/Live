package com.ywj.live.adapter;

import android.content.Context;
import android.net.Uri;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ywj.live.R;
import com.ywj.live.adapter.base.BaseViewHolder;
import com.ywj.live.adapter.base.SimpleAdapter;
import com.ywj.live.entity.PlayInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/2/22 0022.
 */

public class HotAdapter extends SimpleAdapter<PlayInfo> {

    public HotAdapter(Context context, List<PlayInfo> datas) {
        super(context, R.layout.item_live_hot, datas);
    }

    @Override
    protected void convert(BaseViewHolder holder, final PlayInfo item) {
        SimpleDraweeView draweeView = (SimpleDraweeView) holder.getView(R.id.hot_header_img_sdv);
        draweeView.setImageURI(Uri.parse(item.getHeadIcon()));
        draweeView = (SimpleDraweeView) holder.getView(R.id.hot_face);
        draweeView.setImageURI(Uri.parse(item.getInformationImage()));
        holder.getTextView(R.id.hot_name_tv).setText(item.getName());
        holder.getTextView(R.id.hot_location_tv).setText(item.getPlace());
        holder.getTextView(R.id.hot_state).setText(item.getStatus());

    }
}
