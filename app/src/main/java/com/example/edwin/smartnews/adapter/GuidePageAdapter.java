package com.example.edwin.smartnews.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by edwin on 2016/4/18.
 * <p/>
 * 向导界面的适配器
 */
public class GuidePageAdapter extends PagerAdapter {

    private Context mContext;

    private int[] mDatas;

    public GuidePageAdapter(Context context, int[] arr) {
        this.mContext = context;
        this.mDatas = arr;
    }

    @Override
    public int getCount() {
        //设置当前显示的数据个数
        return mDatas.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //创建实例
        ImageView iv = new ImageView(mContext);
        //设置图片全屏显示
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        //设置数据
        iv.setImageResource(mDatas[position]);
        //将要显示的View加载到容器中
        container.addView(iv);
        //返回标记
        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //将容器中的View移除
        container.removeView((View) object);
    }
}
