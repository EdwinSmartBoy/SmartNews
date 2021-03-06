package com.example.edwin.smartnews.controller.tabs;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by edwin on 2016/4/19.
 * <p/>
 * 对ContentFragment的ViewPager的设置的控制器
 * <p/>
 * 当前类需要实现的功能
 * <p/>
 * 1.提供已经绑定过的视图
 * <p/>
 * 2.接收和加载数据
 * <p/>
 * 3.进行数据和视图的绑定
 */
public class SettingTabController extends BaseTabController {

    //在调用构造方法时就调用了initView()方法  则无需在再次调用该方法
    public SettingTabController(Context context) {
        super(context);
    }

    /**
     * 功能:初始化当前SettingTabController实例化视图
     *
     * @param context
     * @return
     */
    /*public View initView(Context context) {

        TextView tv = new TextView(context);
        tv.setText("设置中心");
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

        //设置界面的标题文字
        mTv_title.setText("设置中心");

        //设置界面的的菜单按钮额隐藏
        mBtn_menu.setVisibility(View.GONE);

    }

    /**
     * 初始化FrameLayout容器需要显示的视图
     *
     * @param context
     * @return
     */
    @Override
    protected View initContentView(Context context) {

        /*虽然当前界面可以获取到MainActivity的上下文,但是无法通过以下方法来设置不让当前界面的侧滑菜单拖出
        ((MainActivity) mContext).getSlidingMenu().setTouchModeBehind(SlidingMenu.TOUCHMODE_NONE);*/

        //当前设置为静态文本,用以测试
        TextView tv = new TextView(context);
        tv.setText("设置中心");
        tv.setTextColor(Color.BLACK);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(30);

        return tv;
    }

}
