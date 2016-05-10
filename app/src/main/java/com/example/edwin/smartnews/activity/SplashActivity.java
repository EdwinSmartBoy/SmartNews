package com.example.edwin.smartnews.activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.example.edwin.smartnews.R;
import com.example.edwin.smartnews.utils.LogUtils;
import com.example.edwin.smartnews.utils.MyContains;
import com.example.edwin.smartnews.utils.SPUtils;
import com.example.edwin.smartnews.utils.StartIntentUtils;
import com.example.edwin.smartnews.utils.ToastUtils;

public class SplashActivity extends BaseActivity {


    private static final String TAG = "SplashActivity";
    private RelativeLayout mRlSplash;
    private AnimationSet mAs;

    /**
     * 设置界面布局
     *
     * @return
     */
    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    /**
     * 初始化界面
     */
    @Override
    public void initView() {
        //获取界面组件
        mRlSplash = (RelativeLayout) findViewById(R.id.rl_splash);
    }

    @Override
    public void initData() {

        //初始化动画
        initAnimation();

    }

    /**
     * 初始化事件
     */
    @Override
    public void initEvent() {

        //设置动画的监听事件
        mAs.setAnimationListener(new Animation.AnimationListener() {

            //获取保存的是否进入向导界面的SP值
            boolean completeResult = SPUtils.getBoolean(SplashActivity.this, MyContains.GUIDE_COMPLETE, false);

            @Override
            public void onAnimationStart(Animation animation) {
                //动画开始
                //检测当前的网络状态
                checkNetworkState();

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //动画结束  根据获取到的值判断是进入向导设置界面还是主界面
                if (completeResult) {
                    //已经进行了向导设置  直接进入主界面
                    StartIntentUtils.start(SplashActivity.this, MainActivity.class);

                    /*Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);*/
                    LogUtils.printLog(TAG, "进入主界面执行了");
                } else {
                    //没有进行向导设置  进入设置向导界面
                    // 在动画结束的时候进入引导界面
                    StartIntentUtils.start(SplashActivity.this, GuideActivity.class);

                    /*Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
                    startActivity(intent);*/
                    LogUtils.printLog(TAG, "进入向导设置界面执行了");
                }
                //同时将当前界面关闭
                finish();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //将动画重播

            }
        });

    }

    /**
     * 在splash界面动画开始时检测网络状态
     */
    private void checkNetworkState() {

        //获取网络管理器
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        //获取到移动网络的状态
        State mobileState = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        //获取到wifi的网络状态
        State wifiState = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();

        //首先应该检测无限网络连接
        if (wifiState != State.CONNECTED || wifiState != State.CONNECTING) {
            //当前wifi网络无连接
            if ((mobileState != State.CONNECTED || mobileState != State.CONNECTING)) {
                //判断当前网络是否有移动网络连接
                ToastUtils.showShort(this, "当前检测无网络连接");
            } else {
                //当前网络为移动网络连接状态
                ToastUtils.showShort(this, "当前检测为移动网络,会消耗您的流量");

            }
        } else {
            //当前wifi连接
            ToastUtils.showShort(this, "当前网络状态良好");
        }
    }

    /**
     * 初始化splash界面的动画效果
     */
    private void initAnimation() {
        //设置splash界面的动画效果

        //渐变动画
        AlphaAnimation aa = new AlphaAnimation(0f, 1f);
        //设置动画的显示时长
        aa.setDuration(2000);
        //设置动画停留的位置
        aa.setFillAfter(true);

        //旋转动画  设置旋转动画的旋转中心点
        RotateAnimation ra = new RotateAnimation(0, 360,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        //设置动画时长
        ra.setDuration(2000);
        //设置动画结束停留的位置
        ra.setFillAfter(true);

        //缩放动画  设置缩放动画的返回和缩放中心点
        ScaleAnimation sa = new ScaleAnimation(0F, 1F, 0F, 1F,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        //设置动画时长
        sa.setDuration(2000);
        sa.setFillAfter(true);

        //创建动画集
        mAs = new AnimationSet(true);

        //将动画添加到动画集中
        mAs.addAnimation(aa);
        mAs.addAnimation(ra);
        mAs.addAnimation(sa);

        //将根布局关联上动画  开始动画
        mRlSplash.startAnimation(mAs);
    }
}
