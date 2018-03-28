package com.jst.wxdc.bean;

/**
 * 
* @ClassName: MenuCtrl 
* @Description: TODO(用户跟菜单关联bean) 
* @author 曾传保 
* @date 2018年3月22日 下午3:17:52 
*
 */
public class MenuCtrl implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private String ctrlId;			//关联ID
	private String id;				//用户编号
	private String menuId;			//菜单ID
	private String creater;			//创建人
	private String createTime;		//创建时间
	private String updater;			//创建人
	private String updateTime;		//更新时间
	private String checkMenuId;		//添加菜单权限时,用来接收前端传过来的菜单ID
	public String getCtrlId() {
		return ctrlId;
	}
	public void setCtrlId(String ctrlId) {
		this.ctrlId = ctrlId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdater() {
		return updater;
	}
	public void setUpdater(String updater) {
		this.updater = updater;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getCheckMenuId() {
		return checkMenuId;
	}
	public void setCheckMenuId(String checkMenuId) {
		this.checkMenuId = checkMenuId;
	}
}
