package com.jun.fakeoschina.ui;

import android.support.v4.widget.DrawerLayout;
import android.view.View;

import com.jun.fakeoschina.base.BaseFragment;

/**
 * 侧滑菜单界面 管理fragment
 * Created by jun on 16/5/26.
 */
public class NavigationDrawerFragment extends BaseFragment implements View.OnClickListener {

    private DrawerLayout mDrawerLayout;
    private View mainView;

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

    }

    public void setUp(View mainView, DrawerLayout drawerLayout) {
        this.mainView = mainView;
        this.mDrawerLayout = drawerLayout;
    }

    public void open() {
        mDrawerLayout.openDrawer(mainView);
    }
}
