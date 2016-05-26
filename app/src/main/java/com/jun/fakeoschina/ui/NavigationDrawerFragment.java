package com.jun.fakeoschina.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jun.fakeoschina.base.BaseFragment;

import org.w3c.dom.Text;

/**
 * 侧滑菜单界面 管理fragment
 * Created by jun on 16/5/26.
 */
public class NavigationDrawerFragment extends BaseFragment implements View.OnClickListener {

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
        TextView textView=new TextView(getActivity());
        textView.setText("xiaowujun");
        //设置leftView
        leftView = textView;
        return textView;

    }


    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

    }

    public void setUp(DrawerLayout drawerLayout) {
        this.mDrawerLayout = drawerLayout;
    }

    public void open() {
        mDrawerLayout.openDrawer(leftView);
    }
}
