package com.frame.mengma.mmframe.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.frame.mengma.mmframe.R;
import com.frame.mengma.mmframe.base.BaseActivity;
import com.tuoyan.baselibrary.widget.CircleIndicator;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class CycleImagesActivity extends BaseActivity {
    /**
     * created by szhua
     * 本案例实现轮播图效果：(详情参照demo 附件)
     * 关于indicator的样式请自己进行定义
     */
    @InjectView(R.id.viewpager)
    ViewPager viewpager;
    @InjectView(R.id.circleIndicator)
    CircleIndicator circleIndicator;

    private int[] imgs = new int[]{R.drawable.img1, R.drawable.img2, R.drawable.img3};
    private  SampleAdapter adapter ;

    /**
     * 以下是对轮播的处理：这里只是一种实现方案，更好的方案可自行定义；
     */
    //记录显示的viewpager item的位置；
    private int currentItem = 0;
    //记录viewpager是否滑动；
    private boolean play = false;
    private ScheduledExecutorService scheduledExecutorService;
    //handler 处理viewpager的显示；
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            try {
                viewpager.setCurrentItem(currentItem);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private class ScrollTask implements Runnable {
        public void run() {
            synchronized (viewpager) {
                currentItem = (currentItem + 1)%imgs.length;
                handler.obtainMessage().sendToTarget();
            }
        }
    }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycle_images);
        ButterKnife.inject(this);


        //绑定viewpager和indicator ；
        adapter =new SampleAdapter() ;
        viewpager.setAdapter(adapter);
        circleIndicator.setViewPager(viewpager);

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                currentItem = position;
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }


    @Override
    protected void onStop() {
        super.onStop();
        if (play) {
            // 当Activity不可见的时候停止切换
            scheduledExecutorService.shutdown();
            play = false;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        setHeadTitle("CycleImagesExaple");

        if (!play) {
            scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
            // 当Activity显示出来后，每两秒钟切换一次图片显示
            scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 4, TimeUnit.SECONDS);
            play = true;
        }
    }

    class SampleAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imgs.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            ImageView iv = new ImageView(CycleImagesActivity.this);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //   iv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT));
            iv.setImageResource(imgs[position]);
            container.addView(iv);
            return iv;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
         container.removeView((ImageView)object);
        }
    }


}
