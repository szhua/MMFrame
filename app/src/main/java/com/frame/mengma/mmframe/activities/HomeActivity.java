package com.frame.mengma.mmframe.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.frame.mengma.mmframe.R;
import com.frame.mengma.mmframe.base.BaseActivity;
import com.frame.mengma.mmframe.entity.TabEntity;
import com.frame.mengma.mmframe.fragment.CourseBookingFragment;
import com.frame.mengma.mmframe.fragment.CourseBookingListFragment;
import com.frame.mengma.mmframe.fragment.GridImagesFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 主页 ：
 * 本界面实现现在主流的底部tab展现activity的例子 ；
 */

public class HomeActivity extends BaseActivity {

    @InjectView(R.id.tab)
    CommonTabLayout tab;
    @InjectView(R.id.container)
    LinearLayout container;

    private CourseBookingListFragment courseBookingListFragment  ;
    private CourseBookingFragment  courseBookingFragment ;
    private GridImagesFragment  gridImagesFragment  ;


    //titles的集合
    private String[] titles = new String[]{"主页", "产品世界", "购物车", "着装管家","我的礼遇"};
    private int[] mIconUnselectIds = {
            R.drawable.home_bottom_of, R.drawable.cp_bottom_of,
            R.drawable.gwc_bottom_of, R.drawable.zzgj_bottom_of,R.drawable.wdly_bottom_of};
    private int[] mIconSelectIds = {
            R.drawable.home_bottom_on, R.drawable.cp_bottom_on,
            R.drawable.gwc_bottom_on, R.drawable.zzgj_bottom_on,R.drawable.wdly_bottom_on};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.inject(this);

        //初始化
        if(courseBookingFragment==null){
            courseBookingFragment =new CourseBookingFragment() ;
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container, courseBookingFragment);
        ft.addToBackStack(null);
        ft.commit();


        setTab() ;

    }

    private void setTab() {

        for (int i = 0; i < titles.length; i++) {
            mTabEntities.add(new TabEntity(titles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        tab.setTabData(mTabEntities);

        tab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {

                switch (position){
                    case 0 :
                        toCourseBookingFragment();
                        break ;
                    case 1 :
                        toGridImagesFragment();
                        break ;
                    case 2 :
                        toCourseBookingListFragment();
                        break ;
                    case 3 :
                        toCourseBookingListFragment();
                        break ;
                    case 4 :
                        toCourseBookingFragment();
                        break ;
                }

            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    public void toCourseBookingListFragment(){
          if(courseBookingListFragment==null){
              courseBookingListFragment =new CourseBookingListFragment() ;
          }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, courseBookingListFragment);
        ft.addToBackStack(null);
        ft.commit();

    }


    public void toCourseBookingFragment(){
        if(courseBookingFragment==null){
            courseBookingFragment =new CourseBookingFragment() ;
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, courseBookingFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    public void toGridImagesFragment(){
        if(gridImagesFragment==null){
            gridImagesFragment =new GridImagesFragment() ;
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, gridImagesFragment);
        ft.addToBackStack(null);
        ft.commit();
    }



    @Override
    protected void onStart() {
        super.onStart();
        setHeadTitle("通用主界面");
    }
}
