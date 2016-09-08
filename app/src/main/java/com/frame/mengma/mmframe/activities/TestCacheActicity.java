package com.frame.mengma.mmframe.activities;

import android.os.Bundle;
import android.text.TextUtils;

import com.frame.mengma.mmframe.AppHolder;
import com.frame.mengma.mmframe.R;
import com.frame.mengma.mmframe.base.BaseActivity;
import com.frame.mengma.mmframe.util.CacheUtil;
import com.frame.mengma.mmframe.util.SharedPrefsUtil;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 本Activity测试没有网络的情况下，加载缓存数据的方法 ；
 */

public class TestCacheActicity extends BaseActivity {
    @InjectView(R.id.header_iv)
    CircleImageView headerIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_cache_acticity);
        ButterKnife.inject(this);


        if(AppHolder.getInstance().getUser()!=null){
            Picasso.with(this).load(AppHolder.getInstance().getUser().getAvatar()).placeholder(R.drawable.img_loading).into(headerIv);
        //    tvName.setText(AppHolder.getInstance().getUser().getNickname());
        }else{
            if(!TextUtils.isEmpty(SharedPrefsUtil.getValue(this, CacheUtil.AVATARBIG, "erro"))){
                Picasso.with(this).load(SharedPrefsUtil.getValue(this,CacheUtil.AVATARBIG,"erro")).placeholder(R.drawable.img_loading).into(headerIv);
            }else{
                headerIv.setImageResource(R.drawable.img_loading);
            }

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        setHeadTitle("无网络情况测试缓存数据");
    }
}
