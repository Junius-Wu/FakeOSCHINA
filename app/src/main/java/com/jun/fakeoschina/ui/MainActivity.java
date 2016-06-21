package com.jun.fakeoschina.ui;

import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TabHost;
import android.widget.TextView;

import com.jun.fakeoschina.AppConfig;
import com.jun.fakeoschina.AppContext;
import com.jun.fakeoschina.AppManager;
import com.jun.fakeoschina.R;
import com.jun.fakeoschina.ui.uidata.MainTab;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements TabHost.OnTabChangeListener, View.OnTouchListener {

    private CharSequence mTitle;//标题
    private DoubleClickExitHelper mDoubleClickExit;//双击返回退出
    private NavigationDrawerFragment mNavigationDrawerFragment;//左侧滑菜单
    @Bind(R.id.tabhost)
    public MyFragmentTabHost mTabHost;//底部菜单

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //透明虚拟按键
//        Window window = getWindow();
//        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        AppManager.getInstance().addActivity(this);
        initView();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 初始化 主界面 下的 各个View
     */
    private void initView() {
        //取得标题
        mTitle = getTitle();

        //设置双击关闭
        mDoubleClickExit = new DoubleClickExitHelper(this);

        //设置左侧滑菜单
        mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
                .findFragmentById(R.id.navigation_drawer);//获得左侧fragment
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);//获得drawerLayout
        mNavigationDrawerFragment.setUp(drawerLayout);//fragment 包含drawerLayout变量

        //设置下边菜单栏
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);//设置内容
        mTabHost.getTabWidget().setShowDividers(0);//隐藏分割线 sdk>10
        initTabs();
        mTabHost.setCurrentTab(0);
        mTabHost.setOnTabChangedListener(this);


    }

    /**
     * 初始化tabs
     */
    private void initTabs() {
        //从枚举中获取数据所有tab
        MainTab[] tabs = MainTab.values();
        final int size = tabs.length;
        for (int i = 0; i < size; i++) {
            MainTab thisTab = tabs[i];//得到这个自定义的数据
            TabHost.TabSpec tab = mTabHost.newTabSpec(getString(thisTab.getResName()));//真正要添加的tab

            //设置tab的indicator(内容) ->文字+图片 都放在textview中 可以自定义 从layout中获取
            View indicator = LayoutInflater.from(this).inflate(R.layout.tab_indicator, null);
            TextView textView = (TextView) indicator.findViewById(R.id.tab_indicator_textview);
            textView.setText(thisTab.getResName());
            Drawable drawable = getResources().getDrawable(thisTab.getResIcon());
            //必须设置图片尺寸 否则不显示-> 加上WithIntrinsicBounds 可以不设置
            //drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            textView.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
            tab.setIndicator(indicator);

            tab.setContent(new TabHost.TabContentFactory() {
                @Override
                public View createTabContent(String tag) {
                    return new View(MainActivity.this);//返回一个什么都没有的view 有什么用？？？
                }
            });

            mTabHost.addTab(tab, thisTab.getClz(), null);
            mTabHost.getTabWidget().getChildAt(i).setOnTouchListener(this);
        }

    }


    /**
     * 由下方菜单栏的currentTabTag， 找到现在显示的fragment
     * @return 现在显示的fragment（由下方的菜单栏进行切换）
     */
    private Fragment getCurrentFragment() {
        return getSupportFragmentManager()
                .findFragmentByTag(mTabHost.getCurrentTabTag());
    }
    /**
     * 触摸底部菜单时
     *
     * @param v
     * @param event
     * @return
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.i("tag", "onTouch");
        if (v.equals(mTabHost.getCurrentTabView())) {
            Log.i("tag", "CurrentTabView");
        }
        return false;
    }

    /**
     * 切换fragment时
     *
     * @param tabId
     */
    @Override
    public void onTabChanged(String tabId) {
        Log.i("tag", "onTabChanged:" + tabId);
    }

    /**
     * 如果是按下了返回键 调用DoubleClickExitHelper中的同名方法来确定是否是第二次按下 如果是就退出程序
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (AppContext.get(AppConfig.KEY_DOUBLE_CLICK_EXIT, true)) {
                return mDoubleClickExit.onKeyDown(keyCode, event);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /** 设备配置(横竖屏)改变时 */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        mNavigationDrawerFragment.onConfigurationChanged(newConfig);
        super.onConfigurationChanged(newConfig);
    }

    /**
     * 点击actonBar上的按钮就会调用这个方法
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //直接调用左侧滑菜单的这个方法 就能让点击DrawerToggle时打开关闭菜单
        mNavigationDrawerFragment.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

}
