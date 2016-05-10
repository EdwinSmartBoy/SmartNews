package com.example.edwin.smartnews.controller.newscenter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Edwin on 2016/04/23.
 * <p/>
 * 侧滑菜单的组图显示的内容
 */
public class PhotoController extends BaseNewsMenuController {

    //调用父类的构造方法
    public PhotoController(Context context) {
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

        tv.setText("组图");

        tv.setTextSize(25);

        tv.setGravity(Gravity.CENTER);

        return tv;
    }
}
