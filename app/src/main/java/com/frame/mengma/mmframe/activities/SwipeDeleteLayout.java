package com.frame.mengma.mmframe.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.frame.mengma.mmframe.R;
import com.frame.mengma.mmframe.adapter.ListViewAdapter;
import com.frame.mengma.mmframe.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by szhua
 * 2016/3/14
 * 侧滑删除 ；
 */
public class SwipeDeleteLayout extends BaseActivity {

    @InjectView(R.id.listview)
    ListView listview;
    private ListViewAdapter adapter  ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_delete_layout);
        ButterKnife.inject(this);
        adapter =new ListViewAdapter(this) ;
        listview.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setHeadTitle("侧滑删除,操作");
    }



}
