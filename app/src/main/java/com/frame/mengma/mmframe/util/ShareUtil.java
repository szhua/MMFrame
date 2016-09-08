package com.frame.mengma.mmframe.util;

import android.app.Activity;
import android.content.Context;


import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;


/**
 *Created by szhua
 * 2016/3/14
 */
public class ShareUtil {


    public static void share(Activity context, String content, String title, int imgResourse, String url) {


        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        //微信 appid appsecret

        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        // QQ和Qzone appid appkey

        final SHARE_MEDIA[] displaylist = new SHARE_MEDIA[]
                {
                        SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE,
                        SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE
                };
        new ShareAction(context).setDisplayList(displaylist)
                .withText(content)
                .withTitle(title)
                .withMedia(new UMImage(context, imgResourse))
                .withTargetUrl("http://42.96.145.73:8988/down.html")
                .open();
    }


    public static void share(Activity context, String content, String title, String imgUrl, String url) {


        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        //微信 appid appsecret

        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        // QQ和Qzone appid appkey

        final SHARE_MEDIA[] displaylist = new SHARE_MEDIA[]
                {
                        SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE,
                        SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE
                };
        new ShareAction(context).setDisplayList(displaylist)
                .withText(content)
                .withTitle(title)
                .withMedia(new UMImage(context, imgUrl))
                .withTargetUrl("http://42.96.145.73:8988/down.html")
                .open();
    }


}
