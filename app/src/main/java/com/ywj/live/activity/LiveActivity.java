package com.ywj.live.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.ywj.live.R;
import com.ywj.live.activity.base.BaseActivity;
import com.ywj.live.fragment.Fragment_One;
import com.ywj.live.fragment.Fragment_Two;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yj on 2017/3/12.
 */

public class LiveActivity extends BaseActivity {


    @BindView(R.id.live_mViewPager)
    ViewPager mViewPager;
    FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragmentList = new ArrayList<>();//页卡视图集合


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.live_activity);
        ButterKnife.bind(this);
        initViewPager();
        Intent intent = getIntent();
        int page = intent.getIntExtra("viewPager", 0);
        mViewPager.setCurrentItem(page);//定位到某个界面

    }


    private void initViewPager() {
        Fragment_One view1 = new Fragment_One();
        Fragment_Two view2 = new Fragment_Two();

        //添加页卡视图
        mFragmentList.add(view1);
        mFragmentList.add(view2);

        mAdapter = new FragmentPagerAdapter(this.getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return mFragmentList.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

        };
        mViewPager.setAdapter(mAdapter);//给ViewPager设置适配器
    }


}
