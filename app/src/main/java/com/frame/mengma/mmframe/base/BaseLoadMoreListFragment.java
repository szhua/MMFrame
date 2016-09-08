package com.frame.mengma.mmframe.base;

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

import com.frame.mengma.mmframe.R;


/**
 * RecyclerView + SwipeRefreshLayout实现的下拉刷新、上拉更多，通过子类Fragment继承该类，实现相应功能
 * <p/>
 * /**
 * Created by szhua 2016/3/11 00:22
 */
public abstract class BaseLoadMoreListFragment extends BaseFragment implements OnRefreshListener {
    private RecyclerView recyclerView;
    protected SwipeRefreshLayout swipeRefreshLayout;
    private int lastVisiblePostion;
    private LinearLayoutManager layoutManager;

    protected static final int REFRESH_COMPLETE = 0;
    protected static final int LOAD_COMPLETE = 1;

    /**
     * 获取子类的Adapter
     *
     * @return
     */
    public abstract RecyclerView.Adapter getAdapter();

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
        View view = inflater.inflate(R.layout.fragment_base_loadmore, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeRefreshLayout.setOnRefreshListener(this);
        //设置进度动画的颜色
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light); //没转一圈换一个颜色
//        swipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);//设置进度圈的大小，只有两个值：DEFAULT、LARGE
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white); //圈的背景色

        // 设置位置，设置后swipeRefreshLayout.setRefreshing(true);才会显示
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources()  //applyDimension 该处意思是获取24dip
                        .getDisplayMetrics()));

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(getAdapter());

        //对上拉加载更多的处理；
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && getAdapter() != null &&
                        lastVisiblePostion + 1 == getAdapter().getItemCount()) {
                    if (haveMore()) {
                        loadMore();
                    } else {
                        getAdapter().notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisiblePostion = layoutManager.findLastVisibleItemPosition();
            }
        });
    }

    @Override
    public void onRefresh() {
        refresh();
    }
}
