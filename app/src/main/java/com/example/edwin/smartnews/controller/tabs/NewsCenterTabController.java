package com.example.edwin.smartnews.controller.tabs;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import com.example.edwin.smartnews.activity.MainActivity;
import com.example.edwin.smartnews.bean.NewsCenterBean;
import com.example.edwin.smartnews.bean.NewsCenterBean.DataBean;
import com.example.edwin.smartnews.controller.newscenter.BaseNewsMenuController;
import com.example.edwin.smartnews.controller.newscenter.InteractController;
import com.example.edwin.smartnews.controller.newscenter.NewsController;
import com.example.edwin.smartnews.controller.newscenter.PhotoController;
import com.example.edwin.smartnews.controller.newscenter.TopicController;
import com.example.edwin.smartnews.fragment.LeftMenuFragment;
import com.example.edwin.smartnews.utils.LogUtils;
import com.example.edwin.smartnews.utils.MyContains;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edwin on 2016/4/19.
 * <p/>
 * 对ContentFragment的ViewPager的新闻中心的控制器
 * <p/>
 * 当前类需要实现的功能
 * <p/>
 * 1.提供已经绑定过的视图
 * <p/>
 * 2.接收和加载数据
 * <p/>
 * 3.进行数据和视图的绑定
 */
public class NewsCenterTabController extends BaseTabController {

    private static final String TAG = "NewsCenterTabController";

    private FrameLayout mNewsCenterTabControllerContainer;
    private List<BaseNewsMenuController> mBaseNewsMenuList;

    //在调用构造方法时就调用了initView()方法  则无需在再次调用该方法
    public NewsCenterTabController(Context context) {
        super(context);
    }

    /**
     * 初始化标题栏显示的视图
     * <p/>
     * 1.设置标题栏文字
     * 2.设置菜单按钮是否显示
     */
    @Override
    protected void initTitle() {

        mTv_title.setText("新闻中心");

        mBtn_menu.setVisibility(View.VISIBLE);

    }

    /**
     * 初始化FrameLayout容器需要显示的视图
     *
     * @param context
     * @return
     */
    @Override
    protected View initContentView(Context context) {

        /*TextView tv = new TextView(context);
        tv.setText("新闻中心");
        tv.setTextColor(Color.BLACK);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(20);*/

        mNewsCenterTabControllerContainer = new FrameLayout(mContext);

        return mNewsCenterTabControllerContainer;
    }

    /**
     * 初始化界面的数据
     * <p/>
     * 使用OkHttp来访问网络,获取网络数据
     */
    @Override
    protected void initData() {

        LogUtils.printLog(TAG, "initData-------");

        //使用OkHttp访问网络会出现异常
        /*//实例化OkHttpClient
        OkHttpClient client = new OkHttpClient();

        //设置网络请求的连接
        Request request = new Request.Builder().url(MyContains.NEWS_CENTER).build();

        LogUtils.printLog(TAG, "网络连接-------");
        //与网络建立异步连接
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                //网络连接失败时回调该方法
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                //网络连接成功时回调该方法
                //获取网络返回的数据
                String result = response.body().string();
                //如果当前数据不为空,解析服务器返回的数据   数据格式在接口文档中已经约定好为json格式的数据
                parseJson(result);
                LogUtils.printLog(TAG, "解析方法调用了");
            }
        });*/

        //使用xUtils中的HttpUtils模块进行网络访问
        HttpUtils client = new HttpUtils();

        //与网络建立连接,获取网络返回的数据
        client.send(HttpMethod.GET, MyContains.NEWS_CENTER, null, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                //连接成功时回调该方法  获取到数据
                String stringJson = responseInfo.result;
                //解析json数据
                parseJson(stringJson);
                LogUtils.printLog(TAG, "");
            }

            @Override
            public void onFailure(HttpException e, String s) {
                //连接失败时回调该方法
                e.printStackTrace();
            }
        });
        super.initData();
    }

    /**
     * 解析服务器返回的数据
     *
     * @param result
     */
    private void parseJson(String result) {
        //实例化Gson
        Gson gson = new Gson();
        //获取到解析的json数据的对象
        NewsCenterBean newsCenterBeans = gson.fromJson(result, NewsCenterBean.class);

        //获取到侧滑菜单的数据
        List<DataBean> data = newsCenterBeans.data;

        //获取到侧滑菜单的Fragment的实例对象
        LeftMenuFragment leftFragment = ((MainActivity) mContext).getLeftFragment();

        //调用leftFragment中的方法  将数据传递到Fragment中
        leftFragment.setData(data);

        //创建容器来统一管理新闻界面的内容
        mBaseNewsMenuList = new ArrayList<>();

        BaseNewsMenuController baseNewsMenu = null;

        //遍历获取到的DataBean数据
        for (DataBean dataBean : data) {
            //获取到type
            int type = dataBean.type;
            //判断获取到的类型
            switch (type) {
                case 1:   //进入新闻中心的新闻条目 同时将获取到的新闻的数据传递到NewsController中 展示数据
                    baseNewsMenu = new NewsController(mContext, dataBean.children);
                    break;

                case 10:  //进入新闻中心的专题条目  展示数据
                    baseNewsMenu = new TopicController(mContext);
                    break;

                case 2:  //进入新闻中心的组图条目   展示数据
                    baseNewsMenu = new PhotoController(mContext);
                    break;

                case 3:  //进入新闻中心的互动条目   展示数据
                    baseNewsMenu = new InteractController(mContext);
                    break;

                default:
                    break;
            }
            //将数据添加到容器中
            mBaseNewsMenuList.add(baseNewsMenu);

            /*//将视图添加到容器中进行展示
            mNewsCenterTabControllerContainer.addView(mBaseNewsMenuList.get(0).mRootView);*/
        }
        //设置默认显示的条目信息
        switchContent(0);
    }

    /**
     * @param position 用来显示侧边栏点击的是新闻  组图  互动   话题中的某个条目
     */
    @Override
    public void switchContent(int position) {
        super.switchContent(position);

        //将当前容器中的View都清楚掉
        mNewsCenterTabControllerContainer.removeAllViews();

        //将点击滑动菜单的position传入集合中,获取到要显示的数据
        BaseNewsMenuController controller = mBaseNewsMenuList.get(position);

        //获取要显示的根布局
        View rootView = controller.mRootView;

        //将布局添加到容器中
        mNewsCenterTabControllerContainer.addView(rootView);

        //加载Controller对应的数据,刷新UI
        controller.initData();

        //关闭左侧菜单
        ((MainActivity) mContext).getSlidingMenu().toggle();

    }
}
