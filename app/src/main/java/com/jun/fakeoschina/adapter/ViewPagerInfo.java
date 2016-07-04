package com.jun.fakeoschina.adapter;

/**
 * Created by jun on 16/6/30.
 */
public class ViewPagerInfo {
    public final String tag;
    public final Class<?> clss;
    public final String title;

    public ViewPagerInfo(String _title, String _tag, Class<?> _class) {
        title = _title;
        tag = _tag;
        clss = _class;
    }
}
