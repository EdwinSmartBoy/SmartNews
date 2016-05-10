package com.example.edwin.smartnews.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.edwin.smartnews.R;
import com.example.edwin.smartnews.activity.MainActivity;
import com.example.edwin.smartnews.adapter.LeftFragmentMenuAdapter;
import com.example.edwin.smartnews.bean.NewsCenterBean.DataBean;

import java.util.List;

/**
 * Created by edwin on 2016/4/18.
 * <p/>
 * 主内容的Fragment
 */
public class LeftMenuFragment extends BaseFragment implements OnItemClickListener {

    private List<DataBean> mData;
    private ListView mLvFragmentMenu;

    private LeftFragmentMenuAdapter mAdapter;

    @Override
    public View initView() {

        //设置侧滑菜单的布局样式
        View leftFragmentView = View.inflate(mContext, R.layout.fragment_left_menu, null);

        mLvFragmentMenu = (ListView) leftFragmentView.findViewById(R.id.lv_fragment_left_menu);

        /*TextView tv = new TextView(mContext);
        tv.setText("我是左侧菜单");
        return tv;*/

        mLvFragmentMenu.setOnItemClickListener(this);

        return leftFragmentView;
    }

    public void setData(List<DataBean> data) {
        //获取到侧滑菜单的数据
        this.mData = data;

        //实例化适配器,将需要的参数传递
        mAdapter = new LeftFragmentMenuAdapter(mContext, mData);

        //绑定适配器
        mLvFragmentMenu.setAdapter(mAdapter);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //记录当前位置

        mOnPagerSelectListener.onPagerSelect(position);

        mAdapter.notifyDataSetChanged();

        //当前可以需要知道选中了哪一个ContentFragment中的TabController
        ContentFragment contentFragment = ((MainActivity) mContext).getContentFragment();

        //将当前侧滑菜单的点击条目位置传递给ContentFragment  希望其显示对应的内容
        contentFragment.switchContent(position);

    }

    //使用接口回调来实现侧滑菜单的选中效果
    private OnPagerSelectListener mOnPagerSelectListener;

    /**
     * 定义一个接口  将当前选中的位置数据传递
     */
    public interface OnPagerSelectListener {

        void onPagerSelect(int pagerSelectPosition);
    }

    //定义方法  调用接口
    public void setOnPagerSelectListener(OnPagerSelectListener listener) {

        this.mOnPagerSelectListener = listener;
    }

    //将适配器抽离,自成一个适配器类
    /*class LeftFragmentMenuAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if (mData != null) {
                return mData.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
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
            DataBean bean = mData.get(position);

            //设置布局
            holder.mTV.setText(bean.title);

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
    }*/
}
