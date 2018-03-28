/*
* Copyright (c) 2015-2018 SHENZHEN GUIYI SCIENCE AND TECHNOLOGY DEVELOP CO., LTD. All rights reserved.
*
* 注意：本内容仅限于深圳市捷顺金科研发有限公司内部传阅，禁止外泄以及用于其他的商业目的 
*/
package com.jst.wxdc.bean;

import com.jst.prodution.base.bean.BaseBean;

public class FoodType extends BaseBean{

	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String typeId ;
    private String typeName ;  //分类名称(冷菜，热菜，饮品)
    private String typeIndex ;     //排序
    private String status ;  //0无效1有效
    private String mark ;      //价格
    private String queryType ;      //是否分页查询 1分页0不分页
    private String latestUpdateTime ;
    private String createTime ;
    public String getTypeId() {
        return typeId;
    }
    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    public String getTypeIndex() {
        return typeIndex;
    }
    public void setTypeIndex(String typeIndex) {
        this.typeIndex = typeIndex;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMark() {
        return mark;
    }
    public void setMark(String mark) {
        this.mark = mark;
    }
    public String getQueryType() {
        return queryType;
    }
    public void setQueryType(String queryType) {
        this.queryType = queryType;
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
