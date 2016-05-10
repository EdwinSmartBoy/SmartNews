package com.example.edwin.smartnews.controller.newscenter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.edwin.smartnews.R;
import com.example.edwin.smartnews.adapter.NewsControllerPagerAdapter;
import com.example.edwin.smartnews.bean.NewsCenterBean.DataBean.ChildrenBean;
import com.example.edwin.smartnews.controller.newscenter.news.NewsListController;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edwin on 2016/04/23.
 * <p/>
 * 侧滑菜单的新闻的显示的内容
 */
public class NewsController extends BaseNewsMenuController {

    @ViewInject(R.id.news_controller_tpiIndicator)
    TabPageIndicator news_tpiIndicator;

    @ViewInject(R.id.news_controller_vp)
    ViewPager news_viewPager;

    private List<ChildrenBean> mChildren;
    private List<NewsListController> mChildrenBeanList;

    //调用父类的构造方法
    public NewsController(Context context, List<ChildrenBean> children) {
        super(context);
        this.mChildren = children;
    }

    /**
     * 初始化界面
     *
     * @param context
     * @return
     */
    @Override
    protected View initView(Context context) {

        /*当前使用静态文本进行测试
        TextView tv = new TextView(context);
        tv.setText("新闻");
        tv.setTextSize(25);
        tv.setGravity(Gravity.CENTER);
        return tv;*/

        //将布局文件转换成View对象
        View view = View.inflate(mContext, R.layout.news_controller_indicator, null);
        /*TabPageIndicator newsIndicator = (TabPageIndicator) mRootView.findViewById(R.id.news_controller_tpiIndicator);
        ViewPager newsViewPager = (ViewPager) mRootView.findViewById(R.id.news_controller_vp);*/
        //通过注入的方式来获取控件
        ViewUtils.inject(this, view);

        //返回View
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        //创建集合  用来保存实例化的NewsListController数据
        mChildrenBeanList = new ArrayList<>();
        //遍历集合  获取集合中封装的对象  将数据拆分  封装到不同的NewsListController中
        for (ChildrenBean childrenBean : mChildren) {
            //实例化NewsListController
            NewsListController listController = new NewsListController(mContext, childrenBean);
            //将NewsListController对象添加到集合中
            mChildrenBeanList.add(listController);
        }

        //实例化适配器
        NewsControllerPagerAdapter adapter = new NewsControllerPagerAdapter(mContext, mChildrenBeanList, mChildren);
        //绑定适配器
        news_viewPager.setAdapter(adapter);

        //将Viewpager与指示器绑定
        news_tpiIndicator.setViewPager(news_viewPager);

        //设置默认显示的条目为第一个
        news_tpiIndicator.setCurrentItem(0);

    }
}
