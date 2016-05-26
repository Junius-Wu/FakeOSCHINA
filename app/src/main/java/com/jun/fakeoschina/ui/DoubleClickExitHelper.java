package com.jun.fakeoschina.ui;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.widget.Toast;

import com.jun.fakeoschina.AppContext;
import com.jun.fakeoschina.AppManager;

/**
 * 双击返回键关闭程序
 * Created by jun on 16/5/23.
 */
public class DoubleClickExitHelper {

    private boolean isOnKeyBacking;//是否处于点击返回键2秒内的状态
    private Activity mActivity;//MainActivity
    private Handler mHandler;//主线程
    private Toast mBackToast;//显示的toast “再次返回关闭程序”

    public DoubleClickExitHelper(Activity activity) {
        mActivity = activity;
        mHandler = new Handler(Looper.getMainLooper());//获取主线程 用作显示toast
    }
    /**
     * Activity onKeyDown事件  在MainActivity中有一个同名的方法（重写了Acticity中的onKeyDown），调用了此方法
     * */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != KeyEvent.KEYCODE_BACK) {
            return false;
        }

        if (!isOnKeyBacking) { //第一次按下
            isOnKeyBacking = true;//更改状态为已按下

            //显示toast
            if (mBackToast == null) {
                mBackToast = Toast.makeText(mActivity,"再次返回关闭程序",2000);
            }
            mBackToast.show();

            mHandler.postDelayed(onBackTimeRunnable,2000);//2秒后执行这个runable 把isOnKeyBacking改回false

        } else { //2秒内第二次按下
            mHandler.removeCallbacks(onBackTimeRunnable);//取消2秒后需要执行的取消toast
            if (mBackToast != null) {//马上取消正在显示的toast
                mBackToast.cancel();
            }
            AppManager.getInstance().AppExit(mActivity);//退出程序
        }

        return true;
    }

    private Runnable onBackTimeRunnable = new Runnable() {

        @Override
        public void run() {
            isOnKeyBacking = false;
            if(mBackToast != null){
                mBackToast.cancel();
            }
        }
    };
}
