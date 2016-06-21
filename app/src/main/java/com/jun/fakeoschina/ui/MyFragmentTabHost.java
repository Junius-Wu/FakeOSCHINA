package com.jun.fakeoschina.ui;

import android.content.Context;
import android.support.v4.app.FragmentTabHost;
import android.util.AttributeSet;

/**
 * 自定义TabHost继承自系统的TabHost
 * Created by jun on 16/6/2.
 */
public class MyFragmentTabHost extends FragmentTabHost {

    private String mCurrentId;


    public MyFragmentTabHost(Context context) {
        super(context);
    }

    public MyFragmentTabHost(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onTabChanged(String tabId) {
        super.onTabChanged(tabId);
    }
}
