package com.example.edwin.smartnews.activity;

import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;

import com.example.edwin.smartnews.R;
import com.example.edwin.smartnews.adapter.GuidePageAdapter;
import com.example.edwin.smartnews.utils.LogUtils;
import com.example.edwin.smartnews.utils.MyContains;
import com.example.edwin.smartnews.utils.SPUtils;
import com.example.edwin.smartnews.utils.StartIntentUtils;

/**
 * Created by edwin on 2016/4/17.
 * <p/>
 * 向导界面
 */
public class GuideActivity extends BaseActivity implements OnClickListener {

    private static final String TAG = "GuideActivity";
    private ViewPager mVpGuide;

    //保存ViewPager的数据
    private static int pic_data[] = {R.mipmap.guide_1, R.mipmap.guide_2, R.mipmap.guide_3};
    private LinearLayout mLlIndicator;
    private Button mBtnGuide;
    private ImageView mIvSelect;

    @Override
    public int getLayoutId() {
        return R.layout.activity_guide;
    }

    /**
     * 初始化界面
     */
    @Override
    public void initView() {
        //获取界面布局
        mVpGuide = (ViewPager) findViewById(R.id.vp_guide);
        //获取点的容器
        mLlIndicator = (LinearLayout) findViewById(R.id.ll_view_indicator_guide);
        //获取按钮组件
        mBtnGuide = (Button) findViewById(R.id.btn_guide);
        //获取指示点的容器
        mIvSelect = (ImageView) findViewById(R.id.iv_view_indicator_select);
        //创建适配器对象
        GuidePageAdapter adapter = new GuidePageAdapter(this, pic_data);
        //绑定适配器
        mVpGuide.setAdapter(adapter);
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        //设置滑动的指示点
        for (int i = 0; i < pic_data.length; i++) {
            //指示点是图片数据
            ImageView iv = new ImageView(this);
            //设置点的数据
            iv.setImageResource(R.mipmap.dot_normal);

            //设置点的大小
            int width = (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 7, getResources().getDisplayMetrics()) + .5f);
            int height = (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 7, getResources().getDisplayMetrics()) + .5f);

            //设置点的属性
            LayoutParams params = new LayoutParams(width, height);
            //设置间距 需要判断点的位置
            if (i != 0) {
                params.leftMargin = 10;
            }
            //添加到容器中
            mLlIndicator.addView(iv, params);
        }
    }

    @Override
    public void initEvent() {
        //设置ViewPager的监听事件
        mVpGuide.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //当ViewPager滑动时就调用  在这设置点的滑动  获取到第一个点和第二个点的左边界的距离  定义为一个变量
                int spaceLeft = mLlIndicator.getChildAt(1).getLeft() - mLlIndicator.getChildAt(0).getLeft();
                //计算移动的距离
                int marginLeft = (int) (spaceLeft * (position + positionOffset));

                //重新获取mLlIndicator的LayoutParams
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mIvSelect.getLayoutParams();
                //修改属性

                params.leftMargin = marginLeft;
                //重新设置mLlIndicator的LayoutParams
                mIvSelect.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
                //当滑动选中时调用  判断按钮是否显示
                if (position != pic_data.length - 1) {
                    //只要不是最后一张就隐藏按钮
                    mBtnGuide.setVisibility(View.GONE);
                } else {
                    //是最后一张图片  显示按钮
                    mBtnGuide.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //滑动状态改变时调用
            }
        });

        //设置按钮的点击事件
        mBtnGuide.setOnClickListener(this);
    }

    /**
     * 点击按钮  进入主界面
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        //进入主界面
        /*Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);*/
        StartIntentUtils.start(GuideActivity.this, MainActivity.class);
        //保存设置完成的boolean的值
        SPUtils.putBoolean(GuideActivity.this, MyContains.GUIDE_COMPLETE, true);
        LogUtils.printLog(TAG, "设置向导保存成功");
        //关闭当前界面
        finish();
    }
}
