package com.frame.mengma.mmframe;


import com.frame.mengma.mmframe.entity.User;

/**
 * 全局变量,单例
 * Create by szhua 2016/3/11
 */
public class AppHolder {

    private User user ;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    private static AppHolder ourInstance = new AppHolder();

    public static AppHolder getInstance() {
        return ourInstance;
    }

    private AppHolder() {
    }


}
