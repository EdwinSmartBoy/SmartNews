package com.example.edwin.smartnews.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.edwin.smartnews.R;
import com.example.edwin.smartnews.fragment.ContentFragment;
import com.example.edwin.smartnews.fragment.LeftMenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

/**
 * Created by edwin on 2016/4/18.
 * <p/>
 * 主界面
 */
public class MainActivity extends SlidingFragmentActivity {

    //两个Fragment的标记
    private static final String FRAGMENT_CONTENT = "fragment_content";
    private static final String FRAGMENT_LEFT_MENU = "fragment_left_menu";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        //初始化SlidingMenu的相关属性
        initSlidingMenu();
        //给内容区域和左侧菜单添加内容
        initFragment();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        //设置内容区域的布局
        setContentView(R.layout.activity_main_content);
        //设置侧滑菜单的的布局
        setBehindContentView(R.layout.activity_main_leftmenu);
    }

    /**
     * 设置SlidingMenu相关的属性
     */
    private void initSlidingMenu() {
        //首先要获取到SlidingMenu的对象
        SlidingMenu menu = getSlidingMenu();

        //设置SlidingMenu的属性
        //menu.setBehindWidth(100); //直接规定菜单的宽度  宽度相对于左侧
        menu.setBehindOffset(150); //设置侧滑菜单的宽度  相对于右侧

        menu.setMode(SlidingMenu.LEFT); //设置菜单的显示模式  左侧菜单
        /*menu.setMode(SlidingMenu.RIGHT); //设置菜单的显示模式  右侧菜单
        menu.setMode(SlidingMenu.LEFT_RIGHT); //设置菜单的显示模式  左右两侧都行滑动*/

        //menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN); //触摸模式  全屏都可以拖动
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);  //触摸模式  只有边界可以拖动
        //menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);  //触摸模式  不可以拖动侧滑菜单*/


        //效果-->缩放效果
        menu.setBehindScrollScale(0.5f); //内容移动一个像素  侧滑菜单跟着移动0.5个像素

        //效果-->阴影效果
        menu.setShadowWidth(5); // 5 阴影的宽度
        menu.setShadowDrawable(R.drawable.shadow); //设置阴影

        //效果-->fade  逐渐显露
        menu.setFadeDegree(0.5f); // 从一般的阴影到无阴影
    }

    /**
     * 给主内容和侧滑菜单设置内容
     */
    private void initFragment() {
        //首先要获取Fragment的管理器
        FragmentManager fm = getSupportFragmentManager();
        //开启事务
        FragmentTransaction transaction = fm.beginTransaction();

        //将fragment添加到content容器中
        transaction.add(R.id.main_fragment_content, new ContentFragment(), FRAGMENT_CONTENT);

        //将fragment添加到left_menu中
        transaction.add(R.id.main_fragment_left_menu, new LeftMenuFragment(), FRAGMENT_LEFT_MENU);

        //提交修改
        transaction.commit();
    }

    /**
     * 返回左侧菜单的Fragment
     *
     * @return
     */
    public LeftMenuFragment getLeftFragment() {

        //获取到Fragment的管理器
        FragmentManager manager = getSupportFragmentManager();

        //通过设置的标记获取到侧滑菜单的Fragment
        LeftMenuFragment leftFragment = (LeftMenuFragment) manager.findFragmentByTag(FRAGMENT_LEFT_MENU);

        //返回侧滑菜单fragment
        return leftFragment;

    }

    /**
     * 获取ContentFragment
     *
     * @return
     */
    public ContentFragment getContentFragment() {

        //获取到Fragment的管理器
        FragmentManager fragmentManager = getSupportFragmentManager();

        //通过标记来获取到ContentFragment
        ContentFragment contentFragment = (ContentFragment) fragmentManager.findFragmentByTag(FRAGMENT_CONTENT);

        //将ContentFragment返回
        return contentFragment;
    }

   /* private long currentSystemTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        long currentTime = System.currentTimeMillis();

        if (currentSystemTime == 0) {
            Toast.makeText(this, "再按一次将退出应用程序", Toast.LENGTH_SHORT).show();
            currentSystemTime = currentTime;
        } else if (currentTime - currentSystemTime > 2000) {
            System.exit(0);
        }
        return super.onKeyDown(keyCode, event);
    }*/
}
