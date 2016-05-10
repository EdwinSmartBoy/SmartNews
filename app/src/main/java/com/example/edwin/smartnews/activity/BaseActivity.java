package com.example.edwin.smartnews.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Activity的基类
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        //初始化界面
        initView();
        //初始化数据
        initData();
        //初始化事件
        initEvent();

    }

    /**
     * 初始化界面
     */
    public void initView() {

    }

    /**
     * 初始化数据
     */
    public void initData() {

    }

    /**
     * 初始化事件
     */
    public void initEvent() {

    }

    /**
     * 设置界面
     *
     * @return 返回界面ID
     */
    public abstract int getLayoutId();


}
