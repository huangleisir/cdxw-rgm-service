/*
* Copyright (c) 2015-2018 SHENZHEN GUIYI SCIENCE AND TECHNOLOGY DEVELOP CO., LTD. All rights reserved.
*
* 注意：本内容仅限于深圳市捷顺金科研发有限公司内部传阅，禁止外泄以及用于其他的商业目的 
*/
package com.jst.wxdc.bean;

public class SysParams {

	private String id ;
    private int orderCillAmount ;//订单起送金额
    private int sendAmount ;      //配送费
    private int orderFreeAmount ;      //免费送费起点
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getOrderCillAmount() {
        return orderCillAmount;
    }
    public void setOrderCillAmount(int orderCillAmount) {
        this.orderCillAmount = orderCillAmount;
    }
    public int getSendAmount() {
        return sendAmount;
    }
    public void setSendAmount(int sendAmount) {
        this.sendAmount = sendAmount;
    }
    public int getOrderFreeAmount() {
        return orderFreeAmount;
    }
    public void setOrderFreeAmount(int orderFreeAmount) {
        this.orderFreeAmount = orderFreeAmount;
    }
	
}
