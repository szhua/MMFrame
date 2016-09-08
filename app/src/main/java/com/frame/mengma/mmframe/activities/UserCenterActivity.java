package com.frame.mengma.mmframe.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.frame.mengma.mmframe.AppHolder;
import com.frame.mengma.mmframe.Constant;
import com.frame.mengma.mmframe.R;
import com.frame.mengma.mmframe.base.BaseActivity;
import com.frame.mengma.mmframe.dao.UpdateHeaderDao;
import com.frame.mengma.mmframe.util.CacheUtil;
import com.frame.mengma.mmframe.util.ImageTools;
import com.frame.mengma.mmframe.util.SharedPrefsUtil;
import com.mengma.asynchttp.RequestCode;
import com.squareup.picasso.Picasso;

import com.tuoyan.baselibrary.utils.UiUtil;

import java.io.File;
import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * created by szhua 2016/3/14
 * 1:本案例实现显示圆形头像；
 * 2:选择图片（图库，相机拍照）；
 * 3：上传图片至服务器；
 */
public class UserCenterActivity extends BaseActivity implements View.OnClickListener {

    @InjectView(R.id.header_iv)
    CircleImageView headerIv;
    @InjectView(R.id.tv_name)
    TextView tvName;
    private Bitmap bitmap;

    private UpdateHeaderDao updateHeaderDao =new UpdateHeaderDao(this,this);

    //此路径为缓存头像图片的路径；
    private String tempPicPath = Environment.getExternalStorageDirectory() + "/temp/";
    //头像图片的名字；
    private String tempPicName = "head_temp.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_center);
        ButterKnife.inject(this);

        if(AppHolder.getInstance().getUser()!=null){
            Picasso.with(this).load(AppHolder.getInstance().getUser().getAvatar()).placeholder(R.drawable.img_loading).into(headerIv);
            tvName.setText(AppHolder.getInstance().getUser().getNickname());
        }else{
            if(!TextUtils.isEmpty(SharedPrefsUtil.getValue(this, CacheUtil.AVATARBIG,"erro"))){
                Picasso.with(this).load(SharedPrefsUtil.getValue(this,CacheUtil.AVATARBIG,"erro")).placeholder(R.drawable.img_loading).into(headerIv);
            }else{
                headerIv.setImageResource(R.drawable.img_loading);
            }

        }
        setListener() ;
    }

    private void setListener() {
        headerIv.setOnClickListener(this);
        tvName.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        setHeadTitle("用户中心");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constant.CAMERACODE:
                    int degree = ImageTools.readPictureDegree(tempPicPath + tempPicName); //图片被旋转的角度
                    try {
                        //获得取得的图片对象；
                        bitmap = ImageTools.readBitmapAutoSize(tempPicPath + tempPicName, 500, 600);
                        //压缩图片。必须进行处理，要不然图片太大
                        bitmap = ImageTools.compressImage(bitmap);
                        bitmap = ImageTools.rotaingImageView(degree, bitmap); //把图片转到正的角度
                        // picturePath = ImageTools.savaPhotoToLocal(bitmap);
                        try {
                            headerIv.setImageBitmap(bitmap);
                        } catch (Exception e) {
//                          MobclickAgent.reportError(this_context, e);
                        }
                    } catch (Exception e) {
//                        MobclickAgent.reportError(this_context, e);
                    }
                  //  updateHeaderDao.updateHeader(ImageTools.Bitmap2InputStream(bitmap, 100));
                    showProgressWithText(true, "正在上传头像……");
                    break;
                case Constant.FILECODE:
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage,
                            filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();
                    bitmap = ImageTools.readBitmapAutoSize(picturePath, 500,
                            600);
                    bitmap = ImageTools.compressImage(bitmap);
                    picturePath = ImageTools.savaPhotoToLocal(bitmap);

                    Log.i("szhua",picturePath);
                    // 显示选择的图片
                    try {
                        headerIv.setImageBitmap(bitmap);
                    } catch (Exception e) {
//                        MobclickAgent.reportError(this_context, e);
                    }
                    File dir = new File(tempPicPath);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    updateHeaderDao.updateHeader(new File(picturePath));
                    showProgressWithText(true, "正在上传头像……");
                    break;
            }
        }
    }

    private void showSelectPic() {
        AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setTitle("提示");
        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "去图库", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, Constant.FILECODE);
            }
        });
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "去拍照", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String state = Environment.getExternalStorageState();
                if (state.equals(Environment.MEDIA_MOUNTED)) {
                    Intent intentcamera = new Intent(
                            MediaStore.ACTION_IMAGE_CAPTURE);
                    File dir = new File(tempPicPath);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    File imgFile = new File(dir, tempPicName);
                    if (!imgFile.exists()) {
                        try {
                            imgFile.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    Uri imageUri = Uri.fromFile(imgFile);
                    intentcamera.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intentcamera, Constant.CAMERACODE);
                } else {
                    UiUtil.showLongToast(getApplicationContext(), "无内存卡");
                }
            }
        });
        dialog.show();
    }

    @Override
    public void onRequestSuccess(int requestCode) {
        super.onRequestSuccess(requestCode);

        if(requestCode== RequestCode.CODE_1){
           UiUtil.showLongToast(this,"ok");
        }
    }

    @Override
    public void onRequestFaild(String errorNo, String errorMessage) {
        super.onRequestFaild(errorNo, errorMessage);
    }

    @Override
    public void onRequestError(int requestCode, String errorInfo) {
        super.onRequestError(requestCode, errorInfo);
    }

    @Override
    public void onClick(View v) {
        if(v==headerIv){
            showSelectPic();
        }
        if(v==tvName){
            Intent intent =new Intent(this,TestCacheActicity.class);
            startActivity(intent);
        }



    }
}
