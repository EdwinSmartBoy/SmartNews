package com.example.edwin.smartnews.controller.tabs;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by edwin on 2016/4/19.
 * <p/>
 * 对ContentFragment的ViewPager的智慧服务的控制器
 * <p/>
 * 当前类需要实现的功能
 * <p/>
 * 1.提供已经绑定过的视图
 * <p/>
 * 2.接收和加载数据
 * <p/>
 * 3.进行数据和视图的绑定
 */
public class SmartServiceTabController extends BaseTabController {

    //在调用构造方法时就调用了initView()方法  则无需在再次调用该方法
    public SmartServiceTabController(Context context) {
        super(context);
    }

    /**
     * 功能:初始化当前SmartServiceTabController实例化视图
     *
     * @param context
     * @return
     *//*
    public View initView(Context context) {

        TextView tv = new TextView(context);
        tv.setText("智慧服务");
        tv.setTextColor(Color.BLACK);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(30);

        return tv;
    }*/

    /**
     * 初始化标题栏显示的视图
     * <p/>
     * 1.设置标题栏文字
     * 2.设置菜单按钮是否显示
     */
    @Override
    protected void initTitle() {

        mTv_title.setText("智慧服务");

    }

    /**
     * 初始化FrameLayout容器需要显示的视图
     *
     * @return
     * @param context
     */
    @Override
    protected View initContentView(Context context) {
        TextView tv = new TextView(context);
        tv.setText("智慧服务");
        tv.setTextColor(Color.BLACK);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(30);

        return tv;
    }
}
