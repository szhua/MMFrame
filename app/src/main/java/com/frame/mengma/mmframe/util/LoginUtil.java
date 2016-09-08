package com.frame.mengma.mmframe.util;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.frame.mengma.mmframe.AppHolder;
import com.frame.mengma.mmframe.activities.LoginActivity;
import com.tuoyan.baselibrary.utils.AnimUtil;


/**
 * Created by szhua 2016/3/11
 */
public class LoginUtil {
    /**
     * 判断是否登录
     * @param context
     * @param isIntent  如果没有登录，是否需要跳转到登录页面
     * @return
     */
    public static boolean checkLogin(Activity context, boolean isIntent){
        if (AppHolder.getInstance().getUser() == null || TextUtils.isEmpty(AppHolder.getInstance().getUser().getId())){
            if (isIntent){
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
                AnimUtil.intentPushUp(context);
            }
            return false;
        }
        return true;
    }
}
