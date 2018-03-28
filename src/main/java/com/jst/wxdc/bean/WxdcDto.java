/*
* Copyright (c) 2015-2018 SHENZHEN GUIYI SCIENCE AND TECHNOLOGY DEVELOP CO., LTD. All rights reserved.
*
* 注意：本内容仅限于深圳市捷顺金科研发有限公司内部传阅，禁止外泄以及用于其他的商业目的 
*/
package com.jst.wxdc.bean;

import java.util.List;

import com.jst.prodution.base.bean.BaseBean;

public class WxdcDto extends BaseBean  {

	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String id ;
	private String openId ;
	private String createTime ;  //下单时间
    private String payType ;     //支付类型
    private String payStatus ;   //0已下单1已支付
    private String printStatus ; //打印状态
    private String mark ;        //备注
    private String sendStatus ;	 //0未确认1已确认2已完成
    private String foodAmount ;  //订单金额
    private String sendAmount ;  //配送金额
    private String addrId ;      //地址id
    private String addr ;        //地址
    private String name ;        //姓名
    private String sex ;         //1男2女
    private String mobile ;      //手机号
    private String startTimes ;      
    private String endTimes ; 
    private  List<Food>  food;
    private int foodTotalPrice ; //菜品总金额
    private int orderTotalPrice ; //订单总金额
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getOpenId() {
        return openId;
    }
    public void setOpenId(String openId) {
        this.openId = openId;
    }
    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public String getPayType() {
        return payType;
    }
    public void setPayType(String payType) {
        this.payType = payType;
    }
    public String getPayStatus() {
        return payStatus;
    }
    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }
    public String getPrintStatus() {
        return printStatus;
    }
    public void setPrintStatus(String printStatus) {
        this.printStatus = printStatus;
    }
    public String getMark() {
        return mark;
    }
    public void setMark(String mark) {
        this.mark = mark;
    }
    public String getSendStatus() {
        return sendStatus;
    }
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }
    public String getFoodAmount() {
        return foodAmount;
    }
    public void setFoodAmount(String foodAmount) {
        this.foodAmount = foodAmount;
    }
    public String getSendAmount() {
        return sendAmount;
    }
    public void setSendAmount(String sendAmount) {
        this.sendAmount = sendAmount;
    }
    public String getAddrId() {
        return addrId;
    }
    public void setAddrId(String addrId) {
        this.addrId = addrId;
    }
    public String getAddr() {
        return addr;
    }
    public void setAddr(String addr) {
        this.addr = addr;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getStartTimes() {
        return startTimes;
    }
    public void setStartTimes(String startTimes) {
        this.startTimes = startTimes;
    }
    public String getEndTimes() {
        return endTimes;
    }
    public void setEndTimes(String endTimes) {
        this.endTimes = endTimes;
    }
    public List<Food> getFood() {
        return food;
    }
    public void setFood(List<Food> food) {
        this.food = food;
    }
    public int getFoodTotalPrice() {
        return foodTotalPrice;
    }
    public void setFoodTotalPrice(int foodTotalPrice) {
        this.foodTotalPrice = foodTotalPrice;
    }
    public int getOrderTotalPrice() {
        return orderTotalPrice;
    }
    public void setOrderTotalPrice(int orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }
}
