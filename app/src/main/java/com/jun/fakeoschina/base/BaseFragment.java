package com.jun.fakeoschina.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jun.fakeoschina.AppContext;

import java.util.zip.Inflater;


/**
 * fragment 基类
 * Created by jun on 16/5/26.
 */
public abstract class BaseFragment extends Fragment{

    private LayoutInflater mInflater;

    //子类必须实现这两个抽象方法
    public abstract void initView(View view);
    public abstract void initData();

    /**
     * @return 直接获取activity的application
     */
    public AppContext getApplication() {
        return (AppContext) getActivity().getApplication();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mInflater = inflater;//取得inflater
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * @param resId 取得这个fragment中对应resId的view
     * @return
     */
    protected View inflateView(int resId) {
        return mInflater.inflate(resId,null);
    }

    //show and hide dialog



}
