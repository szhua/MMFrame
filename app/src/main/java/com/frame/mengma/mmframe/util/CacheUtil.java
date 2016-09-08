package com.frame.mengma.mmframe.util;

import android.content.Context;


import com.frame.mengma.mmframe.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 *Create by szhua 2016/3/11 0:23
 * 页面数据缓存,这里使用的是SharedPreference进行缓存 ；
 */
public class CacheUtil {
    public static final String HOME_LEFT_IMAGE = "home_left_image";
  
    public static final String USER_NAME ="username" ;
    public static final String NICKNAME ="nickname" ;
    public static final String CLIENT_ID ="client_id" ;
    public static final String TOKEN ="token" ;
    public static final String AVATARBIG ="avatarbig" ;
    public static final String LNG ="username" ;
    public static final String LAT ="username" ;
    public static final String FEEACCOUNT ="username" ;
    
    //关于对用户数据的保存 ；
    /**
     * 基本实现的方式为：
     * 用户第一次进入App的时候需要进行Login ;
     * 当退出程序的时候User数据会清空，
     * 当再次打开程序的时候，会利用缓存的数据进行登录 ；
     *
     */
       // 缓存用户的信息 ；这个只是缓存作用，获得用户的信息请自动从去全局变量User中去获得；

    /**字段请视情况而定 ；
     * @param context
     * @param user
     */
        public static void saveUserData(Context context, User user) {
        if (user == null) {
            return;
        }
            //各种为空的情况请自行判定
            SharedPrefsUtil.putValue(context,USER_NAME,user.getUsername());
            SharedPrefsUtil.putValue(context,NICKNAME,user.getNickname());
            SharedPrefsUtil.putValue(context,CLIENT_ID,user.getId());
            SharedPrefsUtil.putValue(context,TOKEN,user.getUsername());
            SharedPrefsUtil.putValue(context,AVATARBIG,user.getAvatarbig());
            SharedPrefsUtil.putValue(context,LNG,user.getLng());
            SharedPrefsUtil.putValue(context,LAT,user.getLat());
            SharedPrefsUtil.putValue(context,FEEACCOUNT,user.getFeeaccount());

    }


    
    //// TODO: 2016/3/18  
    //对页面数据的保存，有缓存数据的时候，先显示缓存的数据
    // （本案例情况是没有网络的情况下显示缓存的数据，而有网络的情况下会先显示缓存的数据然后从网络获得数据展现给客户）
    


//    /**
//     * 分类页面数据缓存
//     *
//     * @param context
//     * @param categories
//     */
//    public static void saveCategoryData(Context context, List<Category> categories) {
//        if (categories == null) {
//            return;
//        }
//        int num = 0;
//        if (categories.size() > 30) { //最多保存30条
//        num = 30;
//        } else {
//            num = categories.size();
//        }
//        SharedPrefsUtil.putValue(context, CATEGORY_NUM, num);
//        for (int i = 0; i < num; i++) {
//            SharedPrefsUtil.putValue(context, CATEGORY_NAME + i, categories.get(i).getName());
//            SharedPrefsUtil.putValue(context, CATEGORY_IMAGE + i, categories.get(i).getImgPath());
//            SharedPrefsUtil.putValue(context, CATEGORY_ID + i, categories.get(i).getId());
//        }
//    }

//    /**
//     * 获取缓存的分类数据
//     *
//     * @param context
//     * @return
//     */
//    public static List<Category> getCategoryData(Context context) {
//        List<Category> categories = new ArrayList<>();
//        int num = SharedPrefsUtil.getValue(context, CATEGORY_NUM, 0);
//        for (int i = 0; i < num; i++) {
//            Category category = new Category();
//            category.setImgPath(SharedPrefsUtil.getValue(context, CATEGORY_IMAGE + i, "http://"));
//            category.setId(SharedPrefsUtil.getValue(context, CATEGORY_ID + i, null));
//            category.setName(SharedPrefsUtil.getValue(context, CATEGORY_NAME + i, null));
//            categories.add(category);
//        }
//        return categories;
//    }

//    /**
//     * 首页数据缓存
//     */
//    public static void saveHomeData(Context context, HomeData homeData) {
//        if (homeData == null) {
//            return;
//        }
//        SharedPrefsUtil.putValue(context, HOME_LEFT_IMAGE, homeData.getLeftImgPath());
//        SharedPrefsUtil.putValue(context, HOME_RIGHT_IMAGE, homeData.getRightImgPath());
//        if (homeData.getCarouselList() != null) {
//            SharedPrefsUtil.putValue(context, HOME_AD_NUM, homeData.getCarouselList().size());
//            for (int i = 0; i < homeData.getCarouselList().size(); i++) {
//                SharedPrefsUtil.putValue(context, HOME_AD_IMAGE + i, homeData.getCarouselList().get(i).getImgPath());
//                SharedPrefsUtil.putValue(context, HOME_AD_TITLE + i, homeData.getCarouselList().get(i).getTitle());
//                SharedPrefsUtil.putValue(context, HOME_AD_URL + i, homeData.getCarouselList().get(i).getUrl());
//            }
//        }
//    }

//    /**
//     * 读取首页缓存数据
//     *
//     * @param context
//     * @return
//     */
//    public static HomeData getHomeData(Context context) {
//        HomeData homeData = new HomeData();
//        homeData.setLeftImgPath(SharedPrefsUtil.getValue(context, HOME_LEFT_IMAGE, "http://"));
//        homeData.setRightImgPath(SharedPrefsUtil.getValue(context, HOME_RIGHT_IMAGE, "http://"));
//        int adNum = SharedPrefsUtil.getValue(context, HOME_AD_NUM, 0);
//        List<HomePagerItem> adList = new ArrayList<>();
//        for (int i = 0; i < adNum; i++) {
//            HomePagerItem item = new HomePagerItem();
//            item.setImgPath(SharedPrefsUtil.getValue(context, HOME_AD_IMAGE + i, "http://"));
//            item.setTitle(SharedPrefsUtil.getValue(context, HOME_AD_TITLE + i, null));
//            item.setUrl(SharedPrefsUtil.getValue(context, HOME_AD_URL + i, "http://"));
//            adList.add(item);
//        }
//        homeData.setCarouselList(adList);
//        return homeData;
//    }
}
