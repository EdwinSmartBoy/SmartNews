package com.example.edwin.smartnews.controller.newscenter.news;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.edwin.smartnews.R;
import com.example.edwin.smartnews.adapter.TopNewsPagerAdapter;
import com.example.edwin.smartnews.bean.NewsBJBean;
import com.example.edwin.smartnews.bean.NewsBJBean.DataBean.TopnewsBean;
import com.example.edwin.smartnews.bean.NewsCenterBean.DataBean.ChildrenBean;
import com.example.edwin.smartnews.controller.newscenter.BaseNewsMenuController;
import com.example.edwin.smartnews.utils.LogUtils;
import com.example.edwin.smartnews.utils.MyContains;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * Created by Edwin on 2016/04/25.
 * <p/>
 * TabPageIndicator的内容显示的抽取
 */
public class NewsListController extends BaseNewsMenuController implements OnPageChangeListener {

    private static final String TAG = "NewsListController";
    @ViewInject(R.id.news_list_controller_vp)
    ViewPager vpPicAction;

    @ViewInject(R.id.news_list_controller_title)
    TextView tvTitle;

    @ViewInject(R.id.news_list_controller_dot_container)
    LinearLayout ll_Container;


    private ChildrenBean mChildrenBean;

    private List<TopnewsBean> mTopNewsData;

    public NewsListController(Context context, ChildrenBean childrenBean) {
        super(context);
        this.mChildrenBean = childrenBean;
    }

    /**
     * 初始化视图
     *
     * @param context
     * @return
     */
    @Override
    protected View initView(Context context) {
        //将布局文件转换成View对象
        View view = View.inflate(mContext, R.layout.news_list_controller, null);

        //采用注入的方式获取控件
        ViewUtils.inject(this, view);

        //返回View对象
        return view;
    }

    /**
     * 初始化数据
     * <p/>
     * 1.异步加载数据
     * 2.解析数据
     * 3.将视图和数据进行绑定
     */
    @Override
    public void initData() {
        super.initData();
        //获取数据的URL 获取第一个条目的数据
        String path = MyContains.BASE_URL + mChildrenBean.url;

        LogUtils.printLog(TAG, path);

        //创建HttpUtils的实例对象
        HttpUtils client = new HttpUtils();

        //采用异步的方式访问网络  获取数据
        client.send(HttpMethod.GET, path, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                //访问成功时获取的数据
                String result = responseInfo.result;
                parse2Json(result);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                //访问失败是获取的数据
                e.printStackTrace();
            }
        });
    }

    /**
     * 解析json数据
     *
     * @param result
     */
    private void parse2Json(String result) {
        //获取Gson对象
        Gson gson = new Gson();
        //解析json数据
        NewsBJBean newsBJBean = gson.fromJson(result, NewsBJBean.class);
        //展示当前数据
        mTopNewsData = newsBJBean.data.topnews;

        ImageView iv;

        //在添加前先移除掉所有的点
        ll_Container.removeAllViews();

        //往容器中添加数据
        for (int i = 0; i < mTopNewsData.size(); i++) {
            iv = new ImageView(mContext);
            iv.setImageResource(R.mipmap.dot_normal);
            if (i == 0) {
                //如果当前是第一个位置  显示红点
                iv.setImageResource(R.mipmap.dot_focus);
            }

            //px---> dp的写法
            int width = (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 7, mContext.getResources().getDisplayMetrics()) + .5f);

            int height = (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 7, mContext.getResources().getDisplayMetrics()) + .5f);

            //获取容器的配置参数
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);

            //设置点的间距
            params.leftMargin = width;

            //将点添加到容器中
            ll_Container.addView(iv, params);
        }

        //创建轮播图的适配器
        TopNewsPagerAdapter adapter = new TopNewsPagerAdapter(mContext, mTopNewsData);

        //绑定适配器
        vpPicAction.setAdapter(adapter);

        //设置ViewPager的监听事件
        vpPicAction.setOnPageChangeListener(this);

        //设置默认显示的条目
        tvTitle.setText(mTopNewsData.get(0).title);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //当ViewPager滑动时调用
    }

    @Override
    public void onPageSelected(int position) {
        //当ViewPager被选中时调用
        tvTitle.setText(mTopNewsData.get(position).title);

        //处理点的步骤
        //1.先将选中的点的效果清除
        //遍历容器  获取到孩子位置的点效果
        for (int i = 0; i < mTopNewsData.size(); i++) {
            ImageView imageView = (ImageView) ll_Container.getChildAt(i);
            //将选中的点的效果进行还原处理
            imageView.setImageResource(R.mipmap.dot_normal);
            if (i == position) {
                //如果当前显示的View和遍历到的位置一致,设置为选中效果
                //2.设置选中的点的效果
                imageView.setImageResource(R.mipmap.dot_focus);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //当ViewPager的状态改变时调用
    }
}
