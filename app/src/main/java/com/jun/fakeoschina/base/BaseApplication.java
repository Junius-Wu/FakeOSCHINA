package com.jun.fakeoschina.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jun.fakeoschina.R;

/**
 * Applocation基类
 * 1. set get preferences的key value
 * 2. showToast
 * Created by jun on 16/5/24.
 */
public class BaseApplication extends Application {
    private static String PREF_NAME = "creativelocker.pref";

    private static String lastToast = "";
    private static long lastToastTime;
    static Context _context;
    static Resources _resources;
    //是否是android 2.3或以上
    private static boolean sIsAtLeastGB;
    static {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            sIsAtLeastGB = true;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        _context = getApplicationContext();
        _resources = _context.getResources();
    }


    /**
     *  同一时间只能有一个线程访问这个APP-CONTEXT
     */
    public static synchronized BaseApplication context() {
        return (BaseApplication) _context;
    }


    /**
     * @return 资源？
     */
    public static Resources resources() {
        return _resources;
    }


    //set get preferences
    public static SharedPreferences getPreferences() {
        return context().getSharedPreferences(PREF_NAME,Context.MODE_MULTI_PROCESS);
    }

    public static SharedPreferences getPreferences(String prefName) {
        return context().getSharedPreferences(prefName,Context.MODE_MULTI_PROCESS);
    }

    public static void set(String key,int value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putInt(key,value);
        editor.apply();
    }

    public static void set(String key,boolean value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putBoolean(key,value);
        editor.apply();
    }

    public static void set(String key,String value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putString(key,value);
        editor.apply();
    }

    public static void set(String key,float value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putFloat(key,value);
        editor.apply();
    }

    public static void set(String key,long value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putLong(key,value);
        editor.apply();
    }

    public static boolean get(String key, boolean defValue) {
        return getPreferences().getBoolean(key, defValue);
    }

    public static String get(String key, String defValue) {
        return getPreferences().getString(key, defValue);
    }

    public static int get(String key, int defValue) {
        return getPreferences().getInt(key, defValue);
    }

    public static long get(String key, long defValue) {
        return getPreferences().getLong(key, defValue);
    }

    public static float get(String key, float defValue) {
        return getPreferences().getFloat(key, defValue);
    }

//    /**
//     * 取得字符串资源 直接getApplication.getString 就可以 这个方法没有必要
//     * @param id
//     * @return
//     */
//    public static String string(int id) {
//        return _resources.getString(id);
//    }

    /**
     * 以某种格式取得字符串资源
     * @param id
     * @param args
     * @return
     */
    public static String string(int id, Object... args) {
        return _resources.getString(id, args);
    }


    /**
     * 从preferences中读取设备尺寸 如果没有就设置成0 0
     * @return
     */
    public static int[] getDisplaySize() {
        return new int[]{getPreferences().getInt("screen_width", 0),
                getPreferences().getInt("screen_height", 0)};
    }


    /**
     * 从activity中获取设备尺寸？保存于preferences中
     * @param activity
     */
    public static void saveDisplaySize(Activity activity) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay()
                .getMetrics(displaymetrics);
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putInt("screen_width", displaymetrics.widthPixels);
        editor.putInt("screen_height", displaymetrics.heightPixels);
        editor.putFloat("density", displaymetrics.density);
        editor.commit();
    }

    //各种Toast
    public static void showToast(int message) {
        showToast(message, Toast.LENGTH_LONG, 0);
    }

    public static void showToast(String message) {
        showToast(message, Toast.LENGTH_LONG, 0, Gravity.BOTTOM);
    }

    public static void showToast(int message, int icon) {
        showToast(message, Toast.LENGTH_LONG, icon);
    }

    public static void showToast(String message, int icon) {
        showToast(message, Toast.LENGTH_LONG, icon, Gravity.BOTTOM);
    }

    public static void showToastShort(int message) {
        showToast(message, Toast.LENGTH_SHORT, 0);
    }

    public static void showToastShort(String message) {
        showToast(message, Toast.LENGTH_SHORT, 0, Gravity.BOTTOM);
    }

    public static void showToastShort(int message, Object... args) {
        showToast(message, Toast.LENGTH_SHORT, 0, Gravity.BOTTOM, args);
    }

    public static void showToast(int message, int duration, int icon) {
        showToast(message, duration, icon, Gravity.BOTTOM);
    }

    public static void showToast(int message, int duration, int icon,
                                 int gravity) {
        showToast(context().getString(message), duration, icon, gravity);
    }

    public static void showToast(int message, int duration, int icon,
                                 int gravity, Object... args) {
        showToast(context().getString(message, args), duration, icon, gravity);
    }

    public static void showToast(String message, int duration, int icon,
                                 int gravity) {
        if (message != null && !message.equalsIgnoreCase("")) {
            long time = System.currentTimeMillis();
            if (!message.equalsIgnoreCase(lastToast)
                    || Math.abs(time - lastToastTime) > 2000) {
                View view = LayoutInflater.from(context()).inflate(
                        R.layout.view_toast, null);
                ((TextView) view.findViewById(R.id.title_tv)).setText(message);
                if (icon != 0) {
                    ((ImageView) view.findViewById(R.id.icon_iv))
                            .setImageResource(icon);
                    ((ImageView) view.findViewById(R.id.icon_iv))
                            .setVisibility(View.VISIBLE);
                }
                Toast toast = new Toast(context());
                toast.setView(view);
                if (gravity == Gravity.CENTER) {
                    toast.setGravity(gravity, 0, 0);
                } else {
                    toast.setGravity(gravity, 0, 35);
                }

                toast.setDuration(duration);
                toast.show();
                lastToast = message;
                lastToastTime = System.currentTimeMillis();
            }
        }
    }


}
