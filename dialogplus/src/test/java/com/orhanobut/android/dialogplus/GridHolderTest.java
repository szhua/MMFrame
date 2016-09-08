package com.orhanobut.android.dialogplus;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.orhanobut.dialogplus.BuildConfig;
import com.orhanobut.dialogplus.GridHolder;
import com.orhanobut.dialogplus.HolderAdapter;
import com.orhanobut.dialogplus.OnHolderListener;
import com.orhanobut.dialogplus.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class GridHolderTest {

  Context context;

  @Before public void setup() {
    context = Robolectric.setupActivity(Activity.class);
  }

  private GridHolder getHolder() {
    GridHolder holder = new GridHolder(3);
    LayoutInflater layoutInflater = LayoutInflater.from(context);
    holder.getView(layoutInflater, new LinearLayout(context));
    return holder;
  }

  @Test public void init() {
    assertThat(getHolder()).isInstanceOf(HolderAdapter.class);
    assertThat(getHolder()).isNotNull();
  }

  @Test public void testViewInflation() {
    GridHolder holder = new GridHolder(3);
    LayoutInflater layoutInflater = LayoutInflater.from(context);
    View view = holder.getView(layoutInflater, new LinearLayout(context));

    assertThat(view).isNotNull();
    assertThat(holder.getInflatedView().getId()).isEqualTo(R.id.dialogplus_list);

    GridView gridView = (GridView) holder.getInflatedView();
    assertThat(gridView.getOnItemClickListener()).isInstanceOf(GridHolder.class);
  }

  @Test public void testFooter() {
    GridHolder holder = getHolder();

    assertThat(holder.getFooter()).isNull();

    View footer = new LinearLayout(context);
    holder.addFooter(footer);

    assertThat(holder.getFooter()).isEqualTo(footer);
  }

  @Test public void testHeader() {
    GridHolder holder = getHolder();

    assertThat(holder.getHeader()).isNull();

    View header = new LinearLayout(context);
    holder.addHeader(header);

    assertThat(holder.getHeader()).isEqualTo(header);
  }

  @Test public void testOnItemClick() {
    GridHolder holder = getHolder();
    GridView view = (GridView) holder.getInflatedView();

    //there is no listener, it shouldn't crash
    view.performItemClick(null, 0, 0);

    //with adapter set
    ArrayAdapter<String> adapter = new ArrayAdapter<>(
        context, android.R.layout.simple_list_item_1,
        new String[]{"test"}
    );
    holder.setAdapter(adapter);
    view.performItemClick(null, 0, 0);

    //set listener
    holder.setOnItemClickListener(new OnHolderListener() {
      @Override
      public void onItemClick(Object item, View view, int position) {
        assertThat(String.valueOf(item)).isEqualTo("test");
        assertThat(position).isEqualTo(0);
        assertThat(view).isNull();
      }
    });
    view.performItemClick(null, 0, 0);
  }

}
