package com.frame.mengma.mmframe.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.LinearLayout;

import com.frame.mengma.mmframe.R;
import com.frame.mengma.mmframe.base.BaseActivity;
import com.frame.mengma.mmframe.fragment.CourseBookingListFragment;
import com.frame.mengma.mmframe.fragment.GridImagesFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class GridImagesActivity extends BaseActivity {

    @InjectView(R.id.container_news)
    LinearLayout containerNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_images);
        ButterKnife.inject(this);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container_news, new GridImagesFragment());
        ft.commit();

    }

    @Override
    protected void onStart() {
        super.onStart();
        setHeadTitle("GRID VIEW 显示图片 2列");

    }
}
