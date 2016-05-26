package com.jun.fakeoschina.ui;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.jun.fakeoschina.AppConfig;
import com.jun.fakeoschina.AppContext;
import com.jun.fakeoschina.AppManager;
import com.jun.fakeoschina.R;

public class MainActivity extends AppCompatActivity {

    private DoubleClickExitHelper mDoubleClickExit;
    private NavigationDrawerFragment mNavigationDrawerFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppManager.getInstance().addActivity(this);
        initView();
    }

    private void initView() {
        mDoubleClickExit = new DoubleClickExitHelper(this);
        mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
                .findFragmentById(R.id.navigation_drawer);//获得左侧fragment

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);//获得drawerLayout
        mNavigationDrawerFragment.setUp(drawerLayout);//fragment 包含drawerLayout变量

    }


    /**
     * 如果是按下了返回键 调用DoubleClickExitHelper中的同名方法来确定是否是第二次按下 如果是就退出程序
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (AppContext.get(AppConfig.KEY_DOUBLE_CLICK_EXIT,true)) {
                return mDoubleClickExit.onKeyDown(keyCode,event);
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
