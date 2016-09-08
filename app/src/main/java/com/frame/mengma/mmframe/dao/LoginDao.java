package com.frame.mengma.mmframe.dao;

import android.content.Context;

import com.fasterxml.jackson.databind.JsonNode;
import com.frame.mengma.mmframe.Constant;
import com.frame.mengma.mmframe.entity.User;
import com.loopj.android.http.RequestParams;
import com.mengma.asynchttp.IDao;
import com.mengma.asynchttp.JsonUtil;
import com.mengma.asynchttp.RequestCode;
import com.mengma.asynchttp.interf.INetResult;


import java.io.IOException;
import java.util.List;

/**
 * Created by szhua on 2016/3/11.
 */
public class LoginDao extends IDao {

    private List<User> user ;

    public List<User> getUser() {
        return user;
    }

    public LoginDao(Context context, INetResult iNetResult) {
        super(context, iNetResult);
    }

    public void login() {
        RequestParams params =new RequestParams() ;
        /**
         *username 用户登录名 手机号
         password 登陆密码 服务器端存储的是32位的MD5加密串
         devicetype 用户登录所用手机类型 1：苹果 2：安卓（方便服务器运维统计）
         lastloginversion 登陆所用的系统版本号 记录用户的登录版本，方便服务器运维统计
         */
        params.put("username","18800000000");
        params.put("password","123456");
        params.put("devicetype",2);
        params.put("lastloginversion","1.0.0");
        postRequest(Constant.BASE_URL+Constant.CLIENT_LOGIN,params, RequestCode.CODE_0);

    }

    @Override
    public void onRequestSuccess(JsonNode result, int requestCode) throws IOException {
         if(requestCode== RequestCode.CODE_0){
            user = JsonUtil.node2pojoList(result.findValue("infor"), User.class);
         }
    }
}
