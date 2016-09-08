package com.frame.mengma.mmframe.base;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.frame.mengma.mmframe.R;


/**
 * Created by szhua 2016/3/11 00:22
 */
/// TODO: 2016/3/11  
public abstract class BaseLoadMoreGridAdapter extends RecyclerView.Adapter {
    public final static int LOAD_PROGRESSBAR_VIEW_TYPE = -1;
    protected BaseLoadMoreGridFragment baseLoadMoreGridFragment;

    public BaseLoadMoreGridAdapter(BaseLoadMoreGridFragment baseLoadMoreGridFragment) {
        this.baseLoadMoreGridFragment = baseLoadMoreGridFragment;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateViewHolderInChildClass(parent, viewType);
//        View viewLoadMore = LayoutInflater.from(parent.getContext()).inflate(R.layout.loadmore_progressbar, parent, false);
//        if (viewType == LOAD_PROGRESSBAR_VIEW_TYPE){
//            return new ViewHolderLoadMore(viewLoadMore);
//        }else{
//            return onCreateViewHolderInChildClass(parent, viewType);
//        }
    }


    @Override
    public int getItemViewType(int position) {
        return getItemViewTypeInChildClass(position);
//        if (getItemCount() != 0 && position == getItemCount() - 1) {
//            return LOAD_PROGRESSBAR_VIEW_TYPE;
//        } else {
//            return getItemViewTypeInChildClass(position);
//        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBindViewHolderInChildClass(holder, position);
//        int viewType = getItemViewType(position);
//        if (viewType == LOAD_PROGRESSBAR_VIEW_TYPE){
//            ViewHolderLoadMore viewHolderLoadMore = (ViewHolderLoadMore) holder;
//            viewHolderLoadMore.changeState(position);
//        }else {
//            onBindViewHolderInChildClass(holder,position);
//        }
    }

    @Override
    public int getItemCount() {
        return getItemCountInChildClass();
//        if (getItemCountInChildClass() > 0) {
//            return getItemCountInChildClass() + 1;
//        } else {
//            return 0;
//        }
    }

//    class ViewHolderLoadMore extends RecyclerView.ViewHolder {
//        LinearLayout loading;
//        TextView loadFinish;
//
//        public ViewHolderLoadMore(View itemView) {
//            super(itemView);
//            loading = (LinearLayout) itemView.findViewById(R.id.loading);
//            loadFinish = (TextView) itemView.findViewById(R.id.loadfinish);
//        }
//
//        public void changeState(int postion) {
//            if (postion == 0) {
//                loading.setVisibility(View.GONE);
//                loadFinish.setVisibility(View.GONE);
//                return;
//            }
//            if (baseLoadMoreGridFragment.haveMore()) {
////                loading.setVisibility(View.VISIBLE);
//                baseLoadMoreGridFragment.swipeRefreshLayout.setRefreshing(true);
//                loadFinish.setVisibility(View.GONE);
//            } else {
//                loading.setVisibility(View.GONE);
////                loadFinish.setVisibility(View.VISIBLE);
//            }
//        }
//    }

    public abstract RecyclerView.ViewHolder onCreateViewHolderInChildClass(ViewGroup parent, int viewType);

    public abstract void onBindViewHolderInChildClass(RecyclerView.ViewHolder holder, int position);

    public abstract int getItemCountInChildClass();

    public abstract int getItemViewTypeInChildClass(int position);
}
