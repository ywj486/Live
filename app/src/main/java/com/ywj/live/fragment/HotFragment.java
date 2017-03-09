package com.ywj.live.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cjj.MaterialRefreshLayout;
import com.google.gson.reflect.TypeToken;
import com.ywj.live.R;
import com.ywj.live.adapter.FavoriteAdapter;
import com.ywj.live.adapter.HotAdapter;
import com.ywj.live.adapter.base.BaseAdapter;
import com.ywj.live.adapter.decoration.DividerItemDecoration;
import com.ywj.live.entity.PageResult;
import com.ywj.live.entity.PlayInfo;
import com.ywj.live.http.Contants;
import com.ywj.live.util.PageUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xiaoyuan on 17/3/8.
 */

public class HotFragment extends BaseFragment {


    @BindView(R.id.hot_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.mrefresh)
    MaterialRefreshLayout mRefreshLayout;

    private HotAdapter mAdapter;

    public static HotFragment newInstance() {
        HotFragment fragment = new HotFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_hot, null);
        ButterKnife.bind(this, view);

        PageUtils pageUtils = PageUtils.newBuilder()
                .setUrl(Contants.API.HOT)
                .setLoadMore(true)
                .setOnPageListener(new PageUtils.OnPageListener() {
                    @Override
                    public void load(List datas, int totalPage, int totalCount) {
                        mAdapter = new HotAdapter(getContext(), datas);
                        mAdapter.setmOnItemClickListener(new BaseAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                //   Toast.makeText(getContext(), "点击了：" + position, Toast.LENGTH_SHORT).show();
                                PlayInfo news = mAdapter.getItem(position);
                            }
                        });
                        mRecyclerView.setAdapter(mAdapter);
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                                DividerItemDecoration.VERTICAL_LIST));
                    }

                    @Override
                    public void refresh(List datas, int totalPage, int totalCount) {
                        mAdapter.clearData();
                        mAdapter.addData(datas);
                        mRecyclerView.scrollToPosition(0);
                    }

                    @Override
                    public void loadMore(List datas, int totalPage, int totalCount) {
                        mAdapter.addData(mAdapter.getItemCount(), datas);
                        mRecyclerView.scrollToPosition(mAdapter.getItemCount());
                    }
                })
                .setPageSize(20)
                .setRefreshLayout(mRefreshLayout)
                .build(getContext(), new TypeToken<PageResult<PlayInfo>>() {
                }.getType());
        pageUtils.request();
        return view;
    }
}
