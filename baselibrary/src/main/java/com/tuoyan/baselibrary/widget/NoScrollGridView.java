package com.tuoyan.baselibrary.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;
import android.widget.ListView;

/**
 * 可嵌套在ScrollView中使用的GridView
 * （解决加入该控件后，ScrollView默认不是置顶状态的方法是：gridView.setFocusable(false);）
 *
 * @author dph
 */
public class NoScrollGridView extends GridView {
    public NoScrollGridView(Context context) {
        super(context);
    }

    public NoScrollGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScrollGridView(Context context, AttributeSet attrs,
                            int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    /**
     * 重写该方法，达到使ListView适应ScrollView的效果
     */
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
