package com.example.edwin.smartnews.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by edwin on 2016/4/19.
 * <p/>
 * 自定义不可以滑动的ViewPager
 */
public class NoSlideViewPager extends LazyViewPager {

    public NoSlideViewPager(Context context) {
        super(context);
    }

    public NoSlideViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //重写事件分发的三种方法

    /**
     * 分发事件  一般不对当前进行处理
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 拦截事件  当前选择是否拦截滑动或者触摸事件
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    /**
     * 处理事件  当onInterceptTouchEvent的返回值为true时,拦截的事件会传递到该方法中进行处理
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }
}
