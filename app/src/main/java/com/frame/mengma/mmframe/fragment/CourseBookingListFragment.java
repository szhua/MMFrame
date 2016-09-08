package com.frame.mengma.mmframe.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frame.mengma.mmframe.adapter.CourseCommodityListAdapter;
import com.frame.mengma.mmframe.base.BaseLoadMoreListFragment;
import com.frame.mengma.mmframe.dao.CourseListDao;
import com.frame.mengma.mmframe.entity.Goods;
import com.mengma.asynchttp.RequestCode;


import java.util.List;


/**
 * Created by szhua on 2016/3/11.
 * <p/>
 * 此fragment模拟展示上拉加载更多下拉刷新功能，具体需求请自己定义 ；
 */
public class CourseBookingListFragment extends BaseLoadMoreListFragment {

    private CourseListDao courseListDao = new CourseListDao(getContext(), this);
    private CourseCommodityListAdapter adapter;
    private List<Goods> goodses;

    //若是没有特殊的需求，请在onCreate中进行
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        courseListDao.getCourseList();
        showProgress(true);
        adapter = new CourseCommodityListAdapter(this);
        adapter.setGoodses(goodses);
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
 //// TODO: 2016/3/11
 // coming soon
    }

    @Override
    public void refresh() {
     courseListDao.firstRequest();
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onRequestSuccess(int requestCode) {
        super.onRequestSuccess(requestCode);
        if(requestCode== RequestCode.CODE_0){
            swipeRefreshLayout.setRefreshing(false);
            showProgress(false);
            goodses =courseListDao.getGoodses() ;
            adapter.setGoodses(goodses);
        }

    }
}
