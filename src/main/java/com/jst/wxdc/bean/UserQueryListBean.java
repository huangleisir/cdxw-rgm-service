package com.jst.wxdc.bean;

import java.util.List;

import com.jst.prodution.base.bean.BaseBean;

public class UserQueryListBean extends BaseBean{

	private static final long serialVersionUID = 1L;
	private String id;			//编码
	private String userName;		//用户名称
	private String status;			//状态： 0：启用   1：禁用	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
