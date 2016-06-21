package com.jun.fakeoschina.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jun.fakeoschina.AppStart;
import com.jun.fakeoschina.R;
import com.jun.fakeoschina.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 侧滑菜单界面 fragment
 * Created by jun on 16/5/26.
 */
public class NavigationDrawerFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.menu_item_quests)
    TextView menuItemQuests;
    @Bind(R.id.menu_item_opensoft)
    TextView menuItemOpensoft;
    @Bind(R.id.menu_item_blog)
    TextView menuItemBlog;
    @Bind(R.id.menu_item_gitapp)
    TextView menuItemGitapp;
    @Bind(R.id.menu_item_setting)
    LinearLayout menuItemSetting;
    @Bind(R.id.menu_item_theme)
    LinearLayout menuItemTheme;
    private DrawerLayout mDrawerLayout;
    private View leftView;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        leftView = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        leftView.setOnClickListener(this);
        ButterKnife.bind(this, leftView);
        return leftView;

    }


    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {

    }


    public void setUp(DrawerLayout drawerLayout) {
        this.mDrawerLayout = drawerLayout;

        //设置actionBar上的开关
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        //构造开关
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout,
                null, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {//回调函数？
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
            }
        };

        // 拉出左侧菜单栏时 同步actionbar左边的 箭头<->三横 改变
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();//1.同步状态 只有同步了 actionBar左边的小箭头图标才会发生改变
            }
        });
        mDrawerLayout.setDrawerListener(mDrawerToggle); //2.设置监听  1 2 两步必不可少

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /** 设备配置(横竖屏)改变时 */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        mDrawerToggle.onConfigurationChanged(newConfig);
        super.onConfigurationChanged(newConfig);
    }

    public void open() {
        mDrawerLayout.openDrawer(leftView);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.menu_item_quests, R.id.menu_item_opensoft, R.id.menu_item_blog, R.id.menu_item_gitapp, R.id.menu_item_setting, R.id.menu_item_theme})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.menu_item_quests:
                startActivity(new Intent(getActivity(), AppStart.class));
                break;
            case R.id.menu_item_opensoft:
                break;
            case R.id.menu_item_blog:
                break;
            case R.id.menu_item_gitapp:
                break;
            case R.id.menu_item_setting:
                break;
            case R.id.menu_item_theme:
                break;
        }
    }

    public boolean isDrawerOpen() {
        return mDrawerLayout != null
                && mDrawerLayout.isDrawerOpen(leftView);
    }
    private ActionBar getActionBar() {
        return ((AppCompatActivity) getActivity()).getSupportActionBar();
    }
}
