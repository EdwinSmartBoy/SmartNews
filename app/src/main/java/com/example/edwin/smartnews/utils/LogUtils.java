package com.example.edwin.smartnews.utils;

import android.util.Log;

/**
 * Created by edwin on 2016/4/18.
 * <p/>
 * 日志工具类  方便同意管理日志输出
 */
public class LogUtils {

    //设置标记位
    private static boolean print = true;

    /**
     * 日志输出工具
     *
     * @param TAG
     * @param mes
     */
    public static void printLog(String TAG, String mes) {
        if (print) {
            Log.d(TAG, mes);
        }
    }
}
