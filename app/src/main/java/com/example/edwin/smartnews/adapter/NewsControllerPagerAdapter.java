package com.example.edwin.smartnews.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.example.edwin.smartnews.bean.NewsCenterBean.DataBean.ChildrenBean;
import com.example.edwin.smartnews.controller.newscenter.news.NewsListController;

import java.util.List;

/**
 * Created by Edwin on 2016/04/25.
 * <p/>
 * NewsController的适配器
 */
public class NewsControllerPagerAdapter extends PagerAdapter {

    private static final String TAG = "NewsControllerPagerAdapter";
    private Context mContext;

    private List<NewsListController> mChildrenBeanList;

    private List<ChildrenBean> mChildren;

    public NewsControllerPagerAdapter(Context context, List<NewsListController> childrenBeanList, List<ChildrenBean> children) {
        this.mContext = context;
        this.mChildrenBeanList = childrenBeanList;
        this.mChildren = children;
    }

    @Override
    public int getCount() {
        if (mChildrenBeanList != null && mChildrenBeanList.size() > 0) {
            return mChildrenBeanList.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        /*//使用静态文本进行测试
        TextView tv = new TextView(mContext);
        LogUtils.printLog(TAG, mChildrenData.get(position).title);
        tv.setText(mChildrenData.get(position).title);
        LogUtils.printLog(TAG, "设置了文本数据");
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(25);*/
        NewsListController listController = mChildrenBeanList.get(position);

        View rootView = listController.mRootView;

        //将数据添加到容器中
        container.addView(rootView);

        //执行加载数据的操作
        listController.initData();

        //返回标记
        return rootView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    //获取到PagerTitle的文本信息  必须实现该方法才能让TabPageIndicator的内容才能显示
    @Override
    public CharSequence getPageTitle(int position) {
        return mChildren.get(position).title;
    }
}
