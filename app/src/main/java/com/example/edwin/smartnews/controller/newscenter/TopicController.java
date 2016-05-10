package com.example.edwin.smartnews.controller.newscenter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Edwin on 2016/04/23.
 * <p/>
 * 侧滑菜单的专题显示的内容
 */
public class TopicController extends BaseNewsMenuController {

    //调用父类的构造方法
    public TopicController(Context context) {
        super(context);
    }

    /**
     * 初始化界面
     *
     * @param context
     * @return
     */
    @Override
    protected View initView(Context context) {


        TextView tv = new TextView(context);

        tv.setText("专题");

        tv.setTextSize(25);

        tv.setGravity(Gravity.CENTER);

        return tv;
    }
}
