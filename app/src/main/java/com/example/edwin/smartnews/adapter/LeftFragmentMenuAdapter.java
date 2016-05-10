package com.example.edwin.smartnews.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.edwin.smartnews.R;
import com.example.edwin.smartnews.activity.MainActivity;
import com.example.edwin.smartnews.bean.NewsCenterBean.DataBean;
import com.example.edwin.smartnews.fragment.LeftMenuFragment.OnPagerSelectListener;
import com.example.edwin.smartnews.utils.LogUtils;

import java.util.List;

/**
 * Created by Edwin on 2016/04/23.
 *
 * 左侧菜单的数据适配器
 */
public class LeftFragmentMenuAdapter extends BaseAdapter {

    private static final String TAG = "LeftFragmentMenuAdapter";
    private Context mContext;

    private List<DataBean> mDatas;
    private int mCurrentSelect;

    public LeftFragmentMenuAdapter(Context context, List<DataBean> list) {

        this.mContext = context;

        this.mDatas = list;

        //
    }

    @Override
    public int getCount() {
        if (mDatas != null) {
            return mDatas.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {   //当前没有缓存
            holder = new ViewHolder();
            //将布局文件转换成View对象
            convertView = View.inflate(mContext, R.layout.fragment_left_menu_item, null);

            //获取布局中的控件
            holder.mTV = (TextView) convertView;

            //设置convertView的标记
            convertView.setTag(holder);
        } else {  //当前有缓存
            //获取到标记
            holder = (ViewHolder) convertView.getTag();
        }

        //获取数据
        DataBean bean = mDatas.get(position);

        //设置布局
        holder.mTV.setText(bean.title);

        ((MainActivity) mContext).getLeftFragment().setOnPagerSelectListener(new OnPagerSelectListener() {

            @Override
            public void onPagerSelect(int pagerSelectPosition) {

                LogUtils.printLog(TAG, "接口回调的方法执行了");

                mCurrentSelect = pagerSelectPosition;
            }
        });

        /*mContext.setOnPagerSelectListener(new OnPagerSelectListener() {
            @Override
            public void onPagerSelect(int pagerSelectPosition) {

                mCurrentSelect = pagerSelectPosition;

            }
        });*/

        /*LeftMenuFragment fragment = new LeftMenuFragment();

        fragment.setOnPagerSelectListener(new LeftMenuFragment.OnPagerSelectListener() {
            @Override
            public void onPagerSelect(int pagerSelectPosition) {

                mCurrentSelect = pagerSelectPosition;
            }
        });*/

        //设置侧滑菜单的条目切换

        if (mCurrentSelect == position) {

            holder.mTV.setEnabled(true);
            LogUtils.printLog(TAG, "当前选中的位置是" + bean.title);
        } else {
            holder.mTV.setEnabled(false);
        }

        //返回要显示的View
        return convertView;
    }

    //内部类
    class ViewHolder {
        //显示文本
        TextView mTV;
    }
}
