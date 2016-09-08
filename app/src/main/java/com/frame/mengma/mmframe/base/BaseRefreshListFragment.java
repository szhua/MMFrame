package com.frame.mengma.mmframe.base;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.frame.mengma.mmframe.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;


/**
 * 基本的ReshLisview实现下拉刷新和上啦加载更多 ；
 * 想了解更多请参照：https://github.com/chrisbanes/Android-PullToRefresh
 * <p/>
 * /**
 * Created by szhua  2016/3/15
 */
public abstract class BaseRefreshListFragment extends BaseFragment implements PullToRefreshListView.OnRefreshListener2 {
    protected PullToRefreshListView pullToRefreshListView;

    private int resourseId;

    public void setRefreshIcon(int resourseId) {
        this.resourseId = resourseId;
    }

    /**
     * 获取子类的Adapter
     *
     * @return
     */
    public abstract BaseAdapter getAdapter();

    /**
     * 判断是否还有更多（需要子类进行重写《从dao的状态进行判断》）
     *
     * @return
     */
    public abstract boolean haveMore();

    /**
     * 加载更多（需要子类进行重写）
     */
    public abstract void loadMore();

    /**
     * SwipeRefreshLayout下拉刷新（需要子类进行重写）
     */
    public abstract void refresh();

    /**
     * ItemClick
     *
     * @param position
     */
    public abstract void onItemClick(int position);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base_refresh, container, false);
        pullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.refreshlistview);
        return view;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /**
         * 这一步是设置加载的图片，需要以后进行更改：
         */
        if (resourseId != 0) {
            Drawable drawable = getContext().getDrawable(resourseId);
            pullToRefreshListView.setLoadingDrawable(drawable);
        }
        pullToRefreshListView.setOnRefreshListener(this);
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        pullToRefreshListView.setAdapter(getAdapter());

    }
    /**
     * 加载完成数据的处理请自行处理 ;
     * @param refreshView
     */
    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        refresh();
    }

    /**
     * 加载万数据后的处理请自行解决 ；
     *
     * @param refreshView
     */
    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        loadMore();
    }
}
