package com.jun.fakeoschina.viewpagerfragment;

import android.support.v4.app.Fragment;

import com.jun.fakeoschina.AppContext;
import com.jun.fakeoschina.adapter.ViewPagerFragmentAdapter;
import com.jun.fakeoschina.base.BaseViewPagerFragment;
import com.jun.fakeoschina.ui.NullFragment;
import com.jun.fakeoschina.ui.NullFragment2;
import com.jun.fakeoschina.ui.uidata.NewsSlidingTab;

/**
 * 第一个tab 新闻 fragment
 * Created by jun on 16/6/30.
 */
public class NewsViewPagerFragment extends BaseViewPagerFragment {
    @Override
    protected void onSetupTabAdapter(ViewPagerFragmentAdapter adapter) {//设置新闻tab 上面的导航条的内容

        NewsSlidingTab[] tabs = NewsSlidingTab.values();

        for (int i = 0; i < tabs.length; i++) {
            NewsSlidingTab tab = tabs[i];
            adapter.addTab(getString(tab.getResName()), tab.getTag(), tab.getClz());
        }

    }

    @Override
    protected void setScreenLimit() {//设置左右fragment缓存数量（左3 右3）设置为（页面数-1）
        mViewPager.setOffscreenPageLimit(3);
    }


}
