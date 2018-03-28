/*
* Copyright (c) 2015-2018 SHENZHEN GUIYI SCIENCE AND TECHNOLOGY DEVELOP CO., LTD. All rights reserved.
*
* 注意：本内容仅限于深圳市捷顺金科研发有限公司内部传阅，禁止外泄以及用于其他的商业目的 
*/
package com.jst.wxdc.bean;

import com.jst.prodution.base.bean.BaseBean;

public class Food extends BaseBean {

	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String foodId ;
	private String typeId ;
	private String typeName ;
    private String foodName ;  //
    private String picPath ;     //图片地址
    private String orderId ;  //
    private String price ;      //价格
    private String num ;  //点菜数量
    private String priceTotal ;  //总金额
    private String salePrice ;  //售价
    private String oldPrice ;      //原价
    private String status ;  //0删除1正常2售罄
    private String spiceLevel ;  //辣度等级1微辣，2少辣，3多辣
    private String mark ;  //备注
    private String commendMark ;  //推荐标识1招牌价2今日特价3特别推荐
    private String latestUpdateTime ;
    private String createTime ;
    public String getFoodId() {
        return foodId;
    }
    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }
    public String getTypeId() {
        return typeId;
    }
    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
    public String getFoodName() {
        return foodName;
    }
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
    public String getPicPath() {
        return picPath;
    }
    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getNum() {
        return num;
    }
    public void setNum(String num) {
        this.num = num;
    }
    public String getPriceTotal() {
        return priceTotal;
    }
    public void setPriceTotal(String priceTotal) {
        this.priceTotal = priceTotal;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getSalePrice() {
        return salePrice;
    }
    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }
    public String getOldPrice() {
        return oldPrice;
    }
    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getSpiceLevel() {
        return spiceLevel;
    }
    public void setSpiceLevel(String spiceLevel) {
        this.spiceLevel = spiceLevel;
    }
    public String getMark() {
        return mark;
    }
    public void setMark(String mark) {
        this.mark = mark;
    }
    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    public String getCommendMark() {
        return commendMark;
    }
    public void setCommendMark(String commendMark) {
        this.commendMark = commendMark;
    }
    public String getLatestUpdateTime() {
        return latestUpdateTime;
    }
    public void setLatestUpdateTime(String latestUpdateTime) {
        this.latestUpdateTime = latestUpdateTime;
    }
    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}
