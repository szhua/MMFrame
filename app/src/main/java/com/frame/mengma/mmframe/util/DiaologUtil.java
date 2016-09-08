package com.frame.mengma.mmframe.util;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.SimpleAdapter;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.Holder;
import com.orhanobut.dialogplus.OnClickListener;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;

/**
 * Created by szhua on 2016/3/17.
 */
public class DiaologUtil {

    public static void showDialog(Context context ,int holderViewId, int gravity,OnClickListener onClickListener) {
        Holder holder =new ViewHolder(holderViewId) ;
        final DialogPlus dialog = DialogPlus.newDialog(context)
                .setContentHolder(holder)
                .setGravity(gravity)
                .setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, new String[]{"asdfa"}))
                .setOnClickListener(onClickListener)
                .setOnDismissListener(null)
                .setOnCancelListener(null)
                .setExpanded(false)
                .setCancelable(true)
                .create();
        dialog.show();
    }

}
