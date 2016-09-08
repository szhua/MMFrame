package com.frame.mengma.mmframe.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.frame.mengma.mmframe.R;
import com.frame.mengma.mmframe.base.BaseActivity;
import com.frame.mengma.mmframe.util.LocationUtil;
import com.tuoyan.baselibrary.utils.AnimUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 本界面中：调用LocationUtil 实现定位后的位置信息的显示 ；
 * <p/>
 * 关于Activity的展现方式请视情况使用，Activity消失的动画，请在finish后自行调用，本处不在举例 ；
 */

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @InjectView(R.id.location)
    TextView location;
    @InjectView(R.id.option1)
    Button option1;
    @InjectView(R.id.option2)
    Button option2;
    @InjectView(R.id.option3)
    Button option3;
    @InjectView(R.id.option4)
    Button option4;
    @InjectView(R.id.option5)
    Button option5;
    @InjectView(R.id.option6)
    Button option6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        if (LocationUtil.getInstance().getBdLocation() != null) {
            location.setText(LocationUtil.getInstance().getBdLocation().getAddrStr());
        }
        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        option4.setOnClickListener(this);
        option5.setOnClickListener(this);
        option6.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setHeadTitle("主界面");
    }


    @Override
    public void onClick(View v) {

        if (v == option1) {
            Intent intent = new Intent(this, DropWaterListviewActivity.class);
            startActivity(intent);
        }


        if (v == option2) {
            Intent intent = new Intent(this, DropWaterListviewActivity.class);
            startActivity(intent);
            AnimUtil.intentPushUp(this);

        }
        //因为down必须是进入下一个Activity才可以 ；
        if (v == option3) {
            finish();
            AnimUtil.intentPushDown(this);
        }
        if (v == option4) {
            Intent intent = new Intent(this, DropWaterListviewActivity.class);
            startActivity(intent);
            AnimUtil.intentSlidIn(this);
        }
        if (v == option5) {
            Intent intent = new Intent(this, DropWaterListviewActivity.class);
            startActivity(intent);
            AnimUtil.intentSlidOut(this);
        }

        if(v==option6){
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            AnimUtil.intentSlidOut(this);
        }



    }
}
