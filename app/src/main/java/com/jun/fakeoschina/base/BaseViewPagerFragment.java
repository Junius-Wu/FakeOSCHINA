package com.jun.fakeoschina.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jun.fakeoschina.R;
import com.jun.fakeoschina.adapter.ViewPagerFragmentAdapter;
import com.jun.fakeoschina.widget.PagerSlidingTabStrip;

/**
 * 带有导航条的fragment基类
 * Created by jun on 16/6/28.
 */
public abstract class BaseViewPagerFragment extends BaseFragment {
    //上面选项卡的 控件
    protected PagerSlidingTabStrip mTabStrip;
    //控制左右切换page的 viewGroup
    protected ViewPager mViewPager;
    //这个page的显示的fragemt的 内容
    protected ViewPagerFragmentAdapter mTabsAdapter;
    //加载错误时的显示布局
    //protected EmptyLayout mErrorLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.base_viewpager_fragment, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // 初始化导航栏控件
        mTabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.pager_tabstrip);
        //初始化viewPager
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);

        //初始化viewpagerAdapter
        mTabsAdapter = new ViewPagerFragmentAdapter(getChildFragmentManager(), mTabStrip, mViewPager);
        //由子类设置
        onSetupTabAdapter(mTabsAdapter);
        //设置保留页面数量
        setScreenLimit();
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {

    }

    protected abstract void onSetupTabAdapter(ViewPagerFragmentAdapter adapter);//由子类来设置它的adapter
    protected abstract void setScreenLimit();
}
