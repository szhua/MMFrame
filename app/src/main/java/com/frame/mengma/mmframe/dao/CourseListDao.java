package com.frame.mengma.mmframe.dao;

import android.content.Context;

import com.fasterxml.jackson.databind.JsonNode;
import com.frame.mengma.mmframe.Constant;
import com.frame.mengma.mmframe.entity.Goods;
import com.loopj.android.http.RequestParams;
import com.mengma.asynchttp.IDao;
import com.mengma.asynchttp.JsonUtil;
import com.mengma.asynchttp.RequestCode;
import com.mengma.asynchttp.interf.INetResult;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by szhua on 2016/3/11.
 */
public class CourseListDao extends IDao {

    private List<Goods> goodses;
    private int currentPage;

    public CourseListDao(Context context, INetResult iNetResult) {
        super(context, iNetResult);
    }


    public List<Goods> getGoodses() {
        return goodses;
    }

    public void getCourseList() {
        RequestParams params = new RequestParams();
        params.put("parent_type", "1");// 商品总类型,	1:课程 2:会所 3:场馆
        params.put("lng", 117.003755);//用户位置经度
        params.put("lat", 36.654816);//用户位置纬度
        params.put("city", "济南");//城市名,不要加特殊符号，只填城市名
        params.put("page", 0);//当前列表翻页索引,	第一页时请传递page=0，翻页时依次递增。
        postRequest(Constant.BASE_URL + Constant.GOODS_LIST, params, RequestCode.CODE_0);
    }

    @Override
    public void onRequestSuccess(JsonNode result, int requestCode) throws IOException {
        if(requestCode==RequestCode.CODE_0){
            goodses = JsonUtil.node2pojoList(result.findValue("listItems"), Goods.class);
        }
    }

    /**
     * 重新请求
     */
    public void firstRequest() {
        //后续作为标识会有作用
      //  currentPage = 1;
        goodses = new ArrayList<Goods>();
        getCourseList();
    }


}
