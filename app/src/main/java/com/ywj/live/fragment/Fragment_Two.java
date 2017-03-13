package com.ywj.live.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ywj.live.R;
import com.ywj.live.adapter.Home_Live_Two_Im_Adapter;
import com.ywj.live.adapter.Home_Live_Two_User_Adapter;
import com.ywj.live.adapter.decoration.DividerItemDecoration;
import com.ywj.live.entity.TempBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yj on 2017/3/12.
 */

public class Fragment_Two extends Fragment {

    /**
     * 临时数据
     */
    private String hard_url = "https://tower.im/assets/default_avatars/jokul.jpg";

    @BindView(R.id.live_tow_im)
    ImageView live_tow_im_img;

    @BindView(R.id.live_two_love)
    ImageView live_two_love;

    @BindView(R.id.live_tow_gift)
    ImageView live_tow_gift;

    @BindView(R.id.live_two_head)
    SimpleDraweeView live_two_head;

    @BindView(R.id.live_tow_rl)
    RecyclerView live_tow_rl;

    @BindView(R.id.live_tow_im_rl)
    RecyclerView live_tow_im_rl;

    private Home_Live_Two_User_Adapter mTwo_user_adapter;
    private Home_Live_Two_Im_Adapter mTwo_im_adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.two_fragment, container, false);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        live_two_head.setImageURI(hard_url);
        mTwo_user_adapter = new Home_Live_Two_User_Adapter(getContext(), getHeadData());

        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        live_tow_rl.setLayoutManager(linearLayoutManager);
        live_tow_rl.setItemAnimator(new DefaultItemAnimator());
        live_tow_rl.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.HORIZONTAL_LIST));
        live_tow_rl.setAdapter(mTwo_user_adapter);

        mTwo_im_adapter = new Home_Live_Two_Im_Adapter(getContext(), getHeadData());
        live_tow_im_rl.setLayoutManager(new LinearLayoutManager(getContext()));
        live_tow_im_rl.setItemAnimator(new DefaultItemAnimator());
        live_tow_im_rl.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.HORIZONTAL_LIST));
        live_tow_im_rl.setAdapter(mTwo_im_adapter);

    }

    private List<TempBean> getHeadData() {

        List<TempBean> list = new ArrayList<>();
        TempBean tempBean = new TempBean();
        for (int i = 0; i < 100; i++) {

            tempBean.setUrl("http://bj.c.tedu.cn/zt/tg/images/cyy_03.jpg");
            tempBean.setMessage("We are family ");
            list.add(i, tempBean);
        }
        return list;

    }

}
