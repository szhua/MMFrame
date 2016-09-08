package com.frame.mengma.mmframe.dao;

import android.content.Context;

import com.fasterxml.jackson.databind.JsonNode;
import com.frame.mengma.mmframe.AppHolder;
import com.frame.mengma.mmframe.Constant;
import com.loopj.android.http.RequestParams;
import com.mengma.asynchttp.IDao;
import com.mengma.asynchttp.RequestCode;
import com.mengma.asynchttp.interf.INetResult;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by szhua on 2016/3/12.
 * 上传文件的处理
 *
 * php 端服务器要求：现在暂时定为File的形式：其他的后期在进行处理；
 *
 *
 */
public class UpdateHeaderDao extends IDao {
    public UpdateHeaderDao(Context context, INetResult iNetResult) {
        super(context, iNetResult);
    }

     public void updateHeader(File inputStream){
         RequestParams params =new RequestParams( );
         params.put("token", AppHolder.getInstance().getUser().getToken());
         params.put("keytype", 1);
         params.put("keyid", 0);
         params.put("orderby", 0);
         params.put("content", "vc");
         params.put("duration",0);
         params.put("goods_id", 0);
         //pics.put("temp_file", filePath);// 临时需要上传的文件控件名称对应表单type="file"中的name值,相关文件请先在客户端压缩再上传（压缩尺寸宽度固定640）
         try {
             params.put("temp_file",inputStream);
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         }
         params.setForceMultipartEntityContentType(true);
//         params.setAutoCloseInputStreams(true);
//         params.setUseJsonStreamer(true);
         postRequest(Constant.BASE_URL + Constant.FILE_UPLOAD, params, RequestCode.CODE_1);

     }

    @Override
    public void onRequestSuccess(JsonNode result, int requestCode) throws IOException {

        if(requestCode==RequestCode.CODE_1){

        }

    }
}
