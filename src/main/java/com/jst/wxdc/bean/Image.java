package com.jst.wxdc.bean;

/**
 * 商户图片信息
 * @author Administrator
 *
 */
public class Image implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private String imageId;			//图片ID
	private String type;			//图片类型 1 LOGO 2营业执照 3 法人证件照 4商务人员证件照
	private String userId;			//商户ID
	private String serverPath;		//图片服务器地址
	private String imageMax;		//原始图片地址
	private String imageMin;		//缩略图地址
	private String state;			//状态：1正在使用 2未使用
	private String creator;			//申请人
	private String createTime;		//申请时间
	
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getServerPath() {
		return serverPath;
	}
	public void setServerPath(String serverPath) {
		this.serverPath = serverPath;
	}
	public String getImageMax() {
		return imageMax;
	}
	public void setImageMax(String imageMax) {
		this.imageMax = imageMax;
	}
	public String getImageMin() {
		return imageMin;
	}
	public void setImageMin(String imageMin) {
		this.imageMin = imageMin;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}
