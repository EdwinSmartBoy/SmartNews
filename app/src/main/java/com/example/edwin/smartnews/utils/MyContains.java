package com.example.edwin.smartnews.utils;

/**
 * Created by edwin on 2016/4/19.
 * <p/>
 * 应用程序的常量
 */
public interface MyContains {

    String FILE_NAME = "SystemConfig"; //SP文件名

    String IP_ADDRESS = "188.188.4.35"; //网络访问的IP地址

    String GUIDE_COMPLETE = "guideComplete"; //判断是否设置向导完成

    String BASE_URL = "http://" + IP_ADDRESS + ":8080/zhbj/"; //默认的网络连接的URL

    String NEWS_CENTER = BASE_URL + "categories.json"; //新闻中心获取数据的URL
}
