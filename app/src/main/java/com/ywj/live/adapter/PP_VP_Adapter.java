package com.ywj.live.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.List;

/**
 * Created by Administrator on 2017/3/13 0013.
 */

public class PP_VP_Adapter extends PagerAdapter {
    private Context context;
    private List<GridView> list;
    private List<List> sublist;


    public PP_VP_Adapter(Context context, List<GridView> list, List sublist) {
        this.context = context;
        this.list = list;
        this.sublist=sublist;
    }

    @Override
    public int getCount() {
        Log.e("TAG", "list============="+list);
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView(list.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        GridView gridView = (GridView) list.get(position);

        gridView.setAdapter(new Gv_Adapter(context,sublist.get(position)));
        gridView.setNumColumns(3);

        container.addView(list.get(position));
        return list.get(position);
    }
}
