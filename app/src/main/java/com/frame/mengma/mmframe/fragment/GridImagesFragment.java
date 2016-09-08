package com.frame.mengma.mmframe.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.RecyclerView;

import com.frame.mengma.mmframe.adapter.ImageAdapter;
import com.frame.mengma.mmframe.base.BaseFragment;
import com.frame.mengma.mmframe.base.BaseLoadMoreGridAdapter;
import com.frame.mengma.mmframe.base.BaseLoadMoreGridFragment;
import com.frame.mengma.mmframe.util.Image;

import java.util.logging.Handler;

/**
 * Created by szhua on 2016/3/12
 * 本类使用网络上的图片作为例子：
 */
public class GridImagesFragment extends BaseLoadMoreGridFragment {


    private ImageAdapter adapter ;

    //若没有特殊需求请在oncreate中进行网络请求

    private android.os.Handler handler =new android.os.Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            swipeRefreshLayout.setRefreshing(false);
        }
    } ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter=new ImageAdapter(this);
        adapter.setImageThumbUrls(Image.imageThumbUrls);
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    @Override
    public boolean haveMore() {
        return false;
    }

    @Override
    public void loadMore() {

    }

    @Override
    public void refresh() {
       new Thread(){

           @Override
           public void run() {
               try {
                   sleep(4000);
                   handler.sendEmptyMessage(1) ;
                } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       }.start();
    }

    @Override
    public void onItemClick(int position) {

    }
}
