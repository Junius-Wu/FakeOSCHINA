package com.jun.fakeoschina.ui.uidata;

import com.jun.fakeoschina.R;
import com.jun.fakeoschina.ui.NullFragment;
/**
 * 下菜单栏的显示数据，和点击时跳转的fragment类型
 */
public enum MainTab {

	NEWS(0, R.string.main_tab_name_news, R.drawable.alien,
			NullFragment.class),

	TWEET(1, R.string.main_tab_name_tweet, R.drawable.angel,
			NullFragment.class),

	QUICK(2, R.string.main_tab_name_quick, R.drawable.anger,
			null),

	EXPLORE(3, R.string.main_tab_name_explore, R.drawable.ant,
			NullFragment.class),

	ME(4, R.string.main_tab_name_my, R.drawable.bird,
			NullFragment.class);

	private int idx;
	private int resName;
	private int resIcon;
	private Class<?> clz;

	private MainTab(int idx, int resName, int resIcon, Class<?> clz) {
		this.idx = idx;
		this.resName = resName;
		this.resIcon = resIcon;
		this.clz = clz;
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

	public int getResIcon() {
		return resIcon;
	}

	public void setResIcon(int resIcon) {
		this.resIcon = resIcon;
	}

	public Class<?> getClz() {
		return clz;
	}

	public void setClz(Class<?> clz) {
		this.clz = clz;
	}
}
