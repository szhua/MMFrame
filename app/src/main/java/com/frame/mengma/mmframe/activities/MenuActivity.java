package com.frame.mengma.mmframe.activities;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.frame.mengma.mmframe.R;
import com.frame.mengma.mmframe.base.BaseActivity;
import com.frame.mengma.mmframe.util.DiaologUtil;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnClickListener;
import com.tuoyan.baselibrary.utils.UiUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 本类描述一些菜单的显示方式 ；
 */
public class MenuActivity extends BaseActivity implements View.OnClickListener {

    @InjectView(R.id.sub1)
    Button sub1;
    @InjectView(R.id.sub2)
    Button sub2;
    @InjectView(R.id.sub3)
    Button sub3;
    @InjectView(R.id.sub4)
    Button sub4;
    @InjectView(R.id.tab1)
    Button tab1;
    @InjectView(R.id.tab2)
    Button tab2;
    @InjectView(R.id.tab3)
    Button tab3;
    @InjectView(R.id.menu1)
    RelativeLayout menu1;
    @InjectView(R.id.tab)
    Button tab;
    @InjectView(R.id.menu2)
    RelativeLayout menu2;
    @InjectView(R.id.childMenu)
    LinearLayout childMenu;
    @InjectView(R.id.sub5)
    Button sub5;
    @InjectView(R.id.sub6)
    Button sub6;
    @InjectView(R.id.sub7)
    Button sub7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.inject(this);

        setListener();

    }

    @Override
    protected void onStart() {
        super.onStart();
        setHeadTitle("菜单显示");
    }

    private void setListener() {
        sub1.setOnClickListener(this);
        sub2.setOnClickListener(this);
        sub3.setOnClickListener(this);
        sub4.setOnClickListener(this);
        menu1.setOnClickListener(this);
        menu2.setOnClickListener(this);
        tab.setOnClickListener(this);
        tab1.setOnClickListener(this);
        tab2.setOnClickListener(this);
        tab3.setOnClickListener(this);
        childMenu.setOnClickListener(this);

        sub5.setOnClickListener(this);
        sub6.setOnClickListener(this);
        sub7.setOnClickListener(this);


        //sub6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == sub1) {
            UiUtil.show_menu_alpha(this, menu2);
        }

        if (v == menu2) {
            UiUtil.hide_menu_alpha(this, menu2);
        }
        if (v == sub2) {
            UiUtil.show_menu(this, menu1);
        }
        if (v == menu1) {
            UiUtil.hide_menu(this, menu1);
        }
        if (v == tab) {
            UiUtil.hide_menu(this, menu1);
        }

        if (v == sub3) {
            UiUtil.show_menu_alpha(this, menu1);
            UiUtil.show_menu(this, childMenu);
        }
        if (v == sub4) {
            UiUtil.show_menu_scale(this, tab);
            UiUtil.show_menu_alpha(this, menu2);
        }
        if (v == sub5) {
            DiaologUtil.showDialog(this, R.layout.item_menu, Gravity.BOTTOM, new OnClickListener() {
                @Override
                public void onClick(DialogPlus dialog, View view) {
                    switch (view.getId()) {
                        case R.id.sub1:
                            UiUtil.showLongToast(MenuActivity.this ,"SUB1");
                            dialog.dismiss();
                            break;
                        case R.id.sub2:
                            UiUtil.showLongToast(MenuActivity.this ,"SUB2");
                            dialog.dismiss();
                            break;
                        case R.id.sub3:
                            UiUtil.showLongToast(MenuActivity.this ,"SUB3");
                            dialog.dismiss();
                            break;
                        case R.id.sub4:
                            UiUtil.showLongToast(MenuActivity.this ,"SUB4");
                            dialog.dismiss();
                            break;
                        case R.id.sub5:
                            UiUtil.showLongToast(MenuActivity.this ,"SUB5");
                            dialog.dismiss();
                            break;
                    }
                }
            });
        }
        if (v == sub6) {
            DiaologUtil.showDialog(this, R.layout.item_menu, Gravity.CENTER, new OnClickListener() {
                @Override
                public void onClick(DialogPlus dialog, View view) {
                    switch (view.getId()) {
                        case R.id.sub1:
                            UiUtil.showLongToast(MenuActivity.this ,"SUB1");
                            dialog.dismiss();
                            break;
                        case R.id.sub2:
                            UiUtil.showLongToast(MenuActivity.this ,"SUB2");
                            dialog.dismiss();
                            break;
                        case R.id.sub3:
                            UiUtil.showLongToast(MenuActivity.this ,"SUB3");
                            dialog.dismiss();
                            break;
                        case R.id.sub4:
                            UiUtil.showLongToast(MenuActivity.this ,"SUB4");
                            dialog.dismiss();
                            break;
                        case R.id.sub5:
                            UiUtil.showLongToast(MenuActivity.this ,"SUB5");
                            dialog.dismiss();
                            break;
                    }
                }
            });
        }
        if (v == sub7) {
            DiaologUtil.showDialog(this, R.layout.item_menu, Gravity.TOP, new OnClickListener() {
                @Override
                public void onClick(DialogPlus dialog, View view) {
                    switch (view.getId()) {
                        case R.id.sub1:
                            UiUtil.showLongToast(MenuActivity.this ,"SUB1");
                            dialog.dismiss();
                            break;
                        case R.id.sub2:
                            UiUtil.showLongToast(MenuActivity.this ,"SUB2");
                            dialog.dismiss();
                            break;
                        case R.id.sub3:
                            UiUtil.showLongToast(MenuActivity.this ,"SUB3");
                            dialog.dismiss();
                            break;
                        case R.id.sub4:
                            UiUtil.showLongToast(MenuActivity.this ,"SUB4");
                            dialog.dismiss();
                            break;
                        case R.id.sub5:
                            UiUtil.showLongToast(MenuActivity.this ,"SUB5");
                            dialog.dismiss();
                            break;
                    }


                }
            });
        }


    }
}
