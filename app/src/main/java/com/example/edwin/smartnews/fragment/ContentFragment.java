package com.example.edwin.smartnews.fragment;

import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.example.edwin.smartnews.R;
import com.example.edwin.smartnews.activity.MainActivity;
import com.example.edwin.smartnews.adapter.ContentPageAdapter;
import com.example.edwin.smartnews.controller.tabs.BaseTabController;
import com.example.edwin.smartnews.controller.tabs.GovTabController;
import com.example.edwin.smartnews.controller.tabs.HomeTabController;
import com.example.edwin.smartnews.controller.tabs.NewsCenterTabController;
import com.example.edwin.smartnews.controller.tabs.SettingTabController;
import com.example.edwin.smartnews.controller.tabs.SmartServiceTabController;
import com.example.edwin.smartnews.views.NoSlideViewPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edwin on 2016/4/18.
 * <p/>
 * 主内容的Fragment
 */
public class ContentFragment extends BaseFragment {

    /*模拟显示的数据
    private static final String[] content = {"首页", "新闻中心", "智慧服务", "政务", "设置"};*/

    //采用注解获取组件
    @ViewInject(R.id.content_vp)
    NoSlideViewPager vp_content;
    @ViewInject(R.id.content_rb_home)
    RadioButton rb_home;
    @ViewInject(R.id.content_rb_newsCenter)
    RadioButton rb_newsCenter;
    @ViewInject(R.id.content_rb_smartService)
    RadioButton rb_smartService;
    @ViewInject(R.id.content_rb_gov)
    RadioButton rb_gov;
    @ViewInject(R.id.content_rb_setting)
    RadioButton rb_setting;
    @ViewInject(R.id.content_rg)
    RadioGroup content_rg;

    //设置点击的标记位  用来标记当前显示的ViewPager条目
    private int currentRBId;
    private List<BaseTabController> mBaseTabController;

    @Override
    public View initView() {

        View contentView = View.inflate(mContext, R.layout.fragment_content, null);

        ViewUtils.inject(this, contentView);

        return contentView;
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        //添加数据  用来保存从网络获取到的数据
        mBaseTabController = new ArrayList<>();
        //添加数据  使用Fragment中获取到的上下文 getActivity();
        //添加首页的数据
        mBaseTabController.add(new HomeTabController(mContext));
        //添加新闻中心的数据
        mBaseTabController.add(new NewsCenterTabController(mContext));
        //添加智慧服务的数据
        mBaseTabController.add(new SmartServiceTabController(mContext));
        //添加政务的数据
        mBaseTabController.add(new GovTabController(mContext));
        //添加设置中心的数据
        mBaseTabController.add(new SettingTabController(mContext));

        //绑定适配器
        vp_content.setAdapter(new ContentPageAdapter(mContext, mBaseTabController));

        //设置home界面默认不可以拖动
        setSlidingMenuTouchMode(R.id.content_rb_home);

    }


    @Override
    public void initEvent() {

        //设置RadioGroup的监听事件
        content_rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.content_rb_home:  //点击了首页button
                        currentRBId = 0;
                        break;
                    case R.id.content_rb_newsCenter:  //点击了新闻中心button
                        currentRBId = 1;
                        break;
                    case R.id.content_rb_smartService:  //点击了智慧服务button
                        currentRBId = 2;
                        break;
                    case R.id.content_rb_gov:  //点击了政务button
                        currentRBId = 3;
                        break;
                    case R.id.content_rb_setting:  //点击了设置button
                        currentRBId = 4;
                        break;
                }

                //设置当前显示的ViewPager
                vp_content.setCurrentItem(currentRBId);

                //设置home界面和setting界面不可以拖动的方法
                setSlidingMenuTouchMode(checkedId);
            }
        });
    }

    /**
     * 实现是否可以拖动的功能
     *
     * @param checkedId
     */
    private void setSlidingMenuTouchMode(int checkedId) {

        if (checkedId == R.id.content_rb_home || checkedId == R.id.content_rb_setting) {
            //如果当前选中的checkedId是home界面或者是设置界面  设置其不可以拖动侧滑菜单
            ((MainActivity) mContext).getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        } else {
            //否则让其可以将侧滑菜单滑动出来
            ((MainActivity) mContext).getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        }

    }


    /**
     * 将侧边栏点击的条目位置数据显示在界面上
     * @param position
     */
    public void switchContent(int position) {
        //获取容器中的当前显示的TabController
        BaseTabController controller = mBaseTabController.get(currentRBId);

        //获取到当前ContentFragment下的TabController   通过相应的TabController来获取到新闻  组图  话题  互动的内容
        controller.switchContent(position);
    }
}
