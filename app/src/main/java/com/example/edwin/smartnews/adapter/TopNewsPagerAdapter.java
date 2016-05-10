package com.example.edwin.smartnews.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.example.edwin.smartnews.bean.NewsBJBean.DataBean.TopnewsBean;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

/**
 * Created by Edwin on 2016/04/25.
 * <p/>
 * 轮播图的适配器
 */
public class TopNewsPagerAdapter extends PagerAdapter {

    private Context mContext;

    private List<TopnewsBean> mTopNewsBean;
    private ImageView mIv;
    private BitmapUtils mBitmapUtils;

    public TopNewsPagerAdapter(Context context, List<TopnewsBean> mTopNews) {
        this.mContext = context;
        this.mTopNewsBean = mTopNews;
    }

    @Override
    public int getCount() {
        if (mTopNewsBean != null && mTopNewsBean.size() > 0) {
            return mTopNewsBean.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        //创建ImageView对象
        mIv = new ImageView(mContext);

        //设置图片的显示
        mIv.setScaleType(ScaleType.FIT_XY);

        //使用XUtils的BitmapUtils
        mBitmapUtils = new BitmapUtils(mContext);

        //获取图片的url
        String topImageUrl = mTopNewsBean.get(position).topimage;

        //显示图片数据
        mBitmapUtils.display(mIv, topImageUrl);

        //将要显示的数据添加到容器中
        container.addView(mIv);

        //返回标记
        return mIv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
