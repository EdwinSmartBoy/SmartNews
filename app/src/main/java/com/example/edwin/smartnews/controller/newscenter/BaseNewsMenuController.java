package com.example.edwin.smartnews.controller.newscenter;

import android.content.Context;
import android.view.View;

/**
 * Created by Edwin on 2016/04/23.
 * <p/>
 * 新闻中心的新闻,专题组图,互动界面的基类
 * <p/>
 * 实现功能:1.获取数据  2.加载视图  3.将数据和视图进行绑定
 */
public abstract class BaseNewsMenuController {

    public Context mContext;

    public View mRootView;

    public BaseNewsMenuController(Context context) {
        //保存上下文
        this.mContext = context;
        //初始化数据
        mRootView = initView(context);
    }

    /**
     * 初始化界面 子类必须实现
     *
     * @param context
     * @return
     */
    protected abstract View initView(Context context);

    /**
     * 初始化数据
     * <p/>
     * 1.获取数据  设置数据   将视图和数据进行绑定
     */
    public void initData() {

    }
}




