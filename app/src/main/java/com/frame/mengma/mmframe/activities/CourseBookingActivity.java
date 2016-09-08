package com.frame.mengma.mmframe.activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.frame.mengma.mmframe.R;
import com.frame.mengma.mmframe.base.BaseActivity;
import com.frame.mengma.mmframe.fragment.CourseBookingFragment;
import com.frame.mengma.mmframe.fragment.CourseBookingListFragment;

public class CourseBookingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_booking_list);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container_news, new CourseBookingFragment());
        ft.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setHeadTitle("HanMarkPullRefresh");
    }
}
