package com.jun.fakeoschina.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jun.fakeoschina.AppContext;
import com.jun.fakeoschina.R;

/**
 * Created by jun on 16/6/2.
 */
public class NullFragment extends Fragment {
    public static int count = 0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView tv = new TextView(getActivity());
        tv.setText("这是一个空的fragment support V4" + "count:" + count++ );
        AppContext.showToast("create");
        return tv;
    }
}
