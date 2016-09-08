package com.frame.mengma.mmframe.base;

import android.app.Application;

import com.frame.mengma.mmframe.util.LocationUtil;


/**
 * Created by szhua 2016/3/11 00:22
 *
 * 关于定位 ：
 * 百度现在是将百度地图的api分离开来，而本案例使用的是LBS版本的sdk，略有不同，但是基本的功能是一样的，详情请参照官方文档
 *  1.jar包 so文件一定要相对应正确才能够运行
 *  2。gradle 文件中必须添加：   不然so的引用不会起作用，运行的话不会崩掉，但是定位不成功，地图不会显示，《但是会报出：errocode 162》
 *  ---------------------------------------------------
   sourceSets {
 main {
 jniLibs.srcDirs = ['libs']
 }
 }
 --------------------------------------------------
 * 3.关于地图的显示请参照模板 ，打包好之后观看效果 ；
 * 4.Sdk的初始化,按照规范实在Application中进行初始化 ；
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LocationUtil.getInstance().init(getApplicationContext());//百度定位
        LocationUtil.getInstance().startLocation();
    }
}
