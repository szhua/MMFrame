package com.frame.mengma.mmframe.fragment;

import android.os.Bundle;
import android.widget.BaseAdapter;

import com.frame.mengma.mmframe.adapter.CourseCommodityAdapter;
import com.frame.mengma.mmframe.base.BaseRefreshListFragment;
import com.frame.mengma.mmframe.dao.CourseListDao;
import com.frame.mengma.mmframe.entity.Goods;
import com.mengma.asynchttp.RequestCode;

import java.util.List;

/**
 * Created by szhua on 2016/3/15.
 *
 * 本案例关键实现基本的下拉刷新及加载更多  ；
 */
public class CourseBookingFragment extends BaseRefreshListFragment {

    private CourseCommodityAdapter adapter  ;
    private List<Goods> goodsList  ;
    private CourseListDao courseListDao = new CourseListDao(getContext(), this);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter =new CourseCommodityAdapter() ;
        adapter.setGoodslist(goodsList);
        courseListDao.getCourseList();
    }

    @Override
    public BaseAdapter getAdapter() {
        return adapter;
    }

    //先不看加载更多；《以后会添加dao.isHasMore()进行判断，具体看业务需求》
    @Override
    public boolean haveMore() {
        return false;
    }

    @Override
    public void loadMore() {

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
            goodsList =courseListDao.getGoodses() ;
            adapter.setGoodslist(goodsList);
            pullToRefreshListView.onRefreshComplete();
        }

    }
}
