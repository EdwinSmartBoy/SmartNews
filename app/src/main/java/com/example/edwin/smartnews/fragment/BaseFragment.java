package com.example.edwin.smartnews.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by edwin on 2016/4/18.
 * <p/>
 * Fragment基类
 */
public abstract class BaseFragment extends Fragment {

    public Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //将Fragment的Context封装在基类中  子类实现时只需要调用即可
        this.mContext = getActivity();
        //进行初始化操作 接受其他界面传递过来的参数等
        init();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        //当Activity创建时完成时会调用该方法

        //初始化数据
        initData();
        //初始化事件
        initEvent();
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 进行初始化操作
     */
    public void init() {

    }

    /**
     * 初始化Fragment界面
     * <p/>
     * 让继承BaseFragment的子类强制复写该方法
     *
     * @return 返回界面布局
     */
    public abstract View initView();

    /**
     * 初始化数据
     * <p/>
     * 空实现 让子类有选择的实现
     */
    public void initData() {

    }

    /**
     * 初始化事件
     * <p/>
     * 空实现 让子类有选择的实现
     */
    public void initEvent() {

    }
}
