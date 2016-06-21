package com.jun.fakeoschina;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.jun.fakeoschina.ui.MainActivity;

/**
 * 进入app的界面 ，继承自activity没有actionBar
 */
public class AppStart extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //防止产生2个AppStart
        Activity aty = AppManager.getInstance().getActivity(AppStart.class);
        if (aty != null && !aty.isFinishing()) { //说明这个aty已经存在并且不是正在关闭的状态
            finish();
        }

        View view = View.inflate(this, R.layout.app_start, null);
        setContentView(view);
        AlphaAnimation aa = new AlphaAnimation(0.5f, 1.0f);
        aa.setDuration(800);
        aa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {//动画结束后跳转到主页面
                reDirectTo();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(aa);


    }

    private void reDirectTo() {
        //这里可以上报BUG（未完成）

        //跳转到主界面
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
