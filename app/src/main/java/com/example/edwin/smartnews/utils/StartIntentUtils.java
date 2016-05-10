package com.example.edwin.smartnews.utils;

import android.content.Context;
import android.content.Intent;

/**
 * Created by edwin on 2016/4/19.
 * <p/>
 * 显示意图跳转工具类
 */
public class StartIntentUtils {
    //创建静态方法
    public static void start(Context context, Class clazz) {
        //创建显示意图
        Intent intent = new Intent(context, clazz);
        //开启界面
        context.startActivity(intent);
    }
}
