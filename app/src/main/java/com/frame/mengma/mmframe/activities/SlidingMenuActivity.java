package com.frame.mengma.mmframe.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.frame.mengma.mmframe.R;
import com.frame.mengma.mmframe.base.BaseActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 侧滑demo
 * 具体请自行参考附件demo ；
 */
public class SlidingMenuActivity extends BaseActivity {

    @InjectView(R.id.iv)
    ImageView iv;
    private SlidingMenu menu;
    private View menuView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_menu);
        ButterKnife.inject(this);

        menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT_OF);
//      menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowDrawable(R.mipmap.ic_launcher);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        //  menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        menu.setMenu(R.layout.menu_layout);
//      menu.setMode(SlidingMenu.RIGHT_OF);
        menuView = menu.getMenu();
        initMenuView(menuView);

        //控制菜单的开关
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.toggle();
            }
        });

    }

    //在这里进行对侧滑菜单的加工处理；
    private void initMenuView(View menuView) {
        //// TODO: 2016/3/14
    }


}
