package com.frame.mengma.mmframe.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.frame.mengma.mmframe.R;
import com.frame.mengma.mmframe.base.BaseActivity;
import com.frame.mengma.mmframe.fragment.CourseBookingListFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * 本框架展示上拉下拉recycleview的时候是基于fragment的基础上实现的。
 * 基本步骤：
 * 创建activity：
 * 嵌套Framgment；
 * 具体的业务在fragment中实现；
 */

public class CourseBookingListActivity extends BaseActivity {

    @InjectView(R.id.container_news)
    LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_booking_list);
        ButterKnife.inject(this);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container_news, new CourseBookingListFragment());
        ft.commit();
    }


    @Override
    protected void onStart() {
        super.onStart();
        setHeadTitle("课程列表");
    }
}
