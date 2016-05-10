package com.example.edwin.smartnews.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by edwin on 2016/4/18.
 * <p/>
 * 使用SP来保存少量的数据
 */
public class SPUtils {

    /**
     * 保存String数据
     *
     * @param context
     * @param key
     * @param values
     */
    public static void putString(Context context, String key, String values) {
        //获取到sp  将文件名保存在常量类中
        SharedPreferences sp = context.getSharedPreferences(MyContains.FILE_NAME, Context.MODE_PRIVATE);
        //获取编辑器  保存数据   提交
        sp.edit().putString(key, values).commit();
    }

    /**
     * 获取String数据
     *
     * @param context
     * @param key
     * @param defValues
     * @return
     */
    public static String getString(Context context, String key, String defValues) {
        //获取到sp  将文件名保存在常量类中
        SharedPreferences sp = context.getSharedPreferences(MyContains.FILE_NAME, Context.MODE_PRIVATE);
        //获取数据
        return sp.getString(key, defValues);
    }

    /**
     * 保存boolean数据
     *
     * @param context
     * @param key
     * @param values
     */
    public static void putBoolean(Context context, String key, boolean values) {
        //获取到sp的对象
        SharedPreferences sp = context.getSharedPreferences(MyContains.FILE_NAME, Context.MODE_PRIVATE);
        //保存数据
        sp.edit().putBoolean(key, values).commit();
    }

    /**
     * 获取boolean数据
     *
     * @param context
     * @param key
     * @param defValues
     * @return
     */
    public static boolean getBoolean(Context context, String key, boolean defValues) {
        //获取到SP的对象
        SharedPreferences sp = context.getSharedPreferences(MyContains.FILE_NAME, Context.MODE_PRIVATE);
        //获取数据
        return sp.getBoolean(key, defValues);
    }
}

