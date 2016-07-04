package com.jun.fakeoschina.ui.uidata;

import com.jun.fakeoschina.R;
import com.jun.fakeoschina.ui.NullFragment;

/**
 * Created by jun on 16/7/4.
 */
public enum NewsSlidingTab {

    NEWS(0, R.string.news_sliding_tab_name_news, "news" ,
            NullFragment.class),
    NEWS_WEEK(1, R.string.news_sliding_tab_name_news_week, "news_week" ,
            NullFragment.class),
    BLOG(2, R.string.news_sliding_tab_name_blog, "blog" ,
            NullFragment.class),
    RECOMMEND_BLOG(3, R.string.news_sliding_tab_name_recommend_blog, "recommend_blog" ,
            NullFragment.class);


    private int idx;
    private int resName;
    private String tag;
    private Class<?> clz;


    NewsSlidingTab(int idx, int resName, String tag, Class<?> clz) {
        this.idx = idx;
        this.resName = resName;
        this.tag = tag;
        this.clz = clz;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getResName() {
        return resName;
    }

    public void setResName(int resName) {
        this.resName = resName;
    }

    public Class<?> getClz() {
        return clz;
    }

    public void setClz(Class<?> clz) {
        this.clz = clz;
    }
}
