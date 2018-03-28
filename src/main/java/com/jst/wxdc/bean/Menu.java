package com.jst.wxdc.bean;

/**
 * 
* @ClassName: Menu 
* @Description: TODO(菜单) 
* @author 曾传保
* @date 2018年3月22日 下午3:17:17 
*
 */
public class Menu implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String menuId;		//菜单ID
	private String menuName;	//菜单名称
	private String url;			//url地址
	private String parentId;	//父类ID
	private String open;		//是否展开
	private String position;	//页面 0  操作 1
	private String createTime;	//创建时间
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}
