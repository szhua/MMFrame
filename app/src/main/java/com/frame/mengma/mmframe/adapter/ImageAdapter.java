package com.frame.mengma.mmframe.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.frame.mengma.mmframe.R;
import com.frame.mengma.mmframe.base.BaseLoadMoreGridAdapter;
import com.frame.mengma.mmframe.base.BaseLoadMoreGridFragment;
import com.frame.mengma.mmframe.util.DeviceUtil;
import com.squareup.picasso.Picasso;

/**
 * Created by szhua on 2016/3/12.
 */
public class ImageAdapter extends BaseLoadMoreGridAdapter {

    private String[] imageThumbUrls  ;

    public ImageAdapter(BaseLoadMoreGridFragment baseLoadMoreGridFragment) {
        super(baseLoadMoreGridFragment);
    }

    public void setImageThumbUrls(String[] imageThumbUrls) {
        this.imageThumbUrls = imageThumbUrls;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolderInChildClass(ViewGroup parent, int viewType) {
        View view =View.inflate(parent.getContext() ,R.layout.item_imgs,null) ;
        return new ImageHolder(view);
    }

    @Override
    public void onBindViewHolderInChildClass(RecyclerView.ViewHolder holder, int position) {
        Picasso.with(baseLoadMoreGridFragment.getContext()).load(imageThumbUrls[position]).placeholder(R.drawable.img_loading).into(((ImageHolder)holder).imageView);
    }

    @Override
    public int getItemCountInChildClass() {
        return imageThumbUrls==null?0:imageThumbUrls.length;
    }

    //暂时定为0；
    @Override
    public int getItemViewTypeInChildClass(int position) {
        return 0;
    }


    class ImageHolder extends  RecyclerView.ViewHolder{
        private ImageView imageView ;
        public ImageHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.photo);
          //  image = (ImageView) itemView.findViewById(R.id.image);
            int imageHeight = (DeviceUtil.getDeviceWidth(baseLoadMoreGridFragment.getActivity()) ) / 3;
            ;
            imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, imageHeight));

        }
    }

}
