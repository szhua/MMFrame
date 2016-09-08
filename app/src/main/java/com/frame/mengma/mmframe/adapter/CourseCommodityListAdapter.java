package com.frame.mengma.mmframe.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.frame.mengma.mmframe.R;
import com.frame.mengma.mmframe.base.BaseLoadMoreListAdapter;
import com.frame.mengma.mmframe.base.BaseLoadMoreListFragment;
import com.frame.mengma.mmframe.entity.Goods;
import com.squareup.picasso.Picasso;
import com.tuoyan.baselibrary.utils.UiUtil;

import java.util.List;

import static com.frame.mengma.mmframe.R.layout.abc_popup_menu_item_layout;
import static com.frame.mengma.mmframe.R.layout.item_course;

/**
 * Created by szhua on 2016/3/11.
 */
public class CourseCommodityListAdapter extends BaseLoadMoreListAdapter {


    private List<Goods> goodses;

    public CourseCommodityListAdapter(BaseLoadMoreListFragment baseLoadMoreListFragment) {
        super(baseLoadMoreListFragment);
    }


    public void setGoodses(List<Goods> goodses) {
        this.goodses = goodses;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolderInChildClass(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_course, null);
        return new CourseHolder(view);
    }

    @Override
    public void onBindViewHolderInChildClass(RecyclerView.ViewHolder holder, int position) {

        final Goods goods = goodses.get(position);
        CourseHolder courseHolder = (CourseHolder) holder;
        if (!TextUtils.isEmpty(goods.getImgurl())) {
            Picasso.with(baseLoadMoreListFragment.getContext()).load(goods.getImgurl()).placeholder(R.drawable.img_loading).into(courseHolder.photo);
        } else {
            courseHolder.photo.setImageResource(R.drawable.img_loading);
        }
        courseHolder.name.setText(goods.getGoods_name());
        courseHolder.club.setText(goods.getStore_name());
        courseHolder.distance.setText(goods.getDistance());
        courseHolder.money.setText(goods.getPrice());
        if (TextUtils.isEmpty(goods.getScore())) {
            courseHolder.score.setText("暂无评分");
        } else {
            courseHolder.score.setText("评分" + goods.getScore());
        }
        courseHolder.number.setText(goods.getSaled());

    }

    @Override
    public int getItemCountInChildClass() {
        return goodses == null ? 0 : goodses.size();
    }

    //此处的type先暂时定为0 ；以后可能会用到
    @Override
    public int getItemViewTypeInChildClass(int position) {
        return 0;
    }


    class CourseHolder extends RecyclerView.ViewHolder {
        ImageView photo;
        TextView name;
        TextView club;
        TextView distance;
        TextView money;
        TextView score;
        TextView number;

        public CourseHolder(View itemView) {
            super(itemView);

            photo = (ImageView) itemView.findViewById(R.id.img_small);
            name = (TextView) itemView.findViewById(R.id.tv_name);
            club = (TextView) itemView.findViewById(R.id.tv_club);
            distance = (TextView) itemView.findViewById(R.id.tv_distance);
            money = (TextView) itemView.findViewById(R.id.tv_money);
            score = (TextView) itemView.findViewById(R.id.tv_score);
            number = (TextView) itemView.findViewById(R.id.tv_number);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UiUtil.showLongToast(baseLoadMoreListFragment.getContext(), "点击了" + getAdapterPosition() + "item");
                }
            });

        }
    }


}
