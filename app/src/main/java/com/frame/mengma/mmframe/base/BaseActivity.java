package com.frame.mengma.mmframe.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.frame.mengma.mmframe.R;

import com.mengma.asynchttp.Http;
import com.mengma.asynchttp.dialog.ProgressHUD;
import com.mengma.asynchttp.interf.INetResult;
import com.tuoyan.baselibrary.utils.UiUtil;

/**
 *Create by szhua 2016/3/11
 * 本类为activity的基类
 * 包括：
 * 1： 显示加载progress ；
 * 2： 对于网络请求错误信息的处理 （若想对其进行更改，请自行在子activity中重写）
 * 3：对于共同界面的统一处理：包括header上面的title的处理，返回键的处理，等。 具体请参照自己的需求；
 * 4：
 */
public class BaseActivity extends AppCompatActivity implements INetResult {
    ProgressHUD mProgressHUD;
    private TextView headTitle;
    private ImageView headLeftBtn;
    private ImageView headRightBtn;
    private LinearLayout layout;

    @Override
    protected void onStart() {
        super.onStart();
        headTitle = (TextView) findViewById(R.id.headTitle);
        headLeftBtn = (ImageView) findViewById(R.id.headLeftBtn);
        headRightBtn = (ImageView) findViewById(R.id.headRightBt);

        layout = (LinearLayout) findViewById(R.id.head_layout);
        if (headLeftBtn != null) {
            headLeftBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish(); //默认左按钮为返回按钮
                }
            });
        }

    }

    /**
     * 设置标题栏标题
     * @param title
     */
    public void setHeadTitle(String title) {
        if (headTitle != null) {
            headTitle.setText(title);
        }
    }

    /**
     * 修改标题栏左按钮
     *
     * @param drawableResId
     * @param listener
     */
    public void setHeadLeftBtn(int drawableResId, View.OnClickListener listener) {
        headLeftBtn.setImageResource(drawableResId);
        headLeftBtn.setOnClickListener(listener);
    }

    /**
     * 设置标题栏左按钮是否可见，默认为可见的返回按钮
     *
     * @param isVisibale
     */
    public void setHeadLeftBtnVisibility(boolean isVisibale) {
        if (headLeftBtn != null) {
            if (isVisibale) {
                headLeftBtn.setVisibility(View.VISIBLE);
            } else {
                headLeftBtn.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 设置标题栏右边的第一个按钮
     *
     * @param drawableResId
     * @param listener
     */
    public void setHeadRightBtn(int drawableResId, View.OnClickListener listener) {
        headRightBtn.setImageResource(drawableResId);
        headRightBtn.setOnClickListener(listener);
    }



    /**
     * 设置标题栏背景色
     *
     * @param resId
     */
    public void setHeadBackground(int resId) {
        layout.setBackgroundResource(resId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onRequestSuccess(int requestCode) {
        showProgress(false);
    }

    @Override
    public void onRequestError(int requestCode, String errorInfo) {
        UiUtil.showLongToast(this,errorInfo);
        showProgress(false);
    }


    @Override
    public void onRequestFaild(String errorNo, String errorMessage) {
        UiUtil.showLongToast(this,errorMessage);
        showProgress(false);
    }

    @Override
    public void onNoConnect() {
        showProgress(false);
       UiUtil.showLongToast(this, "无网络连接");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Http.getInstance().cancelRequests(this);
    }

    /**
     * 显示加载进度条
     *
     * @param show
     */
    public void showProgress(boolean show) {
        showProgressWithText(show, "加载中...");
    }

    /**
     * 显示加载进度条
     *
     * @param show
     * @param message
     */
    public void showProgressWithText(boolean show, String message) {
        if (show) {
            mProgressHUD = ProgressHUD.show(this, message, true, true, null);
        } else {
            if (mProgressHUD != null) {
                mProgressHUD.dismiss();
            }
        }
    }
}
