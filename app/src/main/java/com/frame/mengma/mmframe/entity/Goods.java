package com.frame.mengma.mmframe.entity;

/**
 * Created by szhua on 2016/3/11.
 */
public class Goods {


    private String goods_id;//商品id
    private String goods_name;//商品名称
    private String price;//商品价格
    private String store_name;//商家名称
    private String score;//商品评分
    private String distance;//场馆距离
    private String saled;//已售数量
    private String imgurl;//商品列表小图

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getSaled() {
        return saled;
    }

    public void setSaled(String saled) {
        this.saled = saled;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goods_id='" + goods_id + '\'' +
                ", goods_name='" + goods_name + '\'' +
                ", price='" + price + '\'' +
                ", store_name='" + store_name + '\'' +
                ", score='" + score + '\'' +
                ", distance='" + distance + '\'' +
                ", saled='" + saled + '\'' +
                ", imgurl='" + imgurl + '\'' +
                '}';
    }
}
