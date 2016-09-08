package com.frame.mengma.mmframe.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.frame.mengma.mmframe.R;

import com.mengma.asynchttp.dialog.ProgressHUD;
import com.mengma.asynchttp.interf.INetResult;
import com.tuoyan.baselibrary.utils.UiUtil;


/**
 * Created by szhua on 2016/3/11 00:07
 * 功能同baseActivity ；
 */
public class BaseFragment extends Fragment implements INetResult {

    private TextView headTitle;
    private ImageView headLeftBtn;
    private ImageView headRightBtn;
    private ImageView headRightBtn2;
    private RelativeLayout layout;

    ProgressHUD mProgressHUD;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        headTitle = (TextView) view.findViewById(R.id.head_title);
//        headLeftBtn = (ImageView) view.findViewById(R.id.head_leftBtn);
//        headRightBtn = (ImageView) view.findViewById(R.id.head_rightBtn);
//        headRightBtn2 = (ImageView) view.findViewById(R.id.head_rightBtn2);
//        layout = (RelativeLayout) view.findViewById(R.id.head_layout);
//        if (headLeftBtn!=null){
//            headLeftBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    getActivity().finish(); //默认左按钮为返回按钮
//                }
//            });
//        }
    }

    /**
     * 设置标题栏标题
     *
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
     * 设置标题栏右边的第二个按钮
     *
     * @param drawableResId
     * @param listener
     */
    public void setHeadRightBtn2(int drawableResId, View.OnClickListener listener) {
        headRightBtn2.setImageResource(drawableResId);
        headRightBtn2.setOnClickListener(listener);
    }

    /**
     * 设置标题栏背景色
     *
     * @param resId
     */
    public void setHeadBackground(int resId) {
        if (layout != null){
            layout.setBackgroundResource(resId);
        }
    }

    @Override
    public void onRequestSuccess(int requestCode) {
        showProgress(false);
    }

    @Override
    public void onRequestError(int requestCode, String errorInfo) {
        showProgress(false);
    }


    @Override
    public void onRequestFaild(String errorNo, String errorMessage) {
        showProgress(false);
    }

    @Override
    public void onNoConnect() {
        showProgress(false);
        UiUtil.showLongToast(getContext(), "无网络连接");
    }

    public void showProgress(boolean show) {
        showProgressWithText(show, "加载中...");
    }

    public void showProgressWithText(boolean show, String message) {
        if (show) {
            mProgressHUD = ProgressHUD.show(getActivity(), message, true, true, null);
        } else {
            if (mProgressHUD != null) {
                mProgressHUD.dismiss();
            }
        }
    }
}
