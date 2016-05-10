package com.example.edwin.smartnews.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by edwin on 2016/4/20.
 * <p/>
 * toast的工具类
 */
public class ToastUtils {

    /**
     * 用来输出toast的显示
     *
     * @param context
     * @param mes
     */
    public static void showShort(Context context, String mes) {

        Toast.makeText(context, mes, Toast.LENGTH_SHORT).show();
    }

    /**
     * 用来输出toast的显示
     *
     * @param context
     * @param mes
     */
    public static void showLong(Context context, String mes) {

        Toast.makeText(context, mes, Toast.LENGTH_LONG).show();
    }
}
