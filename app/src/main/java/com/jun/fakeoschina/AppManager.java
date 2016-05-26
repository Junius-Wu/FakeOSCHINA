package com.jun.fakeoschina;

import android.app.Activity;
import android.content.Context;

import java.util.Stack;

/**
 * Activity 堆栈式管理器
 * Created by jun on 16/5/23.
 */
public class AppManager {

    public static AppManager instance;
    public static Stack<Activity> activityStack;

    /**
     * Appmanager 单例
     */
    public static AppManager getInstance() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * @param activity 添加到栈中
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }


    /**
     * @return 当前的activity
     */
    public Activity currentActivity() {
        return activityStack.lastElement();
    }

    /**
     * @param cls
     * @return 该class的activity（如果有多个 只返回一个最前面的）
     */
    public Activity getActivity(Class<?> cls) {
        if (activityStack != null) {
            for (Activity activity : activityStack) {
                if (activity.getClass().equals(cls)) {
                    return activity;
                }
            }
        }
        return null;
    }


    /**
     * 结束当前的activity（栈顶）
     */
    public void finishActivity() {
        finishActivity(activityStack.lastElement());
    }


    /**
     * @param activity 结束传入的这个activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * @param cls 结束该class的activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                break;
            }
        }
    }

    /**
     * 结束所有的activity
     */
    public void finishAllActivity() {
        for (Activity activity : activityStack) {
            if (activity != null) {
                activity.finish();
            }
        }
        activityStack.clear();

    }

    /**
     * 结束当前App
     * @param context
     */
    public void AppExit(Context context) {
        try {
            //必须结束所有的activity （这个方法仅仅结束了加入到stack中的activity 请确保所有运行中的activity已经入栈）
            finishAllActivity();
            //杀死当前的进程
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Exception e) {

        }
    }

}
