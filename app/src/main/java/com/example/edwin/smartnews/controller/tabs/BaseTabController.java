package com.example.edwin.smartnews.controller.tabs;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.edwin.smartnews.R;
import com.example.edwin.smartnews.activity.MainActivity;

/**
 * Created by edwin on 2016/4/19.
 * <p/>
 * 对ContentFragment的ViewPager的首页的控制器抽取的基类
 * <p/>
 * 当前类需要实现的功能
 * <p/>
 * 1.提供已经绑定过的视图
 * <p/>
 * 2.接收和加载数据
 * <p/>
 * 3.进行数据和视图的绑定
 */
public abstract class BaseTabController implements OnClickListener {

    //需要传递进来的视图
    public View mRootView;
    //获取Context
    protected Context mContext;
    protected TextView mTv_title;
    protected ImageButton mBtn_menu;
    protected FrameLayout mFl_container;

    public BaseTabController(Context context) {
        this.mContext = context;
        //在初始化对象的时候实例化视图
        mRootView = initView(context);
    }

    /**
     * 初始化视图
     *
     * @param context 上下文
     * @return 返回当前界面的View
     */
    protected View initView(Context context) {

        //设置界面默认的布局样式
        View view = View.inflate(mContext, R.layout.content_base_controller, null);

        //获取界面中的组件
        mTv_title = (TextView) view.findViewById(R.id.tv_base_title);

        mBtn_menu = (ImageButton) view.findViewById(R.id.btn_base_menu);

        mFl_container = (FrameLayout) view.findViewById(R.id.fl_base_container);

        //设置顶部的菜单按钮的显示  当在首页和设置界面的时候不显示菜单按钮
        mBtn_menu.setVisibility(View.VISIBLE);

        //初始化标题
        initTitle();

        //初始化容器显示的视图
        mFl_container.addView(initContentView(context));

        //设置侧滑菜单的开启和关闭
        mBtn_menu.setOnClickListener(this);

        //初始化数据
        initData();

        //将当前获取到的VIew返回给调用者
        return view;
    }

    /**
     * 初始化主内容显示的视图
     *
     * @param context 上下文
     * @return 返回主内容的View
     */
    protected abstract View initContentView(Context context);

    /**
     * 1.对标题进行初始化操作
     * 2.当前类中无法控制子类标题的文本  只能交给子类实现 设置为抽象的方法
     */
    protected abstract void initTitle();

    /**
     * 加载和接收数据
     * 进行数据和视图的绑定
     * 让子类选择性实现
     */
    protected void initData() {

    }

    /**
     * 用来控制侧滑菜单的开启和关闭
     *
     * @param v 点击事件
     */
    @Override
    public void onClick(View v) {
        //通过获取到MainActivity的上下文来控制侧滑菜单的开启和关闭
        ((MainActivity) mContext).getSlidingMenu().toggle();
    }

    /**
     * 用来显示TabController下得新闻  组图  互动  话题的界面
     *
     * @param position 用来显示侧边栏点击的是新闻  组图  互动   话题中的某个条目
     */
    public void switchContent(int position) {

    }
}
