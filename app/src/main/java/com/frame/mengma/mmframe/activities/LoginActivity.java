package com.frame.mengma.mmframe.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.frame.mengma.mmframe.AppHolder;
import com.frame.mengma.mmframe.R;
import com.frame.mengma.mmframe.base.BaseActivity;
import com.frame.mengma.mmframe.dao.LoginDao;
import com.frame.mengma.mmframe.entity.User;
import com.frame.mengma.mmframe.util.CacheUtil;
import com.frame.mengma.mmframe.util.ShareUtil;
import com.tuoyan.baselibrary.utils.UiUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * careate by szhua ；
 * <p>
 * 本activity为本架构网络请求的示例调用，其他的界面调用请参考；
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    @InjectView(R.id.login)
    Button login;
    @InjectView(R.id.img_forgetpwd)
    TextView imgForgetpwd;
    @InjectView(R.id.ll2_login)
    RelativeLayout ll2Login;
    @InjectView(R.id.img_regist)
    TextView imgRegist;
    @InjectView(R.id.sub1)
    Button sub1;
    @InjectView(R.id.sub2)
    Button sub2;
    @InjectView(R.id.sub3)
    Button sub3;
    @InjectView(R.id.sub4)
    Button sub4;
    @InjectView(R.id.sub5)
    Button sub5;
    @InjectView(R.id.sub6)
    Button sub6;
    @InjectView(R.id.sub7)
    Button sub7;
    @InjectView(R.id.sub8)
    Button sub8;
    private LoginDao dao = new LoginDao(this, this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        setListener();
    }

    private void setListener() {

        login.setOnClickListener(this);
        imgForgetpwd.setOnClickListener(this);
        imgRegist.setOnClickListener(this);
        sub1.setOnClickListener(this);
        sub2.setOnClickListener(this);
        sub3.setOnClickListener(this);
        sub4.setOnClickListener(this);
        sub5.setOnClickListener(this);
        sub6.setOnClickListener(this);
        sub7.setOnClickListener(this);
        sub8.setOnClickListener(this);
    }

    //请求后的处理结果
    @Override
    public void onRequestSuccess(int requestCode) {

        Log.i("szhua", dao.getUser().get(0).toString());
        User user = dao.getUser().get(0);
        AppHolder.getInstance().setUser(user);
        UiUtil.showLongToast(this, dao.getUser().get(0).getNickname());

          //保存用户数据；
          CacheUtil.saveUserData(this,user);

        Intent intent = new Intent(this, UserCenterActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onStart() {
        super.onStart();
        setHeadTitle("登录");
    }

    //当请求失败的时候，即服务器端返回乱码（啥都有的东西）时 ；
    @Override
    public void onRequestFaild(String errorNo, String errorMessage) {
        Log.i("szhua", "erro2");
    }

    @Override
    public void onClick(View v) {
        if (v == login) {
            dao.login();
        }
        if (v == imgForgetpwd) {
            Intent intent = new Intent(this, CourseBookingListActivity.class);
            startActivity(intent);
        }

        if (v == imgRegist) {
            Intent intent = new Intent(this, GridImagesActivity.class);
            startActivity(intent);
        }

        if (v == sub1) {
            Intent intent = new Intent(this, TabExampleActivity.class);
            startActivity(intent);
        }
        if (v == sub2) {
            Intent intent = new Intent(this, CycleImagesActivity.class);
            startActivity(intent);
        }
        if (v == sub3) {
            Intent intent = new Intent(this, SwipeDeleteLayout.class);
            startActivity(intent);
        }
        if (v == sub4) {
            ShareUtil.share(this, "MengmaTest", "MengmaTest", R.drawable.bird, "https://github.com/daimajia/AndroidSwipeLayout/wiki/usage");
        }
        if (v == sub5) {
            Intent intent = new Intent(this, SlidingMenuActivity.class);
            startActivity(intent);
        }
        if (v == sub6) {
            Intent intent = new Intent(this, CourseBookingActivity.class);
            startActivity(intent);
        }

        if (v == sub7) {
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        }

        if (v == sub8) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }


    }
}
