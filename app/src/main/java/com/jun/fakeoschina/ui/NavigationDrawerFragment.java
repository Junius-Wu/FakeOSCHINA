package com.jun.fakeoschina.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
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

}
