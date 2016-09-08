package com.frame.mengma.mmframe.adapter;

import java.util.ArrayList;
import java.util.List;

import com.frame.mengma.mmframe.R;
import com.frame.mengma.mmframe.entity.Goods;
import com.squareup.picasso.Picasso;
import com.umeng.socialize.utils.Log;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by szhua 2016/3/15
 * @author szhua
 *
 */
public class CourseCommodityAdapter extends BaseAdapter{

	private  List<Goods> goodslist  ;
	public void setGoodslist(List<Goods> goodslist) {
		this.goodslist = goodslist;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return goodslist==null?0:goodslist.size();
	}

	@Override
	public Object getItem(int position) {
		return goodslist.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if(convertView == null){
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course, null);
			Goods goods = goodslist.get(position);
			viewHolder.photo = (ImageView) convertView.findViewById(R.id.img_small);
			viewHolder.name = (TextView) convertView.findViewById(R.id.tv_name);
			viewHolder.club = (TextView) convertView.findViewById(R.id.tv_club);
			viewHolder.distance = (TextView) convertView.findViewById(R.id.tv_distance);
			viewHolder.money = (TextView) convertView.findViewById(R.id.tv_money);
			viewHolder.score = (TextView) convertView.findViewById(R.id.tv_score);
			viewHolder.number = (TextView) convertView.findViewById(R.id.tv_number);
			convertView.setTag(viewHolder);

		}else{
			viewHolder = (ViewHolder)convertView.getTag();
		}

		final Goods goods = goodslist.get(position);

		if(!TextUtils.isEmpty(goods.getImgurl())){
			Picasso.with(parent.getContext()).load(goods.getImgurl()).placeholder(R.drawable.img_loading).into(viewHolder.photo);
			//context.loadImage1(goods.getImgurl(), viewHolder.photo, R.drawable.img_loading);
		}else{
				viewHolder.photo.setImageResource(R.drawable.img_loading);

			}
		viewHolder.name.setText(goods.getGoods_name());
		viewHolder.club.setText(goods.getStore_name());
		viewHolder.distance.setText(goods.getDistance());
		viewHolder.money.setText(goods.getPrice());
		if(TextUtils.isEmpty(goods.getScore())){
			viewHolder.score.setText("暂无评分");
		}else{
			viewHolder.score.setText("评分"+goods.getScore());
		}
		viewHolder.number.setText(goods.getSaled());


		return convertView;
	}



	private static class ViewHolder{
		ImageView photo;
		TextView name;
		TextView club;
		TextView distance;
		TextView money;
		TextView score;
		TextView number;

	}

}
