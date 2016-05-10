package com.example.edwin.smartnews.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Edwin on 2016/04/27.
 * <p/>
 * 自定义轮播图的ViewPager  在当前自定义控件中设置轮播图的滑动事件
 */
public class InnerRepeatViewPager extends ViewPager {

    private float mDownY;
    private float mDownX;

    public InnerRepeatViewPager(Context context) {
        super(context);
    }

    public InnerRepeatViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     * 在下列任何一个方法中进行事件的处理的都行
     */

    /**
     * 分发事件
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //获取按下的点的坐标
                mDownX = ev.getRawX();
                mDownY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                //获取到移动的点
                float moveX = ev.getRawX();
                float moveY = ev.getRawY();

                int diffX = (int) ((moveX - mDownX) + .5f);
                int diffY = (int) ((moveY - mDownY) + .5f);

                int position = this.getCurrentItem();

                if (diffX > 0) { //从左向右滑动

                    if (position == 0) {

                        requestDisallowInterceptTouchEvent(false);

                    } else if (position == this.getAdapter().getCount() - 1) {

                        requestDisallowInterceptTouchEvent(true);

                    } else {

                        requestDisallowInterceptTouchEvent(true);
                    }

                } else {
                    //从右向左滑动
                    requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 拦截事件
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    /**
     * 处理事件
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }
}
