package com.frame.mengma.mmframe.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.frame.mengma.mmframe.R;
import com.frame.mengma.mmframe.base.BaseActivity;
import com.frame.mengma.mmframe.base.BaseFragment;
import com.frame.mengma.mmframe.entity.TabEntity;
import com.frame.mengma.mmframe.fragment.CourseBookingListFragment;
import com.frame.mengma.mmframe.fragment.GridImagesFragment;

import java.util.ArrayList;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 本案例列举了3个tab案例：《应该可以解决大部分的tab显示问题》
 * 自行参考代码：（或者参考demo ， 框架会有demo附件）
 */
public class TabExampleActivity extends BaseActivity {

    @InjectView(R.id.tl_1)
    CommonTabLayout tl1;
    @InjectView(R.id.tl_2)
    CommonTabLayout tl2;
    @InjectView(R.id.viewpager)
    ViewPager viewpager;
    @InjectView(R.id.tl_3)
    CommonTabLayout tl3;
    @InjectView(R.id.headLeftBtn)
    ImageView headLeftBtn;
    @InjectView(R.id.headTitle)
    TextView headTitle;
    @InjectView(R.id.headRightBt)
    ImageView headRightBt;
    @InjectView(R.id.head_layout)
    LinearLayout headLayout;

    //titles的集合
    private String[] titles = new String[]{"TAB1", "TAB2", "TAB3", "TAB4"};
    //滑动Viewpager中的Fragment；
    private BaseFragment[] fragments = new BaseFragment[]{new CourseBookingListFragment(), new GridImagesFragment(), new CourseBookingListFragment(), new GridImagesFragment()};
    //CustomTabEntity：tab相关实体类，封装显示的信息：包括图片等：
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    //图标集合
    private int[] mIconUnselectIds = {
            R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect,
            R.mipmap.tab_contact_unselect, R.mipmap.tab_more_unselect};
    private int[] mIconSelectIds = {
            R.mipmap.tab_home_select, R.mipmap.tab_speech_select,
            R.mipmap.tab_contact_select, R.mipmap.tab_more_select};
    private View mDecorView;


    @Override
    protected void onStart() {
        super.onStart();
        setHeadTitle("TAB EXAMPLE ");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_example);
        ButterKnife.inject(this);

        SampleAdapter adapter = new SampleAdapter(getSupportFragmentManager());
        viewpager.setAdapter(adapter);
        for (int i = 0; i < titles.length; i++) {
            mTabEntities.add(new TabEntity(titles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        //和viewpager无关的时候：
        tl1.setTabData(mTabEntities);
        tl3.setTabData(mTabEntities);

        //关联viewpager的时候（个人认为是比较合理的切换方式，不会出现闪屏的情况）
        settab2();

    }

    Random mRandom = new Random();

    private void settab2() {
        tl2.setTabData(mTabEntities);
        tl2.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewpager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {
                    tl2.showMsg(0, mRandom.nextInt(100) + 1);
//         UnreadMsgUtils.show(mTabLayout_2.getMsgView(0), mRandom.nextInt(100) + 1);
                }
            }
        });

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tl2.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewpager.setCurrentItem(1);

    }


    class SampleAdapter extends FragmentPagerAdapter {

        private String[] titles = new String[]{"TAB1", "TAB2", "TAB3", "TAB4"};
        private BaseFragment[] fragments = new BaseFragment[]{new CourseBookingListFragment(), new GridImagesFragment(), new CourseBookingListFragment(), new GridImagesFragment()};

        public SampleAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }
    }

}
