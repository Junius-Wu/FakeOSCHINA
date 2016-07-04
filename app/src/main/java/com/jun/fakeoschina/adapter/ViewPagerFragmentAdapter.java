package com.jun.fakeoschina.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jun.fakeoschina.R;
import com.jun.fakeoschina.widget.PagerSlidingTabStrip;

import java.util.ArrayList;

/**
 * 页面fragment adapter 设置滑动选项卡的布局
 * Created by jun on 16/6/30.
 */
public class ViewPagerFragmentAdapter extends FragmentStatePagerAdapter {

    Context mContext;
    PagerSlidingTabStrip mPagerStrip;
    ViewPager mViewPager;
    ArrayList<ViewPagerInfo> pagerInfoList = new ArrayList<ViewPagerInfo>();

    public ViewPagerFragmentAdapter(FragmentManager fm, PagerSlidingTabStrip pagerStrip, ViewPager viewPager) {
        super(fm);
        mContext = viewPager.getContext();
        mPagerStrip = pagerStrip;
        mViewPager = viewPager;
        //设置viewPager的adapter
        mViewPager.setAdapter(this);
        mPagerStrip.setViewPager(mViewPager);
    }

    @Override
    public Fragment getItem(int position) {//这里返回要显示的fragment
        ViewPagerInfo info = pagerInfoList.get(position);
        return Fragment.instantiate(mContext, info.clss.getName(), null);
    }

    @Override
    public int getCount() {
        return pagerInfoList.size();
    }

    public void addTab(String title, String tag, Class<?> clss) {
        // 加入tab title
        //从布局文件中获取
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.base_viewpage_fragment_tab_item, null, false);
        TextView textView = (TextView) view.findViewById(R.id.tab_title);
        textView.setText(title);

        mPagerStrip.addTab(view);
        pagerInfoList.add(new ViewPagerInfo(title, tag, clss));
        //刷新数据
        notifyDataSetChanged();
    }


}
