/*
* Copyright (c) 2015-2018 SHENZHEN GUIYI SCIENCE AND TECHNOLOGY DEVELOP CO., LTD. All rights reserved.
*
* 注意：本内容仅限于深圳市捷顺金科研发有限公司内部传阅，禁止外泄以及用于其他的商业目的 
*/
package com.jst.wxdc.bean;

public class Order {

	private String id ;
	private String openId ;
    private String createTime ;  //下单时间
    private String payType ;     //支付类型
    private String payStatus ;   //0已下单1已支付
    private String printStatus ; //打印状态
    private String mark ;        //备注
    private String sendStatus ;  //0未配送1已配送
    private String foodAmount ;  //订单金额
    private String sendAmount ;  //配送金额
    private String addrId ;      //地址id
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
}
