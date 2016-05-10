package com.example.edwin.smartnews.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.example.edwin.smartnews.controller.tabs.BaseTabController;
import com.example.edwin.smartnews.utils.LogUtils;

import java.util.List;

/**
 * Created by edwin on 2016/4/18.
 * <p/>
 * 主界面的适配器
 */
public class ContentPageAdapter extends PagerAdapter {

    private static final String TAG = "ContentPageAdapter";
    private Context mContext;

    private List<BaseTabController> mDatas;

    public ContentPageAdapter(Context context, List<BaseTabController> list) {
        this.mContext = context;
        this.mDatas = list;
    }

    @Override
    public int getCount() {
        //设置当前显示的数据个数
        if (mDatas != null) {
            return mDatas.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //创建实例
        BaseTabController homeController = mDatas.get(position);
        View view = homeController.mRootView;
        container.addView(view);
        LogUtils.printLog(TAG, "加载了ViewPager" + position);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //将容器中的View移除
        container.removeView((View) object);

        LogUtils.printLog(TAG, "销毁了ViewPager" + position);
    }
}
